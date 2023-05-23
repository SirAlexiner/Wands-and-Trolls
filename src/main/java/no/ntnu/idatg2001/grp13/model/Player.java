package no.ntnu.idatg2001.grp13.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Player {
  @Getter
  private final String name;
  @Getter
  private int health;
  @Getter
  private int gold;
  @Getter
  private final List<String> inventory = new ArrayList<>();
  @Getter
  private int score;

  public Player(String name, int health, int gold, int score) {
    this.name = name;
    this.health = health;
    this.gold = gold;
    this.score = score;
  }

  public Player(PlayerBuilder builder) {
    this.name = builder.name;
  }

  public void addHealth(int health) {
    this.health = getHealth() + health;
  }

  public void addGold(int gold) {
    this.gold = getGold() + gold;
  }

  public void addScore(int score) {
    this.score = getScore() + score;
  }

  public void addToInventory(String item) {
    this.inventory.add(item);
  }

  public void removeFromInventory(String item) {
    this.inventory.remove(item);
  }

  public static class PlayerBuilder {
    private final String name;

    public PlayerBuilder(String name) {
      this.name = name;
    }
  }
}
