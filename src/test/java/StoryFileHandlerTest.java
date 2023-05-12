import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import no.ntnu.idatg2001.gr13.model.Link;
import no.ntnu.idatg2001.gr13.model.Passage;
import no.ntnu.idatg2001.gr13.model.Story;
import no.ntnu.idatg2001.gr13.model.StoryFileHandler;
import no.ntnu.idatg2001.gr13.model.goals.GoldGoals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryFileHandlerTest
{
    String filenameTest;
    String sameFileNameTest;
    String differentFileNameTest;
    Story story;
    Story testStory;
    Story sameTestStory;
    Story differentStory;
    Passage passage;
    Passage secondPassage;
    Link enterLink;
    Link exitLink;

    @BeforeEach
    void setUp(){
        filenameTest = "src/test/resources/test.paths";
        sameFileNameTest = "src/test/resources/testOther.paths";
        differentFileNameTest = "src/test/resources/testDifferentStory.paths";

        testStory = StoryFileHandler.readFromFile(filenameTest);
        sameTestStory = StoryFileHandler.readFromFile(sameFileNameTest);
        differentStory = StoryFileHandler.readFromFile(differentFileNameTest);

        // Create 1 passage
        passage = new Passage("Enter this passage", "the content of this passage");
        passage.addLink(enterLink = new Link("Enter test link", "enter test link reference"));
        passage.addLink(exitLink = new Link("Exit test link", "exit test link reference"));

        // Create 2 passage
        secondPassage =
            new Passage("Go Left", "You've entered the room to the left and run into the troll");
        secondPassage.addLink(new Link("Enter test number 2 link", "enter test link number 2 reference"));
        secondPassage.addLink(new Link("Exit test number 2 link", "exit test link number 2 reference"));

        // Goals
        GoldGoals gold = new GoldGoals(50);

        // Story init
        story = new Story("Title of this test story", passage);

        story.addPassage(passage);
        story.addPassage(secondPassage);

    }
    /**
     * Tests the readFromFile(). Uses positive and negative test.
     */
    @Test
    void testReadFromFileSame()
    {
        // Reads two stories that are equal. Positive test.
        assertEquals(testStory, sameTestStory);
        assertSame(testStory, testStory);
    }

    @Test
    void testReadFromFileDifferent()
    {
        // Negative test
        assertNotSame(testStory, differentStory);
    }

    // TODO this test might needs some additional functionality.
    @Test
    /*
    Test if the objects that are written and read are the same.
     */
    void testWriteToFile(){
        // Writes a story to a "filename"
        StoryFileHandler.writeToFile(story, differentFileNameTest);
        // The "differentStory" object is created from the previous line.
        differentStory = StoryFileHandler.readFromFile(differentFileNameTest);

        Passage differentStoryPassage = differentStory.getPassage(enterLink);
        Passage storyPassage = story.getPassage(enterLink);
        // Checks if the objects are the same using the overwritten equals.
        assertEquals(differentStoryPassage, storyPassage);
        assertEquals(story, differentStory);
        assertNotEquals(differentStory, testStory);
        // Checks if the story title is the same.
        assertEquals(story.getTitle(), differentStory.getTitle());

        // Checks if every object inside the list in the different story are the same
        for (int i = 0; i < story.getOpeningPassage().getLinks().size(); i++)
        {
            Link link = story.getOpeningPassage().getLinks().get(i);
            Link differentLink = differentStory.getOpeningPassage().getLinks().get(i);
            assertEquals(link, differentLink);

            assertEquals(link.getActions(), differentLink.getActions());
        }
    }
}
