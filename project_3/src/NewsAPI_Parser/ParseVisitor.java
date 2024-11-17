package NewsAPI_Parser;

import java.util.List;
import java.util.logging.Logger;

public class ParseVisitor implements ArticleParserVisitor {
	private final UserInput userInput;
	private final String inputData;
	private final Logger logger;
	
	public ParseVisitor(UserInput userInput, String inputData, Logger logger) {
        this.userInput = userInput;
        this.inputData = inputData;
        this.logger = logger;
    }
	
	/**
     * Visits the apiParser to perform .parse() based on proper input source type
     *
     * @param apiParser (an instance of an apiParser)
     * @return a List of Articles parsed from the input
     */
    public List<Article> visit(apiParser apiParser) {
        if (userInput.source.equals("url") && userInput.format.equals("newsapi")) {
        	String convertedData = apiParser.converter(inputData);
            return apiParser.parse(convertedData, logger);
            
        } 
        else if (userInput.source.equals("file") && userInput.format.equals("newsapi")) {
        	SimpleArticleParser dummyParser = new SimpleArticleParser();
        	String convertedData = dummyParser.converter(inputData);
        	return apiParser.parse(convertedData, logger);
        }
        else {
            throw new IllegalArgumentException("wrong source and format combo");
        }
    }

    /**
     * Visits the SimpleArticleParser to perform .parse() only with file input source
     *
     * @param simpleParser (an instance of a SimpleArticleParser)
     * @return a List of Articles parsed from the input
     */
    public List<Article> visit(SimpleArticleParser simpleParser) {
        if (userInput.source.equals("file") && userInput.format.equals("simple")) {
        	String convertedData = simpleParser.converter(inputData);
            return simpleParser.parse(convertedData, logger); 
        } else {
            throw new IllegalArgumentException("wrong source and format combo");
        }
    }

}
