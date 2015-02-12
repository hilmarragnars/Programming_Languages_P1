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
			StdOut.print("END\n");
			return;
		}
		statement();
		if (currToken.tCode == TokenCode.SEMICOL) {
			StdOut.print(";\n");
			currToken = lexer.nextToken();
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
			StdOut.print("ID");
			currToken = lexer.nextToken();
			if(currToken.tCode != TokenCode.ASSIGN){
				StdOut.print("error in statement\n");
				syntaxError();
			}
			else {
				StdOut.print(" = ");
				expr();
			}
		}
		else if(currToken.tCode == TokenCode.PRINT) {
			StdOut.print("PRINT ");
			currToken = lexer.nextToken();
			if(currToken.tCode == TokenCode.ID) {
				StdOut.print("ID");
				currToken = lexer.nextToken();
				return;
			}
		}
	}
	private void expr(){

		term();

		if (currToken.tCode == TokenCode.SEMICOL) {
			//StdOut.print(";\n");
			//currToken = lexer.nextToken();
			return;
		}

		if(currToken.tCode == TokenCode.RPAREN) {
			//StdOut.print(")");
			return;
		}

		//currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.PLUS) {
			StdOut.print(" + ");

			expr();
		}
		else if(currToken.tCode == TokenCode.MINUS) {
			StdOut.print(" - ");
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
			//StdOut.print(";\n");
			//currToken = lexer.nextToken();
			return;
		}


		if(currToken.tCode == TokenCode.MULT) {
			StdOut.print(" * ");
			term();
		}
	}
	private void factor(){
		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.INT) {
			StdOut.print("INT");
			return;
		}
		else if(currToken.tCode == TokenCode.ID) {
			StdOut.print("ID");
			return;
		}
		else if(currToken.tCode == TokenCode.LPAREN){
			StdOut.print("(");
			expr();
			//StdOut.print(currToken.tCode);
			//currToken = lexer.nextToken();

			if(currToken.tCode == TokenCode.RPAREN) {
				StdOut.print(")");
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