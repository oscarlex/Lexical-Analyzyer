// Delete or change the following line if necessary.
package interprete;

public enum TokenType {
    // Tokens: ['(', ')' '{', '}', ',', '.', '-', '+', ';', '/', '*'].
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // Tokens: ['!', '!=', '=', '==', '>', '>=', '<', '<='].
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literal tokens.
    IDENTIFIER, STRING, NUMBER,

    // Reserved words.
    AND, ELSE, FALSE, FUN, FOR, IF, NULL, OR,
    PRINT, RETURN, TRUE, VAR, WHILE,

    EOF
}
