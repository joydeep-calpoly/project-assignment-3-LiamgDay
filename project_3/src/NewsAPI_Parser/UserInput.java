package NewsAPI_Parser;

public class UserInput {
	final String source;
	final String format;
	
	
	/**
     * Constructs a UserInput
     *
     * @param source (the type of source the user wants to work with)
     * @param format (the type of parser the user wants to work with)
     */
	public UserInput(String source, String format) {
		this.source = source;
		this.format = format;
	}
	
}
