enum TokenCode {
	ID,
	ASSIGN, 
	SEMICOL, 
	INT, 
	PLUS, 
	MINUS, 
	MULT, 
	LPAREN, 
	RPAREN, 
	PRINT, 
	END, 
	ERROR
};

public class Token{

	public String lexeme;
	public TokenCode tCode;

	//constructor that expects tCode and a lexeme string.
	public Token(TokenCode tCode, String lexeme){
		this.lexeme = lexeme;
		this.tCode = tCode;
	}
	
}