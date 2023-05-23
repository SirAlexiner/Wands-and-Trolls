package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * Interface class for game enging actions
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public interface Action {
  /**
   * <p>execute.</p>
   *
   * @param player a {@link no.ntnu.idatg2001.grp13.model.Player} object
   */
  void execute(Player player);

  /**
   * <p>getActionType.</p>
   *
   * @return a {@link java.lang.String} object
   */
  String getActionType();

  /**
   * <p>getActionValue.</p>
   *
   * @return a {@link java.lang.String} object
   */
  String getActionValue();
}
