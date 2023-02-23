package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * A class representing a passage, part of the WiNG application.
 */
public class Passage {
  @Getter
  private String reference;
  @Getter
  private String content;
  @Getter
  private List<Link> links = new ArrayList<>();

  public Passage(String reference, String content) {
    this.reference = reference;
    this.content = content;
  }

  /**
   * A method for adding a Link to a list.
   *
   * @param link The link to be added to the list.
   * @return A boolean value.
   */
  public boolean addLink(Link link) {
    return links.add(link);
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
        if (link.toString().equalsIgnoreCase(command)) {
          return link;
        }
      }
    }
    return null;
  }

//  @Override
//  public String toString() {
//  }
//
//  @Override
//  public boolean equals(Object object) {
//  }
//  @Override
//  public int hashCode() {
//  }
}
