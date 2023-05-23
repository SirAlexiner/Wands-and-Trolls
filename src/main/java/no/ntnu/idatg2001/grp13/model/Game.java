package no.ntnu.idatg2001.grp13.model;

import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.grp13.model.goals.Goal;

/**
 * A class representing a game in the WiNG application
 * Part of the package, model.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class Game {

  /**
   * Accessor method for the player in the game.
   */
  @Getter
  private final Player player;
  /**
   * Accessor method for the story in the game.
   */
  @Getter
  private final Story story;
  /**
   * Accessor method for the goals in the game.
   */
  @Getter
  private final List<Goal> goals;

  /**
   * The Game's constructor.
   *
   * @param player The player object in the game.
   * @param story  The story to be played in the game.
   * @param goals  The list of goal objects.
   * @throws java.lang.IllegalArgumentException Throws an exception if the player, story or goals are empty.
   */
  public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException {
    if (player != null && story != null && !goals.isEmpty()) {
      this.player = player;
      this.story = story;
      this.goals = goals;
    } else {
      throw new IllegalArgumentException("Game values not initialized. Values cannot be null");
    }
  }

  /**
   * A method for returning the opening passage of the story.
   *
   * @return The opening passage of the story.
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }

  /**
   * A method for returning a passage in the story that the link points to.
   *
   * @param link The link that was clicked.
   * @return The passage value to the link key.
   * @throws java.lang.IllegalArgumentException if the link does not point towards any passage.
   */
  public Passage go(Link link) throws IllegalArgumentException {
    if (story.getPassages().contains(story.getPassage(link))) {
      return story.getPassage(link);
    } else {
      throw new IllegalArgumentException("Link does not point towards and passage");
    }
  }
}
