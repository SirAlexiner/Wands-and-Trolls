package fileHandlerTests;

import no.ntnu.idatg2001.grp13.model.*;
import no.ntnu.idatg2001.grp13.model.actions.Action;
import no.ntnu.idatg2001.grp13.model.actions.GoldAction;
import no.ntnu.idatg2001.grp13.model.actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class StoryWriterTest {
    String hauntedHouseFileLocation;
    Story storyHauntedHouse;
    Passage passageBeginnings;
    Passage passageAnotherRoom;
    Passage passageWizardRoom;
    Action goldAction;
    Action healthAction;
    Action moreHealth;
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
        hauntedHouseFileLocation = "src/test/resources/hauntedHouse.paths";


        // Actions
        goldAction = new GoldAction(100);
        healthAction = new HealthAction(50);
        moreHealth = new HealthAction(100);
        addPassagesToStory();
    }
    void addPassagesToStory(){
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


        // Story init
        storyHauntedHouse = new Story(storyHauntedHouseTitle, passageBeginnings);

        // Adds passages
        storyHauntedHouse.addPassage(passageAnotherRoom);
        storyHauntedHouse.addPassage(passageWizardRoom);
    }

    @Test
    void posTestWriteLink() {
        Link expected = new Link("Random", "Random");
        passageBeginnings.addLink(expected);
        StoryWriter.writeToFile(storyHauntedHouse, hauntedHouseFileLocation);
        Path filePath = Path.of(hauntedHouseFileLocation);

        try {
            String actual = Files.readString(filePath);
            assertTrue(actual.contains(expected.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Test
    void negTestWriteStoryTitle() {
        Story unexpected = new Story("", passageBeginnings);
        Path filePath = Path.of(emptyStoryFileLocation);


        assertThrows(IllegalArgumentException.class, () -> StoryWriter.writeToFile(unexpected, emptyStoryFileLocation));

        assertEquals("", unexpected.getTitle());
        try {
            String actual = Files.readString(filePath);
            // checks that file writer has not written anything
            assertTrue(actual.isBlank());
        } catch (IOException e) {
            e.printStackTrace();
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
        assertThrows(IllegalArgumentException.class, () -> StoryWriter.writeToFile(unexpected, emptyStoryFileLocation));
        try {
            String actual = Files.readString(filePath);
            // checks that file writer has not written anything
            assertTrue(actual.isBlank());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    void posTestWritePassage() {
        Passage expected = new Passage("Random", "Random");
        storyHauntedHouse.addPassage(expected);
        StoryWriter.writeToFile(storyHauntedHouse, hauntedHouseFileLocation);
        Path filePath = Path.of(hauntedHouseFileLocation);

        try {
            String actual = Files.readString(filePath);
            assertTrue(actual.contains(expected.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void posTestWriteAction() {
        enterLink.addAction(goldAction);
        enterLink.addAction(healthAction);
        String expected = goldAction.getActionType();
        String expected_2 = healthAction.getActionType();
        String expectedValue = goldAction.getActionValue();
        String expectedValue_2 = healthAction.getActionValue();

        StoryWriter.writeToFile(storyHauntedHouse, hauntedHouseFileLocation);
        Path filePath = Path.of(hauntedHouseFileLocation);

        try {
            String actual = Files.readString(filePath);
            assertTrue(actual.contains(expected));
            assertTrue(actual.contains(expected_2));
            assertTrue(actual.contains(expectedValue));
            assertTrue(actual.contains(expectedValue_2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}