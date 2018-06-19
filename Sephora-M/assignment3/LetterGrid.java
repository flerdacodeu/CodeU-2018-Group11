package assignment3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**TODO class description
 * 
 * @author Sephora-M
 *
 *Notes: 
 * 1) I am making the assumption that the grid is significantly larger than the 
 * the dictionary, such as there are far more words that could be made constructed
 * from the grid (whether they are meaningful or not) than there are words in the
 * dictionary.
 * 
 * 2) This implementation would only work if the dictionary is given as a Trie
 */
public class LetterGrid {
	char[][] grid;
	int X;
	int Y;
	
	public LetterGrid(char[][] grid) {
		if (grid == null || grid[0].length == 0)
			throw new IllegalArgumentException("Cannot instatiate an empty or a null grid.");
		this.grid = grid;
		X = grid[0].length;
		Y = grid.length;
	}
	
	public Set<String> dictionnaryWords(Trie dict){
		HashSet<String> foundWords = new HashSet<String>();

		for(int x = 0; x < X; x++) {
			for(int y = 0; y < Y; y++) {
				int[][] depthMap = new int[X][Y];
				int currentDepth = 1;
				Point firstLetterCoord = new Point(x,y);
				String currentPrefix = getLetter(firstLetterCoord)+"";
				if (dict.isWord(currentPrefix))
					foundWords.add(currentPrefix);
				if (dict.isPrefix(currentPrefix))
					searchWords(firstLetterCoord, currentDepth, depthMap, dict, foundWords, currentPrefix);
			}
		}
		
		return foundWords;
	}
	
    private void searchWords(Point letter, int currentDepth, int[][] depthMap, Trie dict,
			HashSet<String> foundWords, String currentPrefix) {
    	
    	ArrayList<Point> neighbors = findNeighbors(letter, currentDepth, depthMap);
    	
    	if (neighbors.size() == 0)
    		return;
    	
    	for (Point n : neighbors) {
    		String newPrefix = currentPrefix + getLetter(n);
    		if (dict.isPrefix(newPrefix)){
    			int[][] newDepthMap = deepCopy(depthMap);
    			newDepthMap[n.x][n.y] = currentDepth + 1;
    			if (dict.isWord(newPrefix))
    				foundWords.add(newPrefix);
    			searchWords(n, currentDepth +1, newDepthMap, dict, foundWords, newPrefix);
    		}
    	}
		
	}
    
    private char getLetter(Point p) {
    	return grid[p.x][p.y];
    }
    
    private int[][] deepCopy(int[][] depthMap) {
        int[][] copy = new int[X][Y];
        
        for (int x = 0; x < X; x++) {
          for (int y = 0; y < Y; y++) {
        		  copy[x][y] = depthMap[x][y];
          }
        }
        
        return copy;
      }
    
	private HashSet<String> recursiveDFS(Trie dict, HashSet<String> foundWords, Point start, boolean[][] visited){

        visited[start.x][start.y] = true;
 
        ArrayList<Point> neighbors = findNeighbors(start);
        for(Point n : neighbors){
            if (!visited[n.x][n.y])
            	recursiveDFS(dict, foundWords, n, visited);
        }
        
        return foundWords;
    }
 

    public HashSet<String> DFSWords(Trie dict, HashSet<String> foundWords, Point start) {
        boolean[][] visited = new boolean[X][Y];
        return recursiveDFS(dict, foundWords, start, visited);
    }
    
	public ArrayList<Point> findNeighbors(Point p){
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				Point neighbor = new Point(p.x + dx, p.y + dy);
				boolean XisOutOfBounds = (neighbor.x < 0 || neighbor.x >= Y) ;
				boolean YisOutOfBounds = (neighbor.y < 0 || neighbor.y >= Y); 
				boolean isStartingPoint = (dx == 0 && dy ==0);
				
				if (!XisOutOfBounds && !YisOutOfBounds && !isStartingPoint) {
					neighbors.add(neighbor);
				}
			}
			
		}
		return neighbors;
	}
	
	public ArrayList<Point> findNeighbors(Point p, int currentDepth, int[][] depthMap){
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				Point neighbor = new Point(p.x + dx, p.y + dy);
				boolean XisOutOfBounds = (neighbor.x < 0 || neighbor.x >= Y) ;
				boolean YisOutOfBounds = (neighbor.y < 0 || neighbor.y >= Y); 
				boolean isStartingPoint = (dx == 0 && dy ==0);
				boolean visited = (depthMap[neighbor.x][neighbor.y] > 0) && (depthMap[neighbor.x][neighbor.y] < currentDepth);
				
				if (!XisOutOfBounds && !YisOutOfBounds && !isStartingPoint && !visited) {
					neighbors.add(neighbor);
				}
			}
			
		}
		return neighbors;
	}
	
	
	
}
