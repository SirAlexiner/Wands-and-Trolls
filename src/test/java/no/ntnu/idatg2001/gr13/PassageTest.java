package no.ntnu.idatg2001.gr13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassageTest
{
    Passage passage;
    ArrayList<Object> list = new ArrayList<>();
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

    @Test
    void testSetLinks(){
        //TODO debug this test, error probably in implementing list
        list.add("link1");
        list.add(2);
        list.add(3);

        // Check size of list
        assertEquals(3, list.size());

        // Check if elements are in the list
        assertTrue(list.contains("link1"));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));

        // Clears the list
        list.clear();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
            list.add(null), "Links cannot be null");

    }
}
