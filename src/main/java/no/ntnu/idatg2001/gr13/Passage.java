package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

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

  public boolean addLink(Link link) {
    return links.add(link);
  }

  public boolean hasLinks() {
    return !links.isEmpty();
  }

  // This function is not in req-spec, but is needed for everything to work:
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
