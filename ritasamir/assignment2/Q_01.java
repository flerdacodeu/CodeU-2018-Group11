package assignment_2;

import java.util.Stack;

public class Q_01 {

	private Stack<Node> stack = new Stack<>();;
	
	public Stack<Node> getStackOfAncestors(){
		return stack;
	}

	/**
	 * @param head is the root of the binary tree
	 * @param key 
	 */
	public void printAncestors(Node head, int key) {
		getAncestors(head, key);
		System.out.println("The ancestors of the key "+key+" :");
		if(stack.isEmpty()){
			System.out.println("this is the head of the binaryTree!");
		}
		for (int i = 0; i < stack.size(); i++) {
			System.out.print(stack.get(i).getValue()+" ");
		}
		System.out.println();
	}

	public void getAncestors(Node head, int key) {

		if (head.getValue() == key)
			return;

		if (!head.visited) {
			stack.push(head);
			head.visited = true;
		}

		if (head.getLeft() != null && !head.getLeft().visited) {
			getAncestors(head.getLeft(), key);
		} else if (head.getRight() != null && !head.getRight().visited) {
			getAncestors(head.getRight(), key);
		} else {
			stack.pop();
			if (!stack.isEmpty()) {
				getAncestors(stack.peek(), key);
			} else {
				throw new Error("Not exist in the binaryTree!!");
			}
		}

	}

}
