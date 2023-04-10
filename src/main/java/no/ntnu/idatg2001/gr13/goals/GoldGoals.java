package no.ntnu.idatg2001.gr13.goals;

import no.ntnu.idatg2001.gr13.Player;

public class GoldGoals implements Goal {
  private final int minimumGold;

  public GoldGoals(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  @Override
  public boolean isFulfilled(Player player) {
    return this.minimumGold == player.getGold();
  }
}
