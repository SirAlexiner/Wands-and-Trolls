package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class GoldAction implements Action{

  private final int gold;

  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public boolean canExecute(Player player) {
    return player.getInventory().contains(String.valueOf(gold));
  }

  @Override
  public String getActionType(){
    return "Gold";
  }

  @Override
  public String getActionValue(){
    return String.valueOf(this.gold);
  }
}
