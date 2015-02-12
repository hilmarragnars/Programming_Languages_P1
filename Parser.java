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
				
			}
		}
	}
	private void expr(){
		//do stuff
	}
	private void term(){
		//do stuff
	}
	private void factor(){
		//do stuff
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