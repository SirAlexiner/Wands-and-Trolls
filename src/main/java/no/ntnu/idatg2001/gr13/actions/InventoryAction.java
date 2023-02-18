package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.Action;

public class InventoryAction implements Action {

  private String item;

  public InventoryAction(String item) {
    this.item = item;
  }

  @Override
  public void execute(Player player) {
  }
}
