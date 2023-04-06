import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
