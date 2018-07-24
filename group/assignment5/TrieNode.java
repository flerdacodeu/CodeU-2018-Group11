
/**
 * Implementation of a TrieNode which extends the parent class TreeNode<Character>. 
 * It contains a isEndOfWord boolean attribute which is true if the node corresponds to the end of 
 * a Trie word.
 * @author Sephora-M
 *
 */
public class TrieNode extends DirectedGraphNode<Character> {

    public TrieNode(Character key) {
        super(key);
    }

    /**
     * Adds a TrieNode with specific key to the current node's list of children.
     * If the current node already contains a child with key childKey, then it will only
     * updates its isEndOfWord attribute accordingly.
     * @param childKey
     * @return
     */
    public TrieNode addChild(Character childKey) {
        TrieNode foundChild = hasChild(childKey);
        if (foundChild != null) {
            return foundChild;
        } else {
            TrieNode child = new TrieNode(childKey);
            child.addParent(this);
            this.children.add(child);
            return child;
        }
    }

    private TrieNode hasChild(Character childKey) {
        for (DirectedGraphNode<Character> child : this.children) {
            if (child.getKey() == childKey)
                return (TrieNode) child;
        }
        return null;
    }
}
