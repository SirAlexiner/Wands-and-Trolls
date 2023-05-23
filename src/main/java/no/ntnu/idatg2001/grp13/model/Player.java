package no.ntnu.idatg2001.grp13.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * <p>Player class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
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

  /**
   * <p>Constructor for Player.</p>
   *
   * @param name a {@link java.lang.String} object
   * @param health a int
   * @param gold a int
   * @param score a int
   */
  public Player(String name, int health, int gold, int score) {
    this.name = name;
    this.health = health;
    this.gold = gold;
    this.score = score;
  }

  /**
   * <p>Constructor for Player.</p>
   *
   * @param builder a {@link no.ntnu.idatg2001.grp13.model.Player.PlayerBuilder} object
   */
  public Player(PlayerBuilder builder) {
    this.name = builder.name;
  }

  /**
   * <p>addHealth.</p>
   *
   * @param health a int
   */
  public void addHealth(int health) {
    this.health = getHealth() + health;
  }

  /**
   * <p>addGold.</p>
   *
   * @param gold a int
   */
  public void addGold(int gold) {
    this.gold = getGold() + gold;
  }

  /**
   * <p>addScore.</p>
   *
   * @param score a int
   */
  public void addScore(int score) {
    this.score = getScore() + score;
  }

  /**
   * <p>addToInventory.</p>
   *
   * @param item a {@link java.lang.String} object
   */
  public void addToInventory(String item) {
    this.inventory.add(item);
  }

  /**
   * <p>removeFromInventory.</p>
   *
   * @param item a {@link java.lang.String} object
   */
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
