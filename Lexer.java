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

		String inputSub = input.substring(position, position + 1);

		if(isAlpha(input.substring(position, position + 1))) {

			StringBuilder str = new StringBuilder();
		
			while(isAlpha(input.substring(position, position + 1))) {

				str.append(input.substring(position, position + 1));
				position++;
				StdOut.print("i"); //bara test til að checka 
			}

			return new Token(TokenCode.ID, str.toString());
		}
		else if(inputSub.equals("=")) {
            return new Token(TokenCode.ASSIGN, inputSub);
        }
		else if(inputSub.equals("+")) {
            return new Token(TokenCode.PLUS, inputSub);
        }
        else if(inputSub.equals("*")) {
            return new Token(TokenCode.MULT, inputSub);
    	}
    	else if(inputSub.equals("-")) {
            return new Token(TokenCode.MINUS, inputSub);
    	}
		return new Token(TokenCode.ID, input);
	}

	public boolean isAlpha(String name) {
    	return name.matches("[a-zA-Z]+");
	}
}