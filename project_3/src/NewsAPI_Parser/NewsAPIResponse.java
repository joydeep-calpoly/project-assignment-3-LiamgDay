package NewsAPI_Parser;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the full response from the NewsAPI.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NewsAPIResponse {
    final String status;
    final int totalResults;
    final List<Article> articles;

    /**
     * Constructs a NewsAPIResponse.
     * This is used to parse the full JSON response.
     * 
     * @param status        
     * @param totalResults  total number of results available
     * @param articles      list of articles in the response
     */
    @JsonCreator
    public NewsAPIResponse(
            @JsonProperty("status") String status,
            @JsonProperty("totalResults") int totalResults,
            @JsonProperty("articles") List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}