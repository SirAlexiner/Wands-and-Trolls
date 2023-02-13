package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import utility.CheckValid;

/**
 * Class representing a Passage class.
 * @author Arthur Borger Thorkildsen
 * @version 0.0.1
 * @since 12/2-2023
 */
public class Passage
{
    private String title;
    private String content;
    private List<String> links;

    public Passage(String title, String content){
        setContent(content);
        setTitle(title);
        // The object can be set up without links.
        links = new ArrayList<>();
    }

    /**
     * Creates a new Passage object with the given title, content, and list of links.
     *
     * @param title The title of the Passage.
     * @param content The content of the Passage.
     * @param links The list of links associated with the Passage.
     */
    // Overriding the constructor
    public Passage(String title, String content, List<String> links) {
        setTitle(title);
        setContent(content);
        updateLinkList(links);
    }

    /**
     * Gets the title.
     * @return Title as a String.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title.
     * @param title As a String.
     */
    public void setTitle(String title) throws IllegalArgumentException
    {
        if (CheckValid.checkString(title)){
            this.title = title;
        } else {
            throw new IllegalArgumentException("error invalid text input");
        }
    }

    /**
     * Gets the content.
     * @return the content as a String.
     */
    public String getContent()
    {
        return content;
    }

    /**
     * Sets the content.
     * @param content As a String.
     */
    public void setContent(String content) throws IllegalArgumentException
    {
        if (CheckValid.checkString(content)) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("error invalid text input");
        }
    }

    /**
     * Gets the Links list.
     * @return Linklist.
     */
    public List<String> getLinks()
    {
        return links;
    }

    /**
     * Updates the list of links
     * @param links The new list of links to set.
     */
    public void updateLinkList(List<String> links) throws IllegalArgumentException
    {
        if (links != null) {
            this.links = links;
        } else {
            throw new IllegalArgumentException("Links cannot be null.");
        }
    }
}
