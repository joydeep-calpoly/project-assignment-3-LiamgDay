package NewsAPI_Parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class apiParserTest {
	
	Article articleA = new Article("a", "a", new Date("Wed Mar 24 14:44:01 PDT 2021"), "a");
	Article articleB = new Article("b", "b", new Date("Wed Mar 24 15:20:12 PDT 2021"), "b");
	
	/**
	 * Tests a valid input of two articles for Parser.parse. 
	 * Ensures both articles are present in returned list.
	 */
	@Test
    void testParserValid() {
        String JSONInput = 
            """
            {
                "articles": [
                    {
                        "title": "a",
                        "description": "a", 
                        "publishedAt": "2021-03-24T21:44:01Z", 
                        "url": "a"
                    },
                    {
                        "title": "b",
                        "description": "b", 
                        "publishedAt": "2021-03-24T22:20:12Z", 
                        "url": "b"
                    }
                ]
            }
            """;
        
        Logger logger = Logger.getLogger("TestLogger");
        ArticleParser parser = new apiParser();
        List<Article> articles = parser.parse(JSONInput, logger);
        
        assertEquals(2, articles.size());                                      
        assertEquals(articleA, articles.get(0));           
        assertNotEquals(articleA, articles.get(1));               
    }
	
	/**
	 * Tests an input with 1 invalid article, and 1 valid article.
	 * Ensures the returned list only contains the valid article.
	 */
	@Test
    void testParserInvalid() {
		String JSONInput = 
	            """
	            {
	                "articles": [
	                    {
	                        "title": "a",
	                     
	                        "publishedAt": "2021-03-24T21:44:01Z", 
	                        "url": "a"
	                    },
	                    {
	                        "title": "b",
	                        "description": "b", 
	                        "publishedAt": "2021-03-24T22:20:12Z", 
	                        "url": "b"
	                    }
	                ]
	            }
	            """;

        Logger logger = Logger.getLogger("TestLogger");
        ArticleParser parser = new apiParser();
        List<Article> articles = parser.parse(JSONInput, logger);
        
        assertEquals(1, articles.size());
        assertEquals(articleB, articles.get(0));
    }
	
	/**
	 * Tests an input with 2 invalid articles, who's invalidities are in different fields.
	 * Ensures the returned list has no articles present.
	 */
	@Test
    void testParserNoValid() {
		String JSONInput = 
	            """
	            {
	                "articles": [
	                    {
	                        "title": "a",
	                         
	                        "publishedAt": "2021-03-24T21:44:01Z", 
	                        "url": "a"
	                    },
	                    {
	                       
	                        "description": "b", 
	                        "publishedAt": "2021-03-24T22:20:12Z", 
	                        "url": "b"
	                    }
	                ]
	            }
	            """;

        Logger logger = Logger.getLogger("TestLogger");
        ArticleParser parser = new apiParser();
        List<Article> articles = parser.parse(JSONInput, logger);
        
        assertEquals(0, articles.size());
    }
	
	/**
	 * Tests proper functionality for a null input as the String parameter for Parser.parse.
	 */
	@Test
    void testParserNullInput() {
        String JSONInput = null;
       
        Logger logger = Logger.getLogger("TestLogger");
        ArticleParser parser = new apiParser();
        List<Article> articles = parser.parse(JSONInput, logger);
        
        assertEquals(0, articles.size());
    }
	
	/**
	 * Tests for proper functionality when an input article has more than the required fields.
	 * Ensures that the excess fields will be ignored.
	 */
	@Test
    void testParserExcessFields() {
		String JSONInput = 
	            """
	            {
	                "articles": [
	                    {
	                        "title": "a",
	                        "description": "a", 
	                        "publishedAt": "2021-03-24T21:44:01Z", 
	                        "url": "a",
	                        "banana": "yum yum yummy"
	                    }
	                ]
	            }
	            """;

        Logger logger = Logger.getLogger("TestLogger");
        ArticleParser parser = new apiParser();
        List<Article> articles = parser.parse(JSONInput, logger);
        
        assertEquals(1, articles.size());
        assertEquals(articleA, articles.get(0));
    }
	
}


