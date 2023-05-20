package no.ntnu.idatg2001.gr13.model.goals;

import java.util.HashSet;
import java.util.List;
import no.ntnu.idatg2001.gr13.model.Player;

public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return new HashSet<>(player.getInventory()).containsAll(this.mandatoryItems);
  }
}
