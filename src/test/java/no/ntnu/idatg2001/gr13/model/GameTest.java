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
    Passage openingPassage;
    Story story;
    Game game;
    Game invalidGame;
    List<Goal> goals;

    @BeforeEach
    void setUp() {
        goals = new ArrayList<>();
        goals.add(new GoldGoals(10));

        openingPassage = new Passage("opening", "opening");
        story = new Story("title", openingPassage);

        emptyPlayer = null;
    }

    @Test
    void constructorTest() {
        assertThrows(IllegalArgumentException.class, () ->
            new Game(emptyPlayer, story, goals));
    }

    @Test
    void beginTest() {
    }

    @Test
    void goTest() {
    }
}