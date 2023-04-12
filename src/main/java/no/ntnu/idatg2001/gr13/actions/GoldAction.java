package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

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

  @Override
  public boolean isFulFilled(Player player){
    return player.getGold() >= gold;
  }
}
