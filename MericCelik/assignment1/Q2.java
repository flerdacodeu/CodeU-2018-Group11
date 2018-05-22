import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class Q2 {

    /*The first solution for the problem. It uses stack to reverse the list. A recursive algorithm can
    * be written as well.
    *
    * The function takes two parameter:
    * The first one is linked list(from java.util) we will work on,
    * The second one is the k value describing index from the last element.
    */
    public static Object returnLastKthElement(LinkedList<Object> givenlist, int k){
        Stack ourstack = new Stack();
        int sizeoflist = givenlist.size();
        Scanner reader = new Scanner(System.in);

        if(k>=sizeoflist){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }

        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }

        //In the block below, the function asks for new input from user when k is inappropriate.
        //It prevents function from returning null.

        /*while(k>=sizeoflist) {
            System.out.println("K value is bigger than size of the list! " +
                    "Please enter a smaller value: ");
            String ob = reader.next();
            try{
                k = Integer.parseInt(ob);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter an positive integer value!");
                continue;
            }
        }

        while(k<0){
            System.out.println("K value is smaller than zero! Please enter a positive integer value!");
            String ob = reader.next();
            try{
                k = Integer.parseInt(ob);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter an positive integer value!");
                continue;
            }
        }*/

        for(int i=0;i<sizeoflist;i++){
            ourstack.push(givenlist.get(i));
        }

        int index = 0;
        while(index<k){
            ourstack.pop();
            index+=1;
        }
        return ourstack.pop();
    }


    /*The second solution for the problem.It calculates the index from the first element
     using k value.
     *
     * The function takes two parameter:
     * The first one is linked list(from java.util) we will work on,
     * The second one is the k value describing index from the last element.
     */

    public static Object returnLastKthElement2(LinkedList<Object> givenlist, int k){
        int sizeoflist = givenlist.size();
        Scanner reader = new Scanner(System.in);

        if(k>=sizeoflist){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }

        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }

        //In the block below, the function asks for new input from user when k is inappropriate.
        //It prevents function from returning null.

        /*while(k>=sizeoflist) {
            System.out.println("K value is bigger than size of the list! " +
                    "Please enter a smaller value: ");
            String ob = reader.next();
            try{
                k = Integer.parseInt(ob);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter an positive integer value!");
                continue;
            }
        }

        while(k<0){
            System.out.println("K value is smaller than zero! Please enter a positive integer value!");
            String ob = reader.next();
            try{
                k = Integer.parseInt(ob);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter an positive integer value!");
                continue;
            }
        }*/

        int indexfrombeginning = sizeoflist - k - 1;
        return givenlist.get(indexfrombeginning);
    }

    public static void main(String args[]){
        LinkedList ll = new LinkedList();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.add("d");

        System.out.print("Elements in reversed order using the first function: ");
        System.out.print(returnLastKthElement(ll,0) + " ");
        System.out.print(returnLastKthElement(ll,1) + " ");
        System.out.print(returnLastKthElement(ll,2) + " ");
        System.out.println(returnLastKthElement(ll,3));

        System.out.print("Elements in reversed order using the second function: ");
        System.out.print(returnLastKthElement2(ll,0) + " ");
        System.out.print(returnLastKthElement2(ll,1) + " ");
        System.out.print(returnLastKthElement2(ll,2) + " ");
        System.out.println(returnLastKthElement2(ll,3));


    }



}
