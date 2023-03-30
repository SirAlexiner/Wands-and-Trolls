import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.gr13.Game;
import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.Story;
import no.ntnu.idatg2001.gr13.StoryFileHandler;
import no.ntnu.idatg2001.gr13.goals.Goal;
import no.ntnu.idatg2001.gr13.goals.GoldGoals;
import no.ntnu.idatg2001.gr13.goals.HealthGoal;
import org.junit.jupiter.api.Assertions;
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

    @BeforeEach
    void setUp(){
        filenameTest = "src/test/resources/test.paths";
        sameFileNameTest = "src/test/resources/testOther.paths";
        differentFileNameTest = "src/test/resources/testDifferentStory.paths";

        testStory = StoryFileHandler.readFromFile(filenameTest);
        sameTestStory = StoryFileHandler.readFromFile(sameFileNameTest);
        differentStory = StoryFileHandler.readFromFile(differentFileNameTest);

        // Create 1 passage
        Passage passage = new Passage("Enter this passage", "the content of this passage");
        passage.addLink(new Link("Enter test link", "exit test link reference"));
        passage.addLink(new Link("Exit test link", "exit test link reference"));

        // Create 2 passage
        Passage passage1 =
            new Passage("Go Left", "You've entered the room to the left and run into the troll");
        passage1.addLink(new Link("Enter test number 2 link", "exit test link number 2 reference"));
        passage1.addLink(new Link("Exit test number 2 link", "exit test link number 2 reference"));

        // Goals
        GoldGoals gold = new GoldGoals(50);

        // Story init
        story = new Story("Title of this test story", passage);

        story.addPassage(passage);
        story.addPassage(passage1);

    }
    /**
     * Tests the readFromFile(). Uses positive and negative test.
     */
    @Test
    void testReadFromFile()
    {
        // Reads two stories that are equal. Positive test.
        Assertions.assertTrue(testStory.equals(sameTestStory));

        // Negative test
        Assertions.assertFalse(testStory.equals(differentStory));
    }

    @Test
    void testWriteToFile()
    {

        // Creates player
        Player player = new Player("Test Player", 1, 2, 3);

        StoryFileHandler.writeToFile(story, "src/test/resources/testDifferentStory.paths");

        // TODO story object is not the same, returns therefore false
        if (story.equals("src/test/resources/testDifferentStory.paths"))
        {
            assert true;
        } else
        {
            assert false;
        }
       // Assertions.assertEquals(StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"), StoryFileHandler.readFromFile("src/test/resources/test.paths"));

        // TODO returns same Story object
        //Assertions.assertNotEquals(StoryFileHandler.readFromFile("src/test/resources/testOther.paths"), StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"));

        //Assertions.assertSame(StoryFileHandler.readFromFile("src/test/resources/testOther.paths"),
         //   StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"));
    }
}
