import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.model.Link;
import no.ntnu.idatg2001.gr13.model.Passage;
import no.ntnu.idatg2001.gr13.model.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryTest {

  Passage passage;
  Passage leftPassage;
  Passage otherPassage;
  Passage openingPassage;
  Link passageLink;
  Link leftLink;
  Link otherLink;
  Link openingLink;
  Link invalidLink;
  Story story;

  @BeforeEach
  void setUp() {
    String contentPassage = "Content of passage";
    String contentOpeningPassage = "Content of Opening passage";
    String contentOtherPassage = "Content of other passage";
    String contentLeftPassage = "Content of left passage";

    String passageTitle = "Passage title";
    String openingPassageTitle = "Opening passage title";
    String otherPassageTitle = "Other passage title";
    String leftPassageTitle = "left passage title";

    passage = new Passage(passageTitle, contentPassage);
    openingPassage = new Passage(openingPassageTitle, contentOpeningPassage);
    otherPassage = new Passage(otherPassageTitle, contentOtherPassage);
    leftPassage = new Passage(leftPassageTitle, contentLeftPassage);

    passageLink = new Link("Title link", passageTitle);
    openingLink = new Link("Text of opening link", openingPassageTitle);
    otherLink = new Link("Title other link", otherPassageTitle);
    leftLink = new Link("Text left link", leftPassageTitle);
    invalidLink = new Link("Invalid Title", "Invalid Reference");

    story = new Story("Title of story", openingPassage);
  }

  @Test
  void testAddPassage() {
    story.addPassage(passage);
    Passage retrivedPassage;
    retrivedPassage = story.getPassage(passageLink);

    assertNotNull(retrivedPassage);
    assertEquals(passage.getTitle(), retrivedPassage.getTitle());
    assertEquals(passage.getContent(), retrivedPassage.getContent());
  }

  @Test
  void testPosGetPassage() {
    passage.addLink(passageLink);
    story.addPassage(passage);
    Passage passageToBeFoundInList = story.getPassage(passageLink);

    assertDoesNotThrow(() -> story.getPassage(invalidLink));
    assertNotNull(passageToBeFoundInList);
  }

  @Test
  void testNegGetPassage() {
    assertDoesNotThrow(() -> {
      story.getPassage(invalidLink);
    });
  }

  @Test
  void testNegRemovePassage() {
    story.addPassage(leftPassage);
    story.addPassage(passage);
    int expected = 2;

    passage.addLink(leftLink);
    story.removePassage(leftLink);
    int actual = story.getPassages().size();

    assertEquals(expected, actual);
    // Edge case, the passage does not exist.
    assertDoesNotThrow(() -> story.removePassage(invalidLink));
  }

  @Test
  void testPosRemovePassage() {
    story.addPassage(passage);
    story.addPassage(leftPassage);

    passage.addLink(leftLink);
    leftPassage.addLink(leftLink);

    story.removePassage(passageLink);
    int expected = 1;
    int actual = story.getPassages().size();

    assertEquals(expected, actual);
  }

  @Test
  void testNegGetBrokenLinks() {
    passage.addLink(passageLink);
    otherPassage.addLink(passageLink);

    story.addPassage(passage);
    story.addPassage(otherPassage);

    int expected = 0;
    int actual = story.getBrokenLinks().size();

    assertEquals(expected, actual);

  }

  @Test
  void testPosGetBrokenLinks() {
    passage.addLink(passageLink);
    otherPassage.addLink(invalidLink);

    story.addPassage(passage);
    story.addPassage(otherPassage);

    int actual = story.getBrokenLinks().size();
    int expected = 1;

    assertEquals(expected, actual);
  }
}
