package assignment3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**Grid of letters. Contains a method that, given a dictionnary represented as a Trie, allows to find 
 * all the words that can be formed in the grid. 
 * 
 * @author Sephora-M
 */
public class LetterGrid {
	char[][] grid;
	int X;
	int Y;
	
	public LetterGrid(char[][] grid) {
		if (grid == null || grid[0].length == 0)
			throw new IllegalArgumentException("Cannot instatiate an empty or a null grid.");
		this.grid = grid;
		Y = grid.length;
		X = grid[0].length;
	}
	
	/**
	 * Given a Trie object, returns a set of all the words in the Trie that can be formed in the grid 
	 * @param dict a Trie, the dictionary
	 * @return set of all the words from the dictionary that can be from in the grid
	 * 
	 * Note: the idea is to start from each letter in the grid and perform a DFS-like search. To optimize
	 * the search, check at each time if the current word that we are constructing is a prefix of a word in the 
	 * dictionary. It is not exactly a DFS, the subtility here is that we don't just want to visit all the 
	 * nodes, but we want to visit them from different path. This is taken care here by keeping track of a
	 * depthMap, which tells us when we visited each node. 
	 */
	public Set<String> dictionnaryWords(Trie dict){
		HashSet<String> foundWords = new HashSet<String>();

		for(int x = 0; x < X; x++) {
			for(int y = 0; y < Y; y++) {
				int[][] depthMap = new int[Y][X];
				int currentDepth = 1;
				depthMap[y][x] = currentDepth;
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
    			newDepthMap[n.y][n.x] = currentDepth + 1;
    			if (dict.isWord(newPrefix))
    				foundWords.add(newPrefix);
    			searchWords(n, currentDepth +1, newDepthMap, dict, foundWords, newPrefix);
    		}
    	}
		
	}
    
    private char getLetter(Point p) {
    	return grid[p.y][p.x];
    }
    
    private int[][] deepCopy(int[][] depthMap) {
        int[][] copy = new int[Y][X];
        
        for (int x = 0; x < X; x++) {
          for (int y = 0; y < Y; y++) {
        		  copy[y][x] = depthMap[y][x];
          }
        }
        
        return copy;
      }
    
	private ArrayList<Point> findNeighbors(Point p, int currentDepth, int[][] depthMap){
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				Point neighbor = new Point(p.x + dx, p.y + dy);
				boolean XisOutOfBounds = (neighbor.x < 0 || neighbor.x >= X) ;
				boolean YisOutOfBounds = (neighbor.y < 0 || neighbor.y >= Y); 
				boolean isStartingPoint = (dx == 0 && dy ==0);
				
				
				if (!XisOutOfBounds && !YisOutOfBounds && !isStartingPoint) {
					boolean visited = (depthMap[neighbor.y][neighbor.x] > 0) && (depthMap[neighbor.y][neighbor.x] < currentDepth);
					if (!visited)
						neighbors.add(neighbor);
				}
			}
			
		}
		return neighbors;
	}
	
	
	
}
