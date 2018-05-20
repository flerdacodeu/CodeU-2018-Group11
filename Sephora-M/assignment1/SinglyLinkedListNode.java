/***
 * This class implements a simple singly linked list data structure where
 * each node stores an object and points to the next node in the list or
 * to null if it is the last node in the list.
 * 
 * The method kToLast returns the kth to last element of the singly linked list 
 * @author Sephora-M
 *
 */
public class SinglyLinkedListNode {
	private Object data;
	private SinglyLinkedListNode next = null;
	
	public SinglyLinkedListNode(Object data) {
		this.data = data;
	}
	
	/**
	 * Find and return the kth to last element of the singly linked 
	 * list if it exists.
	 * Throws and exception if the index is out of bounds
	 * @param k integer, the index 
	 * @return the kth element starting from the end will be return
	 */
	public SinglyLinkedListNode kToLast(int k) {
		SinglyLinkedListNode pointer = this;
		SinglyLinkedListNode runner = this;
		int counter = 0;
		
		while (pointer.getNext() != null) {
			if (counter >= k) runner = runner.getNext();
			pointer = pointer.getNext();
			counter++;
		}
		if (counter >= k) 
			return runner;
		else
			throw new IndexOutOfBoundsException("Index " + k + " to last is out of bounds!");
	}
	
	public boolean isLast() {
		if (next == null) return true;
		return false;
	}

	public String toString() {
		return String.valueOf(data);
	}
	
	public void print() {
		SinglyLinkedListNode current = this;
		do {
			System.out.print(current.toString());
			System.out.print(" -> ");
			current = current.getNext();
		} while (current != null);
		System.out.print("Nil");
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public SinglyLinkedListNode getNext() {
		return next;
	}

	public void setNext(SinglyLinkedListNode next) {
		this.next = next;
	}
}
