package assignment_1;

import java.util.LinkedList;

public class Q_02<T> {

	public T getLastK(LinkedList<T> linkedList, int k) {
		int position = linkedList.size() - k - 1;
		return linkedList.get(position);
	}
}
