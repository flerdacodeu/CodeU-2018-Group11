package Assignment1;

public class Q2 {
	/**
	 * Method that gets Kth node from back of a singly linkedlist.
	 * @param head of the linkedlist
	 * @param kth node to be returned.
	 */
	public ListNode getKth(ListNode head, int k) {
		ListNode first = head;
		for (int i = 0; i < k; i++) {
			// if K > length of linkedlist.
			if (first == null)
				return null;
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
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
	}
}