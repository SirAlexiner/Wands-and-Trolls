package no.ntnu.idatg2001.gr13.goals;

import no.ntnu.idatg2001.gr13.Player;

public class HealthGoal implements Goal {
  private final int minimumHealth;

  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return this.minimumHealth == player.getHealth();
  }
}
