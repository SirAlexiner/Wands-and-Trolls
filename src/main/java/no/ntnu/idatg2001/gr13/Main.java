package no.ntnu.idatg2001.gr13;

import java.util.List;
import java.util.Scanner;
import no.ntnu.idatg2001.gr13.actions.Action;
import no.ntnu.idatg2001.gr13.goals.Goal;

public class Main {
  private static final Scanner in = new Scanner(System.in).useDelimiter("\n");

  public static void main(String[] args) {
    Story story = StoryFileHandler.readFromFile(".paths");
    //Game game = Game.setup();
    //System.out.println("Hello " + game.getPlayer().getName() + ".");
    /*System.out.println(
        "You have embarked on a journey to retrieve a rare spell from an inhabited troll cave.\n" +
            "Your Quest is to retrieve the spell and make it out on the other side of the mountain.");*/

    System.out.println(story.getTitle() + "\n");
    StoryFileHandler.readFromFile(".paths");
    for (Passage passage : story.getPassages()) {
      System.out.println(passage.toString());
      System.out.println("\n");

      for (Link link : passage.getLinks()) {
        System.out.println(link.toString());
        for (Action action : link.getActions()){
          System.out.println(action.getActionType());
          System.out.println(action.getActionValue());
        }
      }

    }


    //Passage start = game.begin();
    //room(game, start);
  }

  private static void room(Game game, Passage passage) {
    System.out.println(
        "||--------------------------------------------------------------------------------||");
    System.out.println("Health: " + game.player().getHealth());
    System.out.println(passage.getTitle());
    System.out.println(
        "||--------------------------------------------------------------------------------||");
    if (passage.hasLinks()) {
      System.out.println("What do you want to do?");
      List<Link> links = passage.getLinks();
      for (Link link : links) {
        System.out.print(link.toString() + " | ");
      }
    } else {
      List<Goal> goals = game.getGoals();
      if (goals.get(0).isFulfilled(game.getPlayer())) {
        System.out.println(
            "You managed to conquered the troll using the spell, but was unable to make it trough the mountain");
        System.exit(0);
      } else if (goals.get(2).isFulfilled(game.getPlayer())) {
        System.out.println(
            "You managed to find the hidden key, and have conquered the troll using the spell. You leave victorious!");
        System.exit(0);
      } else if (goals.get(3).isFulfilled(game.getPlayer())) {
        System.out.println(
            "You managed to find the hidden key and the hidden door, You leave with the spell not having encountered the troll");
        System.exit(0);
      } else {
        System.out.println("You left the cave without having archived anything!");
        System.exit(0);
      }
    }
    System.out.print("Quit | ");
    while (true) {
      String command = in.next();
      if (command.equals("Quit")) {
        System.exit(0);
        break;
      } else {
        Passage newRoom = game.go(passage.getLink(command));
        if (newRoom != null) {
          System.out.println(" ");
          room(game, newRoom);
        } else if (passage.getLink(command) != null && passage.getLink(command).hasAction()){
          if (passage.getLink(command).getActions().get(0).canExecute(game.getPlayer()) && passage.getLink(command).hasAction()) {
            System.out.println("You used the spell, and turned the troll to stone.");
          } else {
            System.out.println("You checked your inventory, but you do not have any spells, the troll kills you in one swell swoop.");
          }
        } else {
          System.out.println("I did not understand what you want to do, please tell me again.");
        }
      }
    }
  }
}