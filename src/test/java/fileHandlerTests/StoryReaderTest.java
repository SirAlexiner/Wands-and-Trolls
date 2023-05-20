package fileHandlerTests;

import no.ntnu.idatg2001.grp13.model.Story;
import no.ntnu.idatg2001.grp13.model.StoryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StoryReaderTest {
    String emptyStoryFileLocation;
    String hauntedHouseFileLocation;

    @BeforeEach
    void setUp() {
        emptyStoryFileLocation = "src/test/resources/emptyStory.paths";
        hauntedHouseFileLocation = "src/test/resources/hauntedHouse.paths";
    }

    @Test
    void posTestReadStoryTitle() {
        String expected = "Haunted House";
        String actual = StoryReader.readFromFile(hauntedHouseFileLocation).getTitle();

        assertEquals(expected, actual);
    }

    @Test
    void posTestReader_ReaderBegPassage() throws IOException {
        Story story = StoryReader.readFromFile(hauntedHouseFileLocation);
        String actual = story.getOpeningPassage().getTitle();
        String expected = "Beginnings";

        assertEquals(expected, actual);

        actual = story.getOpeningPassage().getContent();
        expected = "You are in a small, dimly lit room. There is a door in front of you.";

        assertEquals(actual, expected);
    }
    @Test
    void negTestReader_ReaderEmptyFile() {
        // Checks that it throws if there is an illegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            StoryReader.readFromFile(emptyStoryFileLocation);
        });
    }

    @Test
    void negTestReader_ReaderNotExistingFile() {
        // Checks that it throws if there is an illegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            StoryReader.readFromFile("wrong");
        });
    }
}