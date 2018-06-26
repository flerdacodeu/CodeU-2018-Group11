/**@author AycaMericCelik
 * */
public class TrieNode {
    private char character;
    private TrieNode[] children;
    private boolean isEndOfTheWord;
    private boolean isLeaf;
    private boolean isPrefix;

    public boolean isEndOfTheWord() {
        return isEndOfTheWord;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public char getCharacter() {
        return character;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public boolean isPrefix() {
        return isPrefix;
    }

    public TrieNode(){
        this.children = new TrieNode[26];
        this.isLeaf = true;
        this.isEndOfTheWord = false;
    }

    public TrieNode(char character){
        this();
        this.character = character;
    }

    /**Adds new word/prefix to the trie recursively.
     * Complexity: Best Case O(1) when the length of the word is one.
     *             Worst Case O(N), Average Case O(N), where N is the length of the word.
     * @param word: word to be added
     * @param isPrefix: is the word prefix or not?
     * */
    public void addWord(String word, boolean isPrefix) {
        this.isLeaf = false;

        int charPos = word.charAt(0) - 'a';

        if (children[charPos] == null)
            children[charPos] = new TrieNode(word.charAt(0));

        if (word.length() > 1)
            children[charPos].addWord(word.substring(1), isPrefix);

        else{
            if(!isPrefix)
                children[charPos].isEndOfTheWord = true;
            else
                children[charPos].isPrefix = true;
        }
    }

    /**Gets the node with given character.
     * @param c: character to search
     * @return the node with that character.
     * */
    public TrieNode getNode(char c) {
        if(c=='\u0000')
            return children[Character.toLowerCase(c)];
        return children[Character.toLowerCase(c) - 'a'];
    }
}