package no.ntnu.idatg2001.grp13.model.goals;

import no.ntnu.idatg2001.grp13.model.Player;

public interface Goal {
  boolean isFulfilled(Player player);
}
