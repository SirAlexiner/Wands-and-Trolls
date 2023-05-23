package no.ntnu.idatg2001.grp13.model;

import lombok.Getter;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyPlayerUi;

public record GameHandler(@Getter FantasyPlayerUi playerUi, @Getter String storyPath,
                          @Getter String playerName, @Getter String goldGoals,
                          @Getter String inventoryGoals, @Getter String scoreGoals) {
  private static GameHandler instance;


  public GameHandler {
    if (instance == null) {
      GameHandler.instance = this;
    }
  }

  public static GameHandler getGameHandler() {
    return instance;
  }
}
