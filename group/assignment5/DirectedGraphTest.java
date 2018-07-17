import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class DirectedGraphTest {
    @Test (expected = IllegalArgumentException.class)
    public void findNodeWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.findChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findChildWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.findChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void equalsWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.equals(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addParentWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.addParent(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeChildWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.removeChild(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeParentWithNullArgumentShouldThrowException(){
        DirectedGraphNode node = new DirectedGraphNode('A');
        node.removeParent(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addChildWithNullArgumentShouldThrowException(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('A'));
        graph.addChild(null,new DirectedGraphNode('A'));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addChildWithNullArgumentShouldThrowException2(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('A'));
        graph.addChild(graph.root,null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void findWithNullArgumentShouldThrowException(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('A'));
        graph.find(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeWithNullArgumentShouldThrowException(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('A'));
        graph.remove(null);
    }

    @Test
    public void addChildtTest(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('\0'));
        DirectedGraphNode node = new DirectedGraphNode('A');
        graph.addChild(graph.root, node);
        ArrayList<DirectedGraphNode<Character>> childresult = new ArrayList<>();
        childresult.add(node);
        ArrayList<DirectedGraphNode<Character>> parentresult = new ArrayList<>();
        parentresult.add(graph.root);
        Assert.assertTrue(parentresult.equals(node.getParents()));
        Assert.assertTrue(childresult.equals(graph.root.getChildren()));
    }

    @Test
    public void removeChildTest(){
        DirectedGraph graph = new DirectedGraph(new DirectedGraphNode('\0'));
        DirectedGraphNode node1 = new DirectedGraphNode('A');
        DirectedGraphNode node2 = new DirectedGraphNode('B');
        DirectedGraphNode node3 = new DirectedGraphNode('C');
        graph.addChild(graph.root, node1);
        graph.addChild(graph.root, node2);
        graph.addChild(graph.root, node3);
        DirectedGraphNode nodeToRemove = new DirectedGraphNode('D');
        graph.addChild(graph.root, nodeToRemove);
        graph.root.removeChild(nodeToRemove);
        ArrayList<DirectedGraphNode<Character>> result = new ArrayList<>();
        result.add(node1);
        result.add(node2);
        result.add(node3);
        Assert.assertTrue(graph.root.getChildren().equals(result));
    }








}
