package no.ntnu.idatg2001.gr13;

import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ListIterator;

public class StoryFileHandler
{
    public static void writeToFile(Story story, String fileName){
        // Try-with-resource-statement"
        try(BufferedWriter writer = newBufferedWriter(Path.of(fileName))){
            writer.write(story.getTitle() + "\n\n");

            // Creates a new arrayList
            ArrayList<String> arrayList = new ArrayList<>();
            for (Passage passage: story.getPassages()){
                arrayList.add(passage.getReference());
            }
            int index = 0;
            for (Passage passage: story.getPassages())
            {
                try
                {
                    index++;
                    // Writes the reference to the passage.
                    writer.write("::" + passage.getReference() + "\n");

                    // Writes the content of the passage
                    writer.write(passage.getContent() + "\n");

                    writer.write("[" + "]" + "(" + arrayList.get(index) +  ")");
                    writer.write("\n\n");
                }
                catch (IndexOutOfBoundsException e){
                    System.err.println("Error: index out of bounds!");
                }
            }
        }
        catch (IOException e){
            System.err.println("There was a problem writing to" + fileName);
        }

    }
    public static Story readToFile(String fileName){
        try(BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String lineOfText;
            while ((lineOfText = reader.readLine()) != null){
                String[] words = lineOfText.split("");
            }

        }
        catch (IOException e){

        }
        Story Story = null;
        return Story;
    }
}
