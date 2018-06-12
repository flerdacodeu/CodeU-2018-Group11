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


    /**Finds the returns the node with given element if it occurs in the tree. Otherwise returns null. It does depth-first search in
     * binary tree.
     * Complexities: Average O(N), Worst O(N)(When we traverse all nodes),
     * Best O(1)(When the root has the given element) --- N is the node count of the tree.
     * @param root: root of the tree/subtree
     * @param element: element that we are looking for
     * @return the node with given element, null if not found.
     * */
    private BinaryTreeNode<T> findNodeWithDFS(BinaryTreeNode<T> root, T element){
        if(root==null)
            return null;
        if(root.getKey().equals(element))
            return root;

        BinaryTreeNode<T> left = findNodeWithDFS(root.getLeftchild(), element);
        if(left!=null)
            return left;
        return findNodeWithDFS(root.getRightchild(), element);
    }


    /**Caller method of findNodeWithDFS method with root of the tree and the element we are looking for.
     * Complexities: depends on findNodeWithDFS method.
     * @param element: element we are looking for
     * @throws NullPointerException if the tree does not contain a node with given element, the parameter is null or the
     * tree is empty.
     * @return node with given element
     * */
    public BinaryTreeNode<T> find(T element){
        if(this.getRoot()==null){
            throw new NullPointerException("The tree is empty!");
        }
        if(element == null)
            throw new NullPointerException("Invalid argument!");
        BinaryTreeNode<T> result = findNodeWithDFS(this.root, element);
        if(result == null){
            throw new NullPointerException("Element " + element +" is not in the tree!");
        }
        return result;
    }


    /**Finds the ancestors of the target node from lowest to root and returns them as an ArrayList.
     * The speciality of this function is it does not need the parent field of the nodes.
     * So it is space-friendly.
     * Complexities: Average O(N), Worst O(N)(When we traverse all nodes),
     * Best O(1)(When the target is the root node) --- N is the node count of the tree.
     * @param root : root node of the tree
     * @param target : node whose ancestors are found
     * @return arraylist of ancestors from lowest to highest.
     * */
    private ArrayList<BinaryTreeNode<T>> p_Ancestors(BinaryTreeNode<T> root, BinaryTreeNode<T> target){
        if(root==null)
            return null;
        if(root.getKey().equals(target.getKey()))
            return new ArrayList<>();

        ArrayList<BinaryTreeNode<T>> left = p_Ancestors(root.getLeftchild(),target);
        if(left!=null){
            left.add(root);
            return left;
        }

        ArrayList<BinaryTreeNode<T>> right = p_Ancestors(root.getRightchild(),target);
        if(right!=null){
            right.add(root);
            return right;
        }
        return null;
    }


    /**Caller method of the p_Ancestors method. It finds the node with given parameter named "element",
     * calls the p_Ancestors and prints the elements of returned arraylist. It handles one exception as well, others are
     * handled by find() method.
     * Complexities: depends on find() and p_Ancestors() methods.
     * @throws NullPointerException if the parameter "root" is null.
     * @param root : root node of the tree
     * @param element : element of node whose ancestors are found.
     * @return nothing.
     * */
    public void printAncestors_Recursive(BinaryTreeNode<T> root, T element){

        if(root==null)
            throw new NullPointerException("Parameter \"root\" is invalid!");

        BinaryTreeNode<T> target = this.find(element);

        if(target.equals(root)){
            System.out.println("Target node is the root of the tree, it has no parent!");
            return;
        }

        ArrayList<BinaryTreeNode<T>> ancestors = p_Ancestors(root,target);
        int ancestorcount = ancestors.size();
        for(int i=0;i<ancestorcount;i++)
            System.out.print(ancestors.get(i).getKey()+" ");
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


    /**Calls find() and findAncestors() method and print the items of returned arraylist.
     * Exceptions are handled in find() method and it returns the node with given element.
     * If the target node is the root, it prints a warning message to the stardard output.
     * Complexities: depends on find() and findsAncestors() methods.
     * @param element : element of node whose ancestors are found.
     * @return nothing.
     * */
    public void printAncestors(T element){

        BinaryTreeNode<T> targetnode = this.find(element);

        if(targetnode.equals(this.root)){
            System.out.println("Target node is the root of the tree, it has no parent!");
            return;
        }

        ArrayList<BinaryTreeNode<T>> ancestors = findAncestors(targetnode);
        int ancestor_count = ancestors.size();

        for(int i=0; i<ancestor_count;i++)
            System.out.print(ancestors.get(i).getKey() + " ");
        System.out.println();
    }


    /**Finds the lowest common ancestor of the nodes with given parameters. Firstly, the method calls find() method with
     * first parameter to find the corresponding node. After that, it choose this node as "rootnode", and calls
     * findNodeWithDFS() method to find if the other parameter(elem2) is found in the subtree with this "rootnode".
     * If not, findNodeWithDFS() function returns null and the search goes on. The new "rootnode" is the "rootnode"s
     * parent and the same process is done. The "lastVisited" parameter prevents it from checking same subtrees over
     * and over. At some point, if the function finds "elem2", it returns current "rootnode" as lowest common ancestor.
     * If it could not find the node, then the node is the ancestor of the "rootnode", so it returns it as lowest common
     * ancestor.
     * The order of the nodes (or parameters) is not important, so we easily picked the first parameter's node as a root.
     * Complexities: Average O(N) since we checking all the nodes,
     *               Best O(1) if if two elements are the same.
     *               Worst O(N)
     * @param elem1 : element of the first node
     * @param elem2 : element of the other node
     * @throws NullPointerException if one or both of the elements are null.
     * @return lowest common ancestor node
     * */
    public BinaryTreeNode<T> lowestCommonAncestor(T elem1, T elem2){

        if(elem1==null)
            throw new NullPointerException("First argument is invalid!");
        if(elem2==null)
            throw new NullPointerException("Second argument is invalid!");

        BinaryTreeNode<T> rootnode = this.find(elem1);
        BinaryTreeNode<T> lca = rootnode;
        BinaryTreeNode<T> found = null;
        BinaryTreeNode<T> lastVisited = null;
        if(elem1.equals(elem2))
            return rootnode;

        while(lca !=null){
            if(lca.getLeftchild()!=null){
                if(!lca.getLeftchild().equals(lastVisited))
                    found = this.findNodeWithDFS(lca.getLeftchild(), elem2);
            }
            if(found==null && lca.getRightchild()!=null){
                if(!lca.getRightchild().equals(lastVisited))
                    found = this.findNodeWithDFS(lca.getRightchild(), elem2);
            }
            if(found!=null)
                break;
            lastVisited = lca;
            lca = lca.getParent();
        }

        if(lca!=null)
            return lca;
        return this.find(elem2);
    }


    public static void main(String[] args){

        //Same example with the pdf's. Commented lines cause exceptions.

        BinaryTreeNode<Integer> six = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> one = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> two = new BinaryTreeNode<>(2,one,six);
        BinaryTreeNode<Integer> five = new BinaryTreeNode<>(5,null,null);
        BinaryTreeNode<Integer> three = new BinaryTreeNode<>(3,two,five);
        BinaryTreeNode<Integer> eight = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> four = new BinaryTreeNode<>(4,null,eight);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,three,four);

        BinaryTree<Integer> ourtree = new BinaryTree<>(root);

        System.out.println("Calling printAncestors_Recursive...");
        System.out.print("Target node: "+ root.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root, 7);
        System.out.print("Target node: "+ six.getKey() + ", ancestors: ");
        ourtree.printAncestors_Recursive(ourtree.root, 6);
        //System.out.print("Target node: 10" + ", ancestors: ");
        //ourtree.printAncestors_Recursive(ourtree.root,10);
        //System.out.print("Target node: null" + ", ancestors: ");
        //ourtree.printAncestors_Recursive(ourtree.root,null);
        //System.out.print("Root: null, Target node: " + five.getKey() + ", ancestors: ");
        //ourtree.printAncestors_Recursive(null,5);

        System.out.println("\nCalling printAncestors...");
        System.out.print("Target node: "+ root.getKey() + ", ancestors: ");
        ourtree.printAncestors(7);
        System.out.print("Target node: "+ six.getKey() + ", ancestors: ");
        ourtree.printAncestors(6);
        //System.out.print("Target node: 10" + ", ancestors: ");
        //ourtree.printAncestors(10);
        //System.out.print("Target node: null" + ", ancestors: ");
        //ourtree.printAncestors(null);

        System.out.println("\nCalling lowestCommonAncestor...");
        System.out.print("Node1: "+ three.getKey() + ", Node2: "+ five.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(3,5).getKey());
        System.out.print("Node1: "+ five.getKey() + ", Node2: "+ three.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(5,3).getKey());
        System.out.print("Node1: "+ six.getKey() + ", Node2: "+ eight.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(6,8).getKey());
        System.out.print("Node1: "+ two.getKey() + ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(2,6).getKey());
        //System.out.print("Node1: null"+ ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        //System.out.println(ourtree.lowestCommonAncestor(null,6).getKey());
        //System.out.print("Node1: "+ two.getKey() + ", Node2: null" +", lowest common ancestor: ");
        //System.out.println(ourtree.lowestCommonAncestor(2,null).getKey());
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ ourtree.root.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(7,7).getKey());
        System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: "+ six.getKey() +", lowest common ancestor: ");
        System.out.println(ourtree.lowestCommonAncestor(7,6).getKey());
        //System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: 10" +", lowest common ancestor: ");
        //System.out.println(ourtree.lowestCommonAncestor(7,10).getKey());
        //System.out.print("Node1: "+ ourtree.root.getKey() + ", Node2: 11" +", lowest common ancestor: ");
        //System.out.println(ourtree.lowestCommonAncestor(7,11).getKey());

    }
}
