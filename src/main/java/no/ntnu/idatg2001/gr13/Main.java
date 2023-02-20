package no.ntnu.idatg2001.gr13;

import java.util.List;
import java.util.Scanner;

public class Main {
  private static final Scanner in = new Scanner(System.in).useDelimiter("\n");

  public static void main(String[] args) {
    Game game = Game.setup();
    System.out.println("Hello " + game.getPlayer().getName() + ".");
    System.out.println(
        "You have embarked on a journey to retrieve a rare spell from an inhabited troll cave.");
    Passage start = game.begin();
    room(game, start);
  }

  private static void room(Game game, Passage passage) {
    System.out.println(
        "||--------------------------------------------------------------------------------||");
    System.out.println("Health: " + game.player().getHealth());
    System.out.println(passage.getContent());
    System.out.println(
        "||--------------------------------------------------------------------------------||");
    System.out.println("What do you want to do?");
    if (passage.hasLinks()) {
      List<Link> links = passage.getLinks();
      for (Link link : links) {
        System.out.print(link.toString() + " | ");
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
        }
      }
    }
  }
}