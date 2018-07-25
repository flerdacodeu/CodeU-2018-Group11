import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

/**
 * @author AycaMericCelik
 */
public class DirectedGraphTest {
    @Test (expected = IllegalArgumentException.class)
    public void findNodeWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character> node = new DirectedGraphNode<>('A');
        node.findChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findChildWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        node.findChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void equalsWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        node.equals(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addParentWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        node.addParent(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeChildWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        node.removeChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeParentWithNullArgumentShouldThrowException(){
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        node.removeParent(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addChildWithNullArgumentShouldThrowException(){
        DirectedGraph<Character>  graph = new DirectedGraph<>(new DirectedGraphNode<>('A'));
        graph.addChild(null,new DirectedGraphNode<>('A'));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addChildWithNullArgumentShouldThrowException2(){
        DirectedGraph<Character>  graph = new DirectedGraph<>(new DirectedGraphNode<>('A'));
        graph.addChild(graph.root,null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findWithNullArgumentShouldThrowException(){
        DirectedGraph<Character> graph = new DirectedGraph<>(new DirectedGraphNode<>('A'));
        graph.find(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeWithNullArgumentShouldThrowException(){
        DirectedGraph<Character>  graph = new DirectedGraph<>(new DirectedGraphNode<>('A'));
        graph.remove(null);
    }

    @Test
    public void addChildtTest(){
        DirectedGraph<Character>  graph = new DirectedGraph<>(new DirectedGraphNode<>('\0'));
        DirectedGraphNode<Character>  node = new DirectedGraphNode<>('A');
        graph.addChild(graph.root, node);
        ArrayList<DirectedGraphNode<Character>> childresult = new ArrayList<>();
        childresult.add(node);
        ArrayList<DirectedGraphNode<Character>> parentresult = new ArrayList<>();
        parentresult.add(graph.root);
        Assert.assertEquals(parentresult, node.getParents());
        Assert.assertEquals(childresult, graph.root.getChildren());
    }

    @Test
    public void removeChildTest(){
        DirectedGraph<Character>  graph = new DirectedGraph<>(new DirectedGraphNode<>('\0'));
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        graph.addChild(graph.root, node1);
        graph.addChild(graph.root, node2);
        graph.addChild(graph.root, node3);
        DirectedGraphNode<Character>  nodeToRemove = new DirectedGraphNode<>('D');
        graph.addChild(graph.root, nodeToRemove);
        graph.root.removeChild(nodeToRemove);
        ArrayList<DirectedGraphNode<Character>> result = new ArrayList<>();
        result.add(node1);
        result.add(node2);
        result.add(node3);
        Assert.assertEquals(graph.root.getChildren(), result);
    }

    @Test
    public void addParentTest(){
        DirectedGraphNode<Character> child  = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> parent = new DirectedGraphNode<>('B');
        child.addParent(parent);
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        result.add(parent);
        Assert.assertEquals(result, child.getParents());
    }

    @Test
    public void findChildTest(){
        DirectedGraphNode<Character> root = new DirectedGraphNode<>('Z');
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        DirectedGraphNode<Character> node4 = new DirectedGraphNode<>('D');
        DirectedGraphNode<Character> node5 = new DirectedGraphNode<>('E');

        DirectedGraph<Character> graph = new DirectedGraph<>(root);
        graph.addChild(root, node1);
        graph.addChild(root, node2);
        graph.addChild(node1, node3);
        graph.addChild(node2, node4);
        graph.addChild(node3, node5);

        //Assert.assertEquals(root.findChild(node1.getKey()), node1);
        //Assert.assertEquals(root.findChild(node2.getKey()), node2);
        //Assert.assertNull(node1.findChild(node2.getKey()));
    }

    @Test
    public void findNodeTest(){
        DirectedGraphNode<Character> root = new DirectedGraphNode<>('Z');
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        DirectedGraphNode<Character> node4 = new DirectedGraphNode<>('D');
        DirectedGraphNode<Character> node5 = new DirectedGraphNode<>('E');

        DirectedGraph<Character> graph = new DirectedGraph<>(root);
        graph.addChild(root, node1);
        graph.addChild(root, node2);
        graph.addChild(node1, node3);
        graph.addChild(node2, node4);
        graph.addChild(node3, node5);

        Assert.assertEquals(root.findNode(node1.getKey()), node1);
        Assert.assertEquals(root.findNode(node2.getKey()), node2);
        Assert.assertNull(node1.findNode(node2.getKey()));
    }

    @Test
    public void getKeyTest(){
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        Assert.assertEquals((char) node1.getKey(),'A');
    }

    @Test
    public void getParentsTest(){
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        ArrayList<DirectedGraphNode<Character>> result = new ArrayList<>();
        node1.addParent(node2);
        node1.addParent(node3);
        result.add(node2);
        result.add(node3);
        Assert.assertEquals(result, node1.getParents());
    }

    @Test
    public void getChildrensKeyTest(){
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        DirectedGraph<Character> graph = new DirectedGraph<>(node1);
        graph.addChild(node1, node2);
        graph.addChild(node1, node3);
        ArrayList<Character> result = new ArrayList<>();
        result.add(node2.getKey());
        result.add(node3.getKey());
        Assert.assertEquals(result, node1.getChildrensKeys());
    }

    @Test
    public void removeParentTest(){
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        node3.addParent(node1);
        node3.addParent(node2);
        node3.removeParent(node2);
        ArrayList<DirectedGraphNode<Character>> result = new ArrayList<>();
        result.add(node1);
        Assert.assertEquals(result, node3.getParents());
    }

    @Test
    public void findTest(){
        DirectedGraphNode<Character> root = new DirectedGraphNode<>('Z');
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraphNode<Character> node2 = new DirectedGraphNode<>('B');
        DirectedGraphNode<Character> node3 = new DirectedGraphNode<>('C');
        DirectedGraphNode<Character> node4 = new DirectedGraphNode<>('D');
        DirectedGraphNode<Character> node5 = new DirectedGraphNode<>('E');

        DirectedGraph<Character> graph = new DirectedGraph<>(root);
        graph.addChild(root, node1);
        graph.addChild(root, node2);
        graph.addChild(node1, node3);
        graph.addChild(node2, node4);
        graph.addChild(node3, node5);

        Assert.assertEquals(graph.find(node1.getKey()), node1);
        Assert.assertEquals(graph.find(node2.getKey()), node2);
        Assert.assertNull(graph.find(new DirectedGraphNode<>('P').getKey()));
    }

    @Test
    public void getRootTest(){
        DirectedGraphNode<Character> node1 = new DirectedGraphNode<>('A');
        DirectedGraph<Character> graph = new DirectedGraph<>(node1);
        Assert.assertEquals(graph.getRoot(), node1);
    }
}
