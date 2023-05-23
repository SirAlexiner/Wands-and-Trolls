package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>GoldAction class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class GoldAction implements Action {

  private final int gold;

  /**
   * <p>Constructor for GoldAction.</p>
   *
   * @param gold a int
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.addGold(gold);
  }


  /** {@inheritDoc} */
  @Override
  public String getActionType() {
    return "Gold";
  }

  /** {@inheritDoc} */
  @Override
  public String getActionValue() {
    return String.valueOf(this.gold);
  }
}
