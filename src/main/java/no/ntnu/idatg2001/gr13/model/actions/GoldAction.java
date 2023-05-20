package no.ntnu.idatg2001.gr13.model.actions;

import no.ntnu.idatg2001.gr13.model.Player;

public class GoldAction implements Action{

  private final int gold;

  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public void execute(Player player) {
    player.addGold(gold);
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
