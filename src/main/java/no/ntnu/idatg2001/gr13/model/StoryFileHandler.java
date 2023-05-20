package no.ntnu.idatg2001.gr13.model;

import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatg2001.gr13.model.actions.Action;

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
    public static void writeToFile(Story story, String fileName) throws IOException {
        // Try-with-resource-statement
        try(BufferedWriter writer = newBufferedWriter(Path.of(fileName))){
            writer.write(story.getTitle() + "\n\n");
            writeStory(story, writer);
        }
        catch (IOException e){
            // Logging the exception
            // TODO add Logger Here
            //Logger.getLogger(YourClassName.class.getName()).log(Level.SEVERE, "Error writing to file: " + fileName, e);

            // Rethrowing the exception
            throw new IOException("Error writing to file: " + fileName, e);
        }
    }

    /**
     * The method takes a Story object and writes it using the BufferedWriter.
     *
     * @param story the Story object to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    private static void writeStory(Story story, BufferedWriter writer) throws IOException {
        List<Passage> passagesToWrite = new ArrayList<>();
        passagesToWrite.add(story.getOpeningPassage());
        passagesToWrite.addAll(story.getPassages());

        for (Passage passage: passagesToWrite)
        {
            writePassages(story, passage, writer);
            for (Link link : passage.getLinks()) {
                writeLinks(story, link, writer);
                for (Action action : link.getActions()) {
                    writeActions(action, writer);
                }
            }
            writer.write("\n");
        }
    }

    /**
     * The method takes a Passage object and writes is using the "writer".
     *
     * @param passage the Passage to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    private static void writePassages(Story story, Passage passage, BufferedWriter writer) throws IOException, IllegalArgumentException
    {
        if (story == null || story.getTitle().isBlank()) {
            throw new IllegalArgumentException("Story must contain character and be initialized to write passages!");
        }
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
    private static void writeLinks(Story story, Link link, BufferedWriter writer) throws IOException
    {
        if (story == null || story.getTitle().isBlank()) {
            throw new IllegalArgumentException("Story must contain character and be initialized to write passages!");
        }
        // Writes the text and reference of the link
        writer.write("[" + link.getText() + "]" + "("  + link.getReference() + ")\n");
    }

    /**
     * The method takes an Action object and writes is using the "writer".
     *
     * @param action the Action to be written
     * @param writer the writer to write
     * @throws IOException if an input/output occurs while writing to the file
     */
    private static void writeActions(Action action, BufferedWriter writer)throws IOException{
        // Writes the type and value of the action
        writer.write("=" + action.getActionType() + ";" + action.getActionValue() + "\n");
    }


}
