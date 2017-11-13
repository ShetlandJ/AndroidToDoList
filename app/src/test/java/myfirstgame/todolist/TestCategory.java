package myfirstgame.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by James on 13/11/2017.
 */

public class TestCategory {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category(1, "Strength", 0, 0);
    }

    @Test
    public void canLevelUp() throws Exception {
        category.setExp(1600);
        category.setLevel();
        assertEquals(2.0, category.getLevel(), 0.1);
    }
}
