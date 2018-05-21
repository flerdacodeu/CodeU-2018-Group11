import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void test() {
		assertEquals(Anagrams.checkAnagrams("abcddd ", "dcba", false), false);
		assertEquals(Anagrams.checkAnagrams("abcd ", "dcba", false), true);
		assertEquals(Anagrams.checkAnagrams("ABCD ", "dcba", false), true);
		assertEquals(Anagrams.checkAnagrams("ABCD ", "dcba", true), false);
		assertEquals(Anagrams.checkAnagrams("abcd LOL", "ldoclba?",false), true);
		assertEquals(Anagrams.checkAnagrams(" ", "", true), true);
		assertEquals(Anagrams.checkAnagrams(" A ", "-, ... , [a] ", false), true);
	}

}
