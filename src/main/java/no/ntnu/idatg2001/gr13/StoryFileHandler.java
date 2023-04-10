package no.ntnu.idatg2001.gr13;

import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import no.ntnu.idatg2001.gr13.actions.Action;
import no.ntnu.idatg2001.gr13.actions.GoldAction;
import no.ntnu.idatg2001.gr13.actions.HealthAction;
import no.ntnu.idatg2001.gr13.actions.InventoryAction;
import no.ntnu.idatg2001.gr13.actions.ScoreAction;

/**
 * This class is part of the "WiNG" application, this class acts as a file handler.
 * Includes methods to read and write to a CSV file.
 *
 * @author Arthur Borger Thorkildsen
 * @author Torgrim Thorsen
 * @version 02-04-2023
 */
public class StoryFileHandler
{
    private StoryFileHandler(){
        // empty, private constructor
    }
    /**
     * The methods take in a story object and a file name, and writes the story object to the file.
     *
     * @param story The story object that you want to write to a file.
     * @param fileName The name of the file to write to.
     */
    public static void writeToFile(Story story, String fileName){
        // Try-with-resource-statement
        try(BufferedWriter writer = newBufferedWriter(Path.of(fileName))){
            writer.write(story.getTitle() + "\n\n");
            writeStory(story, writer);
        }
        catch (IOException e){
            System.err.println("There was a problem writing to" + fileName);
        }
    }

    /**
     * The method takes a Story object and writes it using the "writer".
     *
     * @param story the Story object to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    public static void writeStory(Story story, BufferedWriter writer) {
        try {
            story.getPassages().forEach(passage -> {
                try {
                    writePassages(passage, writer);
                    passage.getLinks().forEach(link -> {
                        try {
                            writeLinks(link, writer);
                            link.getActions().forEach(action -> {
                                try {
                                    writeActions(action, writer);
                                } catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                }
                            });
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
                    writer.write("\n");
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (UncheckedIOException e) {
            System.out.println("Error in write story");
        }
        /*
        for (Passage passage: story.getPassages())
        {
            writePassages(passage, writer);
            for (Link link : passage.getLinks()) {
                writeLinks(link, writer);
                for (Action action : link.getActions()) {
                    writeActions(action, writer);
                }
            }
            writer.write("\n");
        }

         */
    }

    /**
     * The method takes a Passage object and writes is using the "writer".
     *
     * @param passage the Passage to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    public static void writePassages(Passage passage, BufferedWriter writer) throws IOException
    {
        // Writes the title of the passage.
        writer.write("::" + passage.getTitle() + "\n");
        // Writes the content of the passage
        writer.write(passage.getContent() + "\n");
    }

    /**
     * The method takes a Link object and writes is using the "writer".
     *
     * @param link the Link to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    public static void writeLinks(Link link, BufferedWriter writer) throws IOException
    {
        // Writes the text and reference of the link
        writer.write("[" + link.getText() + "]" + "("  + link.getReference() + ")\n");
    }

    /**
     * The method takes a Action object and writes is using the "writer".
     *
     * @param action the Action to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    public static void writeActions(Action action, BufferedWriter writer)throws IOException{
        // Writes the type and value of the action
        writer.write("=" + action.getActionType() + ";" + action.getActionValue() + "\n");
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
        //TODO must fix passage content implementation
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
