package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * <p>Goal interface.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public interface Goal {
  /**
   * <p>isFulfilled.</p>
   *
   * @param player a {@link no.ntnu.idatg2001.grp13.model.Player} object
   * @return a boolean
   */
  boolean isFulfilled(Player player);
}
