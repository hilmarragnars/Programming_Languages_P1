public class Compiler{

	public static void main(String args[]){
		Lexer myLexer = new Lexer();
		Parser myParser = new Parser(myLexer);
		int k = myParser.parse();
		System.out.println(k);	
	}
}