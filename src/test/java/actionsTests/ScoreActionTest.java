package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.grp13.model.Player;
import no.ntnu.idatg2001.grp13.model.actions.ScoreAction;
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
    void testExecute() {
        ScoreAction otherScoreAction = new ScoreAction(100);
        otherScoreAction.execute(player);
        int actual = player.getScore();
        int expected = 100;
        // Positive
        assertEquals(expected, actual);
        // Negative
        int unexpected = 10;
        assertNotEquals(unexpected, actual);
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
/*
    @Test
    void testIsFulFilled() {
        scoreAction.execute(player);
        player.addScore(-1);
        // Negative
        assertFalse(scoreAction.isFulFilled(player));
        // Positive
        player.addScore(10);
        assertTrue(scoreAction.isFulFilled(player));
    }

 */
}

