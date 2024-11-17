package NewsAPI_Parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class SimpleArticleParserTest {

	Article articleA = new Article("a", "a", new Date("Wed Mar 24 14:44:01 PDT 2021"), "a");
	Article articleB = new Article("b", "b", new Date("Wed Mar 24 15:20:12 PDT 2021"), "b");
    
	/**
	 * Tests a valid input of one article for Parser.parse. 
	 * Ensures the article is present in returned list.
	 */
    @Test
    void testSimpleParserValid() {
        String JSONInput = 
            """
            {
                "description": "a",
        		"publishedAt": "2021-03-24 21:44:01.709229",
        		"title": "a",
        		"url": "a"
            }
            """;

        ArticleParser simpleParser = new SimpleArticleParser();
        Logger logger = Logger.getLogger("TestLogger");
        List<Article> articles = simpleParser.parse(JSONInput, logger);
        
        assertEquals(1, articles.size());
        assertEquals(articleA, articles.get(0));   
    }
    /**
	 * Tests an invalid input due to missing required field.
	 * Ensures no article is returned in the list.
	 */
    @Test
    void testSimpleParserMissingFields() {
        String JSONInput = 
            """
            {
                "title": "a",
                "description": "a",
                "publishedAt": "2021-03-24T21:44:01Z"
            }
            """;

        ArticleParser simpleParser = new SimpleArticleParser();
        Logger logger = Logger.getLogger("TestLogger");
        List<Article> articles = simpleParser.parse(JSONInput, logger);
        
        assertEquals(0, articles.size());
    }

    
    /**
	 * Tests functionality with null as the String input.
	 * Ensures no articles are returned.
	 */
    @Test
    void testSimpleParserNullInput() {
        ArticleParser simpleParser = new SimpleArticleParser();
        Logger logger = Logger.getLogger("TestLogger");
        List<Article> articles = simpleParser.parse(null, logger);

        assertEquals(0, articles.size(), "Expected zero articles for null input");
    }

    
    /**
	 * Tests for input with more than required fields.
	 * Ensures the parser will ignore extra fields.
	 */
    @Test
    void testSimpleParserExcessFields() {
    	String JSONInput = 
    			"""
                {
                    "title": "b",
                    "description": "b",
                    "publishedAt": "2021-03-24 22:20:12.709229",
                    "url": "b",
                    "banana": "yum yum yummy"
                }
                """;
    	
    	ArticleParser simpleParser = new SimpleArticleParser();
        Logger logger = Logger.getLogger("TestLogger");
        List<Article> articles = simpleParser.parse(JSONInput, logger);
        
        assertEquals(1, articles.size());
        assertEquals(articleB, articles.get(0));
    }
}
