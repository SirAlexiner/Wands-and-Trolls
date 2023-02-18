package no.ntnu.idatg2001.gr13;

import java.util.Collection;
import java.util.Map;
import lombok.Getter;

public class Story {
  @Getter
  private String title;
  private Map<Link,Passage> passages;
  @Getter
  private Passage openingPassage;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
  }

  public void addPassage(Passage passage){

  }

  public Passage getPassage(Link link) {

  }

  public Collection<Passage> getPassages() {

  }
}
