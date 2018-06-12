
/**Implementation of binary tree nodes. BinaryTree class uses this class.
 * @author AycaMericCelik
 * */

public class BinaryTreeNode<T> {
    private T key;
    private BinaryTreeNode<T> rightchild;
    private BinaryTreeNode<T> leftchild;
    private BinaryTreeNode<T> parent;

    public BinaryTreeNode(T key, BinaryTreeNode<T> rightchild, BinaryTreeNode<T> leftchild){
        this.key = key;
        this.rightchild = rightchild;
        this.leftchild = leftchild;
        this.parent = null;

        if(rightchild!=null)
            this.rightchild.parent = this;
        if(leftchild!=null)
            this.leftchild.parent = this;
    }

    public BinaryTreeNode(T key){
        this.key = key;
        this.rightchild = null;
        this.leftchild = null;
        this.parent = null;
    }


    public BinaryTreeNode<T> getParent(){ return parent; }

    public T getKey() {
        return key;
    }

    public BinaryTreeNode<T> getLeftchild() {
        return leftchild;
    }

    public BinaryTreeNode<T> getRightchild() {
        return rightchild;
    }

    public void setKey(T key){
        this.key = key;
    }

    public void setLeftchild(BinaryTreeNode<T> leftchild) {

        this.leftchild = leftchild;
        this.leftchild.parent = this;
    }

    public void setRightchild(BinaryTreeNode<T> rightchild) {
        this.rightchild = rightchild;
        this.rightchild.parent = this;
    }

    public boolean equals(Object other){
        if (other instanceof BinaryTreeNode<?>){
            if (((BinaryTreeNode<?>)other).key.equals(key) ){
                return true;
            }
        }
        return false;

    }

}


