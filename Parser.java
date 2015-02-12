import java.util.*;

public class Parser{

	private Lexer lexer;
	private Token currToken;

	public Parser(Lexer lexer){
		this.lexer = lexer;
		currToken = this.lexer.nextToken();
	}

	public void parse(){
		//do stuff
		return;
	}
}