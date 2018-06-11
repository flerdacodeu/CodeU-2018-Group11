package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

class BinaryTreeTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testFindNode() {
		BinaryTree<String> A = new BinaryTree<String>("a");
		
		A.add("b");
		A.add("c");
		A.add("d");
		A.add("e");
		A.add("f");
		A.add("g");
		A.add("h");
		A.add("i");
		
		assertTrue(A.find("h") != null);
		assertTrue(A.find("c") != null);
		assertTrue(A.find("f") != null);
		assertFalse(A.find("j") != null);
	}
	
	@Test
	void testAddChild() {
		BinaryTree<String> A = new BinaryTree<String>("a");
		A.addChild("a","b");
		A.addChild("a","c");
		
		BinaryTreeNode<String> a = A.find("a");
		BinaryTreeNode<String> b = a.findNode("b");
		
		assertTrue(b.getParent() == a);
	}
	
	@Test
	void testPrintAcestors(){
		BinaryTree<String> A = new BinaryTree<String>("a");
		A.add("b");
		A.add("c");
	
		try {
			A.printAncestors("d");
			assert false;
		} catch (NoSuchElementException e) {
			assert true;
		}
	}
	
	@Test
	void testCommonAncestor() {
		BinaryTree<String> tree = new BinaryTree<String>("a");

		
		tree.addChild("a","b");
		tree.addChild("a","c");
		tree.addChild("b","f");
		tree.addChild("b","k");
		tree.addChild("c","d");
		tree.addChild("c","h");
		tree.addChild("d","e");
		tree.addChild("d","g");
		tree.addChild("h","l");
		tree.addChild("e","i");
		tree.addChild("e","n");
		tree.addChild("g","m");
		tree.addChild("i","j");
		

		assertEquals(tree.commonAncestor("b", "i").getKey(), "a");
		assertEquals(tree.commonAncestor("e", "d").getKey(), "d");
		assertEquals(tree.commonAncestor("d", "m").getKey(), "d");
		assertEquals(tree.commonAncestor("a", "n").getKey(), "a");
		assertEquals(tree.commonAncestor("n", "g").getKey(), "d");
		assertEquals(tree.commonAncestor("n", "l").getKey(), "c");
		assertEquals(tree.commonAncestor("m", "k").getKey(), "a");
		assertEquals(tree.commonAncestor("i", "j").getKey(), "i");
		assertEquals(tree.commonAncestor("j", "n").getKey(), "e");
	}
	
	@Test
	void testPreOrderTreeConstruction() {
		String[] preOrder = {"a","(","b","(","d","(","h",")","(","i",")",")","(","e","(","j",")",")",")",
				"(","c","(","f","(","k",")",")","(","g",")",")"};
		BinaryTree<String> tree = new BinaryTree<String>(preOrder, "(",")");
		
		assertEquals(tree.commonAncestor("b", "k").getKey(), "a");
		assertEquals(tree.commonAncestor("e", "d").getKey(), "b");
		assertEquals(tree.commonAncestor("i", "j").getKey(), "b");
		assertEquals(tree.commonAncestor("h", "k").getKey(), "a");
		assertEquals(tree.commonAncestor("k", "f").getKey(), "f");
		assertEquals(tree.commonAncestor("j", "f").getKey(), "a");
		assertEquals(tree.commonAncestor("j", "j").getKey(), "j");
		assertEquals(tree.commonAncestor("h", "b").getKey(), "b");
		assertEquals(tree.commonAncestor("g", "h").getKey(), "a");
	}

}
