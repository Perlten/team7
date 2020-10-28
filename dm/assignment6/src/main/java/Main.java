import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        State s1 = new State();
        s1.addProperty("A", new HashSet<Integer>(Arrays.asList(1, 2, 3, 4)));
        s1.addProperty("B", new HashSet<Integer>(Arrays.asList(8)));
        s1.addProperty("C", new HashSet<Integer>(Arrays.asList(8, 10)));
        s1.addProperty("D", new HashSet<Integer>(Arrays.asList(11)));
        s1.print();

        State s2 = new State();
        s2.addProperty("A", new HashSet<Integer>(Arrays.asList(1, 2)));
        s2.addProperty("B", new HashSet<Integer>(Arrays.asList(8)));
        s2.addProperty("D", new HashSet<Integer>(Arrays.asList(11)));
        s2.print();

//        System.out.println(s1.compareTo(s2));
        State newState = s1.intersection(s2);
        newState.print();
    }
}
