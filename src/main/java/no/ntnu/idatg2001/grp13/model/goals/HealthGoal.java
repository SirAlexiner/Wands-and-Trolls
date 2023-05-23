package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>HealthGoal class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class HealthGoal implements Goal {
  private final int minimumHealth;

  /**
   * <p>Constructor for HealthGoal.</p>
   *
   * @param minimumHealth a int
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    return this.minimumHealth >= player.getHealth();
  }
}
