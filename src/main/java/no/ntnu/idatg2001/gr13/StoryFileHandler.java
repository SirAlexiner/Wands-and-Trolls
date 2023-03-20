package no.ntnu.idatg2001.gr13;

import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ListIterator;
import no.ntnu.idatg2001.gr13.actions.Action;

public class StoryFileHandler
{
    private static Story story = null;
    private static Passage passage = null;

    public static void writeToFile(Story story, String fileName){
        // Try-with-resource-statement"
        try(BufferedWriter writer = newBufferedWriter(Path.of(fileName))){
            writer.write(story.getTitle() + "\n\n");
            {
                for (Passage passage: story.getPassages())
                {
                    // Writes the reference to the passage.
                    writer.write("::" + passage.getReference() + "\n");

                    // Writes the content of the passage
                    writer.write(passage.getContent() + "\n");

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
                //
                // TODO can use scanner and delimiter() instead of bufferedReader.
                if (lineOfText.startsWith("[") && passage != null){
                    String[] line = lineOfText.split("]");
                    Link link = new Link(line[0], line[1]);
                    passage.addLink(link);
                    if (lineOfText.startsWith("=")){
                        String[] actionLine = lineOfText.split(";");
                    }
                }
            }
            reader.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        return story;
    }

}
