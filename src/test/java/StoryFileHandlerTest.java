import no.ntnu.idatg2001.gr13.StoryFileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StoryFileHandlerTest
{
    /**
     * Tests the readFromFile(). Uses positive and negative test.
     */
    @Test
    void testReadFromFile(){
        // Reads two stories that are equal. From the resources' folder.
        Assertions.assertEquals(StoryFileHandler.readFromFile("src/test/resources/test.paths"), StoryFileHandler.readFromFile("src/test/resources/testOther.paths"));
        // Reads two different stories from the resources' folder.
        Assertions.assertFalse(StoryFileHandler.readFromFile("src/test/resources/test.paths") != StoryFileHandler.readFromFile("src/test/resources/testDifferentStory.paths"));
    }
}
