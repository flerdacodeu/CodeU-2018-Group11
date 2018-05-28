package Assignment1;

public class Q2 {
	/**
	 * Method that gets Kth node from back of a singly linkedlist.
	 * 
	 * @param head
	 *            of the linkedlist
	 * @param kth
	 *            node to be returned.
	 */
	public ListNode getKth(ListNode head, int k) throws IllegalStateException {
		if (k < 0)
			throw new IllegalStateException("K cannot be negative");
		ListNode first = head;
		for (int i = 0; i < k; i++) {
			// if K > length of linkedlist.
			if (first == null)
				throw new IllegalStateException("K cannot be bigger than list size.");
			first = first.next;
		}
		// Dummy node created which follows the first pointer until it hits
		// null, then it means dummy node is currently at desired node.
		ListNode last = new ListNode(0);
		last.next = head;

		while (first != null) {
			first = first.next;
			last = last.next;
		}
		return last;
	}
}

class ListNode {
	Object val;
	ListNode next;

	public ListNode(Object val) {
		this.val = val;
	}
}
