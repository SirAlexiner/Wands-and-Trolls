package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import no.ntnu.idatg2001.grp13.model.Game;
import no.ntnu.idatg2001.grp13.model.Link;
import no.ntnu.idatg2001.grp13.model.Passage;
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
  private static Passage currentPassage;
  private static List<Goal> goals = new ArrayList<>();


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

  public static Passage getNextPassage(Link link) {
    return game.go(link);
  }

  public static ObservableList<Link> getLinkForPassage() {
    ObservableList<Link> links = FXCollections.observableArrayList();
    links.addAll(story.getOpeningPassage().getLinks());
    return links;
  }

}