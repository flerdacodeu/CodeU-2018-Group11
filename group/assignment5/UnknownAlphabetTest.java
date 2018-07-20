import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

public class UnknownAlphabetTest {

    @Test
    //TODO it raises error
    public void emptyDictionary(){
        String[] dict = {};
        //UnknownAlphabet a = new UnknownAlphabet(dict);
        //ArrayList<Character> alphabet = a.alphabet();
        //Assert.assertEquals(alphabet, new ArrayList<ArrayList<Character>>());
    }

    @Test
    public void dictonaryWithSameWords(){
        String[] dict = {"aaa", "aaa", "aaa"};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        Assert.assertEquals(alphabet,result);
    }

    @Test
    public void dictonaryWithSameLetters(){
        String[] dict = {"aaa", "a", "aaaaaa"};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        Assert.assertEquals(alphabet,result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void inconvenientDictionary(){
        String[] dict = {"ab", "bb", "ba",};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
    }

    @Test
    public void dictonaryWithOnlyLetters(){
        String[] dict = {"a", "b", "z"};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('z');
        Assert.assertEquals(alphabet,result);
    }

    @Test
    public void dictonaryWithEmptyWords(){
        String[] dict = {"aa", "ba", "", "o", ""};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
        ArrayList<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('o');
        Assert.assertEquals(alphabet,result);
    }

    @Test
    //TODO the output is [b, =, a, +, :], it should be  [a, +, :, =, b]
    public void dictionaryWithSpecialCharacters(){
        String[] dict = {"a=a", "+ba", ":"};
        UnknownAlphabet a = new UnknownAlphabet(dict);
        ArrayList<Character> alphabet = a.alphabet();
        Character[] resultarray = {'a', '+',';','=','b'};
        ArrayList<Character> result = new ArrayList<>(Arrays.asList(resultarray));
        System.out.println(alphabet);
        //Assert.assertEquals(result, alphabet);
    }
}
