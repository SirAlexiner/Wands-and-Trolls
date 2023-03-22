package no.ntnu.idatg2001.gr13;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 *  A class representing a story, , part of the WiNG application.
 */
public class Story {
  @Getter
  private String title;
  private Map<Link,Passage> passages = new HashMap<>();
  @Getter
  private Passage openingPassage;

  // Constructor
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
  }

  /**
   * A method for putting the passage into a Map.
   * @param passage to be added to the Map.
   */

  public void addPassage(Passage passage){
    passages.put(new Link(passage.getTitle(), passage.getContent()), passage);
  }


  /**
   * Given a link, return the passage that the link points to.
   *
   * @param link The link that the passage is associated with.
   * @return The passage that is associated with the link.
   */
  public Passage getPassage(Link link) {
    return passages.get(link);
  }

  public Collection<Passage> getPassages() {
    return passages.values();
  }
}
