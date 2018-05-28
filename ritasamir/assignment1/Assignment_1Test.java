package assignment_1;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class Assignment_1Test {

	Q_01 q_1 = new Q_01();
	Q_02<Integer> q_2 = new Q_02<Integer>();

	@Test
	public void testCheckAnagram() {
		assertEquals(q_1.checkAnagram("hello word", "hel oworld"), true);
		assertEquals(q_1.checkAnagram("hello world", "world hello"), true);
		assertEquals(q_1.checkAnagram("ab cd", "a bcd"), true);
		assertEquals(q_1.checkAnagram("book", "bok"), false);
		assertEquals(q_1.checkAnagram("heart", "learh"), false);
		assertEquals(q_1.checkAnagram("car", "arc"), true);

	}

	@SuppressWarnings("unused")
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetLastK() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(5);
		l.add(8);
		l.add(10);
		l.add(6);
		l.add(12);
		l.add(13);
		Q_02<Integer> q = new Q_02<>();
		System.out.println(q.getLastK(l, 0));
		assertEquals((Object) q_2.getLastK(l, 0), 13);
		assertEquals((Object) q_2.getLastK(l, 3), 10);
		assertEquals((Object) q_2.getLastK(l, 4), 8);
		assertEquals((Object) q_2.getLastK(l, 5), 5);
		Object answer = (Object) q_2.getLastK(l, 6);
		answer = (Object) q_2.getLastK(l, -1);

	}

}
