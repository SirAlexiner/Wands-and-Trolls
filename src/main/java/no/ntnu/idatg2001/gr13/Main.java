package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.gr13.goals.Goal;
import no.ntnu.idatg2001.gr13.goals.GoldGoals;
import no.ntnu.idatg2001.gr13.goals.HealthGoal;
import no.ntnu.idatg2001.gr13.goals.InventoryGoal;
import no.ntnu.idatg2001.gr13.goals.ScoreGoal;

public class Main
{
    public static void main(String[] args) {
        Player player = new Player("Adventurer", 100, 0, 0);
        Passage startingRoom = new Passage("Cave Opening", "A Seemingly empty cave");
        Link left = new Link("There is an opening to the left", "Left");
        Link right = new Link("Looking around there is also an cavern to the right", "Right");
        startingRoom.addLink(left);
        startingRoom.addLink(right);
        Story story = new Story("Troll Cave", startingRoom);
        List<Goal> goals = new ArrayList<>();

        GoldGoals gold = new GoldGoals(50);
        HealthGoal health = new HealthGoal(0);
        List<String> invGoals = new ArrayList<>();
        invGoals.add("Key");
        InventoryGoal inv = new InventoryGoal(invGoals);
        ScoreGoal score = new ScoreGoal(5000);

        goals.add(gold);
        goals.add(health);
        goals.add(inv);
        goals.add(score);

        Game game = new Game(player, story, goals);
    }
}