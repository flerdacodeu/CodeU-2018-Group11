import java.util.HashSet;

/**Trie-based dictionary which is an alternative for normal dictionary.
 * @author AycaMericCelik
 * */

public class TrieBasedDictionary implements BasicDictionary{

    private Trie trie;

    public TrieBasedDictionary(HashSet<String> words){
        this.trie = new Trie(words);
        this.addPrefixes(words);
    }

    public boolean isPrefix(String prefix){
        return this.trie.find(prefix,true);
    }

    public boolean isWord(String word){
        return this.trie.find(word,false);
    }

    public void add(String word){
        this.trie.addWord(word);
    }

    /**Add all prefixes of given array's words to the dictionary. It gets all the substrings of a string.
     * Complexities: Best O(1) when it has one element with one character.
     *              Worst O(N*M*L), Average O(N*M*L), N is word count, M is average complexity of
     *              constructing a substring and L is the average number of word's substring.
     * @param words: array to add its element's substrings.
     * @throws NullPointerException when the argument is invalid.
     * */
    public void addPrefixes(HashSet<String> words){
        if(words == null)
            throw new NullPointerException("Words field is null!");
        int wordsize;
        for(String word:words){
            wordsize = word.length();
            for(int i=1; i<wordsize+1; i++){
                this.trie.addPrefix(word.substring(0,i));
            }
        }
    }
}