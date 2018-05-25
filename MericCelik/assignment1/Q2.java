import java.util.Stack;

public class Q2 {

     /*The first solution for the problem. It uses stack to reverse the list.
     The function takes two parameter:
     The first one is linked list(from java.util) we will work on,
     The second one is the k value describing index from the last element.
     Time complexity: O(N)
     */

    private static String returnLastKthElement(LinkedList<String> givenlist, int k){
        Stack<String> ourstack = new Stack<>();
        int sizeoflist = givenlist.getSize();

        if(k>=sizeoflist){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }

        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }

        for(int i=0;i<sizeoflist;i++){
            ourstack.push(givenlist.getElement(i));
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
     * The first one is linked list we will work with,
     * The second one is the k value describing index from the last element.
     */

    private static String returnLastKthElement2(LinkedList<String> givenlist, int k){
        int sizeoflist = givenlist.getSize();

        if(k>=sizeoflist){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }

        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }

        int indexfrombeginning = sizeoflist - k - 1;
        return givenlist.getElement(indexfrombeginning);
    }

     /*The third solution for the problem. Very similar approach with the second one,
     * just the algebra is different.
     * The function takes two parameter:
     * The first one is linked list we will work with,
     * The second one is the k value describing index from the last element.
     */

    private static String returnLastKthElement3(LinkedList<String> givenlist, int k){
        int sizeoflist = givenlist.getSize();
        if(k>=sizeoflist){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }

        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }
        LinkedListNode<String> temp = givenlist.head;
        while(k<sizeoflist-1){
            temp = temp.getNext();
            k+=1;
        }
        return temp.getElement();
    }

    /*The fourth solution for the problem. The recursive version of the third solution.
     * The function takes three parameter:
     * The first one is the linked list node we will work with (we will use head node at first call),
     * The second one is the k value describing index from the last element.
     * The third one is the size of the linked list we will work with.
     */

    private static String returnLastKthElement3_recursive(LinkedListNode llNode, int k, int size){
        if(k>size){
            System.out.println("K value is bigger than size of the list!");
            return null;
        }
        else if(k<0){
            System.out.println("K value is negative!");
            return null;
        }
        else if(llNode==null)
            return null;
        else if(k==size-1)
            return (String) llNode.getElement();
        return returnLastKthElement3_recursive(llNode.getNext(),k+1,size);
    }


    public static void main(String args[]){
        LinkedList<String> ll = new LinkedList<>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.add("d");

        System.out.print("Elements in reversed order using the first function: ");
        System.out.print(returnLastKthElement(ll,0) + " ");
        System.out.print(returnLastKthElement(ll,1) + " ");
        System.out.print(returnLastKthElement(ll,2) + " ");
        System.out.println(returnLastKthElement(ll,3));
        System.out.println(returnLastKthElement(ll,5));
        System.out.println(returnLastKthElement(ll,-1)+"\n");


        System.out.print("Elements in reversed order using the second function: ");
        System.out.print(returnLastKthElement2(ll,0) + " ");
        System.out.print(returnLastKthElement2(ll,1) + " ");
        System.out.print(returnLastKthElement2(ll,2) + " ");
        System.out.println(returnLastKthElement2(ll,3));
        System.out.println(returnLastKthElement2(ll,5));
        System.out.println(returnLastKthElement2(ll,-1)+"\n");

        System.out.print("Elements in reversed order using the second function: ");
        System.out.print(returnLastKthElement3_recursive(ll.head,0, ll.size) + " ");
        System.out.print(returnLastKthElement3_recursive(ll.head,1, ll.size) + " ");
        System.out.print(returnLastKthElement3_recursive(ll.head,2, ll.size) + " ");
        System.out.println(returnLastKthElement3_recursive(ll.head,3, ll.size));
        System.out.println(returnLastKthElement3_recursive(ll.head,5,ll.size));
        System.out.println(returnLastKthElement3_recursive(ll.head,-1,ll.size)+"\n");

        System.out.print("Elements in reversed order using the second function: ");
        System.out.print(returnLastKthElement3(ll,0) + " ");
        System.out.print(returnLastKthElement3(ll,1) + " ");
        System.out.print(returnLastKthElement3(ll,2) + " ");
        System.out.println(returnLastKthElement3(ll,3));
        System.out.println(returnLastKthElement2(ll,5));
        System.out.println(returnLastKthElement2(ll,-1)+"\n");
    }



}
