package assignment_1;

import java.util.Iterator;
import java.util.LinkedList;

public class Q_02<T> {

	/**
	 * get the kth to last element of a linked list
	 * @param linkedList
	 * @param k
	 * @return the element at position k from the back.
	 */
	public T getLastK(LinkedList<T> linkedList, int k) {
		int size = 0;
		Iterator<T> itr = linkedList.iterator();
		while (itr.hasNext()) {
			size++;
			itr.next();
		}

		int position = size - k - 1;
		if (position < 0 || k < 0) {
			throw new IndexOutOfBoundsException(" " + k + " is out of bounds");
		}
		return linkedList.get(position);
	}
}
