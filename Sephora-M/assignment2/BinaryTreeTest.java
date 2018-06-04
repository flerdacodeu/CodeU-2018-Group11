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
	void testContainsNode() {
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("a", null);
		BinaryTree<String> A = new BinaryTree<String>(a);
		
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
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("a", null);
		a.addChild("b");
		a.addChild("c");
		
		BinaryTreeNode<String> b = a.findNode("b");
		
		assertTrue(b.getParent() == a);
	}
	
	@Test
	void testFindNode(){
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("a", null);
		a.addChild("b");
		a.addChild("c");
		BinaryTree<String> A = new BinaryTree<String>(a);
	
		try {
			A.printAncestors("d");
			assert false;
		} catch (NoSuchElementException e) {
			assert true;
		}
	}
	
	@Test
	void testCommonAncestor() {
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("a", null);
		BinaryTreeNode<String> b = new BinaryTreeNode<String>("b", null);
		BinaryTreeNode<String> c = new BinaryTreeNode<String>("c", null);
		BinaryTreeNode<String> d = new BinaryTreeNode<String>("d", null);
		BinaryTreeNode<String> e = new BinaryTreeNode<String>("e", null);
		BinaryTreeNode<String> f = new BinaryTreeNode<String>("f", null);
		BinaryTreeNode<String> g = new BinaryTreeNode<String>("g", null);
		BinaryTreeNode<String> h = new BinaryTreeNode<String>("h", null);
		BinaryTreeNode<String> i = new BinaryTreeNode<String>("i", null);
		BinaryTreeNode<String> j = new BinaryTreeNode<String>("j", null);
		BinaryTreeNode<String> k = new BinaryTreeNode<String>("k", null);
		BinaryTreeNode<String> l = new BinaryTreeNode<String>("l", null);
		BinaryTreeNode<String> m = new BinaryTreeNode<String>("m", null);
		BinaryTreeNode<String> n = new BinaryTreeNode<String>("n", null);
		
		a.addChild(b);
		a.addChild(c);
		b.addChild(f);
		b.addChild(k);
		c.addChild(d);
		c.addChild(h);
		d.addChild(e);
		d.addChild(g);
		h.addChild(l);
		e.addChild(i);
		e.addChild(n);
		g.addChild(m);
		i.addChild(j);
		
		BinaryTree<String> A = new BinaryTree<String>(a);
		assertEquals(A.commonAncestor("b", "i").getKey(), "a");
		assertEquals(A.commonAncestor("e", "d").getKey(), "d");
		assertEquals(A.commonAncestor("d", "m").getKey(), "d");
		assertEquals(A.commonAncestor("a", "n").getKey(), "a");
		assertEquals(A.commonAncestor("n", "g").getKey(), "d");
		assertEquals(A.commonAncestor("n", "l").getKey(), "c");
		assertEquals(A.commonAncestor("m", "k").getKey(), "a");
		assertEquals(A.commonAncestor("i", "j").getKey(), "i");
		assertEquals(A.commonAncestor("j", "n").getKey(), "e");
	}

}
