import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class StoryTest
{
    Passage passage;
    Passage leftPassage;
    Passage rightPassage;
    Passage otherPassage;
    Passage openingPassage;
    Link passageLink;
    Link leftLink;
    Link otherLink;
    Link openingLink;
    Story story;
    Story otherStory;
    Map<Link, Passage> passages;
    @BeforeEach
    void setUp(){
        String content = "content";
        String passageTitle = "Passage title";
        String openingPassageTitle = "Opening passage title";
        String otherPassageTitle = "Other passage title";
        String leftPassageTitle = "left passage title";

        passage = new Passage(passageTitle, content);
        openingPassage = new Passage(openingPassageTitle, content);
        otherPassage = new Passage(otherPassageTitle, content);
        leftPassage = new Passage(leftPassageTitle, content);

        passageLink = new Link("Title link", passageTitle);
        openingLink = new Link("Text of opening link", openingPassageTitle);
        otherLink = new Link("Title other link", otherPassageTitle);
        leftLink = new Link("Text left link", leftPassageTitle);

        story = new Story("Title of story", openingPassage);
    }

    /*

    @Test
    void testRemovePassage(){
        story.addPassage(openingPassage);
        passage.addLink(link);
        otherPassage.addLink(link);

        int expected = story.getPassages().size();
        story.removePassage(link);
        int actual = story.getPassages().size();
        assertEquals(expected, actual); // Checks if the to list are the same size

        story.addPassage(leftPassage);
        int unExpected = story.getPassages().size();

        story.removePassage(otherLink);
        actual = story.getPassages().size();
        assertNotEquals(unExpected, actual); // Checks that the list are not the same size
    }

     */

    @Test
    void testAddPassage(){
        story.addPassage(otherPassage);

        Passage retrivedPassage = story.getPassage(new Link("", "Other passage title"));

        assertNotNull(retrivedPassage);
        assertEquals("Other passage title", retrivedPassage.getTitle());
        assertEquals("other passage content", retrivedPassage.getContent());
    }

    @Test
    void testGetPassage() {
        // TODO check this implementation
        /*passage.addLink(link);
        story.addPassage(passage);

        Passage passageToBeFoundInList = story.getPassage(link);

       assertNotNull(passageToBeFoundInList);
         */
    }

    @Test
    void testNegRemovePassage() {
        passage.addLink(leftLink);
        story.addPassage(leftPassage);
        story.addPassage(passage);

        story.removePassage(leftLink);
        int actual = story.getPassages().size();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    void testPosRemovePassage() {
        story.addPassage(passage);
        story.addPassage(leftPassage);

        passage.addLink(leftLink);
        leftPassage.addLink(leftLink);

        story.removePassage(passageLink);
        int expected = 1;
        int actual = story.getPassages().size();

        assertEquals(expected, actual);
    }

    @Test
    void testGetBrokenLinks() {
        Passage testPassage = new Passage("TITLE", "CONTENT");
        Passage comparingPassage = new Passage("OTHER TITLE", "OTHER CONTENT");

        Link testLink = new Link("TITLE", "TITLE");
        Link comparingLink = new Link("OTHER TITLE", "OTHER TITLE");

        testPassage.addLink(testLink);
        comparingPassage.addLink(comparingLink);

        story.addPassage(testPassage);
        story.addPassage(comparingPassage);

        int actual = story.getBrokenLinks().size();

        assertEquals(0, actual);

        Link wrong = new Link("WRONG", "WRONG");
        testPassage.addLink(wrong);
        actual = story.getBrokenLinks().size();


        assertEquals(1, actual);
    }

    @Test
    void testGetPassages() {
        otherStory = new Story("Other story title", openingPassage);
        passages.put(passageLink, passage);

        passage.addLink(passageLink);

        otherStory.addPassage(passage);
        story.addPassage(passage);

        String expected = otherStory.getPassages().toString();
        String actual = story.getPassages().toString();
        assertEquals(expected, actual);
    }
}
