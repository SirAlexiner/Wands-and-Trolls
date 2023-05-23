package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * Implements Goal, and upon initializing you set a goal of how much gold you need to fulfill
 * the gold goal.
 */
public class GoldGoals implements Goal {

  private final int minimumGold;

  public GoldGoals(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * Checks if the set amount of gold is enough to achieve the goal.
   *
   * @param player the player you check if the gold goal is fulfilled for.
   * @return true or false.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getGold() >= minimumGold;
  }
}