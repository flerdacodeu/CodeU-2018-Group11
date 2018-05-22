/***
 * This class implements a simple singly linked list data structure where
 * each node stores data of generic type and points to the next node in the list or
 * to null if it is the last node in the list.
 * 
 * The method kToLast returns the kth to last element of the singly linked list 
 * @author Sephora-M
 *
 */
public class SinglyLinkedListNode<T> {
	private T data;
	private SinglyLinkedListNode<T> next = null;
	
	public SinglyLinkedListNode(T data) {
		this.data = data;
	}
	
	public SinglyLinkedListNode(T data, SinglyLinkedListNode<T> next) {
		this.data = data;
		this.next = next;
	}
	
	/**
	 * Find and return the kth to last element of the singly linked 
	 * list if it exists.
	 * Throws an exception if the index is out of bounds
	 * @param k integer, the index 
	 * @return the kth element from the end
	 */
	public SinglyLinkedListNode<T> kToLast(int k) {
		SinglyLinkedListNode<T> pointer = this;
		SinglyLinkedListNode<T> runner = this;
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
		SinglyLinkedListNode<T> current = this;
		do {
			System.out.print(current.toString());
			System.out.print(" -> ");
			current = current.getNext();
		} while (current != null);
		System.out.print("Nil");
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public SinglyLinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(SinglyLinkedListNode<T> next) {
		this.next = next;
	}
}
