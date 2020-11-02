import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        State endState = new State();
        endState.addProperty("x", new HashSet<Integer>(Arrays.asList(200)));
        endState.addProperty("y", new HashSet<Integer>(Arrays.asList(5)));
        endState.addProperty("h", new HashSet<Integer>(Arrays.asList(205)));

        String source = "" +
                "def test INTEGER { " +
                "let x = 100 " +
                "let x = 200 " +
                "let y = 5 " +
                "let h = y + x " +
                "}";
        AST ast = new AST(source);
        if (ast.compile()) {
            ast.run();
            State astState = ast.getCurrentState();

            System.out.println(astState.compareTo(endState));
        } else {
            System.out.println("COMPILE ERROR!!!");
        }
    }
}
