import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {
    Player player;
    String name = "Test Player";
    int health = 10;
    int gold = 9;
    List<String> inventory = new ArrayList<>();
    int score = 0;

    @BeforeEach
    void setUp() {
        player = new Player(name, health, gold, score);
    }

    @Test
    void testAddHealth() {
        int newHealth = 100;
        player.addHealth(newHealth);
        int actual = player.getHealth();
        int expected = 110;

        assertEquals(expected, actual);
    }

    @Test
    void testAddGold() {
        int newAmountOfGold = 100;
        player.addGold(newAmountOfGold);
        int actual = player.getGold();
        int expected = 109;

        assertEquals(expected, actual);
    }
}
