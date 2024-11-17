package NewsAPI_Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Defines functionality for parsing from the simple format.
 */
final class SimpleArticleParser implements ArticleParser {

	/**
	 * Takes simple style text and creates a list of the Article objecct from said text.
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
            JsonNode JSONRoot = mapper.readTree(input);
            if (JSONRoot.hasNonNull("title") && JSONRoot.hasNonNull("description")
                    && JSONRoot.hasNonNull("publishedAt") && JSONRoot.hasNonNull("url")) {
            	String publishedAt = JSONRoot.get("publishedAt").asText();
                if (publishedAt.contains(" ")) {
                   
                    publishedAt = publishedAt.replace(" ", "T").split("\\.")[0] + "Z";
                    ((ObjectNode) JSONRoot).put("publishedAt", publishedAt);
                }
	            Article article = mapper.treeToValue(JSONRoot, Article.class);
	            output.add(article);
	            }
            else {
                logger.warning(JSONRoot.toString());
            }
        } catch (Exception e) {
            logger.warning("Invalid Input, Returning False Output");
        }
        return output;
    }
    
    /**
	 * Converts a json style file to a string.
	 * 
	 * @param Key (location of the file path)
	 * @return String (Contents of file as a String)
	 */
	public String converter(String key) {
		StringBuilder articles = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(key));
			String line;
			while((line = reader.readLine()) != null) {
				articles.append(line).append("\n");
			}
			reader.close();
			return articles.toString();
		}
		catch(IOException e) {
			return ("An error occured " + e.getMessage());
		}
	}
	
	 @Override
	    public List<Article> accept(ArticleParserVisitor visitor) {
	        return visitor.visit(this);
	    }
}