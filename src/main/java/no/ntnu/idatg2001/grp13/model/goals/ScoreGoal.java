package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>ScoreGoal class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class ScoreGoal implements Goal {
  private final int minimumPoints;

  /**
   * <p>Constructor for ScoreGoal.</p>
   *
   * @param minimumPoints a int
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() >= this.minimumPoints;
  }
}
