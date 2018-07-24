
import java.util.ArrayList;

/**
 * Class that implements the method for finding an alphabet given a dictionary. 
 *
 * The algorithm is as follows:
 * It first builds a Trie from the order list of dictionary words. It then derives dictionary rules.
 * A dictionary rule is a list of characters in the order they should appear in the dictionary. For 
 * example, for the English dictionary, valid rules could be : [a, d, g, h, o, x], [a, b, c, d].
 * The list of rules simply follows from the Trie ; each node's children list defines a rule.
 * From the list of rules, it then builds a directed graph, where each successive character is 
 * a successive child. In the previous example, it would lead to the graph
 * a -> d -> g -> o -> x
 * |    ^
 * v    |
 * b    |
 * |	|
 * v	|
 * c ---
 *
 * Then it constructs the alphabet by traversing the graph starting from a parentless node.
 * It adds characters to the dictionary only if all its parents have been added to the alphabet.
 *
 * TODO: testing and documenting 
 * @author Sephora-M
 *
 */
public class UnknownAlphabet {
    private DirectedGraph<Character> rulesGraph;

    public UnknownAlphabet(String[] dictionary) {
        Trie dictionaryTrie = new Trie(dictionary);
        ArrayList<ArrayList<Character>> rules = deriveRules(dictionaryTrie);
        rulesGraph = buildRulesGraph(rules);
        System.out.println("All possible Alphabets :");
        allPossibleAlphabet(removeDuplicate(rules),0,removeDuplicate(rules).size()-1 );
    }

    /**
     * Finds a possible alphabet for the dictionnary used to initilise the UnknownAlphabet object.
     * It works by travesing the graph of rules rulesGraph. The traversal start from a parentless node and only
     * adds children to the alphabet if their parents have alrady been added the alphabet.
     *	Trie, a dictionary of words given as a Trie
     * @return alphabet,
     * 		a list of Characters reprensenting a valid alphabet for the dictionary
     */
    public ArrayList<Character> alphabet() {
        ArrayList<Character> alphabet = new ArrayList<>();

        DirectedGraphNode<Character> current = rulesGraph.root;

        for(DirectedGraphNode<Character> child : current.children) {
            child.removeParent(current);
            recursiveAlphabet(child, alphabet);
        }

        return reverseArray(alphabet);
    }

    private void recursiveAlphabet(DirectedGraphNode<Character> current, ArrayList<Character> alphabet) {
        if (current.getParents().isEmpty())
            alphabet.add(current.getKey());

        for(DirectedGraphNode<Character> child : current.children) {
            child.removeParent(current);
            recursiveAlphabet(child, alphabet);
        }

    }

    /**
     * Builds a directed graph that is consistant with the set of rules; e.i. if a Character A
     * appears after a Charter B in a rule, then A is a child of B (and B is a parent of A)
     * @param rules
     * 		ArrayList<ArrayList<Character>>, list of rules that the alphabet must follow
     * @return rulesGraph DirectedGraph<Character> the directed graph of rules
     *
     **/
    private DirectedGraph<Character> buildRulesGraph(ArrayList<ArrayList<Character>> rules) {
        DirectedGraph<Character> rulesTree = new DirectedGraph<>(new DirectedGraphNode<>(Trie.ROOT_SYMBOL));

        for (ArrayList<Character> rule : rules) {
            int ruleLength = rule.size();
            DirectedGraphNode<Character> current = rulesTree.find(rule.get(ruleLength-1));
            if (current == null) {
                rulesTree.addChild(rulesTree.root, new DirectedGraphNode<>(rule.get(ruleLength-1)));
                current = rulesTree.find(rule.get(ruleLength-1));
            }

            for(int i = ruleLength -2 ; i>=0 ; i--) {
                DirectedGraphNode<Character> next = rulesTree.find(rule.get(i));
                if (next == null) {
                    rulesTree.addChild(current, new DirectedGraphNode<>(rule.get(i)));
                    current = rulesTree.find(rule.get(i));
                } else { // need to check if next is in the subtree starting from current
                    if (next.findNode(current.getKey()) != null)  // if next comes before current in the tree, the dictionnary is inconsistent!
                        throw new IllegalArgumentException("The given dictionary is inconsistant!");

                    DirectedGraphNode<Character> childOfCurrent = current.findNode(next.getKey());
                    if (childOfCurrent == null) { // if next is not in current's subtree, add it
                        current.getChildren().add(next);
                        next.addParent(current);
                    }
                }
            }
        }
        return rulesTree;
    }

