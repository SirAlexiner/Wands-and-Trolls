import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoldActionTest {
    int gold = 0;
    Player player;
    GoldAction goldAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 10, gold, 10);
        goldAction = new GoldAction(gold);
    }

    @Test
    void testCanExecute() {
        boolean actual = goldAction.canExecute(player);
        assertFalse(actual);

        Player emptyPlayer = null;
        boolean nullPlayer = goldAction.canExecute(emptyPlayer);
        assertFalse(nullPlayer);
    }

    @Test
    void testGetActionType() {
        String expected = "Gold";
        String actual = goldAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Health";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = "0";
        String actual = goldAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Health";
        assertNotEquals(unexpected, actual);
    }
}
