package no.ntnu.idatg2001.grp13.model.actions;

import no.ntnu.idatg2001.grp13.model.Player;

/**
 * Interface class for game enging actions
 */
public interface Action {
  void execute(Player player);

  String getActionType();

  String getActionValue();
}
