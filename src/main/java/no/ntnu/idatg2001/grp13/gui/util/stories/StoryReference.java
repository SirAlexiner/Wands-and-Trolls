package no.ntnu.idatg2001.grp13.gui.util.stories;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.idatg2001.grp13.model.Story;
import no.ntnu.idatg2001.grp13.model.StoryReader;

/**
 * <p>StoryReference class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class StoryReference {
  @Getter
  @Setter
  public String title;
  @Getter
  @Setter
  public String absolutePath;

  /**
   * <p>Constructor for StoryReference.</p>
   *
   * @param absolutePath a {@link java.lang.String} object
   */
  public StoryReference(String absolutePath) {
    Story story = StoryReader.readFromFile(absolutePath);
    assert story != null;
    this.title = story.getTitle();
    this.absolutePath = absolutePath;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    StoryReference other = (StoryReference) obj;
    return title.equals(other.title) && absolutePath.equals(other.absolutePath);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(title, absolutePath);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    // Customize the output format as needed
    return title;
  }
}
