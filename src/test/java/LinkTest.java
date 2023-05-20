import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.grp13.model.Link;
import no.ntnu.idatg2001.grp13.model.actions.Action;
import no.ntnu.idatg2001.grp13.model.actions.GoldAction;
import no.ntnu.idatg2001.grp13.model.actions.InventoryAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkTest {

    Link link;
    Link linkTest;
    Link linkCorrect;
    Link linkNotCorrect;

    @BeforeEach
    void setUp(){
        link = new Link("Test link", "test reference");
        linkTest = new Link("Arthur", "Room 1");
        linkCorrect = new Link("Burger", "Room 1");
        linkNotCorrect = new Link("Torgrim", "Room 3");
    }

    @Test
    void correctEqualsTest(){
        boolean expectedVal = true;
        boolean returnedVal = linkTest.equals(linkCorrect);
        assertEquals(expectedVal,returnedVal);
    }

    @Test
    void notCorrectEqualsTest(){
        boolean expectedVal = false;
        boolean returnedVal = linkTest.equals(linkNotCorrect);
        assertEquals(expectedVal,returnedVal);
    }
    @Test
    /*
      Positive and negative test for addAction. Checks the size of the list.
     */
    void testAddAction(){
        Action action = new GoldAction(50);
        Action inventoryAction = new InventoryAction("Sword");
        link.addAction(action);

        // Positive test
        Assertions.assertEquals(1, link.getActions().size());
        // Negative test
        Assertions.assertNotEquals(0, link.getActions().size());
        link.addAction(inventoryAction);
        Assertions.assertNotEquals(0, link.getActions().size());
    }

    @Test
    void testGetActions(){
        Action inventoryAction = new InventoryAction("Sword");
        link.addAction(inventoryAction);

        List<Action> expectedActions = new ArrayList<>();
        expectedActions.add(inventoryAction);

        List<Action> actualActions = link.getActions();

        // Positive test
        Assertions.assertEquals(expectedActions,actualActions);

        List<Action> emptyList = new ArrayList<>();
        // Negative test
        Assertions.assertNotEquals(emptyList,actualActions);
    }

    @Test
    void testHasAction(){
        Action inventoryAction = new InventoryAction("Sword");
        Assertions.assertFalse(link.hasAction());

        link.addAction(inventoryAction);
        Assertions.assertTrue(link.hasAction());
    }

    @Test
    void testToString(){
        String text = link.getText();
        String reference = link.getReference();
        // Positive test
        String howItShouldLook = "[" + text + "]" + "(" + reference + ")";
        String howItLooks = link.toString();
        // Negative test
        String howItShouldNotLook = "[" + reference + "]" + "(" + text + ")";
        assertEquals(howItShouldLook, howItLooks);
        assertNotEquals(howItShouldNotLook, howItLooks);
    }
}