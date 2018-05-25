import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Q1 {

    /*The function checks whether two strings are anagram. It discards all characters other
    * than letters. the user can decide if the function is case sensitive or not using the parameter "isSensitive".
    * The logic behind it is calculating character's ascii values and
    * using them as an index of arrays. It stores the frequency of letters of original string and compares
    * it with resulting string.*/

    private static boolean isAnagram(String original, String resulting, boolean isSensitive){

        original = original.replaceAll("[^A-Za-z]+", "");
        resulting = resulting.replaceAll("[^A-Za-z]+", "");

        if(!isSensitive){
            original = original.toUpperCase();
            resulting = resulting.toUpperCase();
        }

        int original_len = original.length();
        int resulting_len = resulting.length();
        if(original_len!=resulting_len)
            return false;

        int[] original_chararray_upper = new int[26];
        int[] original_chararray_lower = new int[26];

        for(int i=0;i<original_len;i++){
            char c = original.charAt(i);
            int index;
            if(c>='A' && c<='Z'){
                index = (int)c - 'A';
                original_chararray_upper[index] +=1;
            }
            if(c>='a' && c<='z'){
                index = (int)c - 'a';
                original_chararray_lower[index] +=1;
            }
        }

        for(int t=0;t<resulting_len;t++){
            char c = resulting.charAt(t);
            int index;
            if(c>='A' && c<='Z'){
                index = (int)c - 'A';
                if(original_chararray_upper[index]==0)
                    return false;
                original_chararray_upper[index] -=1;
            }

            if(c>='a' && c<='z'){
                index = (int)c - 'a';
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

    private static boolean isAnagram_fullAscii(String original, String resulting, boolean isSensitive){
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

    private static boolean isAnagram_sentences(String original, String resulting, boolean isSensitive){
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

        System.out.println("According to isAnagram function(Case Insensitive): ");
        System.out.println("Are \"" + or + "\" and \"" + res + "\" anagram?: " +isAnagram(or,res, false));
        System.out.println("Are \"" + or + "\" and \"" + other + "\" anagram?: " +isAnagram(or,other, false));
        System.out.println("Are \"" + or + "\" and \"" + other2 + "\" anagram?: " +isAnagram(or,other2, false));
        System.out.println("Are \"" + or + "\" and \"" + "integral****" + "\" anagram?: " +isAnagram(or,"integral****",false));


        System.out.println("\nAccording to isAnagram function(Case Sensitive): ");
        System.out.println("Are \"" + or + "\" and \"" + res + "\" anagram?: " +isAnagram(or,res,true));
        System.out.println("Are \"" + or + "\" and \"" + other + "\" anagram?: " +isAnagram(or,other,true));
        System.out.println("Are \"" + or + "\" and \"" + other2 + "\" anagram?: " +isAnagram(or,other2,true));
        System.out.println("Are \"" + or + "\" and \"" + "integral****" + "\" anagram?: " +isAnagram(or,"integral****",true));

        System.out.println("\nAccording to isAnagram_fullAscii function: ");
        System.out.println("Are \"" + or + "\" and \"" + res + "\" anagram?: " +isAnagram_fullAscii(or,res, true));
        System.out.println("Are \"" + or + "\" and \"" + other + "\" anagram?: " +isAnagram_fullAscii(or,other,true));
        System.out.println("Are \"" + or + "\" and \"" + other2 + "\" anagram?: " +isAnagram_fullAscii(or,other2,true));

        System.out.println("\nAccording to isAnagram_sentences function: ");

        System.out.println("Are \"" + sentence1 + "\" and \"" + sentence2 + "\" anagram?(case insensitive): " +isAnagram_sentences(sentence1, sentence2, false));
        System.out.println("Are \"" + sentence1 + "\" and \"" + sentence2 + "\" anagram?(case sensitive): " +isAnagram_sentences(sentence1, sentence2, true));
    }
}
