import java.util.*;
import java.io.*;
import edu.princeton.cs.introcs.*;

public class Interpreter{

	BufferedReader in;

	String input, code;


	public Interpreter() {


		try {
			in = new BufferedReader (new InputStreamReader (System.in));

			StringBuilder sb = new StringBuilder();

			code = in.readLine();

		 	sb.append(code);

		 	while(code != null) {
		 		code = in.readLine();
		 		sb.append(code);
		 	}

		 	input = sb.toString();
		 } catch(IOException e) {
		 	//dostuff
		 }
	}

	public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}