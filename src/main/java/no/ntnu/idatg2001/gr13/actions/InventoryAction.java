package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.Action;

public class InventoryAction implements Action {

  private final String item;

  public InventoryAction(String item) {
    this.item = item;
  }

  @Override
  public boolean canExecute(Player player) {
    return player.getInventory().contains(item);
  }

  @Override
  public String getActionType(){
    return "Inventory";
  }

  @Override
  public String getActionValue(){
    return this.item;
  }
}
