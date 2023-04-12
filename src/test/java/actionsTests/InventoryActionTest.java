package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testCanExecute() {
        //Negative
        boolean actual = inventoryAction.canExecute(player);
        assertFalse(actual);
        // Positive
        player.addToInventory(item);
        actual = inventoryAction.canExecute(player);
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

