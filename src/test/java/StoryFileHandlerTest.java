import no.ntnu.idatg2001.gr13.model.Link;
import no.ntnu.idatg2001.gr13.model.Passage;
import no.ntnu.idatg2001.gr13.model.Story;
import no.ntnu.idatg2001.gr13.model.StoryFileHandler;
import no.ntnu.idatg2001.gr13.model.goals.GoldGoals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class StoryFileHandlerTest
{
    String filenameTest;
    String sameFileNameTest;
    String hauntedHouseTest;
    Story storyHauntedHouse;
    Story testStory;
    Story sameTestStory;
    Story differentStory;
    Passage passageBeginnings;
    Passage passageAnotherRoom;
    Link enterLink;
    Link exitLink;
    String storyHauntedHouseTitle = "Haunted House";
    String passageBeginningsTitle = "Beginnings";
    String passageBeginningsContent = "You are in a small, dimly lit room. There is a door in front of you.";
    String passageTitleAnotherRoom = "Another room";
    String passageContentAnotherRoom = "The door opens to another room. You see a desk with a large, dusty book.";

    String content =
            "Haunted House\n" +
            "\n" +
            "::Another room\n" +
            "The door opens to another room. You see a desk with a large, dusty book.\n" +
            "[Open the book](The book of spells)\n" +
            "\n" +
            "::Beginnings\n" +
            "You are in a small, dimly lit room. There is a door in front of you.\n" +
            "[Try to open the door](Another room)\n" +
            "[Exit the room](The book of spells)";


    @BeforeEach
    void setUp(){
        filenameTest = "src/test/resources/test.paths";
        sameFileNameTest = "src/test/resources/testOther.paths";
        hauntedHouseTest = "src/test/resources/hauntedHouse.paths";

        testStory = StoryFileHandler.readFromFile(filenameTest);
        sameTestStory = StoryFileHandler.readFromFile(sameFileNameTest);
        differentStory = StoryFileHandler.readFromFile(hauntedHouseTest);

        // Create 1 passage
        passageBeginnings = new Passage(passageBeginningsTitle, passageBeginningsContent);
        passageBeginnings.addLink(enterLink = new Link("Try to open the door", "Another room"));
        passageBeginnings.addLink(exitLink = new Link("Exit the room", "The book of spells"));

        // Create 2 passage
        passageAnotherRoom =
            new Passage(passageTitleAnotherRoom, passageContentAnotherRoom);
        passageAnotherRoom.addLink(new Link("Open the book", "The book of spells"));
        passageAnotherRoom.addLink(new Link("Go back", "The book of spells"));

        // Goals
        GoldGoals gold = new GoldGoals(50);

        // Story init
        storyHauntedHouse = new Story(storyHauntedHouseTitle, passageBeginnings);

        storyHauntedHouse.addPassage(passageBeginnings);
        storyHauntedHouse.addPassage(passageAnotherRoom);

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

    /*String setUpForCsvWriter(String stringToBeMatched) {

        StoryFileHandler.writeToFile(story, differentFileNameTest);
        Path path = Path.of(differentFileNameTest);

        String actualStoryName = null;
        try {
            // Read the CSV file and convert its content into a stream of lines
            Optional<String> storyName = Files.lines(path)
                    .collect(l.get -> )
            actualStoryName = storyName.orElse("does not appear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actualStoryName;
    }

     */

    @ParameterizedTest
    @CsvFileSource(resources = "hauntedHouse.paths")
    void checkCsvFileSource(
        String input, String expected) {
            String actualValue = input;
            assertEquals(expected, actualValue);
    }

    @Test
    void posReaderStoryName(){
        String actual = StoryFileHandler.readFromFile(hauntedHouseTest).getTitle();
        String expected = storyHauntedHouseTitle;

        assertEquals(expected, actual);
    }

    @Test
    void negReaderStoryName() {
        String actual = StoryFileHandler.readFromFile(hauntedHouseTest).getTitle();
        String unexpected = "A really random test story";

        assertNotEquals(unexpected, actual);
    }
/*
    @Test
    void testWriteToFile() {
        String actual = setUpForCsvWriter(story.getTitle());
        String expected = story.getTitle();

        assertEquals(expected, actual);

        actual = setUpForCsvWriter(content);
        expected = content;

        assertEquals(expected, actual);
    }

 */


    /*
    @Test
    void testWriteToFile(String stringToBeMatched) {
        StoryFileHandler.writeToFile(story, differentFileNameTest);
        Path path = Path.of(differentFileNameTest);
        
        String actualStoryName = null;
        try {
            // Read the CSV file and convert its content into a stream of lines
            Optional<String> storyName = Files.lines(path)
                    .filter(line -> line.matches(story.getTitle()))
                    .findFirst();
            actualStoryName = storyName.orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(story.getTitle(), actualStoryName);

    }

     */
    /*
    Test if the objects that are written and read are the same.
     */
    /*
    void testWriteToFile(){
        // Writes a story to a "filename"
        StoryFileHandler.writeToFile(story, differentFileNameTest);
        // The "differentStory" object is created from the previous line.
        differentStory = StoryFileHandler.readFromFile(differentFileNameTest);

        Passage differentStoryPassage = differentStory.getPassage(enterLink);
        Passage storyPassage = story.getPassage(enterLink);
        // Checks if the objects are the same using the overwritten equals.
        assertEquals(differentStoryPassage, storyPassage);
        //assertEquals(story, differentStory);
        assertNotEquals(differentStory, testStory);
        // Checks if the story title is the same.
        assertEquals(story.getTitle(), differentStory.getTitle());

        // Checks if every object inside the list in the different story are the same
        for (int i = 0; i < story.getOpeningPassage().getLinks().size(); i++)
        {
            Link link = story.getOpeningPassage().getLinks().get(i);
            Link differentLink = differentStory.getOpeningPassage().getLinks().get(i);
            //assertEquals(link, differentLink);

            assertEquals(link.getActions(), differentLink.getActions());
        }
    }

     */
}
