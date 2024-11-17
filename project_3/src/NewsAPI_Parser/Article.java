package NewsAPI_Parser;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a news Article.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
final class Article {
	final String title;
	final String description;
	final Date publishedAt;
	final String url;	
	
	/**
	 * Constructs an Article.
	 * This is used when parsing articles in JSON format.
	 * 
	 * @param title
	 * @param description
	 * @param publishedAt (Time at which the article was published)
	 * @param url
	 */
	@JsonCreator 
	Article(@JsonProperty("title") String title, 
			@JsonProperty("description") String description,
			@JsonProperty("publishedAt") Date publishedAt,
			@JsonProperty("url") String url) {
		this.title = title;
		this.description = description;
		this.publishedAt = publishedAt;
		this.url = url;
	}
	
	/**
	 * Performs typical equals method functionality.
	 * Used for unit tests.
	 * 
	 * @param o (the object being compared to)
	 * @return true if articles are equal, false if not.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Article) {
			Article article = (Article) o;
			return Objects.equals(title, article.title) &&
					Objects.equals(description, article.description) &&
					Objects.equals(publishedAt, article.publishedAt) &&
					Objects.equals(url, article.url);	
		}
		return false;
	}
	
	/**
	 * Performs typical toString method functionality.
	 * String should be in form:
	 * 	title
	 * 	description
	 * 	publishedAt
	 * 	url
	 * 
	 * @return String form of an article.
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("title: ").append(title).append("\n")
		.append(description).append("\n")
		.append("at: ").append(publishedAt).append("\n")
		.append("url: ").append(url);
		return output.toString();
	}
}
