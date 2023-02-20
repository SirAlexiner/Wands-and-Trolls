package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Player {
  @Getter
  private String name;
  @Getter
  private int health;
  @Getter
  private int gold;
  @Getter
  private List<String> inventory = new ArrayList<>();
  @Getter
  private int score;

  // We should add Level to this class

  public Player(String name, int health, int gold, int score) {
    this.name = name;
    this.health = health;
    this.gold = gold;
    this.score = score;
  }

  public void addHealth(int health){
    this.health = getHealth() + health;
  }

  public void addGold(int gold){
  }

  public void addToInventory(String item){
  }

  public void removeFromInventory(String item){
  }
}
