
public class LinkedList{
    LinkedListNode head;

    public LinkedList(){
        this.head = null;
    }

    public void add(LinkedListNode newnode){
        LinkedListNode temp = this.head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newnode;
    }

    public void add(Object newobject){
        LinkedListNode newnode = new LinkedListNode(newobject);
        LinkedListNode temp = this.head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newnode;
    }

    public int getSize(){
        int size = 0;
        LinkedListNode temp = this.head;
        while(temp.next!=null){
            temp = temp.next;
            size+=1;
        }
        return size;
    }

    public Object getKthItem(int k){
        LinkedListNode temp = this.head;
        while(temp.next!=null){
            temp = temp.next;
        }
        return temp.element;
    }
}
