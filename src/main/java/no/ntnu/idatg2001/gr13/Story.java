package no.ntnu.idatg2001.gr13;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class Story {
  @Getter
  private String title;
  private Map<Link,Passage> passages = new HashMap<>();
  @Getter
  private Passage openingPassage;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
  }

  public void addPassage(Passage passage){
    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
  }

  public Passage getPassage(Link link) {
    return passages.get(link);
  }

  public Collection<Passage> getPassages() {
    return passages.values();
  }
}
