package no.ntnu.idatg2001.gr13.model;

import no.ntnu.idatg2001.gr13.model.goals.Goal;
import no.ntnu.idatg2001.gr13.model.goals.GoldGoals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Player player;
    Player emptyPlayer;
    Passage secondPassage;
    Passage openingPassage;
    Story story;
    Game game;
    Link link;
    List<Goal> goals;
    String openingPassageTitle = "Opening passage title";
    String openingPassageContent = "Opening passage content";
    String linkTitle = "Link title";
    String secondPassageTitle = "Second passage title";

    @BeforeEach
    void setUp() {
        goals = new ArrayList<>();
        goals.add(new GoldGoals(10));

        secondPassage = new Passage(secondPassageTitle, "Second passage content");
        openingPassage = new Passage(openingPassageTitle, openingPassageContent);
        story = new Story("Story title", openingPassage);

        link = new Link(linkTitle, secondPassageTitle);
        secondPassage.addLink(link);
        story.addPassage(secondPassage);

        emptyPlayer = null;
        player = new Player("Test player", 100, 100, 100);
        game = new Game(player, story, goals);
    }

    @Test
    void constructorTest() {
        assertThrows(IllegalArgumentException.class, () ->
            new Game(emptyPlayer, story, goals));
    }

    @Test
    void posBeginTest() {
        String actual = game.begin().getTitle();
        String expected = openingPassage.getTitle();

        assertEquals(actual, expected);
    }

    @Test
    void posGoTest() {
        String actual = game.go(link).getTitle();
        String expected = secondPassage.getTitle();

        assertEquals(expected, actual);
    }
}