import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.goals.GoldGoals;
import no.ntnu.idatg2001.gr13.goals.HealthGoal;
import no.ntnu.idatg2001.gr13.goals.InventoryGoal;
import no.ntnu.idatg2001.gr13.goals.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GoalsTest
{
    int gold = 10;
    int health = 10;
    int score = 10;
    String sword = "Sword";
    String shield = "Shield";
    List<String> expectedInventory;
    Player player;
    GoldGoals goldGoals;
    HealthGoal healthGoal;
    ScoreGoal scoreGoal;
    InventoryGoal inventoryGoal;
    boolean conditionToBeChecked;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", health, gold, score);
        expectedInventory = new ArrayList<>();
    }

    @Test
    void testIsFulFilledGoldGoals(){
        goldGoals = new GoldGoals(gold);
        conditionToBeChecked = goldGoals.isFulfilled(player);
        assertTrue(conditionToBeChecked);

        player.addGold(-1);
        conditionToBeChecked = goldGoals.isFulfilled(player);
        assertFalse(conditionToBeChecked);
    }

    @Test
    void testIsFulFilledHealthGoals(){
        healthGoal = new HealthGoal(health);
        conditionToBeChecked = healthGoal.isFulfilled(player);
        assertTrue(conditionToBeChecked);

        player.addHealth(-1);
        conditionToBeChecked = healthGoal.isFulfilled(player);
        assertFalse(conditionToBeChecked);
    }
    @Test
    void testIsFulFilledScoreGoals(){
        scoreGoal = new ScoreGoal(score);
        conditionToBeChecked = scoreGoal.isFulfilled(player);
        assertTrue(conditionToBeChecked);

        player.addScore(-1);
        conditionToBeChecked = scoreGoal.isFulfilled(player);
        assertFalse(conditionToBeChecked);
    }
}