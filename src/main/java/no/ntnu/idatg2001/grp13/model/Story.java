package no.ntnu.idatg2001.grp13.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;

/**
 * A class representing a story, part of the WiNG application.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class Story {

  @Getter
  private final String title;
  private final Map<Link, Passage> passages;
  @Getter
  private final Passage openingPassage;

  // Constructor
  /**
   * <p>Constructor for Story.</p>
   *
   * @param title a {@link java.lang.String} object
   * @param openingPassage a {@link no.ntnu.idatg2001.grp13.model.Passage} object
   */
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  /**
   * A method to remove a specified passage from Passages. The passage will not be removed if other
   * passages are linked to the passage.
   *
   * @param link The Link key to the Passage object to be removed.
   * @throws java.lang.IllegalStateException if any.
   */
  public void removePassage(Link link) throws IllegalStateException {
    if (passages.containsKey(link)) {
      String passageToBeRemoved = passages.get(link).getTitle();
      List<Link> linksInPassages = passages.values().stream()
          .map(Passage::getLinks)
          .flatMap(Collection::stream)
          .toList();
      linksInPassages.stream()
          .filter(l -> l != link)
          .filter(l -> !l.getReference().matches(passageToBeRemoved))
          .forEach(passages::remove);
    }
  }


  /**
   * A method for getting Link objects that are not referring to any Passage Title.
   *
   * @return A list of broken Links.
   */
  public List<Link> getBrokenLinks() {
    List<Link> brokenLinks = new ArrayList<>();
    Set<Link> allLinks = new HashSet<>(passages.keySet());

    passages.values().forEach(passage -> allLinks.addAll(passage.getLinks()));
    allLinks.forEach(link -> {
      if (!passages.containsKey(link)) {
        brokenLinks.add(link);
      }
    });
    return brokenLinks;
  }

  /**
   * A method for putting the passage into a Map.
   *
   * @param passage to be added to the Map.
   */
  public void addPassage(Passage passage) {
    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
  }

  /** {@inheritDoc} */
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

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(title, passages, openingPassage);
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

  /**
   * <p>Getter for the field <code>passages</code>.</p>
   *
   * @return a {@link java.util.Collection} object
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }
}
