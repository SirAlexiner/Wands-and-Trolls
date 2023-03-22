package no.ntnu.idatg2001.gr13.actions;

import no.ntnu.idatg2001.gr13.Player;

public interface Action {
  boolean canExecute(Player player);

  String getActionType();

  String getActionValue();

}
