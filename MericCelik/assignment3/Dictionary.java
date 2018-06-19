import java.util.HashSet;

/**@author AycaMericCelik
 * */

public class Dictionary {
    private String[] words;
    private String[] prefixes;

    public Dictionary(String[] words, String[] prefixes){
        this.words = words;
        this.prefixes = prefixes;
    }

    /**Finds all prefixes of given array's words. It gets all the substrings of a string.
     * Complexities: Best O(1) when it has one element with one character.
     *              Worst O(N*M*L), Average O(N*M*L), N is word count, M is average complexity of
     *              constructing a substring and L is the average number of word's substring.
     * @param words: array to find its element's substrings.
     * @return set of prefixes(no duplicate)
     * @throws NullPointerException when the argument is invalid.
     * */
    public String[] getPrefixes(String[] words){
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
        return prefixset.toArray(new String[prefixset.size()]);
    }

    public Dictionary(String[] words){
        this.words = words;
        this.prefixes = this.getPrefixes(words);
    }

    /**It checks whether given array contains the given element.
     * Complexities: Best Case: O(1) when it finds element at the first index.
     * Worst Case: O(N) when it could not find the item or found it at the last index.
     * Average Case: O(N), N is the array size.
     * @param array: array that search is done
     * @param element: element to find
     * @throws NullPointerException when the arguments are invalid
     * @return boolean indicates that array contains the element or not.
     * */
    private boolean contains(String[] array, String element){
        if(array==null || element==null)
            throw new NullPointerException("Arguments are invalid!");
        element = element.toLowerCase();
        int arraysize = array.length;
        for(int i=0;i<arraysize;i++){
            if(array[i].toLowerCase().equals(element))
                return true;
        }
        return false;
    }

    /**It checks whether the dictionary contains the given word.
     * Complexities depend on contains() method.
     * @param word: word to find
     * @return boolean indicates that dictionary contains the word or not.
     * */
    public boolean isWord(String word){
        return this.contains(words, word);
    }

    /**It checks whether the dictionary contains the given word.
     * Complexities depend on contains() method.
     * @param prefix: prefix to find
     * @return boolean indicates that dictionary contains the prefix or not.
     * */
    public boolean isPrefix(String prefix){
        return this.contains(prefixes, prefix);
    }
}
