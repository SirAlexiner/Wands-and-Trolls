package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import no.ntnu.idatg2001.gr13.actions.Action;

public class Link {
  @Getter
  private String text;
  @Getter
  private String reference;
  @Getter
  private List<Action> actions = new ArrayList<>();
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
  }

  public void addAction(Action action) {
    this.actions.add(action);
  }

//  @Override
//  public String toString() {
//  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Link other))
      return false;
    return (this.reference == null && other.reference == null)
        || (this.reference != null && this.reference.equals(other.reference));
  }

//  @Override
//  public int hashCode() {
//  }
}
