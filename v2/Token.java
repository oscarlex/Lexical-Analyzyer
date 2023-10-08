// Delete or change the following line if necessary.
package interprete;

public class Token {
    final TokenType tokenType;
    final String lexeme;
    final Object literal;

    public Token(TokenType tokenType, String lexeme) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.literal = null;
    }

    public Token(TokenType tokenType, String lexeme, Object literal) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    public String toString() {
        return "<" + tokenType + " " + lexeme + " " + literal + ">";
    }
}
