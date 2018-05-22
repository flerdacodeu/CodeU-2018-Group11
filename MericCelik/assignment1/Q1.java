import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Q1 {

    /*The function checks whether two strings are anagram. It is case insensitive and it discards all
    * characters other than letters. The logic behind it is calculating character's ascii values and
    * using them as an index of arrays. It stores the frequency of letters of original string and compares
    * it with resulting string.*/

    public static boolean isAnagram(String original, String resulting){
        int original_len = original.length();
        int resulting_len = resulting.length();
        if(original_len!=resulting_len)
            return false;

        int[] original_chararray = new int[26];

        original = original.toUpperCase();
        resulting = resulting.toUpperCase();

        for(int i=0;i<original_len;i++){
            char c = original.charAt(i);
            int ascii = (int) c;
            if(ascii>=65 && ascii<=90){
                int index = (int)c - 65;
                original_chararray[index] +=1;
            }
        }

        for(int t=0;t<resulting_len;t++){
            char c = resulting.charAt(t);
            int ascii = (int) c;
            if(ascii>=65 && ascii<=90) {
                int index = (int) c - 65;
                if (original_chararray[index] == 0)
                    return false;
                original_chararray[index] -= 1;
            }
        }

        return true;
    }

    /*The function checks whether two strings are anagram. It is case sensitive and it discards all
     * characters other than letters. The logic behind it is calculating character's ascii values and
     * using them as an index of arrays. It stores the frequency of letters of original string and compares
     * it with resulting string.*/

    public static boolean isAnagram_caseSensitive(String original, String resulting){
        int original_len = original.length();
        int resulting_len = resulting.length();
        if(original_len!=resulting_len)
            return false;

        int[] original_chararray_upper = new int[26];
        int[] original_chararray_lower = new int[26];

        for(int i=0;i<original_len;i++){
            char c = original.charAt(i);
            int ascii = (int)c;
            int index;
            if(ascii>=65 && ascii<=90){
                index = (int)c - 65;
                original_chararray_upper[index] +=1;
            }
            else if(ascii>=97 && ascii <=122){
                index = (int)c - 97;
                original_chararray_lower[index] +=1;
            }

        }

        for(int t=0;t<resulting_len;t++){
            char c = resulting.charAt(t);
            int ascii = (int)c;
            int index;
            if(ascii>=65 && ascii<=90){
                index = (int)c - 65;
                if(original_chararray_upper[index]==0)
                    return false;
                original_chararray_upper[index] -=1;
            }

            else if(ascii>=97 && ascii <=122){
                index = (int)c - 97;
                if(original_chararray_lower[index]==0)
                    return false;
                original_chararray_lower[index] -=1;
            }
        }

        return true;
    }

    /*The function checks whether two strings are anagram. It works with all ascii characters
    and the user can decide if the function is case sensitive or not using the parameter "isSensitive".
    The function uses HashMap as a frequency table. It stores the frequency of letters of
    original string and compares it with resulting string.*/

    public static boolean isAnagram_fullAscii(String original, String resulting, boolean isSensitive){
        int original_len = original.length();
        int resulting_len = resulting.length();
        if(original_len!=resulting_len)
            return false;

        HashMap<Character,Integer> frequency_table = new HashMap<>();

        if(!isSensitive){
            original = original.toUpperCase();
            resulting = resulting.toUpperCase();
        }

        for(int i=0;i<original_len;i++){
            char c = original.charAt(i);
            if(!frequency_table.containsKey(c))
                frequency_table.put(c,1);
            else{
                int value = frequency_table.get(c);
                frequency_table.replace(c, value+1);
            }
        }

        for(int t=0;t<resulting_len;t++){
            char c = resulting.charAt(t);
            if(!frequency_table.containsKey(c)||frequency_table.get(c)==0)
                return false;
            int value = frequency_table.get(c);
            frequency_table.replace(c, value-1);
        }

        return true;
    }


    /*The function checks whether two sentences are anagram by inspecting words one by one.
    * It uses HashMap "lengthsandwords", where the keys are word lengths and the values are the
    * words which have that length. It builds this map with original sentence and make comparisons
    * with resulting sentence. The function calls "isAnagram_fullAscii" function. The user can decide
    * whether the function is case sensitive or not. The function works for every ascii character.*/

    public static boolean isAnagram_sentences(String original, String resulting, boolean isSensitive){
        String[] original_words = original.split(" ");
        String[] resulting_words = resulting.split(" ");
        if(original_words.length!=resulting_words.length)
            return false;

        HashMap<Integer, ArrayList<String>> lengthsandwords = new HashMap<>();
        for(String or_word : original_words) {
            int current_len = or_word.length();
            if(!lengthsandwords.containsKey(current_len))
                lengthsandwords.put(current_len, new ArrayList<>());
            lengthsandwords.get(current_len).add(or_word);
        }

        int matchcount = 0;

        for(String res_word : resulting_words){
            int current_len = res_word.length();
            if(!lengthsandwords.containsKey(current_len))
                return false;
            ArrayList<String> possiblewords = lengthsandwords.get(current_len);
            Iterator it = possiblewords.iterator();

            while(it.hasNext()){
                String wordToCompare = (String) it.next();
                if(isAnagram_fullAscii(wordToCompare, res_word, isSensitive)){
                    it.remove();
                    matchcount+=1;
                }
            }
        }

        return matchcount==resulting_words.length;

    }
    public static void main(String args[]){

        String or = "integRal/";
        String res = "trianGle)";
        String other = "Angle";
        String other2 = "tRiangle/";

        String sentence1= "Hello, world!";
        String sentence2 = "ord!lw o,llhe";
        /*System.out.println(isAnagram(or,res));
        System.out.println(isAnagram(or,other));
        System.out.println(isAnagram(or,other2));

        System.out.println(isAnagram_caseSensitive(or,res));
        System.out.println(isAnagram_caseSensitive(or,other));
        System.out.println(isAnagram_caseSensitive(or,other2));

        System.out.println(isAnagram_fullAscii(or,res, true));
        System.out.println(isAnagram_fullAscii(or,other,true));
        System.out.println(isAnagram_fullAscii(or,other2,true));*/

        System.out.println(isAnagram_sentences(sentence1, sentence2, false));
        System.out.println(isAnagram_sentences(sentence1, sentence2, true));
    }
}
