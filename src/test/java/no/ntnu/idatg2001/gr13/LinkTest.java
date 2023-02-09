package no.ntnu.idatg2001.gr13;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
    void testSetLinkText(){
        // Negative test, checks if it throws "IllegalArgumentException"
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setText(null), "error invalid text");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            link.setText(""), "error invalid text");
        // Positive test, sets the text.
        link.setText("Test");
    }
}
