import org.junit.Test;
import org.junit.Assert;

public class MainTest {
    boolean[][] tiles_basic =
            {{false,true,false,true},
            {true,true,false,false},
            {false,false,true,false},
            {false,false,true,false}};

    boolean[][] tiles_alltrue =
            {{true,true,true,true},
            {true,true,true,true},
            {true,true,true,true},
            {true,true,true,true}};

    boolean[][] tiles_allfalse =
            {{false,false,false,false},
            {false,false,false,false},
            {false,false,false,false},
            {false,false,false,false}};

    boolean[][] tiles_basic2 =
            {{false,true,true,true, true},
            {true,false,true,false, false},
            {false,true,false,true, true},
            {false,true,true,false, false},
            {false,false,false,true, true},};

    boolean[][] tiles_basic3 =
            {{true,true,false,false, true},
            {true,false,false,true, false},
            {false,false,false,false, false},
            {false,true,false,true, true}};

    boolean[][] tiles_onetile_false= {{false}};
    boolean[][] getTiles_onetile_true = {{true}};

    @Test
    public void allTrueMustReturnOne(){
        Assert.assertEquals(1, Main.countIslands(4,4,tiles_alltrue));
    }

    @Test
    public void allFalseMustReturnZero(){
        Assert.assertEquals(0, Main.countIslands(4,4,tiles_allfalse));
    }

    @Test
    public void basicExample(){
        Assert.assertEquals(3, Main.countIslands(4,4,tiles_basic));
    }

    @Test
    public void basicExample2(){
        Assert.assertEquals(5, Main.countIslands(5,5,tiles_basic2));
    }

    @Test
    public void oneFalseTileShouldReturn0(){
        Assert.assertEquals(0, Main.countIslands(1,1,tiles_onetile_false));
    }

    @Test
    public void oneTrueTileShouldReturn1(){
        Assert.assertEquals(1, Main.countIslands(1,1,getTiles_onetile_true));
    }

    @Test
    public void basicExample3(){
        Assert.assertEquals(5, Main.countIslands(4,5,tiles_basic3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullTilesShouldThrowException(){
        Main.countIslands(50,70,null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void rowCountIsNotEqualTheActualOne_Exception(){
        Main.countIslands(50,4,tiles_basic);
    }

    @Test (expected = IllegalArgumentException.class)
    public void columnCountIsNotEqualTheActualOne_Exception(){
        Main.countIslands(4,70,tiles_basic);
    }

}
