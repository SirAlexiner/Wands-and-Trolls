package no.ntnu.idatg2001.gr13.goals;

import java.util.List;
import no.ntnu.idatg2001.gr13.Player;

public class InventoryGoal implements Goal {
  private List<String> mandatoryItems;

  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return false;
  }
}
