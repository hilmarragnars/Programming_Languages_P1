import java.util.*;
import java.io.*;

public class Interpreter{

	//Declare the Scanner to fetch the input
	Scanner in;

	//We will store the input in this string, one line at a time
	String input;

	//Declare the stack that will be used for the mathematical functions
	Stack<String> stack;

	//Array of strings to split the lines up.
	String arrayString[];

	//Map to store the values of the variables.
	Map<String, String> variables;

	public Interpreter() {

		//Initalize the Scanner, stack and Map.
		in = new Scanner(System.in);

		stack = new Stack<String>();

		variables = new HashMap<String, String>();
	}


	/*************************************************************************
	* A function that checks if there's a line left in the input. If there 
	* is we assign that to the input string and return true, otherwise false.
	**************************************************************************/
	private boolean getNextLine() {

		if(in.hasNextLine()) {

			input = in.nextLine();
			return true;
		}
		else {
			return false;
		}

	}

	/******************************************************************************
	* The main function which interprets the input string and calculates the output
	*******************************************************************************/

	private void interpret() {

		//We continue the interpretations as long there is a line left
		while(getNextLine()) {

			//Split the input string up into an array
			arrayString = input.split("\\s+");

			//If the command is PUSH we push the variable to the stack
			if(arrayString[0].equals("PUSH")) {

				stack.push(arrayString[1]);
			}

			//Else, if the command is a calculus operation we send the appropriate
			//operation to the math function.
			else if(arrayString[0].equals("ADD")) {

				stack.push(math("ADD"));

			}
			else if(arrayString[0].equals("MULT")) {
	
				stack.push(math("MULT"));

			}
			else if(arrayString[0].equals("SUB")) {

				stack.push(math("SUB"));

			}

			//If the command is Assign put the variable to the stack and also
			//to the variable map with the value attached.
			else if (arrayString[0].equals("ASSIGN")){

				String s1 = stack.pop();
				String s2 = stack.pop();

				variables.put(s2, s1);
				stack.push(s2);
			}

			//If the command is print we print it out.
			else if(arrayString[0].equals("PRINT")) {
				
				String result = stack.pop();

				//if the string is a variable we fetch the value through the map.
				if(!isDigit(result)) {
					result = (String)variables.get(result);
				}

				System.out.println(result);

				return;
			}
			else {
				System.out.println("Error for operator: " + arrayString[0]);
			}
		}
	}


	/******************************************************
	* A function that checks if the given string is a digit
	********************************************************/
	private boolean isDigit(String str) {

		try {
			Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return false;
		}

		return true;
	}

	/*************************************************************************
	* A function that takes in one operation strings and calculates the top 
	* two string in the stack together with the appropriate operation.
	**************************************************************************/

	private String math(String operation) {

		Integer result = 0;
		String s1 = stack.pop();
		String s2 = stack.pop();

		if(!isDigit(s1)) {
			s1 = (String)variables.get(s1);
		}
		if(!isDigit(s2)) {
			s2 = (String)variables.get(s2);
		}

		Integer num1 = Integer.parseInt(s1);
		Integer num2 = Integer.parseInt(s2);

		if(operation.equals("ADD")) {
			result = num1 + num2;
		}
		else  if(operation.equals("SUB")) {
			result = num2 - num1;
		}
		else  if(operation.equals("MULT")) {
			result = num1 * num2;
		}

		return Integer.toString(result);
		
	}

	public static void main(String[] args) {

		Interpreter in = new Interpreter();

		in.interpret();

		return;
	}
}