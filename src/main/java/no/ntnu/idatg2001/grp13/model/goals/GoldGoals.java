package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * Implements Goal, and upon initializing you set a goal of how much gold you need to fulfill
 * the gold goal.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class GoldGoals implements Goal {

  private final int minimumGold;

  /**
   * <p>Constructor for GoldGoals.</p>
   *
   * @param minimumGold a int
   */
  public GoldGoals(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * {@inheritDoc}
   *
   * Checks if the set amount of gold is enough to achieve the goal.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getGold() >= minimumGold;
  }
}
