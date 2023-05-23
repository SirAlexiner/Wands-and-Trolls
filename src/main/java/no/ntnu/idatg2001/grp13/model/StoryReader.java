package no.ntnu.idatg2001.grp13.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import no.ntnu.idatg2001.grp13.model.actions.Action;
import no.ntnu.idatg2001.grp13.model.actions.GoldAction;
import no.ntnu.idatg2001.grp13.model.actions.HealthAction;
import no.ntnu.idatg2001.grp13.model.actions.InventoryAction;
import no.ntnu.idatg2001.grp13.model.actions.ScoreAction;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * This class is part of the "WiNG" application, this class acts as a file handler.
 * Includes methods to read and write to a CSV file.
 *
 * @author Arthur Borger Thorkildsen
 * @author Torgrim Thorsen
 * @version 02-04-2023
 */

public class StoryReader {

  private StoryReader() {
    // empty, private constructor
  }

  /**
   * The method reads a file and creates a story object from the file.
   *
   * @param fileName The name of the file to read from.
   * @return A story object.
   */
  public static Story readFromFile(String fileName) {
    Path filePath = Path.of(fileName);
    validateFileExists(filePath);

    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
      String titleOfGame = reader.readLine();
      validateTextExists(titleOfGame);

      Story story = null;
      Passage passage = null;
      Link link = null;

      String lineOfText;
      while ((lineOfText = reader.readLine()) != null) {
        if (lineOfText.startsWith("::")) {
          passage = createPassage(lineOfText, reader.readLine());
          story = createStoryIfNull(story, titleOfGame, passage);
          story.addPassage(passage);
        }
        if (lineOfText.startsWith("[") && (passage != null)) {
          link = createLink(lineOfText);
          passage.addLink(link);
        }
        if (lineOfText.startsWith("=") && link != null) {
          link.addAction(readActionFromFile(lineOfText));
        }
      }

      return story;
    } catch (IOException e) {
      logError("An Error occurred while reading from file: " + e);
      return null;
    }
  }

  private static void validateFileExists(Path filePath) {
    if (!Files.exists(filePath)) {
      throw new IllegalArgumentException("File does not exist: " + filePath);
    }
  }

  private static void validateTextExists(String text) {
    if (text == null || text.isEmpty()) {
      throw new IllegalArgumentException("File does not contain any text.");
    }
  }

  private static Passage createPassage(String lineOfText, String passageText) {
    String[] line = lineOfText.split("::");
    return new Passage(line[1], passageText);
  }

  private static Story createStoryIfNull(Story story, String titleOfGame, Passage passage) {
    if (story == null) {
      return new Story(titleOfGame, passage);
    }
    return story;
  }

  private static Link createLink(String lineOfText) {
    String[] line = lineOfText.split("\\[");
    String[] text = line[1].split("]");

    line = text[1].split("\\(");
    String[] reference = line[1].split("\\)");
    return new Link(text[0], reference[0]);
  }

  private static void logError(String message) {
    ErrorLogger.LOGGER.log(Level.SEVERE, message);
  }


  /**
   * A method for reading action from a CSV file format.
   *
   * @param actionFromFile A string with the action to be read.
   * @return the Action.
   */
  private static Action readActionFromFile(String actionFromFile) {
    String[] action = actionFromFile.split("=");
    action = action[1].split(";");

    switch (action[0]) {
      // Parsing it to integer since the input from the file is an int.
      case ("Gold") -> {
        return (new GoldAction(Integer.parseInt(action[1])));
      }
      case ("Health") -> {
        return (new HealthAction(Integer.parseInt(action[1])));
      }
      case ("Score") -> {
        return (new ScoreAction(Integer.parseInt(action[1])));
      }
      // Inventory action uses String as datatype.
      case ("Inventory") -> {
        return (new InventoryAction(action[1]));
      }

      default -> ErrorLogger.LOGGER.log(Level.WARNING, "Error in Switch case");
    }
    return null;
  }
}
