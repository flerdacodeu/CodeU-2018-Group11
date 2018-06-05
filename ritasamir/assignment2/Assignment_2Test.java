package assignment_2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Assignment_2Test {

	BinaryTree bTree;

	@Test
	public void printAncestorsTest() {
		setBinaryTree();
		Q_01 q = new Q_01();
		q.printAncestors(bTree.getBinaryTree(), 12);

	}

	@Test
	public void getCommonAncestorTest() {
		setBinaryTree();
		Q_02 q_2 = new Q_02();
		assertEquals(q_2.getCommonAncestor(bTree.getBinaryTree(), 6, 5)
				.getValue(), 3);
	}

	private void setBinaryTree() {
		bTree = new BinaryTree(7);
		bTree.addNode(new Node(3));
		bTree.addNode(new Node(4));
		bTree.addNode(new Node(2));
		bTree.addNode(new Node(5));
		bTree.addNode(new Node(8));
		bTree.addNode(new Node(1));
		bTree.addNode(new Node(6));
		bTree.addNode(new Node(10));
		bTree.addNode(new Node(12));
	}

}
