package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

public class HealthAction implements Action{

  private final int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  @Override
  public String getActionType(){
    return "Health";
  }
  @Override
  public String getActionValue(){
    return String.valueOf(this.health);
  }
}
