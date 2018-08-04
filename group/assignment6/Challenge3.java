import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Challenge3 {
    /**
     * We use arrays to represent parking slots as this is the simplest DS and most convieniet to use and numbers denote
     * car IDs and we assume that there is no duplicate car ID e.g elements are always unique and we denote empty car space
     * slot a special number '0'.
     * The basic idea is to loop over the cars one by one and to see if it's not in the desired final place then we try to
     * look for the empty slot '0' and swap it with the car and put the desired car in the current slot. The algorithm takes
     * O(n*n) one pass for going over the cars and another pass for trying to find index of 0 but I believe can be enhanced using
     * hashmaps.
     * Reserved uptade: When the current car cannot be placed to empty slot, it iterates over other cars until it find a
     * placeable car, although it is in the right place.
     * @param source initial arrangement of cars in the parking
     * @param destination desired arrangement of cars in the parking.
     */
    public static void generateMoves(int source[], int destination[],
                                     HashMap<Integer, ArrayList<Integer>> reserved) {
        int length = source.length;
        int indexInSrc;
        int zeroIndex;
        printState(source);

        for (int i = 0; i < length; i++) {
            if (destination[i] != 0 && source[i] != destination[i]) {
                zeroIndex = findIndex(source, 0);
                if(!reserved.get(zeroIndex).contains(source[i])){
                    int y = i;
                    while (y<length-1 && !reserved.get(zeroIndex).contains(source[y]))
                        y+=1;
                    swap(source,zeroIndex, y);
                    zeroIndex = findIndex(source, 0);
                }
                swap(source,zeroIndex, i);
                indexInSrc = findIndex(source, destination[i]);
                swap(source,i, indexInSrc);
            }
        }
    }

    private static void swap(int source[],int index1, int index2) {
        int temp = source[index1];
        source[index1] = source[index2];
        source[index2] = temp;
        printState(source);
    }

    private static void printState(int array[]) {
        for (int i : array)
            System.out.print(i + " ");
        System.out.println();
    }

    private static int findIndex(int array[], int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val)
                return i;
        }
        return -1;
    }
}