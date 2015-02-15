import java.util.*;
import java.io.*;

public class Lexer{

	private int position;

	BufferedReader in;

	String input;

	String code;

	public Lexer(){

		 position = 0;

		 try {
		 	in = new BufferedReader (new InputStreamReader (System.in));
		 	code = in.readLine();

		 	StringBuilder sb = new StringBuilder();

		 	sb.append(code);

		 	while(code != null) {
		 		code = in.readLine();
		 		sb.append(code);
		 	}

		 	input = sb.toString();
		 }
		 catch(IOException e) {
		 	//dostuff
		 }
	}

	public Token nextToken() {

		if(position == input.length()) {
			return new Token(TokenCode.END, "");
		}

		if (input.substring(position, position + 1).equals(" ")) {
			position++;			
		}

		String inputSub = input.substring(position, position + 1);
		//StdOut.print(inputSub);
		position++;

		//if there is a whitespace nothing is returned.
				

		if(isAlpha(inputSub)) {

			StringBuilder str = new StringBuilder();
			str.append(inputSub);

			if(input.substring(input.length() - 4, input.length()).equals("null")) {
				input = input.substring(0, input.length() - 4);
			}
		
			while(position < input.length() && isAlpha(input.substring(position, position + 1))) {

				str.append(input.substring(position, position + 1));
				position++;
	
			}

			if(str.toString().equals("print")) {
				return new Token(TokenCode.PRINT, str.toString());	
			}
			else if(str.toString().equals("end")) {
				return new Token(TokenCode.END, str.toString());	
			}

			return new Token(TokenCode.ID, str.toString());
		}
		else if(isDigit(inputSub)) {

			StringBuilder str = new StringBuilder();
			str.append(inputSub);

			while(isDigit(input.substring(position, position + 1))) {

				str.append(input.substring(position, position + 1));
				position++;
			}

			return new Token(TokenCode.INT, str.toString());

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
		else {
			return new Token(TokenCode.ERROR, "");
		}
	}

	private boolean isAlpha(String str) {
    	return str.matches("[a-zA-Z]+");
	}

	private boolean isDigit(String str) {
		try {
			Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return false;
		}

		return true;
	}
}