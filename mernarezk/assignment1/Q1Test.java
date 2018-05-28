package Assignment1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Q1Test {

	@Test
	void isAnagramTest() {
		Q1 tester = new Q1();
		assertEquals(true,tester.isAnagram("cat","Cat"));
		assertEquals(false,tester.isAnagram("foo","bar"));
		assertEquals(true,tester.isAnagram("meow give me food hooman","MEOW!! Give Me food,hooman!"));
	}

}
