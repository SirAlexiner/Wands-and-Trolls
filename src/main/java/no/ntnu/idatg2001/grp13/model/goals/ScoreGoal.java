package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

public class ScoreGoal implements Goal {
  private final int minimumPoints;

  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() >= this.minimumPoints;
  }
}
