package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>ScoreAction class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class ScoreAction implements Action {

  private final int points;

  /**
   * <p>Constructor for ScoreAction.</p>
   *
   * @param points a int
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.addScore(points);
  }

  /** {@inheritDoc} */
  @Override
  public String getActionType() {
    return "Score";
  }

  /** {@inheritDoc} */
  @Override
  public String getActionValue() {
    return String.valueOf(this.points);
  }
}
