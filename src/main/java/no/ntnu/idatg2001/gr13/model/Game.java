package no.ntnu.idatg2001.gr13.model;

import java.util.List;

import lombok.Getter;
import no.ntnu.idatg2001.gr13.model.goals.Goal;

/**
 * A class representing a game, part of the WiNG application, package model.
 */
public class Game {

  @Getter
  private final Player player;
  @Getter
  private final Story story;
  @Getter
  private final List<Goal> goals;

  /**
   * The Game's constructor. Throws an exception if the player, story or goals are empty.
   *
   * @param player The player object in the game.
   * @param story  The story to be played in the game.
   * @param goals  The list of goal objects.
   * @throws IllegalArgumentException
   */
  public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException {
    if (player != null || story != null || !goals.isEmpty()) {
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
   */
  public Passage go(Link link) {
    return story.getPassage(link);
  }
}
