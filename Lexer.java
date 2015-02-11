import java.util.*;
import edu.princeton.cs.introcs.*;

public class Lexer{

	private int position;

	Scanner input;

	public Lexer(){

		position = 0;

		input = new Scanner(System.in);

	}

	public Token nextToken() {

		String s = input.next();

		if(isAlpha(s)) {
			StdOut.println("jei, bókstafur");
			return new Token(TokenCode.ID, s);
		}

		return new Token(TokenCode.ID, s);
	}

	public boolean isAlpha(String name) {
    	return name.matches("[a-zA-Z]+");
	}
}