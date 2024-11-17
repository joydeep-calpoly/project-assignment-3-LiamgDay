package NewsAPI_Parser;

import java.util.List;

public interface ArticleParserVisitor {
	/**
     * Parses the given input string and returns a list of Articles.
     *
     * @param apiParser (an instance of an apiParser)
     * @return a List of Articles parsed from the input
     */
	List<Article> visit(apiParser apiParser);
	
	/**
     * Parses the given input string and returns a list of Articles.
     *
     * @param apiParser (an instance of a SimpleArticleParser)
     * @return a List of Articles parsed from the input
     */
	List<Article> visit(SimpleArticleParser simpleParser);

}
