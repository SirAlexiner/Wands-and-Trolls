package goalsTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.goals.GoldGoals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoldGoalsTest
{
    int gold = 0;
    Player player;
    GoldGoals goldGoals;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 10, gold, 10);
        goldGoals = new GoldGoals(gold);
    }
}
