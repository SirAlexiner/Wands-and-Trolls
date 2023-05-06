package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public class ScoreAction implements Action{

  private final int points;

  public ScoreAction(int points) {
    this.points = points;
  }

  @Override
  public void execute(Player player) {
    player.addScore(points);
  }
  @Override
  public String getActionType(){
    return "Score";
  }

  @Override
  public String getActionValue(){
    return String.valueOf(this.points);
  }
  @Override
  public boolean isFulFilled(Player player){
    return player.getScore() >= points;
  }
}
