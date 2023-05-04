package no.ntnu.idatg2001.gr13;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * A class representing a Player, part of the WiNG application.
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

    // We should add Level to this class

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


    // Builder
    public static class PlayerBuilder {
        private final String name;

        public PlayerBuilder(String name) {
            this.name = name;
        }

        //Return the finally constructed User object
        public Player build() {
            Player user = new Player(this);
            validateUserObject(user);
            return user;
        }

        private void validateUserObject(Player player) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
