// Delete or change the following line if necessary.
package interprete;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private static final Map<String, TokenType> reservedWords;

    static {
        reservedWords = new HashMap<>();
        reservedWords.put("and",    TokenType.AND);
        reservedWords.put("else",   TokenType.ELSE);
        reservedWords.put("false",  TokenType.FALSE);
        reservedWords.put("for",    TokenType.FOR);
        reservedWords.put("fun",    TokenType.FUN);
        reservedWords.put("if",     TokenType.IF);
        reservedWords.put("null",   TokenType.NULL);
        reservedWords.put("or",     TokenType.OR);
        reservedWords.put("print",  TokenType.PRINT);
        reservedWords.put("return", TokenType.RETURN);
        reservedWords.put("true",   TokenType.TRUE);
        reservedWords.put("var",    TokenType.VAR);
        reservedWords.put("while",  TokenType.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        String lexeme = "";
        int state = 0;
        char c;
        
        for(int i = 0; i < source.length(); i++) {
            c = source.charAt(i);
            
            switch (state) {
                case 0:
                    if(Character.isLetter(c)) {
                        state = 13;
                        lexeme += c;
                    }
                    else if(Character.isDigit(c)) {
                        state = 15;
                        lexeme += c;
                    }
                    else if(c == '/') {
                        state = 26;
                        lexeme += c;
                    }
                break;
                  
                case 13:
                    if(Character.isLetterOrDigit(c)) {
                        state = 13;
                        lexeme += c;
                    }
                    else {
                        TokenType tt = reservedWords.get(lexeme);

                        if(tt == null) {
                            Token t = new Token(TokenType.IDENTIFIER, lexeme);
                            tokens.add(t);
                        }
                        else {
                            Token t = new Token(tt, lexeme);
                            tokens.add(t);
                        }
                        state = 0;
                        lexeme = "";
                        i--;
                    }
                break;

                case 15:
                    if(Character.isDigit(c)) {
                        state = 15;
                        lexeme += c;
                    }
                    else if(c == '.') {
                        state = 16;
                        lexeme += c;       
                    }
                    else if(c == 'E') {
                        state = 18;
                        lexeme += c; 
                    }
                    else {
                        Token t = new Token(TokenType.NUMBER, lexeme, Integer.valueOf(lexeme));
                        tokens.add(t);

                        state = 0;
                        lexeme = "";
                        i--;
                    }
                break;
                
                case 16:
                    if(Character.isDigit(c)) {
                        state = 17;
                        lexeme += c;  
                    }
                break;
                
                case 17:
                    if(Character.isDigit(c)) {
                        state = 17;
                        lexeme += c;  
                    }
                    else if(c == 'E') {
                        state = 18;
                        lexeme += c; 
                    }
                    else {
                        Token t = new Token(TokenType.NUMBER, lexeme, Float.valueOf(lexeme));
                        tokens.add(t);
                        state = 0;
                        lexeme = "";
                        i--;
                    }
                break;
                
                case 18:
                    if(Character.isDigit(c)) {
                        state = 20;  
                    }
                    else if(c == '+'||c == '-') {
                        state = 19;
                    }
                    lexeme += c;
                break;

                case 19:
                    if(Character.isDigit(c)) {
                        state = 20;
                        lexeme += c;  
                    }
                break;

                case 20:
                    if(Character.isDigit(c)) {
                        state = 20;
                        lexeme += c;  
                    }
                    else {
                        Token t = new Token(TokenType.NUMBER, lexeme, Float.valueOf(lexeme));
                        tokens.add(t);

                        state = 0;
                        lexeme = "";
                        i--;
                    }
                break;

                case 26:
                    if(c=='*') {
                        state = 27;
                        lexeme += c;
                    }
                    else if(c=='/') {
                        state = 30;
                        lexeme += c;
                    }
                    else {
                        Token t = new Token(TokenType.SLASH, lexeme);
                        tokens.add(t); 
                        state = 0;
                        lexeme = "";
                        i--;
                    }
                break;

                case 27:
                    if(c=='*') {
                        state = 28;
                    }
                    else {
                        state = 27;
                    }
                    lexeme += c;
                break;
            
                case 28:
                    if(c =='*') {
                        state = 28;
                        lexeme += c;  
                    }
                    else if(c=='/') {
                        state = 0;
                        lexeme = "";
                    }
                    else {
                        state = 27;
                        lexeme += c;  
                    }
                break;
                
                case 30:
                    if(c=='\n') { 
                        state = 0;
                        lexeme = "";
                    }
                    else {
                        state=30;
                        lexeme += c;
                    }
                break;
            }
        }
        return tokens;
    }
}

