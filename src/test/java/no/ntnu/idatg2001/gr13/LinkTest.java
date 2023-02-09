package no.ntnu.idatg2001.gr13;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
     */
    @Test
    void testSetLinkText(){
        try
        {
            link.setText(null);
            link.setText("");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }
}
