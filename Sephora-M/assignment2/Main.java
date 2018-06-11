package assignment2;

public class Main {

	public static void main(String[] args) {
		// we can construct a tree by adding each node one by one at random locations
		BinaryTree<String> tree = new BinaryTree<String>("a");
		
		String[] keys = {"b","c","d","e","f","g","h","i","j","k","l","m", "n"};
		
		for (String  key : keys) {
			tree.add(key);
			tree.printAncestors(key);
		}
		
		System.out.println("Common Ancestors ");
		System.out.println("Common ancestor of (b, i) is " + tree.commonAncestor("b", "i").toString());
		System.out.println("Common ancestor of (e, d) is " + tree.commonAncestor("e", "d").toString());
		System.out.println("Common ancestor of (d, m) is " + tree.commonAncestor("d", "m").toString());
		System.out.println("Common ancestor of (a, n) is " + tree.commonAncestor("a", "n").toString());
		System.out.println("Common ancestor of (n, g) is " + tree.commonAncestor("n", "g").toString());
		System.out.println("Common ancestor of (m, g) is " + tree.commonAncestor("n", "l").toString());
		System.out.println("Common ancestor of (m, g) is " + tree.commonAncestor("m", "k").toString());
		System.out.println("Common ancestor of (m, g) is " + tree.commonAncestor("i", "j").toString());
		
		
		// or we can specify a in-order description of the desired tree
		String[] preOrder = {"a","(","b","(","d","(","h",")","(","i",")",")","(","e","(","j",")",")",")",
				"(","c","(","f","(","k",")",")","(","g",")",")"};
		System.out.println("in order description of the tree: "+ preOrder);
		
		tree = new BinaryTree<String>(preOrder, "(",")");
		
		for (String  key : tree.getKeys()) {
			tree.printAncestors(key);
		}
		
		System.out.println("Common Ancestors ");
		System.out.println("Common ancestor of (b, i) is " + tree.commonAncestor("b", "i").toString());
		System.out.println("Common ancestor of (e, d) is " + tree.commonAncestor("e", "d").toString());
		System.out.println("Common ancestor of (d, k) is " + tree.commonAncestor("d", "k").toString());
		System.out.println("Common ancestor of (a, f) is " + tree.commonAncestor("a", "f").toString());
		System.out.println("Common ancestor of (h, i) is " + tree.commonAncestor("h", "i").toString());
		System.out.println("Common ancestor of (j, g) is " + tree.commonAncestor("j", "g").toString());
		System.out.println("Common ancestor of (h, e) is " + tree.commonAncestor("h", "e").toString());
		System.out.println("Common ancestor of (k, c) is " + tree.commonAncestor("k", "c").toString());
	}

}
