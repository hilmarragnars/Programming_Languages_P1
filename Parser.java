import java.util.*;
import edu.princeton.cs.introcs.*;

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
		if (currToken.tCode == TokenCode.END) {
			StdOut.print("END\n");
			return;
		}
		statement();
		if (currToken.tCode == TokenCode.SEMICOL) {
			currToken = lexer.nextToken();
			statements();
		}
		else{
			syntaxError();
		}
		
	}
	private void statement(){

		if (currToken.tCode == TokenCode.ID) {

			numbers.add(currToken.lexeme);

			StdOut.println("PUSH " + currToken.lexeme);

			currToken = lexer.nextToken();

			if(currToken.tCode != TokenCode.ASSIGN){
				syntaxError();
			}
			else {
				operators.add("ASSIGN");
				expr();
			}
		}
		else if(currToken.tCode == TokenCode.PRINT) {
			StdOut.print("PUSH ");
			currToken = lexer.nextToken();
			if(currToken.tCode == TokenCode.ID) {
				StdOut.println(currToken.lexeme);
				currToken = lexer.nextToken();
				return;
			}
		}
	}
	private void expr(){

		term();

		if (currToken.tCode == TokenCode.SEMICOL) {

			return;
		}

		if(currToken.tCode == TokenCode.RPAREN) {
			return;
		}

		if(currToken.tCode == TokenCode.PLUS) {
			operators.add("PLUS");
			expr();
		}
		else if(currToken.tCode == TokenCode.MINUS) {
			operators.add("SUB");
			expr();
		}
		else {
			syntaxError();
		}
	}
	private void term(){
		factor();

		currToken = lexer.nextToken();


		if (currToken.tCode == TokenCode.SEMICOL) {
			Collections.reverse(operators);

			for(int i = 0; i < operators.size(); i++) {
				StdOut.println(operators.get(i));
			}

			operators.clear();

			return;
		}

		if(currToken.tCode == TokenCode.MULT) {
			operators.add("MULT");
			term();
		}
	}
	private void factor(){
		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.INT) {
			StdOut.println("PUSH " + currToken.lexeme);
			numbers.add(currToken.lexeme);

			return;
		}
		else if(currToken.tCode == TokenCode.ID) {
			StdOut.println("PUSH " + currToken.lexeme);
			numbers.add(currToken.lexeme);
			return;
		}
		else if(currToken.tCode == TokenCode.LPAREN){

			expr();

			if(currToken.tCode == TokenCode.RPAREN) {
				return;
			}

		}

	}
	private void syntaxError(){
		//print();
		StdOut.println("Syntax error!");
		System.exit(0);
	}
	private void print(){

	}
	public void parse(){
		statements();
		return;
	}
}