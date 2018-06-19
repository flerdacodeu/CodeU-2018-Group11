import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.HashSet;

/**@author AycaMericCelik
 * */
public class MainTest {

    @Test (expected = NullPointerException.class)
    public void NullParametersShouldThrowExceptions(){
        Main.findWords(null, null);
    }

    @Test (expected = NullPointerException.class)
    public void NullGridShouldThrowExceptions(){
        String[] words = {"a"};
        Dictionary dict = new Dictionary(words);
        Main.findWords(null, dict);
    }

    @Test (expected = NullPointerException.class)
    public void NullDictionaryShouldThrowExceptions(){
        String[][] grids = {"aaa".split("")};
        Main.findWords(grids, null);
    }

    @Test
    public void test_nonDefinedGrids(){
        String[][] grids = new String[5][5];
        String[] words = {"a"};
        Dictionary dict = new Dictionary(words);
        Assert.assertEquals(new HashSet<>(),Main.findWords(grids,dict));
    }

    @Test (expected = NullPointerException.class)
    public void test_nonDefinedDictionary(){
        String[][] grids = {"aaa".split("")};
        Dictionary dict = new Dictionary(null);
        Main.findWords(grids,dict);
    }

    @Test
    public void test_completeexample(){
        String[][] grids = {"aar".split(""), "tcd".split("")};
        String[] words = {"CAR", "CARD", "CART", "CAT"};
        Dictionary dict = new Dictionary(words);

        String[] expectedOutputList = {"car", "card", "cat"};
        HashSet<String> expectedOutput = new HashSet<>(Arrays.asList(expectedOutputList));

        Assert.assertEquals(expectedOutput,Main.findWords(grids, dict));
    }

    @Test
    public void test_completeexamplewithtriebaseddictionary(){
        String[][] grids = {"aar".split(""), "tcd".split("")};
        String[] words = {"CAR", "CARD", "CART", "CAT"};
        TrieBasedDictionary dict = new TrieBasedDictionary(words);

        String[] expectedOutputList = {"car", "card", "cat"};
        HashSet<String> expectedOutput = new HashSet<>(Arrays.asList(expectedOutputList));

        Assert.assertEquals(expectedOutput,TrieBasedDictionary.findWords(grids, dict));
    }



}
