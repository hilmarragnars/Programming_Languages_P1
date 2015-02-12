import java.util.*;
import edu.princeton.cs.introcs.*;

public class Parser{

	private Lexer lexer;
	private Token currToken;

	public Parser(Lexer lexer){
		this.lexer = lexer;
		currToken = this.lexer.nextToken();
	}

	private void statements(){
		if (currToken.tCode == TokenCode.END) {
			StdOut.print("code ended\n");
			return;
		}
		statement();
		currToken = lexer.nextToken();
		if (currToken.tCode == TokenCode.SEMICOL) {
			StdOut.print("semicol in statements\n");
			statements();
		}
		else{
			StdOut.print("error in statements\n");
			syntaxError();
		}
		
	}
	private void statement(){
		//StdOut.print(currToken.tCode);

		//currToken = lexer.nextToken();
		if (currToken.tCode == TokenCode.ID) {
			StdOut.print("ID in statement\n");
			currToken = lexer.nextToken();
			if(currToken.tCode != TokenCode.ASSIGN){
				StdOut.print("error in statement\n");
				syntaxError();
			}
			else {
				StdOut.print("=\n");
				expr();
			}
		}
	}
	private void expr(){

		term();

		if(currToken.tCode == TokenCode.RPAREN) {
			StdOut.print("right paren in expr\n");
			return;
		}

		//currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.PLUS) {
			StdOut.print("+\n");

			expr();
		}
		else if(currToken.tCode == TokenCode.MINUS) {
			StdOut.print("-\n");
			expr();
		}
		else {
			StdOut.print("syntaxError in expr\n");
			syntaxError();
		}
	}
	private void term(){
		factor();

		currToken = lexer.nextToken();


		if (currToken.tCode == TokenCode.SEMICOL) {
			StdOut.print("semicol in term\n");
			currToken = lexer.nextToken();
			statements();
		}


		if(currToken.tCode == TokenCode.MULT) {
			StdOut.print("*\n");
			term();
		}
	}
	private void factor(){
		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.INT) {
			StdOut.print("INT\n");
			return;
		}
		else if(currToken.tCode == TokenCode.ID) {
			StdOut.print("ID in factor\n");
			return;
		}
		else if(currToken.tCode == TokenCode.LPAREN){
			StdOut.print("left paren\n");
			expr();
			StdOut.print(currToken.tCode);
			//currToken = lexer.nextToken();

			if(currToken.tCode == TokenCode.RPAREN) {
				StdOut.print("right paren\n");
				return;
			}

		}

	}
	private void syntaxError(){
		print();
		StdOut.print("Syntax Error");
		return;
		//quit program
	}
	private void print(){

	}
	public void parse(){
		statements();
		return;
	}
}