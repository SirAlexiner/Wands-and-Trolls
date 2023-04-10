import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class HealthActionTest {
    int health = 0;
    Player player;
    HealthAction healthAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", health, 10, 10);
        healthAction = new HealthAction(health);
    }

    @Test
    void testCanExecute() {
        boolean actual = healthAction.canExecute(player);
        assertFalse(actual);

        Player emptyPlayer = null;
        boolean nullPlayer = healthAction.canExecute(emptyPlayer);
        assertFalse(nullPlayer);
    }

    @Test
    void testGetActionType() {
        String expected = "Health";
        String actual = healthAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = "0";
        String actual = healthAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }
}
