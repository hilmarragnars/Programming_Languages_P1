import java.util.*;
//import edu.princeton.cs.introcs.*;

public class Parser{

	private Lexer lexer;
	private Token currToken;
	private List<String> numbers;
	private List<String> operators;

	public Parser(Lexer lexer){
		this.lexer = lexer;
		currToken = this.lexer.nextToken();
		numbers = new ArrayList<String>();
		operators = new ArrayList<String>();
	}

	private void statements(){

		//StdOut.println(tCode());
		if (tCode() == TokenCode.END) {
		//	StdOut.println("HALLO");
			System.exit(0);
		}

		statement();
		if (tCode() == TokenCode.SEMICOL) {
			nextToken();
			statements();
		}
		
	}
	private void statement(){

		if (tCode() == TokenCode.ID) {


			StdOut.println("PUSH " + currToken.lexeme);

			nextToken();

			if(tCode() != TokenCode.ASSIGN){
				syntaxError();
			}
			else {
				nextToken();
				expr();
				StdOut.println("ASSIGN");
			}
		}
		else if(currToken.tCode == TokenCode.PRINT) {

			nextToken();

			if(tCode() == TokenCode.ID) {

				StdOut.println("PUSH " + currToken.lexeme);

				nextToken(); 

				StdOut.println("PRINT");
				System.exit(0);
				return;
			}
		}
	}
	private void expr(){

		term();

		if(tCode() == TokenCode.PLUS) {
			
			nextToken();
			expr();
			StdOut.println("ADD");
		}
		else if(tCode() == TokenCode.MINUS) {

			nextToken();
			expr();
			StdOut.println("SUB");
		}
		else if(tCode() == TokenCode.SEMICOL) {
			return;
		}
	}
	private void term(){
		factor();

		while(tCode() == TokenCode.MULT) {
			nextToken();		
			term();
			StdOut.println("MULT");
		}
	}
	private void factor(){

		if(tCode() == TokenCode.INT || tCode() == TokenCode.ID) {

			StdOut.println("PUSH " + currToken.lexeme);
			nextToken();
		}
		else if(tCode() == TokenCode.LPAREN){

			nextToken();
			expr();

			if(tCode() == TokenCode.RPAREN) {
				nextToken();
			}
			else {
				syntaxError();
			}
		}
		else {
			syntaxError();
		}
	}
	private void syntaxError(){

		StdOut.println("Syntax error!");
		System.exit(0);
	}

	private void nextToken() {

		currToken = lexer.nextToken();

		if(tCode() == TokenCode.ERROR) {
			syntaxError();
		}
		else if(tCode() == TokenCode.END) {
			System.exit(0);
		}
	}

	private TokenCode tCode() {
		return currToken.tCode;
	}

	public void parse(){
		statements();
		return;
	}
}
