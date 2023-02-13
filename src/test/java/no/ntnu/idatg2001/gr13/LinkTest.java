package no.ntnu.idatg2001.gr13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkTest
{
    Link link;
    LinkTest(){
    }
    @BeforeEach
    void setUp() {
        this.link = new Link("test text", "test reference");
    }


    /**
     * Tester for setting the text in Link class.
     * Positive and negative test.
     */
    @Test
    void testSetText(){
        // Negative test, checks if it throws "IllegalArgumentException"
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setText(null), "error invalid text");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setText(""), "error invalid text");
        // Positive test, sets the text.
        link.setText("Test");
    }

    /**
     * Tester for setting the reference in Link class.
     * Positive and negative test.
     */
    @Test
    void testSetReference(){
        // Negative test, checks if it throws "IllegalArgumentException"
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setReference(null), "error invalid text");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setReference(""), "error invalid text");
        // Positive test, sets the reference.
        link.setText("Test");
    }

    /**
     * A positive test for setAction. The test
     * creates an arraylist with String.
     */
    @Test
    void positiveTestUpdateLinkList() {
        List<String> links = new ArrayList<>();
        links.add("test 1");
        links.add("test 2");

        link.setAction(links);

        assertEquals(links, link.getAction());
    }

    /**
     * Negative test for setAction. The test checks if
     * it throws any arguments.
     */
    @Test
    void testSetAction() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setAction(null), "Action cannot be null.");
    }
}
