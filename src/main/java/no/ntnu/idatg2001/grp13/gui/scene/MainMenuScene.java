package no.ntnu.idatg2001.grp13.gui.scene;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.util.App;
import no.ntnu.idatg2001.grp13.gui.util.OpenAiImage;
import no.ntnu.idatg2001.grp13.stage.MainStage;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

@UtilityClass
public class MainMenuScene {
  private static final boolean USE_AI = false;
  @Getter
  private StackPane contentContainer;

  public static StackPane getScene(Stage stage) {
    Image logo =
        new Image(String.valueOf(MainStage.class.getResource(
            "/Image/MainMenu/WnT_logo_caption.png")));
    ImageView logoView = new ImageView(logo);
    logoView.setFitWidth(225);
    logoView.preserveRatioProperty().set(true);

    HBox logoBox = new HBox();
    logoBox.setAlignment(Pos.CENTER_LEFT);
    logoBox.setPadding(new Insets(50, 20, 20, 20));

    logoBox.getChildren().add(logoView);

    VBox box = new VBox();
    box.setSpacing(10);
    box.setAlignment(Pos.CENTER);
    Button newGameButton = new FantasyButton("New Game");
    newGameButton.setOnMouseClicked(event -> {
      Scene newGameScene = NewGame.getNewGameScene(stage);
      contentContainer.getChildren().add(newGameScene.getRoot());
    });
    newGameButton.setPrefWidth(225);
    box.getChildren().add(newGameButton);

    BorderPane content = new BorderPane();
    contentContainer = new StackPane(content);
    contentContainer.setPrefHeight(660);

    Button loadButton = new FantasyButton("Load Adventure");
    loadButton.setOnMouseClicked(event -> {
      Scene filePickerScene = FilePicker.getFilePickerScene(stage);
      contentContainer.getChildren().add(filePickerScene.getRoot());
    });
    loadButton.setPrefWidth(225);
    box.getChildren().add(loadButton);

    Button createButton = new FantasyButton("Create Adventure");
    createButton.setPrefWidth(225);
    box.getChildren().add(createButton);

    Button settingsButton = new FantasyButton("Settings");
    settingsButton.setPrefWidth(225);
    box.getChildren().add(settingsButton);
    settingsButton.setOnMouseClicked(event -> {
      Scene settingsScene = SettingsScene.getSettingScene(stage);
      contentContainer.getChildren().add(settingsScene.getRoot());
    });

    BorderPane bottomInformation = new BorderPane();
    bottomInformation.setPadding(new Insets(20));

    HBox versionInformation = new HBox();
    Label version =
        new Label("Version: " + App.getVersion() + "\nBuild: " + App.getBuild());
    versionInformation.setAlignment(Pos.BOTTOM_LEFT);
    versionInformation.getChildren().add(version);

    HBox copyrightInformation = new HBox();
    copyrightInformation.setAlignment(Pos.BOTTOM_CENTER);
    copyrightInformation.setSpacing(25);

    VBox companyLogoBox = new VBox();
    companyLogoBox.setAlignment(Pos.CENTER);
    companyLogoBox.setSpacing(10);

    Image companyLogo =
        new Image(
            String.valueOf(MainStage.class.getResource(
                "/Image/MainMenu/company_logo.png")));
    ImageView companyLogoview = new ImageView(companyLogo);
    companyLogoview.setFitWidth(225);
    companyLogoview.preserveRatioProperty().set(true);

    Label copyright = new Label("Copyright © 2023 - " + LocalDate.now().getYear()
        + ": Thunder Entertainment. All Rights Reserved.");

    companyLogoBox.getChildren().addAll(companyLogoview, copyright);

    copyrightInformation.getChildren().add(companyLogoBox);


    HBox emptySpacer = new HBox();
    emptySpacer.setPrefWidth(150);

    bottomInformation.setLeft(versionInformation);
    bottomInformation.setCenter(copyrightInformation);
    bottomInformation.setRight(emptySpacer);

    Image background = new Image(
        Objects.requireNonNull(MainStage.class.getResourceAsStream(
            "/Image/MainMenu/Main_Menu.gif")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    content.getChildren().add(backgroundView);

    content.setTop(logoBox);
    content.setCenter(box);
    content.setBottom(bottomInformation);

    // Create a rectangle with rounded top corners
    Rectangle clipRect = new Rectangle(1024, 660);
    clipRect.setArcWidth(30);
    clipRect.setArcHeight(30);

    contentContainer.setClip(clipRect);


    Task<Image> loadImageTask = new Task<>() {
      @Override
      protected Image call() {
        String description =
            "Fantasy,"
                + " Realistic Digital Art,"
                + " You finally managed to climb the mountain,"
                + " in the distance you see a cave opening with a troll guarding the entrance.";
        if (USE_AI) {
          try {
            return OpenAiImage.generateImage(description);
          } catch (Exception e) {
            ErrorLogger.LOGGER.log(Level.SEVERE,
                String.format("Failed to load AI Image: %s", e));
            return new Image(Objects.requireNonNull(
                MainStage.class.getResourceAsStream("/Image/Window/Background.png")));
          }
        } else {
          return null;
        }
      }
    };

    // Update the UI once the image is loaded
    loadImageTask.setOnSucceeded(event -> {
      if (USE_AI) {
        Image roomImage = loadImageTask.getValue();
        ImageView roomImageView = new ImageView(roomImage);
        roomImageView.setFitWidth(1024);
        roomImageView.setFitHeight(768);
        content.getChildren().remove(0);
        content.getChildren().add(0, roomImageView);
      }
    });

    // Start the background task to load the image
    Thread loadImageThread = new Thread(loadImageTask);
    loadImageThread.setDaemon(true);
    loadImageThread.start();

    return contentContainer;
  }
}