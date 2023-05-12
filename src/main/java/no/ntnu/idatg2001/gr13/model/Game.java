package no.ntnu.idatg2001.gr13.model;

import java.util.List;
import lombok.Getter;
import no.ntnu.idatg2001.gr13.model.goals.Goal;

public record Game(@Getter Player player, @Getter Story story, @Getter List<Goal> goals) {

  public Passage begin() {
    return story.getOpeningPassage();
  }

  public Passage go(Link link) {
    return story.getPassage(link);
  }

  public static Game setup() {
    /*
    Player player = new Player("Adventurer", 100, 0, 0);
    Passage startingRoom = new Passage("Starting Room", "<: A seemingly empty cave opening :>");
    startingRoom.addLink(new Link("Enter Cave", "empty LINK reference"));
    startingRoom.addLink(new Link( "Exit Cave", "empty LINK reference"));
    Story story = new Story("Exit Cave", startingRoom);
    Passage caveEntrance = new Passage("Enter Cave", "<: You've entered the troll cave," +
        " it's very dark, but you see openings to the left and right :>");
    Passage justLeft = new Passage("Exit Cave", "You left the cave");
    Passage trollRoom =
        new Passage("Go Left", "<: You've entered the room to the left and run into the troll :>");
    Link magicSpell = new Link("Cast Magic Spell", "empty LINK reference");
    magicSpell.addAction(new InventoryAction("Spell Scroll"));
    trollRoom.addLink(magicSpell);
    Passage spellRoom = new Passage("Go Right", "<: You've entered a seemingly empty room," +
        " it's very dark! :>");
    caveEntrance.addLink(new Link("Go Left", "empty LINK reference"));
    caveEntrance.addLink(new Link("Go Right", "empty LINK reference"));
    story.addPassage(caveEntrance);
    story.addPassage(justLeft);
    story.addPassage(trollRoom);
    story.addPassage(spellRoom);
    Goal gold = new GoldGoals(50);
    Goal health = new HealthGoal(0);
    List<String> mandatoryItems = new ArrayList<>();
    mandatoryItems.add("Key");
    Goal inv = new InventoryGoal(mandatoryItems);
    Goal score = new ScoreGoal(5000);

    List<Goal> goals = new ArrayList<>();
    goals.add(gold);
    goals.add(health);
    goals.add(inv);
    goals.add(score);

    return new Game(player, story, goals);
     */


    // Read from file '.paths'
    StoryFileHandler.readActionFromFile(".paths");


    return null;
  }
}
