package no.ntnu.idatg2001.gr13.model.goals;

import no.ntnu.idatg2001.gr13.model.Player;

public interface Goal {
  boolean isFulfilled(Player player);
}
