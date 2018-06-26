import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.HashSet;

/**@author AycaMericCelik
 * */
public class MainTest {
    static char[][] grids = {"aar".toCharArray(), "tcd".toCharArray()};
    static String[] wordslist = {"CAR", "CARD", "CART", "CAT"};
    static HashSet<String> words = new HashSet<>(Arrays.asList(wordslist));
    static TrieBasedDictionary triedict = new TrieBasedDictionary(words);
    static Dictionary dict = new Dictionary(words);
    static String[] expectedOutputList = {"car", "card", "cat"};
    static HashSet<String> expectedOutput = new HashSet<>(Arrays.asList(expectedOutputList));

    static TrieBasedDictionary triedict3 = new TrieBasedDictionary(new HashSet<>());
    static Dictionary dict3 = new Dictionary(new HashSet<>());
    static HashSet<String> expectedOutput3 = new HashSet<>();


    @Test (expected = IllegalArgumentException.class)
    public void NullParametersShouldThrowExceptions(){
        Main.findWords(null, null,triedict,false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullParametersShouldThrowExceptions_TrieBased(){
        Main.findWords(null, dict,null,true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullGridShouldThrowExceptions(){
        Main.findWords(null, dict, triedict, false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullGridShouldThrowExceptions_TrieBased(){
        Main.findWords(null, dict, triedict, true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullDictionaryShouldThrowExceptions(){
        Main.findWords(grids, null,triedict,false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullDictionaryShouldThrowExceptions_TrieBased(){
        Main.findWords(grids, dict,null,true);
    }

    @Test
    public void test_nonDefinedGrids(){
        char[][] grids = new char[5][5];
        Assert.assertEquals(new HashSet<>(),Main.findWords(grids,dict,null,false));
    }

    @Test
    public void test_nonDefinedGrids_TrieBased(){
        char[][] grids = new char[5][5];
        Assert.assertEquals(new HashSet<>(),Main.findWords(grids,null,triedict,true));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_nonDefinedDictionary(){
        Dictionary dict = new Dictionary(null);
        Main.findWords(grids,dict,null,false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_nonDefinedDictionary_TrieBased(){
        TrieBasedDictionary triedict = new TrieBasedDictionary(null);
        Main.findWords(grids,null,triedict,true);
    }

    @Test
    public void test_completeexample(){
        Assert.assertEquals(expectedOutput,Main.findWords(grids, dict,null,false));
    }

    @Test
    public void test_completeexamplewithtriebaseddictionary(){
        Assert.assertEquals(expectedOutput,Main.findWords(grids, null,triedict,true));
    }

    static char[][] grids2 = {"aaa".toCharArray(), "aaa".toCharArray()};
    static String[] wordslist2 = {"AAA", "A", "AA", "AAAAAAAAA"};
    static HashSet<String> words2 = new HashSet<>(Arrays.asList(wordslist2));
    static TrieBasedDictionary triedict2 = new TrieBasedDictionary(words2);
    static Dictionary dict2 = new Dictionary(words2);
    static String[] expectedOutputList2 = {"a", "aaa", "aa"};
    static HashSet<String> expectedOutput2 = new HashSet<>(Arrays.asList(expectedOutputList2));

    @Test
    public void isPrefixTest(){
        Assert.assertFalse(dict2.isPrefix("b"));
        Assert.assertFalse(dict2.isPrefix(""));
        Assert.assertFalse(dict2.isPrefix("aaaaaaaaaaaaa"));
        Assert.assertFalse(dict2.isPrefix(null));

        Assert.assertTrue(dict2.isPrefix("a"));
        Assert.assertTrue(dict2.isPrefix("aaaa"));
    }

    @Test
    public void isPrefixTest_TrieBased(){
        Assert.assertFalse(triedict2.isPrefix("b"));
        Assert.assertFalse(triedict2.isPrefix(""));
        Assert.assertFalse(triedict2.isPrefix("aaaaaaaaaaaaa"));
        Assert.assertFalse(triedict2.isPrefix(null));

        Assert.assertTrue(triedict2.isPrefix("a"));
        Assert.assertTrue(triedict2.isPrefix("aaaa"));
    }

    @Test
    public void isWordTest(){
        Assert.assertFalse(dict2.isWord("dhsj"));
        Assert.assertFalse(dict2.isWord(""));
        Assert.assertFalse(dict2.isWord("aaaaaaaaaaaaa"));
        Assert.assertFalse(dict2.isWord(null));

        Assert.assertTrue(dict2.isWord("a"));
        Assert.assertTrue(dict2.isWord("aaaaaaaaa"));
    }

    @Test
    public void isWordTest_TrieBased(){
        Assert.assertFalse(triedict2.isWord("dhsj"));
        Assert.assertFalse(triedict2.isWord(""));
        Assert.assertFalse(triedict2.isWord("aaaaaaaaaaaaa"));
        Assert.assertFalse(triedict2.isWord(null));

        Assert.assertTrue(triedict2.isWord("a"));
        Assert.assertTrue(triedict2.isWord("aaaaaaaaa"));
    }


    @Test
    public void test_completeexample2(){
        Assert.assertEquals(expectedOutput2,Main.findWords(grids2, dict2,null,false));
    }

    @Test
    public void test_completeexample2_TrieBased(){
        Assert.assertEquals(expectedOutput2,Main.findWords(grids2, null, triedict2,true));
    }

    @Test
    public void test_completeexample3(){
        Assert.assertEquals(expectedOutput3,Main.findWords(grids, dict3,null,false));
    }

    @Test
    public void test_completeexample3_TrieBased(){
        Assert.assertEquals(expectedOutput3,Main.findWords(grids, null, triedict3,true));
    }
}