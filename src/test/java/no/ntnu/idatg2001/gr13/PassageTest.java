package no.ntnu.idatg2001.gr13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassageTest
{
    Passage passage;
    ArrayList<String> list = new ArrayList<>();
    PassageTest(){
    }

    @BeforeEach
    void setUp(){
        this.passage = new Passage("test title", "test content", list);
    }

    /**
     * Tester for setting the text in Passage class.
     * Positive and negative test.
     */
    @Test
    void testSetTitle(){
        // Negative test, checks if it throws "IllegalArgumentException"
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            passage.setTitle(null), "error invalid text");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            passage.setTitle(""), "error invalid text");
        // Positive test, sets the text.
        passage.setTitle("Test");
    }
    /**
     * Tester for setting the content in Passage class.
     * Positive and negative test.
     */
    @Test
    void testSetContent(){
        // Negative test, checks if it throws "IllegalArgumentException"
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            passage.setContent(null), "error invalid text");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            passage.setContent(""), "error invalid text");
        // Positive test, sets the text.
        passage.setContent("Test");
    }

    /**
     * A positive test for updateLinkList. The test
     * creates an arraylist with String.
     */
    @Test
    void positiveTestUpdateLinkList() {
        List<String> links = new ArrayList<>();
        links.add("test 1");
        links.add("test 2");

        passage.updateLinkList(links);

        assertEquals(links, passage.getLinks());
    }

    /**
     * Negative test for updateLinkList. The test checks if
     * it throws any arguments.
     */
    @Test
    void setLinks_NullInput_ThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            passage.updateLinkList(null), "Links cannot be null.");
    }
}
