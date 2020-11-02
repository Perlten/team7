import java.util.Arrays;
import java.util.HashSet;

public class AST {

    private String source = "";
    private State state = null;
    private Node rootNode = null;

    public AST(String source) {
        this.source = source;
    }

    public boolean compile() {
        this.state = new State();
        Node latestNode = null;
        String[] lineSplit = this.source.split(" ");
        for (int i = 0; i < lineSplit.length; i++) {
            String currentWord = lineSplit[i];
            if (currentWord.equals("def")) {
                this.rootNode = new Node(currentWord, this.state);
                String defName = lineSplit[++i];
                String defType = lineSplit[++i];
                Node defNameNode = new Node(defName, this.state);
                Node defTypeNode = new Node(defType, this.state);
                this.rootNode.leftNode = defNameNode;
                defNameNode.leftNode = defTypeNode;

                if (lineSplit[++i].equals("{")) {
                    Node n = new Node(lineSplit[i], this.state);
                    latestNode = n;
                    this.rootNode.rightNode = n;
                } else {
                    return false;
                }
            }

            if (currentWord.equals("let")) {
                Node letNode = new Node(currentWord, this.state);
                String letName = lineSplit[++i];
                String eq = lineSplit[++i];
                if (!eq.equals("=")) {
                    return false;
                }
                String letValue = lineSplit[++i];

                Node nameNode = new Node(letName, this.state);
                Node valueNode = new Node(letValue, this.state);
                if (lineSplit[i + 1].equals("+")) {
                    String plusOp = lineSplit[++i];
                    String nextValue = lineSplit[++i];
                    Node plusNode = new Node(plusOp, this.state);
                    Node nextNode = new Node(nextValue, this.state);
                    valueNode.leftNode = plusNode;
                    plusNode.leftNode = nextNode;
                }


                latestNode.leftNode = letNode;
                letNode.leftNode = nameNode;
                nameNode.leftNode = valueNode;
                latestNode = valueNode;
            }
        }


        return true;
    }

    public void run() {
        handleNode(this.rootNode);
    }

    private void handleNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.section.equals("let")) {
            if (node.leftNode.leftNode.leftNode != null && node.leftNode.leftNode.leftNode.section.equals("+")) {
                String letName = node.leftNode.section;
                String A = node.leftNode.leftNode.section;
                String B = node.leftNode.leftNode.leftNode.leftNode.section;
                int AValue = this.state.getSingleValue(A);
                int bValue = this.state.getSingleValue(B);
                this.state.addProperty(letName, new HashSet<>(Arrays.asList(AValue + bValue)));

            } else {
                String letName = node.leftNode.section;
                String value = node.leftNode.leftNode.section;
                this.state.addProperty(letName, new HashSet<>(Arrays.asList(Integer.parseInt(value))));
            }
        }


        handleNode(node.leftNode);
        handleNode(node.rightNode);
    }


    public State getCurrentState() {
        return this.state;
    }

    private class Node {
        private Node leftNode;
        private Node rightNode;

        public String section = null;
        private State state;

        public Node(String section, State state) {
            this.section = section;
            this.state = state;
        }
    }
}
