package no.ntnu.idatg2001.gr13.model.actions;

import no.ntnu.idatg2001.gr13.model.Player;

public interface Action {
  void execute(Player player);

  String getActionType();

  String getActionValue();
}
