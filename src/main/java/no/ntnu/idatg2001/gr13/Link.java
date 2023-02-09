package no.ntnu.idatg2001.gr13;

import java.util.List;
import javax.swing.Action;
import utility.CheckValid;

/**
 * Class representing a Link class.
 * @author Arthur Borger Thorkildsen
 * @version 0.0.1
 * @since 9/2-2023
 */
public class Link
{
    private String text;
    private String reference;
    private List<Action> action;

    public Link(String text, String reference) throws IllegalArgumentException{
        setReference(reference);
        setText(text);
    }

    /**
     * Gets text String
     * @return text
     */
    public String getText()
    {
        return text;
    }

    /**
     * Sets text String
     * @param text to be set
     */
    public void setText(String text) throws IllegalArgumentException
    {
        if (CheckValid.checkString(text)) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("error invalid text input");
        }
    }

    /**
     * Gets reference String
     * @return reference
     */
    public String getReference()
    {
        return reference;
    }

    /**
     * Sets reference String
     * @param reference to be set
     */
    public void setReference(String reference) throws IllegalArgumentException
    {
        if (CheckValid.checkString(reference)) {
            this.reference = reference;
        } else {
            throw new IllegalArgumentException("error invalid reference input");
        }
    }

    /**
     * Gets a list of actions
     * @return The action
     */
    public List<Action> getAction()
    {
        return action;
    }

    /**
     * Sets the action
     * @param action Available user action as a list
     */
    public void setAction(List<Action> action)
    {
        this.action = action;
    }
}
