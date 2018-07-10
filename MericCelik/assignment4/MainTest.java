import org.junit.Test;
import org.junit.Assert;

/**
 * @author AycaMericCelik
 * */
public class MainTest {
    @Test
    public void allTrueMustReturnOne(){
        boolean[][] tiles_alltrue = {{true,true,true,true},
                                    {true,true,true,true},
                                    {true,true,true,true},
                                    {true,true,true,true}};
        Assert.assertEquals(1, Main.countIslands(tiles_alltrue));
    }

    @Test
    public void allFalseMustReturnZero(){
        boolean[][] tiles_allfalse = {{false,false,false,false},
                                      {false,false,false,false},
                                      {false,false,false,false},
                                      {false,false,false,false}};

        Assert.assertEquals(0, Main.countIslands(tiles_allfalse));
    }

    @Test
    public void basicExample(){
        boolean[][] tiles_basic = {{false,true,false,true},
                                    {true,true,false,false},
                                    {false,false,true,false},
                                    {false,false,true,false}};
        Assert.assertEquals(3, Main.countIslands(tiles_basic));
    }

    @Test
    public void basicExample2(){
        boolean[][] tiles_basic2 = {{false,true,true,true, true},
                                    {true,false,true,false, false},
                                    {false,true,false,true, true},
                                    {false,true,true,false, false},
                                    {false,false,false,true, true},};

        Assert.assertEquals(5, Main.countIslands(tiles_basic2));
    }

    @Test
    public void oneFalseTileShouldReturn0(){
        boolean[][] tiles_onetile_false= {{false}};
        Assert.assertEquals(0, Main.countIslands(tiles_onetile_false));
    }

    @Test
    public void oneTrueTileShouldReturn1(){
        boolean[][] getTiles_onetile_true = {{true}};
        Assert.assertEquals(1, Main.countIslands(getTiles_onetile_true));
    }

    @Test
    public void basicExample3(){
        boolean[][] tiles_basic3 = {{true,true,false,false, true},
                                    {true,false,false,true, false},
                                    {false,false,false,false, false},
                                    {false,true,false,true, true}};
        Assert.assertEquals(5, Main.countIslands(tiles_basic3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullTilesShouldThrowException(){
        Main.countIslands(null);
    }

    @Test
    public void basicExample4(){
        boolean[][] tiles = {{true,true,true},
                            {true,false,true},
                            {true,true,true}};

        Assert.assertEquals(Main.countIslands(tiles), 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullNodeShouldThrowExceptionInFind(){
        DisjointSet.find(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullNodeShouldThrowExceptionInUnion(){
        DisjointSet.union(null,new Node());
    }
}
