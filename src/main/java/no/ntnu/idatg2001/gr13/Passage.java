package no.ntnu.idatg2001.gr13;

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
    private List<Link> links;

    public Passage(String title, String content, List links){
        setContent(content);
        setLinks(links);
        setTitle(title);
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
    public List<Link> getLinks()
    {
        return links;
    }

    /**
     * Updates the list of links
     * @param links The new list of links to set.
     */
    public void setLinks(List<Link> links) throws IllegalArgumentException
    {
        if (links != null) {
            this.links = links;
        } else {
            throw new IllegalArgumentException("Links cannot be null.");
        }
    }
}
