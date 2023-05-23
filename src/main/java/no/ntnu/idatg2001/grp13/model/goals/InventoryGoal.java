package no.ntnu.idatg2001.grp13.model.goals;

import java.util.HashSet;
import java.util.List;
import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>InventoryGoal class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /**
   * <p>Constructor for InventoryGoal.</p>
   *
   * @param mandatoryItems a {@link java.util.List} object
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    return new HashSet<>(player.getInventory()).containsAll(this.mandatoryItems);
  }
}
