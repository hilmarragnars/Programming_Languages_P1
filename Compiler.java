import edu.princeton.cs.introcs.*;

public class Compiler{

	public static void main(String args[]){
		Lexer myLexer = new Lexer();
		Parser myParser = new Parser(myLexer);
		myParser.parse();

		Token t = myLexer.nextToken();
	}
}