package no.ntnu.idatg2001.gr13;

import java.util.List;
import lombok.Getter;

public class Passage {
  @Getter
  private String title;
  @Getter
  private String content;
  @Getter
  private List<Link> links = null;

  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public boolean addLink(Link link) {
    return links.add(link);
  }

  public boolean hasLinks() {
    return !links.isEmpty();
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
