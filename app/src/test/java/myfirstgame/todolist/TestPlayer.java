package myfirstgame.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by James on 09/11/2017.
 */

public class TestPlayer {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("James", 0, 0, 0, 0, 0);
    }

    @Test
    public void canChangeName(){
        player.setName("Brian");
        assertEquals("Brian", player.getName());
    }

    @Test
    public void canGetExperienceTotal(){
        player = new Player("James", 250, 100, 50, 50, 1);
        assertEquals(450, player.getTotalExperience(), 0.1);
    }

    @Test
    public void canLevelUp(){
        player = new Player("James", 1000, 750, 2000, 50, 1);
        player.setLevel();
        assertEquals(2.0, player.getLevel(), 0.1);
    }

    @Test
    public void canShowProgressPercentage(){
        player = new Player("James", 1000, 750, 2000, 50, 1);
        assertEquals(84.0, player.progressByPercentage(), 0.1);
    }


}
