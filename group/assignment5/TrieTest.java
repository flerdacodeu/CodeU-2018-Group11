import org.junit.Test;

/**
 * @author AycaMericCelik
 */
public class TrieTest {
    @Test (expected = IllegalArgumentException.class)
    public void addChildWithNullParameterShouldThrowException(){
        TrieNode parent = new TrieNode('A');
        parent.addChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addWordWithNullParameterShouldThrowException(){
        Trie trie = new Trie();
        trie.addWord(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addWordsWithNullParameterShouldThrowException(){
        Trie trie = new Trie();
        trie.addWords(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isWordsWithNullParameterShouldThrowException(){
        Trie trie = new Trie();
        trie.isWord(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void isPrefixWithNullParameterShouldThrowException(){
        Trie trie = new Trie();
        trie.isPrefix(null);
    }
}
