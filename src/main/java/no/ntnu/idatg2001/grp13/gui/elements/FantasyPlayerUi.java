package no.ntnu.idatg2001.grp13.gui.elements;

import java.text.DecimalFormat;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;

/**
 * <p>FantasyPlayerUi class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
public class FantasyPlayerUi extends StackPane {
  private int maxHealth;
  private int health;
  private final Label healthLabel;
  private int gold;
  private int score;
  private final Label goldLabel;
  private final Label scoreLabel;
  private final ImageView avatarImageView;

  private final ProgressBar healthBar = new ProgressBar();

  /**
   * <p>Constructor for FantasyPlayerUi.</p>
   *
   * @param health a int
   * @param gold a int
   * @param scale a double
   */
  public FantasyPlayerUi(int health, int gold, double scale) {
    if (health > 99999) {
      maxHealth = 99999;
      this.health = 99999;
    } else {
      maxHealth = health;
      this.health = health;
    }
    this.gold = gold;
    score = 0;

    setScaleX(scale);
    setScaleY(scale);

    getStylesheets()
        .add(String.valueOf(MainStage.class.getResource("/CSS/PlayerUi/FantasyPlayerUi.css")));

    Image playerUiImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/PlayerUi_Background.png")));
    ImageView playerUiView = new ImageView(playerUiImage);
    playerUiView.setPreserveRatio(true);
    playerUiView.setFitWidth(200);

    healthBar.setProgress(health);
    healthBar.getStylesheets().add(String.valueOf(FantasyPlayerUi.class.getResource(
        "/CSS/PlayerUi/FantasyProgressBar_Health.css")));
    healthBar.setPrefWidth(190);
    healthBar.setPrefHeight(18);
    healthBar.setTranslateX(-7);
    healthBar.setTranslateY(-9.5);

    ColorAdjust healthColorAdjust = new ColorAdjust();
    healthColorAdjust.setHue(Color.RED.getHue());
    healthColorAdjust.setSaturation(Color.RED.getSaturation());
    healthBar.setEffect(healthColorAdjust);

    VBox bars = new VBox(healthBar);
    bars.setAlignment(Pos.CENTER);
    bars.setSpacing(1);
    bars.setTranslateY(-8);

    Rectangle barClip = new Rectangle(60, 80);
    Rectangle windowShape = new Rectangle(200, 150);
    Shape invertedClip = Shape.subtract(windowShape, barClip);
    bars.setClip(invertedClip);

    Image avatarRingImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/PlayerUi_Avatar_Frame.png")));
    ImageView avatarRingView = new ImageView(avatarRingImage);
    avatarRingView.setPreserveRatio(true);
    avatarRingView.setFitWidth(75);
    avatarRingView.setTranslateX(-65);

    healthLabel = new Label(this.health + " / " + maxHealth);
    healthLabel.setId("health");
    Image healthIconImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/Icons/Health.png")));
    ImageView healthIconImageView = new ImageView(healthIconImage);
    healthIconImageView.setPreserveRatio(true);
    healthIconImageView.setFitWidth(13);
    healthIconImageView.setTranslateY(1);
    healthLabel.setGraphic(healthIconImageView);
    healthLabel.setAlignment(Pos.CENTER_LEFT);
    healthLabel.setTranslateX(23);
    healthLabel.setTranslateY(-0.5);

    goldLabel = new Label(formatNumber(this.gold));
    goldLabel.setId("gold");
    Image goldIconImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/Icons/Gold.png")));
    ImageView goldIconImageView = new ImageView(goldIconImage);
    goldIconImageView.setPreserveRatio(true);
    goldIconImageView.setFitWidth(12);
    goldLabel.setGraphic(goldIconImageView);
    goldLabel.setAlignment(Pos.CENTER);
    goldLabel.setPrefHeight(18);
    goldIconImageView.setTranslateY(1);


    scoreLabel = new Label(formatNumber(score));
    scoreLabel.setId("score");
    Image scoreIconImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/Icons/Score.png")));
    ImageView scoreIconImageView = new ImageView(scoreIconImage);
    scoreIconImageView.setPreserveRatio(true);
    scoreIconImageView.setFitWidth(12);
    scoreLabel.setGraphic(scoreIconImageView);
    scoreLabel.setAlignment(Pos.CENTER);
    scoreLabel.setPrefHeight(18);
    scoreIconImageView.setTranslateY(1);

    HBox goldScoreBox = new HBox(goldLabel, scoreLabel);
    goldScoreBox.setAlignment(Pos.CENTER_LEFT);
    goldScoreBox.setSpacing(5);
    goldScoreBox.setTranslateY(1.5);
    goldScoreBox.setTranslateX(74);

    VBox labels = new VBox(healthLabel, goldScoreBox);
    labels.setAlignment(Pos.CENTER);
    labels.setTranslateY(-8);

    Image avatarImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream(
            "/Image/PlayerUi/Default_Avatar.png")));
    avatarImageView = new ImageView(avatarImage);
    avatarImageView.setFitWidth(60);
    avatarImageView.setFitHeight(60);
    avatarImageView.setTranslateX(-65);

    Rectangle avatarClip = new Rectangle(60, 60);
    avatarClip.setArcWidth(60);
    avatarClip.setArcHeight(60);

    avatarImageView.setClip(avatarClip);

    getChildren().addAll(playerUiView, bars, labels, avatarRingView, avatarImageView);
  }

  private static String formatNumber(int number) {
    if (number > 1000000) {
      int quotient = number / 1000000;

      DecimalFormat decimalFormat = new DecimalFormat("#.#");
      decimalFormat.setDecimalSeparatorAlwaysShown(false);

      return decimalFormat.format(quotient) + "m";
    } else if (number > 1000) {
      int quotient = number / 1000;

      DecimalFormat decimalFormat = new DecimalFormat("#.#");
      decimalFormat.setDecimalSeparatorAlwaysShown(false);

      return decimalFormat.format(quotient) + "k";
    } else {
      return String.valueOf(number);
    }
  }

  /**
   * <p>updateHealth.</p>
   *
   * @param health a int
   */
  public void updateHealth(int health) {
    if (health > 99999) {
      maxHealth = 99999;
      this.health = 99999;
    } else {
      maxHealth = health;
      this.health = health;
    }
    animateProgressBar(healthBar, ((this.health + (maxHealth * 0.20)) / maxHealth));
    healthLabel.setText(this.health + " / " + maxHealth);
  }

  /**
   * <p>Setter for the field <code>health</code>.</p>
   *
   * @param health a int
   */
  public void setHealth(int health) {
    this.health = Math.min(health, 99999);
    animateProgressBar(healthBar, ((this.health + (maxHealth * 0.20)) / maxHealth));
    healthLabel.setText(this.health + " / " + maxHealth);
  }

  /**
   * <p>Setter for the field <code>gold</code>.</p>
   *
   * @param gold a int
   */
  public void setGold(int gold) {
    this.gold = gold;
    goldLabel.setText(String.valueOf(gold));
  }

  /**
   * <p>Setter for the field <code>score</code>.</p>
   *
   * @param score a int
   */
  public void setScore(int score) {
    this.score = score;
    scoreLabel.setText(String.valueOf(score));
  }

  /**
   * <p>setPlayerAvatar.</p>
   *
   * @param image a {@link javafx.scene.image.Image} object
   */
  public void setPlayerAvatar(Image image) {
    if (image != null) {
      avatarImageView.setImage(image);
    }
  }

  private void animateProgressBar(ProgressBar progressBar, double targetProgress) {
    Duration duration = Duration.millis(1000);

    Timeline timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(duration,
        new KeyValue(progressBar.progressProperty(), targetProgress));
    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
  }
}
