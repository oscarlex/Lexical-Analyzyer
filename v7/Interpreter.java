// Delete or change the following line if necessary.
package interprete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Interpreter {

    static boolean errorFound = false;

    public static void main(String[] args) throws IOException {
        if(args.length > 1) {
            System.out.println("Usage: Interpreter [file.txt]");

            // Define convention in UNIX "system.h" file.
            System.exit(64);
        } else if(args.length == 1) {
            executeFile(args[0]);
        } else {
            executePrompt();
        }
    }
    private static void executeFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        execute(new String(bytes, Charset.defaultCharset()));

        // Indicates an error in the exit code.
        if(errorFound) System.exit(65);
    }

    private static void executePrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print(">>> ");
            String line = reader.readLine();
            if(line == null) break; // Press [Ctrl + D].
            execute(line);
            errorFound = false;
        }
    }

    private static void execute(String source) {
        try{
            Scanner scanner = new Scanner(source);
            List<Token> tokens = scanner.scan();

            for(Token token : tokens){
                System.out.println(token);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /*
        You can use this method to report errors in the interpreter.
        Interpreter.error(....);
    */
    static void error(int line, String message){
        report(line, "", message);
    }

    private static void report(int line, String position, String message){
        System.err.println(
                "[line " + line + "] Error " + position + ": " + message
        );
        errorFound = true;
    }   
}
