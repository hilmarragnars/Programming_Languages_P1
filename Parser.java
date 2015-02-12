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
		statement();
	}
	private void statement(){

		if (currToken.tCode == TokenCode.ID) {
			currToken = lexer.nextToken();
			if(currToken.tCode != TokenCode.ASSIGN){
				syntaxError();
			}
			else {
				expr();
			}
		}
	}
	private void expr(){

		term();

		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.PLUS) {

			expr();
		}
		else if(currToken.tCode == TokenCode.MINUS) {

			expr();
		}
		else {
			syntaxError();
		}
	}
	private void term(){
		factor();

		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.MULT) {
			term();
		}
	}
	private void factor(){
		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.INT) {
			return;
		}
		else if(currToken.tCode == TokenCode.ID) {

		}
		else if(currToken.tCode == TokenCode.LPAREN){
			expr();

			currToken = lexer.nextToken();

			if(currToken.tCode == TokenCode.RPAREN) {
				//dostuff
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