package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class HealthAction implements Action{

  private int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) {
  }
}
