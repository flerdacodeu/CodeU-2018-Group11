import java.util.ArrayList;
import java.util.HashSet;

/**Trie-based dictionary which is an alternative for normal dictionary.
 * @author AycaMericCelik
 * */

public class TrieBasedDictionary {

    private Trie trie;

    public TrieBasedDictionary(String[] words){
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
    public void addPrefixes(String[] words){
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

    /** Finds all words that can be created using grids. Calls buildAllWords method for each starting grid.
     * It creates zero-filled visited matrix for each call of buildAllWords method. It passes current grids
     * coordinates, grids matrix and the dictionary.
     * @param dict : The dictionary that contains some words and prefixes.
     * @param grids :The grids matrix to construct words from its elements.
     * @return set of possible words(no duplicates)
     * @throws NullPointerException when the parameters are invalid.
     *
     * The logic is the same with the Main's but it uses TrieBasedDictionary, so when it calls buildAllWords() method,
     * isWord() and isPrefix() method is much faster.
     * */
    public static HashSet<String> findWords(String[][] grids, TrieBasedDictionary dict){

        if(grids == null || dict ==null)
            throw new NullPointerException("Invalid parameter!");

        int rowcount = grids.length;
        int columncount = grids[0].length;

        HashSet<String> allresults = new HashSet<>();
        ArrayList<String> result;
        for(int r=0;r<rowcount;r++){
            for(int c=0;c<columncount;c++){
                int[][] visited = new int[rowcount][columncount];
                result = buildAllWords(grids,r,c,"",visited,dict, new ArrayList<>());
                allresults.addAll(result);
            }
        }
        return allresults;
    }

    /** Builds all possible words that begins with given character. It is a recursive algorithm, so possible words are
     * stored in the parameter named "result". Current word is stores in the parameter "word".
     * The method adds the character to the word and check whether the dictionary contains this word as a prefix. If not,
     * there is no need to add new letters from this word, so it returns current result array. If contains, method continues
     * and check whether the dictionary contains this word as a complete word. If not, it does not add it to the result array.
     * After that, it check all neighbour grids and call the same function. During this process, it
     * updates the visited array for each possible words. A word cannot use the same grid twice, so it keeps track of it.
     * Complexities: Best Case: O(M*N) when the dictionary has none of the grids as a prefix.
     *              Worst Case: O((M*N)^2) when the dictionary has all the words can be constructed with grids.
     *              Average Case: O((M*N)^2), M and N are row and column counts.
     * @param grids : The grids matrix to construct words from its elements.
     * @param dict : The dictionary that contains some words and prefixes.
     * @param r : current row coordinate/number
     * @param c: current column coordinate/number
     * @param word : current word it is constructing
     * @param result : possible words it finds so far
     * @param visited : binary array to keep track what grids are visited while constructing current word.
     * @return list of possible words (no duplicates)
     *
     * The logic is the same with the Main's but it uses TrieBasedDictionary, so isWord() and isPrefix() method is much
     * faster.
     * */
    public static ArrayList<String> buildAllWords(String[][] grids, int r, int c, String word, int[][] visited,
                                           TrieBasedDictionary dict,ArrayList<String> result){

        word += grids[r][c];
        int rowcount = grids.length;
        int columncount = grids[0].length;

        if(!dict.isPrefix(word))
            return result;
        visited[r][c] = 1;

        if(dict.isWord(word)&&!result.contains(word)){
            result.add(word);
        }

        if(c-1>-1 && visited[r][c-1]==0 && grids[r][c-1]!=null)
            result = buildAllWords(grids, r, c-1, word, visited,dict,  result);  //left = c-1;
        if(c+1<columncount && visited[r][c+1]==0 && grids[r][c+1]!=null)
            result = buildAllWords(grids, r, c+1, word, visited,dict, result);   //right = c+1;
        if(r-1>-1 && visited[r-1][c]==0 && grids[r-1][c]!=null)
            result = buildAllWords(grids, r-1, c, word, visited,dict, result);   //up = r-1;
        if(r+1<rowcount && visited[r+1][c]==0 && grids[r+1][c]!=null)
            result  = buildAllWords(grids, r+1, c, word, visited,dict, result);  //down = r+1;
        if(c-1>-1 && r-1>-1 && visited[r-1][c-1]==0 && grids[r-1][c-1]!=null){
            result = buildAllWords(grids, r-1, c-1, word, visited,dict, result); //upleft = r-1, c-1

        }
        if(c-1>-1 && r+1<rowcount && visited[r+1][c-1]==0 && grids[r+1][c-1]!=null){
            result = buildAllWords(grids, r+1, c-1, word, visited,dict, result); //downleft = r+1, c-1
        }
        if(c+1<columncount && r+1<rowcount && visited[r+1][c+1]==0 && grids[r+1][c+1]!=null){
            result = buildAllWords(grids, r+1, c+1, word, visited,dict, result); //downright = r+1, c+1
        }
        if(c+1<columncount && r-1>-1 && visited[r-1][c+1]==0 && grids[r-1][c+1]!=null){
            result = buildAllWords(grids, r-1, c+1, word, visited,dict, result); //upright = r-1,c+1
        }
        visited[r][c] = 0;
        return result;
    }
}
