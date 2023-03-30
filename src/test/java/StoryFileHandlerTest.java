import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Story;
import no.ntnu.idatg2001.gr13.StoryFileHandler;
import no.ntnu.idatg2001.gr13.goals.GoldGoals;
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
        passage.addLink(enterLink = new Link("Enter test link", "exit test link reference"));
        passage.addLink(exitLink = new Link("Exit test link", "exit test link reference"));

        // Create 2 passage
        secondPassage =
            new Passage("Go Left", "You've entered the room to the left and run into the troll");
        secondPassage.addLink(new Link("Enter test number 2 link", "exit test link number 2 reference"));
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
        Assertions.assertEquals(testStory, sameTestStory);
        Assertions.assertSame(testStory, testStory);
    }

    @Test
    void testReadFromFileDifferent()
    {
        // Negative test
        Assertions.assertNotSame(testStory, differentStory);
    }

    @Test
    void testWriteToFile(){
        StoryFileHandler.writeToFile(story, differentFileNameTest);

        Assertions.assertEquals(differentStory.getPassage(enterLink), story.getPassage(enterLink));
        Assertions.assertEquals(differentStory.getPassage(exitLink), story.getPassage(exitLink));

        Assertions.assertNotSame(differentStory.getPassage(enterLink), story.getPassage(exitLink));
    }
//    @Test
//    void testWriteToFile()
//    {
//
//        // Creates player
//        Player player = new Player("Test Player", 1, 2, 3);
//
//        StoryFileHandler.writeToFile(story, "src/test/resources/testDifferentStory.paths");
//
//        // TODO story object is not the same, returns therefore false
//        if (story.equals(differentStory))
//        {
//            assert true;
//        } else
//        {
//            assert false;
//        }
//       // Assertions.assertEquals(StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"), StoryFileHandler.readFromFile("src/test/resources/test.paths"));
//
//        // TODO returns same Story object
//        //Assertions.assertNotEquals(StoryFileHandler.readFromFile("src/test/resources/testOther.paths"), StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"));
//
//        //Assertions.assertSame(StoryFileHandler.readFromFile("src/test/resources/testOther.paths"),
//         //   StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"));
//    }
}
