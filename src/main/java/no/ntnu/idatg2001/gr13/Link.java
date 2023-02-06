package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;

public class Link
{
    private final String text;
    private String reference;
    private List<String> action;

    public Link(String text, String reference, List action){
        this.text = text;
        this.reference = reference;
        this.action = action;
    }
}
