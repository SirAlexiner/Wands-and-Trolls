package no.ntnu.idatg2001.gr13;

import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import no.ntnu.idatg2001.gr13.actions.Action;
import no.ntnu.idatg2001.gr13.actions.GoldAction;
import no.ntnu.idatg2001.gr13.actions.HealthAction;
import no.ntnu.idatg2001.gr13.actions.InventoryAction;
import no.ntnu.idatg2001.gr13.actions.ScoreAction;

public class StoryFileHandler
{
    private static Story story = null;
    private static Passage passage = null;
    private static Link link = null;

    public static void writeToFile(Story story, String fileName){
        // Try-with-resource-statement"
        try(BufferedWriter writer = newBufferedWriter(Path.of(fileName))){
            writer.write(story.getTitle() + "\n\n");
            {
                for (Passage passage: story.getPassages())
                {
                    // Writes the reference to the passage.
                    writer.write("::" + passage.getContent() + "\n");

                    // Writes the content of the passage
                    writer.write(passage.getTitle() + "\n");

                    for (Link link : passage.getLinks()) {
                        // Writes the reference of the link
                        writer.write("[" + "]" + "("  + link.getReference() + ")");

                        for (Action action : link.getActions()) {
                            writer.write("=" + action.toString() + ";");
                        }

                    }
                    writer.write("\n");
                }

            }
        }
        catch (IOException e){
            System.err.println("There was a problem writing to" + fileName);
        }
    }
    public static Story readFromFile(String fileName){
        String lineOfText;
        try(BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String titleOfGame = reader.readLine();
            while ((lineOfText = reader.readLine()) != null){
                if (lineOfText.startsWith("::")){
                    String[] line = lineOfText.split("::");
                    passage = new Passage(line[1], reader.readLine());

                    if (story == null) {
                        story = new Story(titleOfGame, passage);
                    }

                    story.addPassage(passage);

                }
                // TODO can use scanner and delimiter() instead of bufferedReader.
                if (lineOfText.startsWith("[") && passage != null){
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
    public static Action readActionFromFile(String actionFromFile){
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
