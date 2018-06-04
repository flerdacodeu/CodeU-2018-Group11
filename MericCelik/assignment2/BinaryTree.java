import java.util.ArrayList;

/**Implementation of binary tree
 * @author AycaMericCelik
 * */

public class BinaryTree<T> {
    private BinaryTreeNode<T> root;


    public BinaryTree(BinaryTreeNode<T> root){
        this.root = root;
    }

    public BinaryTree(T root){
        this(new BinaryTreeNode<>(root));
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    /**Finds the ancestors of the target node from lowest to root and print them to the standard output.
     * The speciality of this function is it does not need the parent field of the nodes.
     * So it is space-friendly.
     * Complexities: Average O(N), Worst O(N)(When we traverse all nodes),
     * Best O(1)(When the target is the root node) --- N is the node count of the tree.
     * @param root : root node of the tree
     * @param target : node whose ancestors are found
     * @return 1 on 0 depending on whether the target is found or not.
     * */

    private int p_Ancestors(BinaryTreeNode<T> root, BinaryTreeNode<T> target){
        if(root==null)
            return 0;
        if(root.getKey().equals(target.getKey()))
            return 1;
        if(p_Ancestors(root.getLeftchild(),target)==1){
            System.out.print(root.getKey() + " ");
            return 1;
        }
        else if(p_Ancestors(root.getRightchild(),target)==1){
            System.out.print(root.getKey() + " ");
            return 1;
        }
        return 0;
    }

    /**Caller method of the findAncestors method. It prevents from wrong output.
     * --If findAncestors function returns 0, it means the target is not in the binary tree.
     * It prints a warning message to the standard output.
     * --If root or target is null, it prints a warning message to the standard output.
     * @param root : root node of the tree
     * @param target : node whose ancestors are found.
     * @return nothing.
     * */

    public void printAncestors_Recursive(BinaryTreeNode<T> root, BinaryTreeNode<T> target){
        if(root==null){
            System.out.println("There is no root node!");
            return;
        }
        if(target==null){
            System.out.println("There is no target node!");
            return;
        }

        if(target.equals(root)){
            System.out.println("Target node is the root of the tree, it has no parent!");
            return;
        }

        int result = p_Ancestors(root,target);
        if(result == 0){
            System.out.println("This node is not in the binary tree!");
            return;
        }
        System.out.println();
    }

    /**It finds target node's ancestors from lowest to highest and return them as an arraylist.
     * Complexity: Average O(logn)(The average height of the tree), Worst O(N)(Linear binary tree),
     * Best O(1)(When target is the root)--- N is the node count of the tree.
     * @param target : node whose ancestors are found.
     * @return arraylist of ancestors.
     * */

    public ArrayList<BinaryTreeNode<T>> findAncestors(BinaryTreeNode<T> target){
        ArrayList<BinaryTreeNode<T>> ancestors = new ArrayList<>();
        BinaryTreeNode<T> temp = target;
        while(temp.getParent()!=null){
            ancestors.add(temp.getParent());
            temp = temp.getParent();
        }
        return ancestors;
    }

    /**Calls findAncestors method and print the items of returned arraylist.
     * It checks if the input is valid, tree is not empty, target is not the root and
     * the target is in the tree. If these conditions are not hold, it prints suitable
     * warning message to the standart output.
     * Complexities: O(1) when warnings occurs, otherwise depend on findsAncestors method.
     * @param target : node whose ancestors are found.
     * @return nothing.
     * */

    public void printAncestors(BinaryTreeNode<T> target){
        if(target==null){
            System.out.println("There is no target node!");
            return;
        }
        if(this.getRoot()==null){
            System.out.println("The tree is empty!");
            return;
        }

        if(target.equals(this.root)){
            System.out.println("Target node is the root of the tree, it has no parent!");
            return;
        }

        ArrayList<BinaryTreeNode<T>> ancestors = findAncestors(target);
        int ancestor_count = ancestors.size();
        if(ancestor_count==0 || !this.getRoot().equals(ancestors.get(ancestor_count-1))){
            System.out.println("This node is not in the binary tree!");
            return;
        }

        for(int i=0; i<ancestor_count;i++)
            System.out.print(ancestors.get(i).getKey() + " ");
        System.out.println();
    }

    /**Calls the findAncestors method for two nodes and finds their lowest common ancestor by comparing
     * resulting arrays. If one of them is in other's resulting array, this mean it is the ancestor of the other.
     * Else if the array sizes are equal, it means they are in the same level. It traverse arrays simultaneously
     * until it finds first common item. This is the lowest common ancestor.
     * Else if the array sizes are not equal, it discards the deeper node's extra ancestors. It does it by incrementing
     * the index value for the array traverse.
     * The order of the nodes are not important.
     * Complexities: O(1) when warnings occurs, otherwise depend on findsAncestors method.
     * @param node1 : first node to consider
     * @param node2 : other node to consider
     * @return lowest common ancestor node
     * */

    public BinaryTreeNode<T> lowestCommonAncestor(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2){
        if(node1==null&&node2==null){
            System.out.println("There are no first and second node!");
            return null;
        }
        if(node1==null){
            System.out.println("There is no first node!");
            return null;
        }
        if(node2==null){
            System.out.println("There is no second node!");
            return null;
        }
        if(this.getRoot()==null){
            System.out.println("The tree is empty!");
            return null;
        }

        if(node1.equals(this.getRoot())&&node2.equals(this.getRoot())){
            System.out.println("Both of them are root!");
            return null;
        }

        ArrayList<BinaryTreeNode<T>> ancestors1 = findAncestors(node1);
        int ancestor_count1 = ancestors1.size();

        ArrayList<BinaryTreeNode<T>> ancestors2 = findAncestors(node2);
        int ancestor_count2 = ancestors2.size();

        if(ancestor_count1==0 && ancestor_count2==0){
            System.out.println("Both nodes have no ancestors!");
            return null;
        }

        if(node1.equals(this.getRoot())){
            if(ancestors2.contains(node1))
                return this.getRoot();
            else{
                System.out.println("Second node is not in the tree!");
                return null;
            }
        }
        if(node2.equals(this.getRoot())){
            if(ancestors1.contains(node2))
                return this.getRoot();
            else{
                System.out.println("First node is not in the tree!");
            }
        }


        if(!this.getRoot().equals(ancestors1.get(ancestor_count1-1))){
            System.out.println("First node is not in the tree!");
            return null;
        }
        if(!this.getRoot().equals(ancestors2.get(ancestor_count2-1))){
            System.out.println("Second node is not in the tree!");
            return null;
        }

        if(ancestors2.contains(node1))
            return node1;
        if(ancestors1.contains(node2))
            return node2;


        int index1=0, index2=0;
        if(ancestor_count1>ancestor_count2)
            index1 = ancestor_count1-ancestor_count2;
        else if(ancestor_count2>ancestor_count1)
            index2 = ancestor_count2-ancestor_count1;
        while(!ancestors1.get(index1).equals(ancestors2.get(index2))){
            index1+=1;
            index2+=1;
        }
        return ancestors1.get(index1);
    }


    public static void main(String[] args){

        //Same example with the pdf's
        BinaryTreeNode<Integer> six = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> one = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> two = new BinaryTreeNode<>(2,one,null);
        BinaryTreeNode<Integer> five = new BinaryTreeNode<>(5,null,six);
        BinaryTreeNode<Integer> three = new BinaryTreeNode<>(3,two,five);
        BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> four = new BinaryTreeNode<>(4,null,eight);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,three,four);

        BinaryTree<Integer> ourtree = new BinaryTree<>(root);

        BinaryTreeNode<Integer> eleven = new BinaryTreeNode<>(11);
        BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(10,eleven,null);

        System.out.println("Calling printAncestors_Recursive...");
        System.out.print("Target node: "+ root.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root, root);
        System.out.print("Target node: "+ six.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root, six);
        System.out.print("Target node: "+ ten.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root,ten);
        System.out.print("Target node: null" + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root,null);
        System.out.print("Root: null, Target node: " + five.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(null,five);

        System.out.println("\nCalling printAncestors...");
        System.out.print("Target node: "+ root.getKey() + ", ancestors: ");
        ourtree.printAncestors(root);
        System.out.print("Target node: "+ six.getKey() + ", ancestors: ");
        ourtree.printAncestors( six);
        System.out.print("Target node: "+ ten.getKey() + ", ancestors: ");
        ourtree.printAncestors(ten);
        System.out.print("Target node: null" + ", ancestors: ");
        ourtree.printAncestors(null);

        System.out.println("\nCalling lowestCommonAncestor...");
        System.out.print("Node1: "+ three.getKey() + ", Node2: "+ five.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(three,five).getKey());
        System.out.print("Node1: "+ five.getKey() + ", Node2: "+ three.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(five,three).getKey());
        System.out.print("Node1: "+ six.getKey() + ", Node2: "+ eight.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(six,eight).getKey());
        System.out.print("Node1: "+ two.getKey() + ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(two,six).getKey());
        System.out.print("Node1: null"+ ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(null,six));
        System.out.print("Node1: "+ two.getKey() + ", Node2: null" +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(two,null));
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ ourtree.root.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(ourtree.root,ourtree.root));
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(ourtree.root,six).getKey());
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ ten.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(ourtree.root,ten));
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ eleven.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(ourtree.root,eleven));
    }
}