    /**
     * This method derives a set of alphabet rules from a dictionaryTrie. If a Trie is built from a
     * lexicographically ordered list of words, then the siblings appear in the order they should
     * appear in the alphabet. So the set of rules is retrieved by extracting the list of nodes'
     * children in order.
     * @param dictionaryTrie
     *	Trie, a dictionary of words given as a Trie
     * @return derivedRules, a list of rules
     */
    private ArrayList<ArrayList<Character>> deriveRules(Trie dictionaryTrie) {
        ArrayList<ArrayList<Character>> derivedRules = new ArrayList<>();
        DirectedGraphNode<Character> current = dictionaryTrie.root;
        derivedRules.add(current.getChildrensKeys());

        for(DirectedGraphNode<Character> child : current.children) {
            recursiveDeriveRules(child, derivedRules);
        }
        System.out.println("rules :");
        System.out.println(derivedRules);
        return derivedRules;
    }

    private void recursiveDeriveRules(DirectedGraphNode<Character> current,
                                      ArrayList<ArrayList<Character>> derivedRules) {
        if (!current.getChildrensKeys().isEmpty())
            derivedRules.add(current.getChildrensKeys());
        for(DirectedGraphNode<Character> child : current.children) {
            recursiveDeriveRules(child, derivedRules);
        }

    }

    private ArrayList<Character> reverseArray(ArrayList<Character> array){
        ArrayList<Character> reversed = new ArrayList<>(array.size());
        for (int i = array.size() - 1; i>= 0; i--) {
            reversed.add(array.get(i));
        }
        return reversed;
    }
    private void allPossibleAlphabet(ArrayList<ArrayList<Character>> rules,int start,int end){
	     if(start==end){
	    	 for(int i=0;i<rules.size();i++){
	    		 for(char c : rules.get(i))
	    			 System.out.print(c+" ");
	    	 } 
	    	 System.out.println();
	     }
	     else{
	    	 for(int i=start;i<=end;i++){
	    		 rules= changePosition(rules,start,i);
	    		 allPossibleAlphabet(rules, start+1, end);
	    		 rules=changePosition(rules,start,i);
	    	 }
	     }
    }
 
    private ArrayList<ArrayList<Character>> changePosition(ArrayList<ArrayList<Character>> rules, int start, int i) {
        ArrayList<Character> temp = rules.get(start);
	    rules.set(start, rules.get(i));
	    rules.set(i, temp);
	
	    return rules;
    }

    private ArrayList<ArrayList<Character>> removeDuplicate(ArrayList<ArrayList<Character>> rules){
	    ArrayList<ArrayList<Character>> newRules = new ArrayList<>();
	    newRules.add(rules.get(0));
	    for(int i=1;i<rules.size();i++){
		    if(!newRules.contains(rules.get(i))){
			    if(!isSubset(rules.get(i),newRules))
			        newRules.add(rules.get(i));
		        }
	        }
        return newRules;
    }

    private boolean isSubset(ArrayList<Character> arrayList,ArrayList<ArrayList<Character>> newRules) {
	    for(char c: arrayList){
		    for(int i=0; i<newRules.size(); i++){
			    if(newRules.get(i).contains(c))
				    return true;
		        }
	        }
	    return false;
    }
}
