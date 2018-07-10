/**Disjoint set node class
 * @author AycaMericCelik
 * */
public class Node {
    int rank;
    Node parent;

    public Node(){
        this.parent = this;
        this.rank = 0;
    }
}
