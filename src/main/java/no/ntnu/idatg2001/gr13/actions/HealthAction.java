package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class HealthAction implements Action{

  private int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public boolean canExecute(Player player) {
    return false;
  }

  @Override
  public String getActionType(){
    return "Health";
  }
  @Override
  public String getActionValue(){
    return "" + this.health;
  }
}
