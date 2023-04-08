package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

/**
 * A class representing a passage, part of the WiNG application.
 */
public class Passage {
  @Getter
  private final String title;
  @Getter
  private final String content;
  @Getter
  private final List<Link> links = new ArrayList<>();

  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
  }

  /**
   * A method for adding a Link to a list. Checks if the
   * link object already exist in the list.
   *
   * @param link The link to be added to the list.
   * @return A boolean value.
   */
  public boolean addLink(Link link) {
    if (links.contains(link)){
      return false;
    } else return links.add(link);
  }

  /**
   * A method for checking if the list has any Links.
   * @return A boolean value.
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  // This function is not in req-spec, but is needed for everything to work:
  /**
   * If the list includes any links, then a for each loop is used. If the link's
   * toString() method returns the same value as the command parameter, then return the link.
   *
   * @param command The command that the user entered.
   * @return A link object
   */
  public Link getLink(String command) {
    if (this.hasLinks()) {
      for (Link link : this.getLinks()
      ) {
        if (link.getReference().equalsIgnoreCase(command)) {
          return link;
        }
      }
    }
    return null;
  }

  /**
   * A method for returning the Link object to a string.
   * @return A String of the Link object.
   */
  @Override
  public String toString() {
    // The different characters refers to '.path' characters for Link.
    return ( ("::") + this.title + ("\n") + this.content);
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    Passage passage = (Passage) o;
    return  Objects.equals(title, passage.title);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(title);
  }
}
