package no.ntnu.idatg2001.gr13.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import no.ntnu.idatg2001.gr13.model.actions.Action;
import no.ntnu.idatg2001.gr13.model.actions.GoldAction;
import no.ntnu.idatg2001.gr13.model.actions.HealthAction;
import no.ntnu.idatg2001.gr13.model.actions.InventoryAction;
import no.ntnu.idatg2001.gr13.model.actions.ScoreAction;

/**
 * This class is part of the "WiNG" application, this class acts as a file handler.
 * Includes methods to read and write to a CSV file.
 *
 * @author Arthur Borger Thorkildsen
 * @author Torgrim Thorsen
 * @version 02-04-2023
 */

public class StoryReader {

    private StoryReader(){
        // empty, private constructor
    }

    /**
     * The method reads a file and creates a story object from the file.
     *
     * @param fileName The name of the file to read from.
     * @return A story object.
     */
    public static Story readFromFile(String fileName){
        String lineOfText;
        Story story = null;
        Passage passage = null;
        Link link = null;
        Path filePath = Path.of(fileName);

        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("File does not exist: " + fileName);
        }

        try(BufferedReader reader = Files.newBufferedReader(filePath)) {
            String titleOfGame = reader.readLine();

            if (titleOfGame == null || titleOfGame.isEmpty()) {
                throw new IllegalArgumentException("File does not contain any text.");
            }

            while ((lineOfText = reader.readLine()) != null){
                if (lineOfText.startsWith("::")){
                    String[] line = lineOfText.split("::");
                    passage = new Passage(line[1], reader.readLine());

                    if (story == null) {
                        story = new Story(titleOfGame, passage);
                    }
                    story.addPassage(passage);
                }
                if (lineOfText.startsWith("[") && (passage != null )){
                    String[] line = lineOfText.split("\\[");
                    String[] text = line[1].split("]");

                    line = text[1].split("\\(");
                    String[] reference = line[1].split("\\)");
                    link = new Link(text[0], reference[0]);
                    passage.addLink(link);
                }
                if (lineOfText.startsWith("=") && link != null){
                    // Adds an action to the link object.
                    link.addAction(readActionFromFile(lineOfText));
                }
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return story;
    }

    /**
     * A method for reading action from a CSV file format.
     * @param actionFromFile A string with the action to be read.
     * @return the Action.
     */
    private static Action readActionFromFile(String actionFromFile){
        String[] action = actionFromFile.split("=");
        action = action[1].split(";");

        switch (action[0]){
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

            default -> System.err.print("error in switch");
        }
        return null;
    }
}
