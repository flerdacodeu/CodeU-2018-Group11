public class Main {

    /**Does Depth-First Search and unites current node and neighbour node into same set.
     * @param nodetiles 2D node array to iterate.
     * @param r row number
     * @param c column number
     * @param visited 2D integer array to keep track visited nodes.
     * @param parentnode previously passed node. Neighbor nodes are added to its set.
     * */
    private static void NodeDFS(Node[][] nodetiles, int r, int c, int[][] visited, Node parentnode){
        visited[r][c] = 1;
        Node currentnode = nodetiles[r][c];
        DisjointSet.union(parentnode,currentnode);

        //up
        if(r-1>=0 && nodetiles[r-1][c]!=null && visited[r-1][c]==0)
            NodeDFS(nodetiles, r-1, c, visited,parentnode);
        //down
        if(r+1<nodetiles.length && nodetiles[r+1][c]!=null && visited[r+1][c]==0)
            NodeDFS(nodetiles, r+1, c, visited,parentnode);
        //left
        if(c-1>=0 && nodetiles[r][c-1]!=null && visited[r][c-1]==0)
            NodeDFS(nodetiles, r, c-1, visited,parentnode);
        //right
        if(c+1<nodetiles[0].length && nodetiles[r][c+1]!=null && visited[r][c+1]==0)
            NodeDFS(nodetiles, r, c+1, visited,parentnode);
    }

    /**Finds the island count of given map representation.
     * @param tiles = 2D boolean array, where true values represent land and false values represent water.
     * Firstly, it creates 2D Node array and adds land tiles as nodes. The index of water tiles remains null. To make
     * this, it iterates tile array once. After that, it creates 2D integer array to keep track of visited nodes,
     * and calls NodeDFS function for each tile, if tiles satisfy the properties above:
     * - If the tile is not visited before.
     * - If the tile is not a member of a disjoint set (its parent is itself).
     * In each call, it increases the islandCount.
     * Complexity: O(N*M) where N is the row count and M is the column count.
     * @return island count
     * @throws IllegalArgumentException when tile parameter is null
     * */
    public static int countIslands(boolean [][] tiles){
        if(tiles==null)
            throw new IllegalArgumentException("Argument is null!");

        int rowCount = tiles.length;
        int columnCount = tiles[0].length;
        Node[][] nodeTiles = new Node[rowCount][columnCount];

        for(int r=0;r<rowCount;r++) {
            for (int c = 0; c < columnCount; c++) {
                if(tiles[r][c]){
                    nodeTiles[r][c] = new Node();
                }
            }
        }

        int[][] visited = new int[rowCount][columnCount];
        int islandCount = 0;
        for(int r=0;r<rowCount;r++) {
            for (int c = 0; c < columnCount; c++) {
                Node currentNode = nodeTiles[r][c];
                if(currentNode!=null && currentNode.parent.equals(currentNode) && visited[r][c]==0){
                    NodeDFS(nodeTiles, r, c, visited, currentNode);
                    islandCount+=1;
                }
            }
        }
        return islandCount;
    }
}
