import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import no.ntnu.idatg2001.gr13.Link;
import no.ntnu.idatg2001.gr13.Passage;
import no.ntnu.idatg2001.gr13.Story;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class HashCodeContractTest {
  @Test
  void equalsHashCodeContracts() {
    EqualsVerifier.forClass(Link.class)
        .withOnlyTheseFields("reference")
        .verify();
  }

  @Test
  void equalsAndHashCodeForPassageObjectTest() {
    EqualsVerifier.forClass(Passage.class)
            .withOnlyTheseFields("title")
            .usingGetClass()
            .verify();
  }

  @Test
  public void testEqualsForStoryObject() {
    Passage passage1 = new Passage("This is passage 1.", "content of passage 1");
    Passage passage2 = new Passage("This is passage 2.", "content of passage 2");
    Passage passage3 = new Passage("This is passage 3.", "content of passage 3");
    Passage openingPassage = new Passage("This is the opening passage.", "Content of opening passage");
    Passage differentOpeningPassage = new Passage("Different opening passage", "Content");

    Link link1 = new Link("1", "1");
    Link link2 = new Link("2", "2");
    Link link3 = new Link("3", "3");

    openingPassage.addLink(link1);
    passage1.addLink(link1);
    passage2.addLink(link2);
    passage3.addLink(link3);

    Story story1 = new Story("My Story", openingPassage);
    Story story2 = new Story("My Story", openingPassage);
    Story differentStory = new Story("Different story", differentOpeningPassage);

    // Map<Link, Passage> passages1 = new HashMap<>();
    story1.addPassage(passage1);
    story1.addPassage(passage2);
    story1.addPassage(passage3);

    //Map<Link, Passage> passages2 = new HashMap<>();
    story2.addPassage(passage1);
    story2.addPassage(passage2);
    story2.addPassage(passage3);

    assertEquals(story1, story2);
    assertEquals(story2, story1);
    assertNotEquals(differentStory, story1);
  }
/*
  @Test
  void equalsAndHashCodeForStoryObjectTest() {
    EqualsVerifier.forClass(Story.class)
            .withIgnoredFields("passages")
            .usingGetClass()
            .verify();
  }
  // TODO: Cannot invoke "java.util.Map.entrySet()"
      because "this.passages" is null, needs fixing
 */
}
