public class LinkedListNode<T> {
    private T element;
    private LinkedListNode<T> next;

    public LinkedListNode(T element){
        this.element = element;
        this.next = null;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }
}



