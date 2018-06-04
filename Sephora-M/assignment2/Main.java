package assignment2;

public class Main {

	public static void main(String[] args) {
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("a", null);
		BinaryTree<String> A = new BinaryTree<String>(a);
		
		String[] keys = {"b","c","d","e","f","g","h","i","j","k","l","m", "n"};
		
		for (String  key : keys) {
			A.add(key);
			A.printAncestors(key);
		}
		
		System.out.println("Common Ancestors ");
		System.out.println("Common ancestor of (b, i) is " + A.commonAncestor("b", "i").toString());
		System.out.println("Common ancestor of (e, d) is " + A.commonAncestor("e", "d").toString());
		System.out.println("Common ancestor of (d, m) is " + A.commonAncestor("d", "m").toString());
		System.out.println("Common ancestor of (a, n) is " + A.commonAncestor("a", "n").toString());
		System.out.println("Common ancestor of (n, g) is " + A.commonAncestor("n", "g").toString());
		System.out.println("Common ancestor of (m, g) is " + A.commonAncestor("n", "l").toString());
		System.out.println("Common ancestor of (m, g) is " + A.commonAncestor("m", "k").toString());
		System.out.println("Common ancestor of (m, g) is " + A.commonAncestor("i", "j").toString());
		
	}

}
