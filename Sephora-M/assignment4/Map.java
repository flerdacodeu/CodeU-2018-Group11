package assignment4;

import java.awt.Point;
import java.util.ArrayList;


/**Grid of booleans representing a map. True elements in the grid represent land whereas false tiles represent water.  
 * 
 * @author Sephora-M
 */
public class Map {
	private boolean[][] grid;
	private final int numRows;
	private final int numCols;
	
	/**
	 * This constructor takes a map of boolean representing the final map.
	 * @param map, 2d array of boolean, represent the final map
	 */
	public Map(boolean[][] map) {
		if (map == null || map[0].length == 0)
			throw new IllegalArgumentException("Cannot instatiate an empty or a null grid.");
		this.grid = map;
		numCols = map.length;
		numRows = map[0].length;
	}
	
	/**
	 * This constructor takes the size of the map and the locations of land tiles.
	 * @param numRows int, the width of the map
	 * @param numCols int, the height of the map
	 */
	public Map(int numRows, int numCols, ArrayList<Point> landTiles){
		boolean[][] map = new boolean[numCols][numRows];
		this.grid = map;
		this.numCols = numCols;
		this.numRows = numRows;
		setLand(landTiles);
	}

	/**
	 * Count the number of island in the map.
	 * @return a int, number of island found
	 */
	public int countIslands(){
		int numIslands = 0;
		boolean[][] visited = new boolean[numCols][numRows];
		
		for(int x = 0; x < numRows; x++) {
			for(int y = 0; y < numCols; y++) {
				if (!visited[y][x] && grid[y][x]){
					visited[y][x] = true;
					numIslands++;
					
					Point firstTileCoord = new Point(x,y);
					
					markIsland(firstTileCoord, visited);
				}
			}
		}
		
		return numIslands;
	}
	
    private void markIsland(Point startingTile, boolean[][] visited) {
    	
    	ArrayList<Point> neighbors = findNeighbors(startingTile, visited);
    	
    	for (Point n : neighbors) {
    		if (getTile(n)){
    			visited[n.y][n.x] = true;
    			markIsland(n, visited);
    		}
    	}	
	}
    
    private boolean getTile(Point p) {
    	return grid[p.y][p.x];
    }
     
	private ArrayList<Point> findNeighbors(Point p, boolean[][] depthMap){
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				Point neighbor = new Point(p.x + dx, p.y + dy);
				boolean XisOutOfBounds = (neighbor.x < 0 || neighbor.x >= numRows) ;
				boolean YisOutOfBounds = (neighbor.y < 0 || neighbor.y >= numCols); 
				boolean isStartingPoint = (dx == 0 && dy ==0);
				boolean isDiagonalNeighbor = (Math.abs(dy) + Math.abs(dx)) == 2;
				
				if (!XisOutOfBounds && !YisOutOfBounds && !isStartingPoint && !isDiagonalNeighbor) {
					boolean visited = (depthMap[neighbor.y][neighbor.x]);
					if (!visited)
						neighbors.add(neighbor);
				}
			}
		}
		return neighbors;
	}
	
	
	private void setLand(ArrayList<Point> landTiles){
		for(Point tile : landTiles){
			grid[tile.y][tile.x] = true;
		}
	}
	
}
