package no.ntnu.idatg2001.grp13.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.grp13.model.actions.Action;

/**
 * A class representing a Link, part of the WiNG application.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class Link {
  @Getter
  private final String text;
  @Getter
  private final String reference;

  private final List<Action> actions;

  // To be noted is that reference is the reference to the "passage object".
  /**
   * <p>Constructor for Link.</p>
   *
   * @param text a {@link java.lang.String} object
   * @param reference a {@link java.lang.String} object
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    actions = new ArrayList<>();
  }

  /**
   * A method for adding the action.
   *
   * @param action The action to be performed.
   */
  public void addAction(Action action) {
    actions.add(action);
  }

  /**
   * <p>Getter for the field <code>actions</code>.</p>
   *
   * @return a {@link java.util.List} object
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Returns true if the action is not null.
   *
   * @return The method is returning a boolean value.
   */
  public boolean hasAction() {
    return !actions.isEmpty();
  }

  /**
   * {@inheritDoc}
   *
   * A method for overriding the toString() method.
   */
  @Override
  public String toString() {
    return (("[") + this.text + ("](") + this.reference + (")"));
  }

  /**
   * {@inheritDoc}
   *
   * If the references are both null, or if the references are both not null and equal, then the
   * objects are equal.
   */
  @Override
  public final boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Link other)) {
      return false;
    }
    return (this.reference == null && other.reference == null)
        || (this.reference != null && this.reference.equals(other.reference));
  }

  /**
   * {@inheritDoc}
   *
   * If the reference is null, return 17. Otherwise, return 31 times 17 plus the hash code of the
   * reference.
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
