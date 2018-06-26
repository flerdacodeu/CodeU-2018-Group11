import java.util.HashSet;

/**@author AycaMericCelik
 * */

public class Dictionary implements BasicDictionary{
    private HashSet<String> words;
    private HashSet<String> prefixes;

    /**Finds all prefixes of given array's words. It gets all the substrings of a string.
     * Complexities: Best O(1) when it has one element with one character.
     *              Worst O(N*M*L), Average O(N*M*L), N is word count, M is average complexity of
     *              constructing a substring and L is the average number of word's substring.
     * @param words: set to find its element's substrings.
     * @return set of prefixes(no duplicate)
     * @throws NullPointerException when the argument is invalid.
     * */
    public HashSet<String> getPrefixes(HashSet<String> words){
        if(words == null)
            throw new NullPointerException("Words field is null!");
        HashSet<String> prefixset = new HashSet<>();
        int wordsize;
        for(String word:words){
            wordsize = word.length();
            for(int i=1; i<wordsize+1; i++){
                prefixset.add(word.substring(0,i));
            }
        }
        return prefixset;
    }

    /**It takes an String set, make all of its items lowercase, add them to the new set and return that set.
     * Complexity: O(N*M), where N is word count of set and M is average length of the strings.
     * @param set: set whose items are converted.
     * @return set of lowercase strings.
     * @throws IllegalArgumentException when the set is null.
     * */
    public HashSet<String> toLowerCase(HashSet<String> set){
        if(set==null)
            throw new IllegalArgumentException("The parameter is null!");
        HashSet<String> lowerCaseMap = new HashSet<>();
        for(String string : set ){
            lowerCaseMap.add(string.toLowerCase());
        }
        return lowerCaseMap;
    }

    public Dictionary(HashSet<String> words){
        this.words = this.toLowerCase(words);
        this.prefixes = this.getPrefixes(this.words);
    }

    /**It checks whether the dictionary contains the given word.
     * Complexity: O(1)
     * @param word: word to find
     * @return boolean indicates that dictionary contains the word or not.
     * */
    public boolean isWord(String word){
        return this.words.contains(word);
    }

    /**It checks whether the dictionary contains the given prefix.
     * Complexity: O(1)
     * @param prefix: prefix to find
     * @return boolean indicates that dictionary contains the prefix or not.
     * */
    public boolean isPrefix(String prefix){
        return this.prefixes.contains(prefix);
    }
}