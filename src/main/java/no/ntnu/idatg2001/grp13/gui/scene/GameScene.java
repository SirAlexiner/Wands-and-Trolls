package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import java.util.logging.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.gui.util.AI.OpenAiImage;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;
import no.ntnu.idatg2001.grp13.gui.util.sound.MusicPlayer;
import no.ntnu.idatg2001.grp13.gui.util.sound.SoundEffectPlayer;
import no.ntnu.idatg2001.grp13.model.GameHandler;
import no.ntnu.idatg2001.grp13.model.Link;
import no.ntnu.idatg2001.grp13.model.Passage;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * <p>GameScene class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class GameScene {
  private final GameHandler gameHandler = GameHandler.getGameHandler();
  private static TextArea passageTextArea;
  private static final Duration DURATION_PER_CHARACTER = Duration.millis(100);
  private static HBox linkButtonsContainer;
  private static BorderPane root;

  /**
   * <p>getGameScene.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   * @return a {@link javafx.scene.Scene} object
   */
  public static Scene getGameScene(Stage stage) {
    GameController.startGame(stage);
    root = new BorderPane();
    setupScene(root);

    Settings settings = SettingsDao.loadSettingsFromFile();
    if (settings.isAiImagesEnabled()) {
      loadImage();
    }

    HBox playerBox = new HBox(gameHandler.getPlayerUi());
    playerBox.setPadding(new Insets(50, 0, 0, 50));

    root.setTop(playerBox);

    setTextAreaContent();

    linkButtonsContainer = new HBox();
    linkButtonsContainer.setAlignment(Pos.CENTER);
    linkButtonsContainer.setPadding(new Insets(0, 10, 20, 10));
    setupLinkButtons(stage);

    VBox containerPassageAndLink = setupContainerForPassageAndLink();

    VBox containerAction =
        setupActionContainer(containerPassageAndLink, linkButtonsContainer);

    root.setBottom(containerAction);

    MusicPlayer.playAdventureMusic();

    return new Scene(root);
  }

  /**
   * <p>setupLinkButtons.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   */
  public static void setupLinkButtons(Stage stage) {
    linkButtonsContainer.getChildren().clear();
    if (GameController.getLinksForCurrentPassage().isEmpty()) {
      FantasyButton linkButton = new FantasyButton("button.next", true);
      linkButton.setPrefWidth(210);
      linkButton.setScaleX(0.9);
      linkButton.setScaleY(0.9);

      linkButton.setOnAction(event -> {
        MainMenuScene.getContentContainer().getChildren().clear();
        MainMenuScene.getContentContainer().getChildren()
            .add(VictoryScene.getVictoryScene(stage).getRoot());
      });

      linkButtonsContainer.getChildren().add(linkButton);
    } else {
      for (Link link : GameController.getLinksForCurrentPassage()) {
        FantasyButton linkButton = new FantasyButton(link.getText().toUpperCase(), false);
        linkButton.setPrefWidth(210);
        linkButton.setScaleX(0.9);
        linkButton.setScaleY(0.9);

        linkButton.setOnAction(event -> {
          // Clears the screen
          passageTextArea.clear();
          // Get the next passage text from the controller
          Passage nextPassage = GameController.getNextPassage(link);
          Settings settings = SettingsDao.loadSettingsFromFile();
          if (settings.isAiImagesEnabled()) {
            loadImage();
          }
          animateText(nextPassage.getContent(), passageTextArea);

          // Refresh the links
          setupLinkButtons(stage);
        });

        linkButtonsContainer.getChildren().add(linkButton);
      }
    }
    FantasyButton restartButton = setupRestartButton(stage);

    FantasyButton mainMenuButton = new FantasyButton("button.mainMenu", true);
    mainMenuButton.setFantasyButtonType(FantasyButtonType.BONE);
    mainMenuButton.setOnAction(event -> {
      MusicPlayer.stop();
      MainMenuScene.getContentContainer().getChildren().clear();
      MainMenuScene.getContentContainer().getChildren().add(MainMenuScene.getScene(stage));
      MusicPlayer.playMainMenuMusic();
    });

    linkButtonsContainer.getChildren().addAll(restartButton, mainMenuButton);
  }

  private static void setupScene(BorderPane root) {
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    ImageView backgroundView = setupBackgroundView();
    root.getChildren().add(backgroundView);

    passageTextArea = setupPassageContentTextArea();
  }

  private static ImageView setupBackgroundView() {
    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Background/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    return backgroundView;
  }

  private static TextArea setupPassageContentTextArea() {
    TextArea passageContentTextArea = new TextArea();
    passageContentTextArea.setPrefSize(400, 150);
    passageContentTextArea.setEditable(false);
    passageContentTextArea.setDisable(true);

    return passageContentTextArea;
  }

  private static void setTextAreaContent() {
    animateText(GameController.getCurrentPassage().getContent(), passageTextArea);
  }

  private static VBox setupContainerForPassageAndLink() {
    VBox containerPassageAndLink = new VBox();
    containerPassageAndLink.setMaxWidth(900);
    containerPassageAndLink.setAlignment(Pos.CENTER);
    containerPassageAndLink.setPadding(new Insets(20));
    containerPassageAndLink.getChildren().addAll(passageTextArea);

    return containerPassageAndLink;
  }

  private static FantasyButton setupRestartButton(Stage stage) {
    FantasyButton restartButton = new FantasyButton("button.restart", true);
    restartButton.setFantasyButtonType(FantasyButtonType.BONE);
    restartButton.setPrefWidth(210);
    restartButton.setScaleX(0.9);
    restartButton.setScaleY(0.9);
    restartButton.setOnMouseClicked(event -> {
      // Reset the game state
      GameController.restartGame(stage);
      // Reset passage text area and link view
      passageTextArea.clear();
      // Sets the text
      animateText(GameController.getCurrentPassage().getContent(), passageTextArea);

      setupLinkButtons(stage);
    });
    return restartButton;
  }

  private static VBox setupActionContainer(VBox containerPassageAndLink,
                                           HBox linkButtonsContainer) {
    VBox containerAction = new VBox();
    containerAction.getChildren()
        .addAll(containerPassageAndLink, linkButtonsContainer);
    containerAction.setAlignment(Pos.CENTER);

    return containerAction;
  }

  /**
   * <p>storyContainingBrokenLinks.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   * @return a {@link no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert} object
   */
  public static FantasyAlert storyContainingBrokenLinks(Stage stage) {
    FantasyAlert brokenLinksAlert = new FantasyAlert(stage);
    brokenLinksAlert.setAlertType(Alert.AlertType.INFORMATION);
    brokenLinksAlert.setHeader("alert.brokenLinks");
    brokenLinksAlert.setTitle("alert.error");
    return brokenLinksAlert;
  }

  /**
   * <p>animateText.</p>
   *
   * @param input a {@link java.lang.String} object
   * @param textArea a {@link javafx.scene.control.TextArea} object
   */
  public void animateText(String input, TextArea textArea) {
    Timeline timeline = new Timeline();
    for (int i = 0; i < input.length(); i++) {
      char letter = input.charAt(i);
      KeyFrame keyFrame = new KeyFrame(
          DURATION_PER_CHARACTER.multiply(i + 1.0),
          event -> {
            textArea.appendText(String.valueOf(letter));
            SoundEffectPlayer.playTypeWriterSoundEffect();
          }
      );
      timeline.getKeyFrames().add(keyFrame);
    }
    timeline.play();
  }

  private void loadImage() {
    Task<Image> loadImageTask = new Task<>() {
      @Override
      protected Image call() {
        String description =
            "Fantasy,"
                + " Realistic Digital Art,"
                + GameController.getCurrentPassage().getContent();
        try {
          return OpenAiImage.generateImage(description);
        } catch (Exception e) {
          ErrorLogger.LOGGER.log(Level.SEVERE,
              String.format("Failed to load AI Image: %s", e));
          return new Image(Objects.requireNonNull(
              MainStage.class.getResourceAsStream("/Image/Background/Background.png")));
        }
      }
    };

    loadImageTask.setOnSucceeded(event -> {
      Image roomImage = loadImageTask.getValue();
      ImageView roomImageView = new ImageView(roomImage);
      roomImageView.setFitWidth(1024);
      roomImageView.setFitHeight(768);
      root.getChildren().remove(0);
      root.getChildren().add(0, roomImageView);
    });

    Thread loadImageThread = new Thread(loadImageTask);
    loadImageThread.setDaemon(true);
    loadImageThread.start();
  }
}
