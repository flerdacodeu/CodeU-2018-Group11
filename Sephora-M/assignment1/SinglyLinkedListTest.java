import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class SinglyLinkedListTest {

	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testKToLast() {
		SinglyLinkedListNode<Integer> one = new SinglyLinkedListNode<Integer>(1);
		SinglyLinkedListNode<Integer> two = new SinglyLinkedListNode<Integer>(2);
		SinglyLinkedListNode<Integer> three = new SinglyLinkedListNode<Integer>(3);
		SinglyLinkedListNode<Integer> four = new SinglyLinkedListNode<Integer>(4);
		SinglyLinkedListNode<Integer> five = new SinglyLinkedListNode<Integer>(5);
		SinglyLinkedListNode<Integer> six = new SinglyLinkedListNode<Integer>(6);
		SinglyLinkedListNode<Integer> seven = new SinglyLinkedListNode<Integer>(7);
		SinglyLinkedListNode<Integer> eight = new SinglyLinkedListNode<Integer>(8);
		SinglyLinkedListNode<Integer> nine = new SinglyLinkedListNode<Integer>(9);
		SinglyLinkedListNode<Integer> ten = new SinglyLinkedListNode<Integer>(10);
		
		one.setNext(two);
		two.setNext(three);
		three.setNext(four);
		four.setNext(five);
		five.setNext(six);
		six.setNext(seven);
		seven.setNext(eight);
		eight.setNext(nine);
		nine.setNext(ten);
		
		assertEquals(one.kToLast(2), eight);
		assertEquals(one.kToLast(0), ten);
		assertEquals(one.kToLast(5), five);
		assertEquals(one.kToLast(9), one);
		
	}
	
	void testKToLastException(){
		SinglyLinkedListNode<Integer> one = new SinglyLinkedListNode<Integer>(1);
		thrown.expect(IndexOutOfBoundsException.class);
		one.kToLast(2);
	}

}
