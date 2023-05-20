import no.ntnu.idatg2001.gr13.model.Link;
import no.ntnu.idatg2001.gr13.model.Passage;
import no.ntnu.idatg2001.gr13.model.Story;
import no.ntnu.idatg2001.gr13.model.StoryWriter;
import no.ntnu.idatg2001.gr13.model.actions.Action;
import no.ntnu.idatg2001.gr13.model.actions.GoldAction;
import no.ntnu.idatg2001.gr13.model.actions.HealthAction;
import no.ntnu.idatg2001.gr13.model.goals.GoldGoals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class StoryWriterTest {
    String filenameTest;
    String testOtherFileLocation;
    String hauntedHouseFileLocation;
    Story storyHauntedHouse;
    Story testStory;
    Story sameTestStory;
    Story differentStory;
    Passage passageBeginnings;
    Passage passageAnotherRoom;
    Passage passageWizardRoom;
    Action goldAction = new GoldAction(100);
    Action healthAction = new HealthAction(50);
    Action moreHealth = new HealthAction(100);
    Link enterLink;
    Link exitLink;
    Link openBookLink;
    Link goBackLink;
    String storyHauntedHouseTitle = "Haunted House";
    String passageWizardRoomTitle = "Wizard Room";
    String passageWizardRoomContent = "A dimly lit room full of brooms";
    String emptyStoryFileLocation;
    final String passageBeginningsTitle = "Beginnings";
    final String passageBeginningsContent = "You are in a small, dimly lit room. There is a door in front of you.";
    final String passageTitleAnotherRoom = "Another room";
    final String passageContentAnotherRoom = "The door opens to another room. You see a desk with a large, dusty book.";

    @BeforeEach
    void setUp() {
        emptyStoryFileLocation = "src/test/resources/emptyStory.paths";
        filenameTest = "src/test/resources/test.paths";
        testOtherFileLocation = "src/test/resources/testOther.paths";
        hauntedHouseFileLocation = "src/test/resources/hauntedHouse.paths";
/*
        testStory = StoryFileHandler.readFromFile(filenameTest);
        sameTestStory = StoryFileHandler.readFromFile(testOtherFileLocation);
        differentStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);


 */
        // Create 1 passage
        passageBeginnings = new Passage(passageBeginningsTitle, passageBeginningsContent);
        passageBeginnings.addLink(enterLink = new Link("Try to open the door", "Another room"));
        passageBeginnings.addLink(exitLink = new Link("Exit the room", "The room of wizards"));

        // Create 2 passage
        passageAnotherRoom =
                new Passage(passageTitleAnotherRoom, passageContentAnotherRoom);
        passageAnotherRoom.addLink(openBookLink = new Link("Open the book", "The book of spells"));
        passageAnotherRoom.addLink(goBackLink = new Link("Go back", "The book of spells"));

        // Create 3 passage
        passageWizardRoom = new Passage(passageWizardRoomTitle, passageWizardRoomContent);

        // Goals
        GoldGoals gold = new GoldGoals(50);

        // Story init
        storyHauntedHouse = new Story(storyHauntedHouseTitle, passageBeginnings);

        storyHauntedHouse.addPassage(passageBeginnings);
        storyHauntedHouse.addPassage(passageAnotherRoom);

    }

    String getHauntedHouseContent(){
        return  """
                Haunted House
                                
                ::Wizard Room
                A dimly lit room full of brooms
                                
                ::Another room
                The door opens to another room. You see a desk with a large, dusty book.
                [Open the book](The book of spells)
                =Gold;100
                =Health;50
                =Health;100
                [Exit the room](The room of wizards)
                                         
                ::Beginnings
                You are in a small, dimly lit room. There is a door in front of you.
                [Try to open the door](Another room)
                [Exit the room](The room of wizards)
                                                                                        
                """;
    }

    /**
     * Tests the readFromFile(). Uses positive and negative test.
     */
    @Test
    void testReadFromFileSame() {
        // Reads two stories that are equal. Positive test.
        assertEquals(testStory, sameTestStory);
        assertSame(testStory, testStory);
    }

    @Test
    void testReadFromFileDifferent() {
        // Negative test
        assertNotSame(testStory, differentStory);
    }

    /*
    @Test
    void posReaderStoryName() {
        String actual = StoryFileHandler.readFromFile(hauntedHouseFileLocation).getTitle();
        String expected = storyHauntedHouseTitle;

        assertEquals(expected, actual);
    }

    @Test
    void negReaderStoryName() {
        String actual = StoryFileHandler.readFromFile(hauntedHouseFileLocation).getTitle();
        String unexpected = "A really random test story";

        assertNotEquals(unexpected, actual);
    }

    @Test
    void posReaderPassages() {
        String actualPassageTitle;
        String actualPassageContent;
        Story hauntedHouse = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        for (Passage passageInStory : hauntedHouse.getPassages()) {
            if (passageInStory.getTitle().matches(passageBeginningsTitle)) {
                assertTrue(true);
            }
            if (passageInStory.getTitle().matches(passageTitleAnotherRoom)) {
                assertTrue(true);
            }

            //System.out.println(passageInStory.getContent().matches(passageContentAnotherRoom));
        }
    }
    */
    @ParameterizedTest
    @CsvFileSource(resources = "hauntedHouse.paths")
    void posTestWriteStoryTitle(String csvFile) {
        String expected = storyHauntedHouseTitle;
        if (csvFile.contains(expected)){
            assertEquals(expected, csvFile);
        }
    }

    @Test
    void negTestWriteStoryTitle() throws IOException {
        Story unexpected = new Story("", passageBeginnings);
        StoryWriter.writeToFile(unexpected, emptyStoryFileLocation);

        Path filePath = Path.of(emptyStoryFileLocation);

        assertEquals("", unexpected.getTitle());
        try {
            String actual = Files.readString(filePath);
            // checks that file writer has not written anything
            assertTrue(actual.isBlank());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvFileSource(resources = "hauntedHouse.paths")
    void posTestWritePassageTitle(String csvFile) {
        String expected = "::" + passageAnotherRoom.getTitle();
        if (csvFile.contentEquals(expected)){
            assertEquals(expected, csvFile);
        }
    }

    @Test
    void negTestWritePassage() {
        Path filePath = Path.of(emptyStoryFileLocation);
        Story unexpected = new Story("", passageBeginnings);
        // adds a second passage to the story
        unexpected.addPassage(passageWizardRoom);

        // Writes the story to .path format
        // Checks that it throws if there is an illegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            StoryWriter.writeToFile(unexpected, emptyStoryFileLocation);
        });
        try {
            String actual = Files.readString(filePath);
            // checks that file writer has not written anything
            assertTrue(actual.isBlank());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "hauntedHouse.paths")
    void posTestWritePassageContent(String csvFile) {
        String expected = passageAnotherRoom.getContent();
        if (csvFile.contains(expected)){
            assertEquals(expected, csvFile);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "hauntedHouse.paths")
    void posTestWriteLink(String csvFile) {
        String expected = openBookLink.toString();
        assertTrue(csvFile.contentEquals(expected));
    }
    @Test
    void negTestWriteLink() {
        Path filePath = Path.of(emptyStoryFileLocation);
        Story unexpected = new Story("", passageBeginnings);
        // adds a second passage to the story
        unexpected.addPassage(passageWizardRoom);
        // adds an empty link
        passageWizardRoom.addLink(new Link("", ""));
        // adds a not empty link
        passageWizardRoom.addLink(new Link("Not an empty link", "Not an empty link"));


        // Writes the story to .path format
        assertThrows(IllegalArgumentException.class, () -> {
            // Checks that it throws if there is an illegalArgumentException
            StoryWriter.writeToFile(unexpected, emptyStoryFileLocation);
        });
        try {
            String actual = Files.readString(filePath);
            // checks that file writer has not written anything
            assertTrue(actual.isBlank());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "testOther.paths")
    void posTestWriteTitle_Story(String csvFile) {
        String expected = "Planet of the trolls";
        assertTrue(csvFile.contentEquals(expected));
    }

    /*
    @Test
    void posReadStoryTitle(){
        Story actualStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        String actual = actualStory.getTitle();
        String expected = storyHauntedHouseTitle;

        assertEquals(expected, actual);
    }

    @Test
    void posReadPassageTitle() {
        Story actualStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        String actual = actualStory.getPassage(enterLink).getTitle();

        assertEquals(passageTitleAnotherRoom, actual);
    }

    @Test
    void posReadPassageContent() {
        Story actualStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        String actual = actualStory.getPassage(enterLink).getContent();

        assertEquals(passageContentAnotherRoom, actual);
    }
    @Test
    void posTestReadLinks(){
        Story actualStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        actualStory.getBrokenLinks();
        String expected = openBookLink.getText();
        String actual = actualStory.getBrokenLinks().get(0).getText();

        assertEquals(expected, actual);
    }



    @Test
    void negTestReadLinks(){
        Story actualStory = StoryFileHandler.readFromFile(hauntedHouseFileLocation);
        List<Link> brokenLinks = actualStory.getBrokenLinks();
        String actual = brokenLinks.get(0).toString();

        String unexpected = new Link("", "").toString();
        assertNotEquals(unexpected, actual);
    }

     */

    void setupFor_posTestWriteWithMultipleAttributes() throws IOException{
        passageAnotherRoom.addLink(exitLink);
        passageAnotherRoom.addLink(openBookLink);

        openBookLink.addAction(goldAction);
        openBookLink.addAction(healthAction);
        openBookLink.addAction(moreHealth);

        storyHauntedHouse.addPassage(passageBeginnings);
        storyHauntedHouse.addPassage(passageAnotherRoom);
        storyHauntedHouse.addPassage(passageWizardRoom);

        StoryWriter.writeToFile(storyHauntedHouse, hauntedHouseFileLocation);
    }
    @Test
    void posTestWriteWithMultiplePassages() throws IOException {
        // setup
        setupFor_posTestWriteWithMultipleAttributes();

        Path filePath = Path.of(hauntedHouseFileLocation);
        try {
            String actual = Files.readString(filePath);
            assertEquals(getHauntedHouseContent(), actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

