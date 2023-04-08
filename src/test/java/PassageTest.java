import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PassageTest {
    String title;
    String content;
    List<Link> links;
    Link link;
    Link otherLink;
    Passage passage;
    Passage wrongPassage;

    @BeforeEach
    void setUp(){
        title = "Title of passage";
        content = "Content of passage";

        link = new Link("text","reference");
        otherLink = new Link("text", "other reference");
        links = new ArrayList<>();
        passage = new Passage(title, content);
        wrongPassage = new Passage(content, title);
    }

    @Test
    void testAddLink(){
        // Positive, adding link to list
        boolean successfullyAdded = passage.addLink(link);
        boolean successfullyAddedSecondLink = passage.addLink(otherLink);
        assertTrue(successfullyAdded);
        assertTrue(successfullyAddedSecondLink);
        // Negative, link already exist will therefore become false
        boolean notSuccess = passage.addLink(link);
        assertFalse(notSuccess);
    }

    @Test
    void testHasLinks(){
        boolean listIsEmpty = passage.hasLinks();
        assertFalse(listIsEmpty);

        passage.addLink(link);
        boolean listIsNotEmpty = passage.hasLinks();
        assertTrue(listIsNotEmpty);
    }

    @Test
    void testGetLink(){
        passage.addLink(link);
        String emptyCommand = "";
        assertNull(passage.getLink(emptyCommand));

        passage.addLink(otherLink);
        String commandForLink = "other reference";
        assertNotNull(passage.getLink(commandForLink));
    }

    @Test
    void testToString() {
        String correctFormat = "::" + title + "\n" + content;
        assertEquals(correctFormat, passage.toString());

        String notCorrectFormat = "::" + content + "\n" + title;
        assertNotEquals(notCorrectFormat, passage.toString());
    }
}
