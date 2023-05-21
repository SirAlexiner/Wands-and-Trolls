package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.control.TextArea;
import no.ntnu.idatg2001.grp13.model.Game;
import no.ntnu.idatg2001.grp13.model.Player;
import no.ntnu.idatg2001.grp13.model.Story;
import no.ntnu.idatg2001.grp13.model.StoryReader;
import no.ntnu.idatg2001.grp13.model.goals.Goal;
import no.ntnu.idatg2001.grp13.model.goals.GoldGoals;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * This class is part of the "WiNG" application and represents the controller for the game scene.
 * It populates the text area and lists view in the game scene with relevant data.
 */
public class GameController {



  private static Game game;
  private static Story story;
  private static Player player;
  static List<Goal> goals = new ArrayList<>();

  public void populateFields(TextArea passageContent) {
    passageContent.setText(getPassageContent());
  }

  public static String getPassageContent() {
    goals.add(new GoldGoals(10));
    player = new Player("Arthur", 10, 10, 10);
    story = StoryReader.readFromFile("src/test/resources/hauntedHouse.paths");
    try {
      game = new Game(player, story, goals);
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Error loading the game: %s", e));
    }
    return story.getOpeningPassage().getContent();
  }
}