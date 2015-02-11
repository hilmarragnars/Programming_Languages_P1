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
		StdOut.print(input.length());




		//if there is a whitespace nothing is returned.
		if (inputSub.equals(" ")) {
			position++;
			inputSub = input.substring(position, position + 1);			
		}		

		if(isAlpha(inputSub)) {

			StringBuilder str = new StringBuilder();
		
			while(isAlpha(input.substring(position, position + 1))) {

				str.append(input.substring(position, position + 1));
	
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
    	else if(inputSub.equals("(")){
    		return new Token(TokenCode.LPAREN, inputSub);
    	}
    	else if(inputSub.equals(")")){
    		return new Token(TokenCode.RPAREN, inputSub);
    	}
    	else if(inputSub.equals(";")){
    		return new Token(TokenCode.SEMICOL, inputSub);
    	}
		return new Token(TokenCode.ID, input);
	}

	public boolean isAlpha(String name) {
    	return name.matches("[a-zA-Z]+");
	}
}