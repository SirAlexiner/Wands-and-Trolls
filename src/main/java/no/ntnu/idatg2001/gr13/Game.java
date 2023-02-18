package no.ntnu.idatg2001.gr13;

import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.gr13.goals.Goal;

public class Game {
  @Getter
  private Player player;
  @Getter
  private Story story;
  @Getter
  private List<Goal> goals;

  public Game(Player player, Story story, List<Goal> goals) {
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  public Passage begin() {
  }

  public Passage go(Link link) {
  }
}
