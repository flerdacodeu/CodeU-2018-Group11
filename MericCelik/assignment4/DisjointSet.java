/**The class which has set operations.
 * @author AycaMericCelik
 * */
public class DisjointSet {
    /**Finds root of the set.
     * @param node: node to find its sets root.
     * @return root node
     * @throws IllegalArgumentException when argument is null.
     * */
    public static Node find(Node node) {
        if(node==null)
            throw new IllegalArgumentException("Argument is null!");
        if (!node.parent.equals(node))
            node.parent = find(node.parent);
        return node.parent;
    }

    /**Merges two sets into one regarding to their node rank. Set with smaller rank is added to bigger one. If equal,
     * rank is increased by one.
     * @param first: first node
     * @param second: second node
     * @throws IllegalArgumentException when either or both of the arguments are null.
     * */
    public static void union(Node first, Node second) {
        if(first == null)
            throw new IllegalArgumentException("First argument is null!");
        if(second == null)
            throw new IllegalArgumentException("Second argument is null!");
        Node firstRoot = find(first);
        Node secondRoot = find(second);
        if (firstRoot.equals(secondRoot)) {
            if (firstRoot.rank < secondRoot.rank)
                firstRoot.parent = secondRoot;
            else if (firstRoot.rank > secondRoot.rank)
                secondRoot.parent = firstRoot;
            else {
                secondRoot.parent = firstRoot;
                firstRoot.rank +=1;
            }
        }
    }
}
