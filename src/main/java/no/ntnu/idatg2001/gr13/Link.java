package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;

public class Link
{
    private String text;
    private String reference;
    private List<Action> action;

    public Link(String text, String reference){
        setReference(reference);
        setText(text);
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }
}
