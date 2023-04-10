import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class StoryTest
{
    Passage passage;
    Passage otherPassage;
    Passage openingPassage;
    Link link;
    Story story;
    Map<Link, Passage> passages;
    @BeforeEach
    void setUp(){
        passage = new Passage("Passage title", "passage content");
        openingPassage = new Passage("Opening passage title", "opening passage content");
        otherPassage = new Passage("Other passage title", "other passage content");
        passages = new HashMap<>();
        link = new Link("Title link", "Reference link");
        story = new Story("Title of story", openingPassage);
    }

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
        /*passage.addLink(link);
        story.addPassage(passage);

        Passage passageToBeFoundInList = story.getPassage(link);

       assertNotNull(passageToBeFoundInList);
         */
    }
}
