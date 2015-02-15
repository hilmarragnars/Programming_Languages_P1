import java.util.*;

public class Parser{

	private Lexer lexer;
	private Token currToken;

	public Parser(Lexer lexer){
		this.lexer = lexer;
		currToken = this.lexer.nextToken();
	}

	//The statements function. Statements can either be 'end' or 'Statement ; Statements'
	//If the statement is an end, we quit the program. Otherwise we run the statement function
	//and then if it's followed by a semilomn we run the statements function recursively
	private void statements(){

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
	//The statement function. If the tcode is ID we print 'PUSH "variable"'. and 
	//get the next token. If the token was ID it has to be followed by '='. and expression.
	//If it's not we print Syntax error. 
	private void statement(){

		if (tCode() == TokenCode.ID) {

			System.out.println("PUSH " + currToken.lexeme);

			nextToken();

			if(tCode() != TokenCode.ASSIGN){
				syntaxError();
			}
			else {
				nextToken();
				expr();
				System.out.println("ASSIGN");
			}
		}
		//If the token is a PRINT we get the next token and print 'Push "variable"' and
		//quit the program.
		else if(currToken.tCode == TokenCode.PRINT) {

			nextToken();

			if(tCode() == TokenCode.ID) {

				System.out.println("PUSH " + currToken.lexeme);

				nextToken(); 

				System.out.println("PRINT");
				System.exit(0);
				return;
			}
		}
	}
	//Expression alwaus starts with term so we start by calling the term function.
	//After the term has finished we check if the current tCode is either a plus or a sub
	//. We then call the expression function and print out the appropriate operation symbol.
	private void expr(){

		term();

		if(tCode() == TokenCode.PLUS) {
			
			nextToken();
			expr();
			System.out.println("ADD");
		}
		else if(tCode() == TokenCode.MINUS) {

			nextToken();
			expr();
			System.out.println("SUB");
		}
		//if the token is a semicol we return. 
		else if(tCode() == TokenCode.SEMICOL) {
			return;
		}
	}

	//A term always begins with a factor so we start by calling the factor function.
	//If the token after the factor is a MULT operation we call another term and then print out
	//the MULT after the term has finished running. 
	private void term(){
		factor();

		while(tCode() == TokenCode.MULT) {
			nextToken();		
			term();
			System.out.println("MULT");
		}
	}

	//The factor function. If the tCode is either a INT or ID we immidiately print out 
	//'PUSH "variable"' and fetch the next token. The factor can also be '(Expr)' and we check
	//if that's correct if the tCode is a Right parensis. Otherwise we print out the syntax error
	//message. 
	private void factor(){

		if(tCode() == TokenCode.INT || tCode() == TokenCode.ID) {

			System.out.println("PUSH " + currToken.lexeme);
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

	//function that prints Syntax error. 
	private void syntaxError(){

		System.out.println("Syntax error!");
		System.exit(0);
	}
	//A function the fetches the next token and prints out a error message if the tCode is
	//an ERROR.
	private void nextToken() {

		currToken = lexer.nextToken();

		if(tCode() == TokenCode.ERROR) {
			syntaxError();
		}
		else if(tCode() == TokenCode.END) {
			System.exit(0);
		}
	}

	//function that returns the current tCode.
	private TokenCode tCode() {
		return currToken.tCode;
	}

	public void parse(){
		statements();
		return;
	}
}
