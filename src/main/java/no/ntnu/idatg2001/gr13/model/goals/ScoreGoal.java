package no.ntnu.idatg2001.gr13.model.goals;

import no.ntnu.idatg2001.gr13.model.Player;

public class ScoreGoal implements Goal {
  private int minimumPoints;

  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() == this.minimumPoints;
  }
}
