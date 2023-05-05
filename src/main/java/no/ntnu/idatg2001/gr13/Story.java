package no.ntnu.idatg2001.gr13;

import java.util.*;
import java.util.stream.Collectors;

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
  /*
  public void removePassage(Link link) throws IllegalStateException{
    passages.values().stream()
            .forEach(passage -> {
              passage.getLinks().contains(link);
              // TODO Link reference match passage title
            });
    if (){
      passages.remove(link);
    }
    else {
      throw new IllegalStateException("Passage is being linked to, cannot remove");
    }
  }

   */

  /**
   *
   */
  public List<Link> getBrokenLinks4() {
    List<Link> brokenLinks = new ArrayList<>();
    Set<Link> allLinks = new HashSet<>(passages.keySet());

    passages.values().forEach(passage -> allLinks.addAll(passage.getLinks()));

    for (Link link : allLinks) {
      if (!passages.containsKey(link)) {
        brokenLinks.add(link);
      }
    }
    return brokenLinks;
  }

  public List<Link> getBrokenLinks() {
    List<Link> brokenLinks = new ArrayList<>();
    List<String> passageTitleList = new ArrayList<>();
    List<Link> linkReferenceList;

    linkReferenceList = new ArrayList<>(passages.keySet());
    // adds to a list of links
    passages.values().forEach(passage -> //if (passages.get(link) == null) {
            //}
            linkReferenceList.addAll(passage.getLinks()));
    /*

    // for each passage in the map
    for (Passage passage : passages.values()) {
      passageTitleList.add(passage.getTitle());
      // for each link in passage arraylist
      for (Link link : passages.keySet()) {
        linkReferenceList.add(link.getReference());
        // if the link reference matches any item
        if (linkReferenceList.equals(passageTitleList)) {
          brokenLinks.add(link);
        }
        //if (getPassage(link) == null) {
        //  brokenLinks.add(link);
        //}
      }
    }

     */
    for (Link link : linkReferenceList) {
      if (!passages.containsKey(link)) {
        brokenLinks.add(link);
      }
    }
    return brokenLinks;
  }

  public List<Link> getBrokenLinks2() {
    List<Link> brokenLinks = new ArrayList<>();

    // Iterate over all entries (i.e., key-value pairs) in the map
    for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
      Link link = entry.getKey();
      Passage passage = entry.getValue();

      // Check if the link's reference is a valid passage title in the map
      if (!passages.containsKey(new Link(link.getReference(), link.getReference()))) {
        brokenLinks.add(link);
      }
    }

    return brokenLinks;
  }

  public List<Link> getBrokenLinks3() {
    List<Link> matchingLinks = new ArrayList<>();
    for (Passage passage : passages.values()) {
      for (Link link : passage.getLinks()) {
        if (passages.containsKey(link) && passages.get(link).getTitle().equals(link.getReference())) {
          matchingLinks.add(link);
        }
      }
    }
    return matchingLinks;
  }



  /**
   * A method for putting the passage into a Map.
   * @param passage to be added to the Map.
   */
  public void addPassage(Passage passage){
    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
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
