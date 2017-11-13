package myfirstgame.todolist;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by James on 09/11/2017.
 */

public class TestQuest {

    Quest quest;
    SimpleDateFormat date;

    @Before
    public void setUp() throws Exception {
        date = new SimpleDateFormat("d MMMM, yyyy");
        quest = new Quest("Go to the Gym", "Do Yoga and weightlifting", 100, 1, date.toString());
    }

    @Test
    public void canChangeCategory(){
//        quest.setCategory(Category.STAMINA);
//        assertEquals(Category.STAMINA, quest.getCategory());
    }

    @Test
    public void canChangeExpValue(){
        quest.setExpValue(50);
//        assertEquals(50, quest.getExpValue());
    }

    @Test
    public void canReturnName(){
        assertEquals("Strength", quest.showCategoryNameByNumber(1));
    }

}
