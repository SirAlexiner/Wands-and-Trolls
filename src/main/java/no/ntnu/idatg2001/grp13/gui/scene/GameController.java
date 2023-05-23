package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.model.Game;
import no.ntnu.idatg2001.grp13.model.GameHandler;
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
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class GameController {
  private static final GameHandler gameHandler = GameHandler.getGameHandler();
  private static Game game;
  private static Story story;
  private static Player player;
  @Getter
  private static Passage currentPassage;
  private static final List<Goal> goals = new ArrayList<>();

  /**
   * A method for starting the game. Sets the current passage.
   *
   * @param stage a {@link javafx.stage.Stage} object
   */
  public static void startGame(Stage stage) {
    setupGoalsAndPlayer();
    initializeStory(stage, gameHandler.getStoryPath());
    initializeGame();
    currentPassage = game.begin();
  }

  /**
   * <p>restartGame.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   */
  public static void restartGame(Stage stage) {
    startGame(stage);
  }

  private static void setupGoalsAndPlayer() {
    goals.add(new GoldGoals(10));
    player = new Player("Arthur", 10, 10, 10);
  }

  private static void initializeStory(Stage stage, String file) {
    story = StoryReader.readFromFile(file);
    if (storyHasBrokenLinks()) {
      GameScene.storyContainingBrokenLinks(stage).showAndWait();
    }
  }

  private static boolean storyHasBrokenLinks() {
    return !story.getBrokenLinks().isEmpty();
  }

  /**
   * <p>initializeGame.</p>
   */
  public static void initializeGame() {
    try {
      game = new Game(player, story, goals);
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Error loading the game: %s", e));
    }
  }

  /**
   * <p>getNextPassage.</p>
   *
   * @param link a {@link no.ntnu.idatg2001.grp13.model.Link} object
   * @return a {@link no.ntnu.idatg2001.grp13.model.Passage} object
   */
  public static Passage getNextPassage(Link link) {
    currentPassage = game.go(link);
    return currentPassage;
  }

  /**
   * <p>getLinkForPassage.</p>
   *
   * @return a {@link javafx.collections.ObservableList} object
   */
  public static ObservableList<Link> getLinkForPassage() {
    ObservableList<Link> links = FXCollections.observableArrayList();
    List<Link> brokenLinks = story.getBrokenLinks();
    currentPassage.getLinks().stream()
        .filter(link -> !brokenLinks.contains(link))
        .forEach(links::add);
    return links;
  }

  /**
   * <p>getLinksForCurrentPassage.</p>
   *
   * @return a {@link java.util.List} object
   */
  public static List<Link> getLinksForCurrentPassage() {
    return getLinkForPassage();
  }
}
