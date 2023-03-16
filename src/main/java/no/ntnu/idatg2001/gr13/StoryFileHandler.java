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


                for (Link link : passage.getLinks()){
                    System.out.println("---");
                    System.out.println(link.getReference());
                    System.out.println(link.getAction());
                    System.out.println("---");
                }
            }
            int index = 1;
            while (story.getPassages().size() > index){
                for (Passage passage: story.getPassages())
                {
                    // Writes the reference to the passage.
                    writer.write("::" + passage.getReference() + "\n");

                    // Writes the content of the passage
                    writer.write(passage.getContent() + "\n");

                    for (Link link : passage.getLinks()) {
                        try {

                            writer.write("[" + "]" + "("  + link.getReference() + ")");
                            writer.write("\n");
                            index++;
                        }
                        catch (IndexOutOfBoundsException e){
                            System.err.println("Error: index out of bounds!");
                        }
                        catch (EOFException e){
                            System.err.println("Error: index out of bounds!");
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
