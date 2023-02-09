package no.ntnu.idatg2001.gr13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkTest
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
}
