package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ScoreActionTest {
    int score = 0;
    Player player;
    ScoreAction scoreAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 10, 10, score);
        scoreAction = new ScoreAction(score);
    }

    @Test
    void testCanExecute() {
        boolean actual = scoreAction.canExecute(player);
        assertFalse(actual);

        Player emptyPlayer = null;
        boolean nullPlayer = scoreAction.canExecute(emptyPlayer);
        assertFalse(nullPlayer);
    }

    @Test
    void testGetActionType() {
        String expected = "Score";
        String actual = scoreAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = "0";
        String actual = scoreAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }
}

