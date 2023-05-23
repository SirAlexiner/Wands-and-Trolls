package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>InventoryAction class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class InventoryAction implements Action {

  private final String item;

  /**
   * <p>Constructor for InventoryAction.</p>
   *
   * @param item a {@link java.lang.String} object
   */
  public InventoryAction(String item) {
    this.item = item;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.addToInventory(item);
  }

  /** {@inheritDoc} */
  @Override
  public String getActionType() {
    return "Inventory";
  }

  /** {@inheritDoc} */
  @Override
  public String getActionValue() {
    return this.item;
  }
}
