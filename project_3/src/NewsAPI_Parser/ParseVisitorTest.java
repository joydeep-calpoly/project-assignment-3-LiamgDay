package NewsAPI_Parser;

import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class ParseVisitorTest {

    /**
     * Tests if ParseVisitor correctly assigns APIParser for a URL source with newsapi format.
     */
    @Test
    void testVisitorWithAPIParser() {
        UserInput userInput = new UserInput("url", "newsapi");
        String inputData = "banana.com";
        Logger logger = Logger.getLogger("TestLogger");

        ParseVisitor visitor = new ParseVisitor(userInput, inputData, logger);
        apiParser apiParser = new apiParser();

        apiParser.accept(visitor);

        
        assertDoesNotThrow(() -> apiParser.accept(visitor));
    }

    
    /**
     * Tests if ParseVisitor correctly assigns APIParser for a file source with newsapi format.
     */
    @Test
    void testVisitorWithAPIParserComboTwo() {
        UserInput userInput = new UserInput("file", "newsapi");
        String inputData = "banana.txt";
        Logger logger = Logger.getLogger("TestLogger");

        ParseVisitor visitor = new ParseVisitor(userInput, inputData, logger);
        apiParser apiParser = new apiParser();

        apiParser.accept(visitor);

        
        assertDoesNotThrow(() -> apiParser.accept(visitor));
    }
    /**
     * Tests if ParseVisitor correctly assigns SimpleArticleParser for a file source with simple format.
     */
    @Test
    void testVisitorWithSimpleParser() {
        UserInput userInput = new UserInput("file", "simple");
        String inputData = "banana.txt";
        Logger logger = Logger.getLogger("TestLogger");

        ParseVisitor visitor = new ParseVisitor(userInput, inputData, logger);
        SimpleArticleParser simpleParser = new SimpleArticleParser();

        simpleParser.accept(visitor);

        
        assertDoesNotThrow(() -> simpleParser.accept(visitor));
    }

    
    
    /**
     * Tests if ParseVisitor throws an exception for invalid combination.
     */
    @Test
    void testVisitorWithInvalidConfiguration() {
        UserInput userInput = new UserInput("url", "simple"); 
        String inputData = "banana.com";
        Logger logger = Logger.getLogger("TestLogger");

        ParseVisitor visitor = new ParseVisitor(userInput, inputData, logger);
        apiParser apiParser = new apiParser();

        assertThrows(IllegalArgumentException.class, () -> apiParser.accept(visitor));
    }

}
