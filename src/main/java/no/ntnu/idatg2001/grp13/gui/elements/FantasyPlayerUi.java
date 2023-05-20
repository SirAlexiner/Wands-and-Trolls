package no.ntnu.idatg2001.grp13.gui.elements;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenu;

public class FantasyPlayerUi extends StackPane {
  private int maxHealth;
  private int currentHealth;
  private final Label healthLabel;
  private int maxMana;
  private int currentMana;
  private final Label manaLabel;
  private final ImageView avatarImageView;

  private final ProgressBar healthBar = new ProgressBar();
  private final ProgressBar manaBar = new ProgressBar();

  public FantasyPlayerUi(int health, int mana, double scale) {
    maxHealth = health;
    currentHealth = health;
    maxMana = mana;
    currentMana = mana;

    setScaleX(scale);
    setScaleY(scale);

    getStylesheets()
        .add(String.valueOf(MainMenu.class.getResource("/CSS/PlayerUi/FantasyPlayerUi.css")));

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
    healthBar.setTranslateY(-0.5);

    ColorAdjust healthColorAdjust = new ColorAdjust();
    healthColorAdjust.setHue(Color.RED.getHue());
    healthColorAdjust.setSaturation(Color.RED.getSaturation());
    healthBar.setEffect(healthColorAdjust);

    manaBar.setProgress(mana);
    manaBar.getStylesheets().add(String.valueOf(FantasyPlayerUi.class.getResource(
        "/CSS/PlayerUi/FantasyProgressBar_Mana.css")));
    manaBar.setPrefWidth(170);
    manaBar.setPrefHeight(15);
    manaBar.setTranslateX(-11);
    manaBar.setTranslateY(1.5);

    ColorAdjust manaColorAdjust = new ColorAdjust();
    manaColorAdjust.setHue(Color.CYAN.getHue());
    manaColorAdjust.setSaturation(Color.CYAN.getSaturation());

    manaBar.setEffect(manaColorAdjust);

    VBox bars = new VBox(healthBar, manaBar);
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

    healthLabel = new Label(currentHealth + " / " + maxHealth);
    healthLabel.setId("health");
    healthLabel.setTextAlignment(TextAlignment.CENTER);
    Image healthIconImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/Icons/heart.png")));
    ImageView healthIconImageView = new ImageView(healthIconImage);
    healthIconImageView.setPreserveRatio(true);
    healthIconImageView.setFitWidth(13);
    healthIconImageView.setTranslateY(1);
    healthLabel.setGraphic(healthIconImageView);
    healthLabel.setAlignment(Pos.CENTER);
    healthLabel.setTranslateX(8);
    healthLabel.setTranslateY(-0.5);

    manaLabel = new Label(currentMana + " / " + maxMana);
    manaLabel.setId("mana");
    manaLabel.setTextAlignment(TextAlignment.CENTER);
    Image manaIconImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream("/Image/PlayerUi/Icons/Mana.png")));
    ImageView manaIconImageView = new ImageView(manaIconImage);
    manaIconImageView.setPreserveRatio(true);
    manaIconImageView.setFitWidth(8);
    manaLabel.setGraphic(manaIconImageView);
    manaLabel.setAlignment(Pos.CENTER);
    manaLabel.setPrefHeight(18);
    manaLabel.setTranslateX(8);
    manaLabel.setTranslateY(1.5);

    VBox labels = new VBox(healthLabel, manaLabel);
    labels.setAlignment(Pos.CENTER);
    labels.setTranslateY(-8);

    Image avatarImage = new Image(Objects.requireNonNull(
        FantasyPlayerUi.class.getResourceAsStream(
            "/Image/PlayerUi/Default_Avatar.png")));
    avatarImageView = new ImageView(avatarImage);
    avatarImageView.setPreserveRatio(true);
    avatarImageView.setFitWidth(60);
    avatarImageView.setTranslateX(-65);

    Rectangle avatarClip = new Rectangle(60, 60);
    avatarClip.setArcWidth(60);
    avatarClip.setArcHeight(60);

    avatarImageView.setClip(avatarClip);

    getChildren().addAll(playerUiView, bars, labels, avatarRingView, avatarImageView);
  }

  public void updateHealth(int health) {
    maxHealth = health;
    animateProgressBar(healthBar, ((currentHealth + (maxHealth * 0.20)) / maxHealth));
    healthLabel.setText(currentHealth + " / " + maxHealth);
  }

  public void setHealth(int health) {
    currentHealth = health;
    animateProgressBar(healthBar, ((currentHealth + (maxHealth * 0.20)) / maxHealth));
    healthLabel.setText(currentHealth + " / " + maxHealth);
  }

  public void updateMana(int mana) {
    maxMana = mana;
    animateProgressBar(manaBar, ((currentMana + (maxMana * 0.20)) / maxMana));
    manaLabel.setText(currentMana + " / " + maxMana);
  }

  public void setMana(int mana) {
    currentMana = mana;
    animateProgressBar(manaBar, ((currentMana + (maxMana * 0.20)) / maxMana));
    manaLabel.setText(currentMana + " / " + maxMana);
  }

  public void setPlayerAvatar(Image image) {
    avatarImageView.setImage(image);
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
