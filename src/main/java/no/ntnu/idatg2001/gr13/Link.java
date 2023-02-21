package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.gr13.actions.Action;

public class Link {
  @Getter
  private String text;
  @Getter
  private final String reference;
  @Getter
  private Action action;
  public Link(String reference) {
    this.reference = reference;
  }

  public void addAction(Action action) {
    this.action = action;
  }

  public boolean hasAction() {
    if (action != null) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public String toString() {
    return this.reference;
  }

  @Override
  public final boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Link other))
      return false;
    return (this.reference == null && other.reference == null)
        || (this.reference != null && this.reference.equals(other.reference));
  }

  @Override
  public final int hashCode() {
    int result = 17;
    if (reference != null) {
      result = 31 * result + reference.hashCode();
    }
    return result;
  }
}
