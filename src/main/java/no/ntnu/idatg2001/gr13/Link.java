package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.gr13.actions.Action;

/**
 * A class representing a Link, part of the WiNG application.
 */
public class Link {
  @Getter
  private final String text;
  @Getter
  private final String reference;

  private final List<Action> actions;

  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    actions = new ArrayList<>();
  }

  /**
   * A method for adding the action.
   * @param action The action to be performed.
   */
  public void addAction(Action action) {
    actions.add(action);
  }

  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Returns true if the action is not null.
   *
   * @return The method is returning a boolean value.
   */
  // Overriding the toString method.
  public boolean hasAction() {
    return !actions.isEmpty();
  }

  /**
   * A method for overriding the toString() method.
   *
   * @return The reference as a String.
   */
  @Override
  public String toString() {
    return ( ("[") + this.text + ("](") +  this.reference + (")") );
  }

  /**
   * If the references are both null, or if the references are both not null and equal, then the
   * objects are equal
   *
   * @param o The object to compare to.
   * @return The hashCode of the reference.
   */
  @Override
  public final boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Link other))
      return false;
    return (this.reference == null && other.reference == null)
        || (this.reference != null && this.reference.equals(other.reference));
  }

  /**
   * If the reference is null, return 17. Otherwise, return 31 times 17 plus the hash code of the
   * reference.
   *
   * @return The hash code of the object.
   */
  @Override
  public final int hashCode() {
    int result = 17;
    if (reference != null) {
      result = 31 * result + reference.hashCode();
    }
    return result;
  }
}
