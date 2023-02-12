package no.ntnu.idatg2001.gr13;

import java.util.List;

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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks(List<Link> links)
    {
        this.links = links;
    }
}
