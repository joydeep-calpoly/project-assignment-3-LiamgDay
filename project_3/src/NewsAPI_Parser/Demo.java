package NewsAPI_Parser;


import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;






/**
 * Demonstrates utilization of visitor pattern for this project.
 */
final class Demo {
	/**
	 * Typical main method which runs the demo on newsapi.txt and simple.txt.
	 */
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("NewsAPIParserLogger");
		try {
			FileHandler fileHandler = new FileHandler("parser.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
		}
		catch(Exception e) {
			System.out.println("Error with logger, try again.");
		}
		
		System.out.println("file + newsapi format using Visitor Pattern" + "\n");
		UserInput userInputApi = new UserInput("file", "newsapi");
		ParseVisitor visitorApi = new ParseVisitor(userInputApi, "inputs/newsapi.txt", logger);
		apiParser parser = new apiParser();
		List<Article> apiArticles = parser.accept(visitorApi);
		
		for(Article apiArticle : apiArticles) {
			System.out.println(apiArticle.toString());
			System.out.print("\n");
		}
		
		System.out.println("file + simple format using Visitor Pattern" + "\n");
		UserInput userInputSimple = new UserInput("file", "simple");
		ParseVisitor visitorSimple = new ParseVisitor(userInputSimple, "inputs/simple.txt", logger);
		SimpleArticleParser simpleParser = new SimpleArticleParser();
		List<Article> simpleArticles = simpleParser.accept(visitorSimple);

		for (Article simpleArticle : simpleArticles) {
		    System.out.println(simpleArticle.toString());
		    System.out.print("\n");
		}
	}
}