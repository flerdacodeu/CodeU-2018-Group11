public class Main {
    public static void main(String args[]){
        boolean[][] tiles = {{false,true,false,true},
                            {true,true,false,false},
                            {false,false,true,false},
                            {false,false,true,false}};

        System.out.println(countIslands(4, 4, tiles));
    }

    /**Returns left tiles value if there is any, false otherwise.
     * */
    private static boolean lookLeft(int r, int c, boolean[][] tiles){
        if(c-1<0)
            return false;
        return tiles[r][c-1];
    }

    /**Returns up tiles value if there is any, false otherwise.
     * */
    private static boolean lookUp(int r, int c, boolean[][] tiles){
        if(r-1<0)
            return false;
        return tiles[r-1][c];
    }

    /**Returns upleft tiles value if there is any, false otherwise.
     * */
    private static boolean lookUpLeft(int r, int c, boolean[][] tiles){
        if(r-1<0 || c-1<0)
            return false;
        return tiles[r-1][c-1];
    }

    /**Finds the island count of given map representation.
     * @param tiles = 2D boolean array, where true values represent land and false values represent water.
     * @param rowcount = map representation's row count.
     * @param columncount = map representations's column count.
     * It has very simple algorithm. It iterates over the tiles and for each tile, it looks up and left. If these values
     * are false, it means this tile is independent from previously iterated parts (which are up and left directions).
     * So it increases the island count by 1.
     * If one of the directions is true, it means it has a connection with one of the previously iterated parts. Therefore,
     * it is not an independent island. So it does not increase the island count.
     * The tricky part comes next. When both of the directions are true, it looks a new direction: upright. The purpose
     * of this is checking whether up and left tiles connected before current tile. It can happen only if the upperleft
     * tile is true. So it becomes the fourt tile of a 2x2 square island(or bigger), it does not link up and left tiles.
     * But if the upperleft tile is false, it means the algorithm did not realize the link until that tile. It increased
     * the island count falsely. So the algorithm decreases the island count by 1.
     * Complexity: O(N*M) where N is the row count and M is the column count. (Optimal solution)
     * @return island count
     * @throws IllegalArgumentException when tile parameter is null, rowcount or columncount is not equal to the actual ones.
     * */
    public static int countIslands(int rowcount, int columncount, boolean[][] tiles){
        if(tiles==null)
            throw new IllegalArgumentException("Third argument is null!");
        if(rowcount!=tiles.length)
            throw new IllegalArgumentException("First argument is not equal to row count!");
        if(columncount!=tiles[0].length)
            throw new IllegalArgumentException("Second argument is not equal to column count!");

        int islandcount = 0;
        for(int r=0;r<rowcount;r++){
            for(int c=0;c<columncount;c++){
                if(tiles[r][c] && !lookLeft(r,c,tiles) && !lookUp(r,c,tiles)) //(probably)island found.
                    islandcount+=1;
                if(tiles[r][c] && lookLeft(r,c,tiles) && lookUp(r,c,tiles) &&!lookUpLeft(r,c,tiles))
                    islandcount-=1;  //the lands are connected and become one bigger island, so reduce the island count.
            }
        }
        return islandcount;
    }
}
