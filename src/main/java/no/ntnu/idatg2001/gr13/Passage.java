package no.ntnu.idatg2001.gr13;

import java.util.List;

public class Passage
{
    /**
     * Class representing a Passage class.
     * @author Arthur Borger Thorkildsen
     * @version 0.0.1
     * @since 12/2-2023
     */
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
    public void setTitle(String title)
    {
        this.title = title;
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
    public void setContent(String content)
    {
        this.content = content;
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
     * Sets the list.
     * @param links As List.
     */
    public void setLinks(List<Link> links)
    {
        this.links = links;
    }
}
