package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>HealthAction class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class HealthAction implements Action {

  private final int health;

  /**
   * <p>Constructor for HealthAction.</p>
   *
   * @param health a int
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  /** {@inheritDoc} */
  @Override
  public String getActionType() {
    return "Health";
  }

  /** {@inheritDoc} */
  @Override
  public String getActionValue() {
    return String.valueOf(this.health);
  }
}
