package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.util.StylizedBorderPane;
import no.ntnu.idatg2001.grp13.gui.util.sound.MusicPlayer;


/**
 * The VictoryScene class constructs and displays a victory scene when the player wins a game
 * or completes a level.
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class VictoryScene {

  private static final int SCREEN_WIDTH = 1100;
  private static final int SCREEN_HEIGHT = 768;
  private static List<Box> confettiPieces;
  private static Random random;

  /**
   * This function creates a victory scene with confetti animation,
   * and a button to go back to the main.
   * Menu.
   *
   * @param stage The stage parameter is an instance of the JavaFX Stage class, which represents the
   *              top-level container for a JavaFX application.
   *              It is used to display the Victory scene and switch
   *              between different scenes in the application.
   * @return A JavaFX Scene object is being returned.
   */
  public static Scene getVictoryScene(Stage stage) {
    BorderPane root = StylizedBorderPane.getBorderPane();

    Image victory = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Background/Victory.png")));
    ImageView victoryView = new ImageView(victory);

    root.setCenter(victoryView);

    // Initialize confetti pieces
    confettiPieces = new ArrayList<>();
    random = new Random();
    for (int i = 0; i < 1000; i++) {
      Box confettiPiece = createConfettiPiece();
      root.getChildren().add(confettiPiece);
      confettiPieces.add(confettiPiece);
    }

    // Start the confetti animation
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> updateConfetti()));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    root.sceneProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue != null) {
        Platform.runLater(MusicPlayer::playVictoryMusic);
      }
    });

    HBox goBackButtonBox = new HBox();
    goBackButtonBox.setAlignment(Pos.CENTER);
    goBackButtonBox.setPadding(new Insets(20));

    FantasyButton mainMenuButton = new FantasyButton("button.mainMenu", true);
    mainMenuButton.setOnAction(event -> {
      MusicPlayer.stop();
      MainMenuScene.getContentContainer().getChildren().clear();
      MainMenuScene.getContentContainer().getChildren().add(MainMenuScene.getScene(stage));
      MusicPlayer.playMainMenuMusic();
    });

    goBackButtonBox.getChildren().add(mainMenuButton);

    root.setBottom(goBackButtonBox);

    return new Scene(root);
  }

  private static Box createConfettiPiece() {
    Box confettiPiece = new Box(7.5, 7.5, 2.5);
    confettiPiece.setTranslateX(
        random.nextDouble() * (SCREEN_WIDTH - 50)); // Random X position within a centered range
    confettiPiece.setTranslateY(-random.nextDouble() * 1000); // Start at the top of the screen
    confettiPiece.setRotationAxis(new Point3D(random.nextDouble(), random.nextDouble(),
        random.nextDouble()));
    confettiPiece.setRotate(random.nextDouble() * 360);

    PhongMaterial gold = new PhongMaterial(Color.GOLD);
    gold.setSpecularColor(Color.WHITE);
    gold.setSpecularPower(30);

    confettiPiece.setMaterial(gold);
    return confettiPiece;
  }

  private static void updateConfetti() {
    for (Box confettiPiece : confettiPieces) {
      double xvariation = random.nextDouble() * 4 - 2; // Random horizontal variation
      double yvariation = random.nextDouble() * 4 + 1; // Random vertical variation

      confettiPiece.setTranslateX(confettiPiece.getTranslateX() + xvariation);
      confettiPiece.setTranslateY(confettiPiece.getTranslateY() + yvariation);

      confettiPiece.setRotate(
          confettiPiece.getRotate() + random.nextDouble() * 4 - 2); // Random rotation

      Bounds bounds = confettiPiece.localToScene(confettiPiece.getBoundsInLocal());
      if (bounds.getMaxY() > SCREEN_HEIGHT) {
        confettiPiece.setTranslateY(-random.nextDouble() * 100);
        confettiPiece.setTranslateX(
            random.nextDouble() * (SCREEN_WIDTH - 50)); // Random X position within a centered range
        confettiPiece.setRotationAxis(new Point3D(random.nextDouble(), random.nextDouble(),
            random.nextDouble()));
        confettiPiece.setRotate(random.nextDouble() * 360);
      }
    }
  }
}
