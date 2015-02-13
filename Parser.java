import java.util.*;
//import edu.princeton.cs.introcs.*;

public class Parser{

	private Lexer lexer;
	private Token currToken;
	private List<String> numbers;
	private List<String> operators;
	private boolean mult;

	public Parser(Lexer lexer){
		this.lexer = lexer;
		currToken = this.lexer.nextToken();
		numbers = new ArrayList<String>();
		operators = new ArrayList<String>();
		mult = false;
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
				StdOut.println("PRINT");
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
			operators.add("ADD");
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
			printNum(mult);
			printOp(mult);
			return;
		}

		if(currToken.tCode == TokenCode.MULT) {
			operators.add("MULT");
			mult = true;
			term();
		}
	}
	private void factor(){
		currToken = lexer.nextToken();

		if(currToken.tCode == TokenCode.INT) {

			numbers.add(currToken.lexeme);
			if(mult) {
				printNum(mult);
				printOp(mult);
				mult = false;
			}

			return;
		}
		else if(currToken.tCode == TokenCode.ID) {
			numbers.add(currToken.lexeme);
			return;
		}
		else if(currToken.tCode == TokenCode.LPAREN){
			
			expr();

			if(currToken.tCode == TokenCode.RPAREN) {
				mult = false;
				return;
			}

		}

	}
	private void syntaxError(){
		//print();
		StdOut.println("Syntax error!");
		System.exit(0);
	}

	private void printOp(boolean mult) {
		if(mult) {
			for(int i = 0; i < operators.size(); i++) {
				if(operators.get(i) == "MULT") {
					StdOut.println("MULT");
					operators.remove(i);
				}
			}
		}
		else {
			Collections.reverse(operators);

			for(int i = 0; i < operators.size(); i++) {
				StdOut.println(operators.get(i));
			}

			operators.clear();
		}
	}

	private void printNum(boolean mult) {

		for(int i = 0; i < numbers.size(); i++) {
			StdOut.println("PUSH " + numbers.get(i));
		}

		numbers.clear();
	}
	private void print(){

	}
	public void parse(){
		statements();
		return;
	}
}