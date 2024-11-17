package NewsAPI_Parser;


import java.util.List;
import java.util.logging.Logger;

/**
 * Defines the contract for a parser that converts data sources into a list of Articles.
 */
public interface ArticleParser {

	/**
     * Parses the given input string and returns a list of Articles.
     *
     * @param input  the input data as a string
     * @param logger the logger for logging issues
     * @return a List of Articles parsed from the input
     */
	
    List<Article> parse(String input, Logger logger);
    
    /**
     * Converts the inputed string to the proper form to be used by parse.
     *
     * @param key (a url or filepath)
     * @return String (the data retrieved from the key)
     */
    
    String converter(String key);
    
    /**
     * Accepts the visitor pattern call to use parse.
     *
     * @param visitor
     * @return List<Article> a list of articles from .parse().
     */
    
    List<Article> accept(ArticleParserVisitor visitor);
}
