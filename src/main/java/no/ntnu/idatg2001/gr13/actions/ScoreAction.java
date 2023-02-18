package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class ScoreAction implements Action{

  private int points;

  public ScoreAction(int points) {
    this.points = points;
  }

  @Override
  public void execute(Player player) {

  }
}
