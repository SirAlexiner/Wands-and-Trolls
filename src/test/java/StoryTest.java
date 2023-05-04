import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StoryTest
{
    Passage passage;
    Passage leftPassage;
    Passage rightPassage;
    Passage otherPassage;
    Passage openingPassage;
    Link link;
    Link otherLink;
    Story story;
    Story otherStory;
    Map<Link, Passage> passages;
    @BeforeEach
    void setUp(){
        passage = new Passage("Passage title", "passage content");
        openingPassage = new Passage("Opening passage title", "opening passage content");
        otherPassage = new Passage("Other passage title", "other passage content");
        leftPassage = new Passage("left passage title", "left passage content");
        passages = new HashMap<>();
        link = new Link("Title link", "Passage Title");
        otherLink = new Link("Title other link", "Other passage title");
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
    void testPosGetBrokenLinks() {
        passage.addLink(link);
        otherPassage.addLink(otherLink);

        story.addPassage(passage);
        story.addPassage(otherPassage);


        List<Link> linkList = story.getBrokenLinks();
        int actual = linkList.size();
        int expected = 0;

        assertEquals(expected, actual);

        passage.addLink(new Link("random","random"));
        linkList = story.getBrokenLinks();
        expected = 1;
        actual = linkList.size();

        assertEquals(expected, actual);

    }

    @Test
    void testNegGetBrokenLinks() {


    }

    @Test
    void testGetPassages() {
        otherStory = new Story("Other story title", openingPassage);
        passages.put(link, passage);

        passage.addLink(link);

        otherStory.addPassage(passage);
        story.addPassage(passage);

        String expected = otherStory.getPassages().toString();
        String actual = story.getPassages().toString();
        assertEquals(expected, actual);
    }
}
