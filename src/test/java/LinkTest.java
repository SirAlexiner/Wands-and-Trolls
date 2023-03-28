import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.actions.Action;
import no.ntnu.idatg2001.gr13.actions.GoldAction;
import no.ntnu.idatg2001.gr13.actions.InventoryAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkTest {

    Link link;

    @BeforeEach
    void setUp(){
        link = new Link("Test link", "test reference");
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
}