package no.ntnu.idatg2001.gr13;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;

/**
 *  A class representing a story, part of the WiNG application.
 */
public class Story {
  @Getter
  private final String title;
  private final Map<Link,Passage> passages;
  @Getter
  private final Passage openingPassage;

  // Constructor
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  /**
   * A method to remove a specified passage from Passages. The
   * passage will not be removed if other passages links to the passage.
   * @param link
   */
  public void removePassage(Link link) throws IllegalStateException{
    AtomicBoolean isReferenced = new AtomicBoolean(false);
    passages.values().stream()
            .forEach(passage -> {
              isReferenced.set(passage.getLinks().contains(link));
              // TODO Link reference match passage title
            });
    if (!isReferenced.get()){
      passages.remove(link);
    }
    else {
      throw new IllegalStateException("Passage is being linked to, cannot remove");
    }

  }
  /**
   * A method for putting the passage into a Map.
   * @param passage to be added to the Map.
   */
  public void addPassage(Passage passage){
    passages.put(new Link("", passage.getTitle()), passage);
  }

  //TODO not part of the remarks on this task, but should this at all use equals method?
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Story story = (Story) o;
    if (!Objects.equals(title, story.title)) {
      return false;
    }
    if (!Objects.equals(passages, story.passages)) {
      return false;
    }
    for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
      Link key = entry.getKey();
      Passage value = entry.getValue();
      Passage otherValue = story.passages.get(key);
      if (!Objects.equals(value, otherValue)) {
        return false;
      }
    }
    return Objects.equals(openingPassage, story.openingPassage);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(title, passages, openingPassage);
  }

  /**
   * Given a link, return the passage that the link points to.
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
