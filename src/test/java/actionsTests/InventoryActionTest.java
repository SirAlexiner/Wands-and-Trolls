package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class InventoryActionTest {
    String item = "Item example";
    Player player;
    InventoryAction inventoryAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 10, 10, 10);
        inventoryAction = new InventoryAction(item);
    }

    @Test
    void testExecute() {
        String otherItem = "Other item";
        InventoryAction otherInventoryAction = new InventoryAction(otherItem);
        otherInventoryAction.execute(player);
        List<String> actual = player.getInventory();
        List<String> expected = new ArrayList<>();
        expected.add(otherItem);
        // Positive
        assertEquals(expected, actual);
        // Negative
        expected.remove(otherItem);
        assertNotEquals(expected, actual);
    }

    @Test
    void testIsFulFilled() {
        // Negative
        boolean notCorrect = inventoryAction.isFulFilled(player);
        assertFalse(notCorrect);
        // Positive
        player.addToInventory(item);
        boolean actual = inventoryAction.isFulFilled(player);
        assertTrue(actual);
    }

    @Test
    void testGetActionType() {
        String expected = "Inventory";
        String actual = inventoryAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = item;
        String actual = inventoryAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }
}

