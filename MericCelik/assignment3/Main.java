import java.util.Arrays;
import java.util.HashSet;

/**Solution class that uses Dictionary class.
 * @author AycaMericCelik*/

public class Main {
    public static void main(String[] args){
        char[][] grids = {"aar".toCharArray(), "tcd".toCharArray()};
        String[] wordslist = {"CAR", "CARD", "CART", "CAT"};
        HashSet<String> words = new HashSet<>(Arrays.asList(wordslist));

        Dictionary dict = new Dictionary(words);
        TrieBasedDictionary dict2  = new TrieBasedDictionary(words);

        System.out.println(Main.findWords(grids, dict, null, false));
        System.out.println(Main.findWords(grids,null,dict2,true));
    }

    /** Finds all words that can be created using grids. Calls buildAllWords method for each starting grid.
     * It creates zero-filled visited matrix for each call of buildAllWords method. It passes current grids
     * coordinates, grids matrix and the dictionary.
     * @param dict : The dictionary that contains some words and prefixes.
     * @param grids :The grids matrix to construct words from its elements.
     * @return set of possible words(no duplicates)
     * @throws IllegalArgumentException when the parameters are null.
     * */
    public static HashSet<String> findWords(char[][] grids, Dictionary dict, TrieBasedDictionary triedict,
                                            boolean isTrieBased){

        if(grids == null)
            throw new IllegalArgumentException("First parameter is null!");
        if(dict ==null && !isTrieBased)
            throw new IllegalArgumentException("Second parameter is null!");
        if(triedict ==null && isTrieBased)
            throw new IllegalArgumentException("Third parameter is null!");

        int rowcount = grids.length;
        int columncount = grids[0].length;

        HashSet<String> allresults = new HashSet<>();
        for(int r=0;r<rowcount;r++){
            for(int c=0;c<columncount;c++){
                int[][] visited = new int[rowcount][columncount];
                allresults.addAll(buildAllWords(grids,r,c,"",visited,dict,triedict, new HashSet<String>(),
                        isTrieBased));
            }
        }
        return allresults;
    }

    /** Builds all possible words that begins with given character. It is a recursive algorithm, so possible words are
     * stored in the parameter named "result". Current word is stores in the parameter "word".
     * At first, the function decides which type of dictionary to use by looking the parameter named "isTrieBased". If true,
     * it works with TrieBasedDictionary, Dictionary otherwise.
     * The method adds the character to the word and check whether the dictionary contains this word as a prefix. If not,
     * there is no need to add new letters from this word, so it returns current result set. If contains, method continues
     * and check whether the dictionary contains this word as a complete word. If not, it does not add it to the result set.
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
     * @return set of possible words (no duplicates)
     * */
    public static HashSet<String> buildAllWords(char[][] grids, int r, int c, String word, int[][] visited, Dictionary dict,
            TrieBasedDictionary triedict, HashSet<String> result, boolean isTrieBased){

        word += grids[r][c];
        int rowcount = grids.length;
        int columncount = grids[0].length;

        if(isTrieBased){
            if(!triedict.isPrefix(word))
                return result;
            visited[r][c] = 1;

            if(triedict.isWord(word)){
                result.add(word);
            }
        }

        else{
            if(!dict.isPrefix(word))
                return result;
            visited[r][c] = 1;

            if(dict.isWord(word)){
                result.add(word);
            }
        }

        if(c-1>-1 && visited[r][c-1]==0 && grids[r][c-1]!='\u0000')
            buildAllWords(grids, r, c-1, word, visited, dict,triedict, result,isTrieBased);  //left = c-1;
        if(c+1<columncount && visited[r][c+1]==0 && grids[r][c+1]!='\u0000')
            buildAllWords(grids, r, c+1, word, visited, dict,triedict, result,isTrieBased);   //right = c+1;
        if(r-1>-1 && visited[r-1][c]==0 && grids[r-1][c]!='\u0000')
            buildAllWords(grids, r-1, c, word, visited, dict,triedict, result,isTrieBased);   //up = r-1;
        if(r+1<rowcount && visited[r+1][c]==0 && grids[r+1][c]!='\u0000')
            result  = buildAllWords(grids, r+1, c, word, visited, dict,triedict, result,isTrieBased);  //down = r+1;
        if(c-1>-1 && r-1>-1 && visited[r-1][c-1]==0 && grids[r-1][c-1]!='\u0000'){
            buildAllWords(grids, r-1, c-1, word, visited, dict,triedict, result,isTrieBased); //upleft = r-1, c-1
        }
        if(c-1>-1 && r+1<rowcount && visited[r+1][c-1]==0 && grids[r+1][c-1]!='\u0000'){
            buildAllWords(grids, r+1, c-1, word, visited, dict,triedict, result,isTrieBased); //downleft = r+1, c-1
        }
        if(c+1<columncount && r+1<rowcount && visited[r+1][c+1]==0 && grids[r+1][c+1]!='\u0000'){
            buildAllWords(grids, r+1, c+1, word, visited, dict,triedict, result,isTrieBased); //downright = r+1, c+1
        }
        if(c+1<columncount && r-1>-1 && visited[r-1][c+1]==0 && grids[r-1][c+1]!='\u0000'){
            buildAllWords(grids, r-1, c+1, word, visited, dict,triedict, result,isTrieBased); //upright = r-1,c+1
        }
        visited[r][c] = 0;
        return result;
    }
}