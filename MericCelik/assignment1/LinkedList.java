
public class LinkedList<T>{
    LinkedListNode<T> head;
    int size;

    public LinkedList(){
        this.head = null;
        this.size=0;
    }

    public void add(LinkedListNode<T> newnode){
        LinkedListNode<T> temp = this.head;
        if(temp==null)
            head = newnode;
        else{
            while(temp.getNext()!=null)
                temp = temp.getNext();
            temp.setNext(newnode);
        }
        this.size +=1;
    }

    public void add(T newobject){
        LinkedListNode<T> newnode = new LinkedListNode<>(newobject);
        add(newnode);
    }

    public int getSize(){
        return size;
    }

    public LinkedListNode<T> getNode(int index){
        LinkedListNode<T> temp = this.head;
        int iter = 0;
        while(temp.getNext()!=null && iter<index){
            temp = temp.getNext();
            iter+=1;
        }
        return temp;
    }

    public void add(int index){

    }

    public T getElement(int index){
        return getNode(index).getElement();
    }

    public void remove(int index){
        if(index==0){
            head = head.getNext();
            size-=1;
            return;
        }
        LinkedListNode<T> beforetemp = this.getNode(index-1);
        LinkedListNode<T> temp = beforetemp.getNext();
        beforetemp.setNext(temp.getNext());
        size-=1;
    }
}
