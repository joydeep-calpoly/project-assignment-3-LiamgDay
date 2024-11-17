package NewsAPI_Parser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

/**
 * Defines functionality for parsing json text.
 */
final class apiParser implements ArticleParser{
	
	/**
	 * Takes json style text and creates a list of Article objects from said text.
	 * Disregards any "article" that isn't of the proper form (outlined by Article object)
	 * 
	 * @param input (json style String)
	 * @param logger (any logger okay, but the setup logger in Demo is recommended)
	 * @return List of Articles (these will be all of the properly written articles from the input String)
	 */
	public List<Article> parse(String input, Logger logger) {
		List<Article> output = new ArrayList<>();
		if (input == null) {
			return output;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			NewsAPIResponse response = mapper.readValue(input, NewsAPIResponse.class);
			List<Article> articles = response.articles;
			 if (articles != null) {
		            for (Article article : articles) {		      
		                if (article.title != null && article.description != null &&
		                    article.publishedAt != null && article.url != null) {
		                    output.add(article);
		                } else {		                    
		                    logger.warning(article.toString());
		                }
		            }
		        } else {
		            logger.warning(response.toString());
		        }
		    } catch (Exception e) {
		        logger.severe(e.getMessage());
		    }
		    return output;
		}
	
	/**
     * Uses given URL to return a json style String from NewsAPI.
     * Works with any valid apiKey.
     * My key: "0c98fa25b19b4b5890e375dce020a260".
     * 
     * @param apiKey The NewsAPI key, can just used mine above
     * @return Contents of url as a String
     */
	public String converter(String key) {
	    String urlString = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=" + key;
	    StringBuilder jsonResponse = new StringBuilder();
	    
	    try {
	        URL url = new URL(urlString);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        try (Scanner scanner = new Scanner(url.openStream())) {
	            while (scanner.hasNext()) {
	                jsonResponse.append(scanner.nextLine());
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error");
	    }
	    return jsonResponse.toString();
	          
	}
	
	@Override
    public List<Article> accept(ArticleParserVisitor visitor) {
        return visitor.visit(this);
    }
}