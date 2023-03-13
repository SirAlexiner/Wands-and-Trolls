package no.ntnu.idatg2001.gr13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StoryFileHandler
{
    public static ArrayList<Story> readCSV(String fileName){
        ArrayList<Story> stories = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String lineOfText;
            while ((lineOfText = reader.readLine()) != null){
                String[] words = lineOfText.split("");
            }

        }
        catch (IOException e){

        }
        return stories;
    }
}
