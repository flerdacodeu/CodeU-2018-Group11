import java.util.HashSet;

/**@author AycaMericCelik
 * */
public class Trie {
    private TrieNode root;

    public Trie(HashSet<String> words){
        if(words==null)
            throw new IllegalArgumentException("Argument is null!");
        this.root = new TrieNode();
        for(String word:words)
            this.addWord(word);
    }

    public void addWord(String word) {
        root.addWord(word.toLowerCase(),false);
    }

    public void addPrefix(String word) {
        root.addWord(word.toLowerCase(),true);
    }

    /**Finds whether the trie contains the word or prefix. It uses the nodes isEndOfTheWord and isPrefix fields.
     * Complexity: Best Case: O(1) when the word/prefix is not found at its first character.
     *             Worst Case: O(N) when the word/prefix is found.
     *             Average Case: O(N), where N is the length of the word.
     * The complexity is much better compared to Dictionary class when the dictinary is huge.
     * @param word: the word it searchs
     * @param isPrefix: is the word it searches prefix or not?
     * @return boolean that indicates whether the word/prefix is found or not.
     * */
    public boolean find(String word, boolean isPrefix) {
        if(word==null)
            return false;

        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getNode(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        if(!isPrefix)
            return current.isEndOfTheWord();
        return current.isPrefix();
    }
}