import java.util.*;
import java.io.*;
import edu.princeton.cs.introcs.*;

public class Lexer{

	private int position;

	BufferedReader in;

	String input;

	public Lexer(){
		 
		 position = 0;

		 try {
		 	in = new BufferedReader (new InputStreamReader (System.in));
		 	input = in.readLine();
		 }
		 catch(IOException e) {
		 	//dostuff
		 }
		 

	}

	public Token nextToken() {

		if(isAlpha(input.substring(0, 1))) {
			StdOut.println("jei, bókstafur");
			return new Token(TokenCode.ID, input);
		}

		return new Token(TokenCode.ID, input);
	}

	public boolean isAlpha(String name) {
    	return name.matches("[a-zA-Z]+");
	}
}