package myfirstgame.todolist;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by James on 15/11/2017.
 */

public class TestLevel {

    Level level;

    @Before
    public void setUp() throws Exception {
        level = new Level();
    }

    @Test
    public void testLevel() {
        assertEquals(3100, Level.getLevelExp(3));
        assertEquals(4800, Level.getLevelExp(4));
        assertEquals(6600, Level.getLevelExp(5));


    }

}
