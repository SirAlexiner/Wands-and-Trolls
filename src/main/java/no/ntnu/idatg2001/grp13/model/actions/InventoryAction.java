package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

public class InventoryAction implements Action {

  private final String item;

  public InventoryAction(String item) {
    this.item = item;
  }

  @Override
  public void execute(Player player) {
    player.addToInventory(item);
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
