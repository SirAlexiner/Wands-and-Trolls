package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class HealthAction implements Action{

  private final int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) {
    player.addScore(health);
  }

  @Override
  public String getActionType(){
    return "Health";
  }
  @Override
  public String getActionValue(){
    return String.valueOf(this.health);
  }
  @Override
  public boolean isFulFilled(Player player){
    return player.getHealth() <= health;
  }
}
