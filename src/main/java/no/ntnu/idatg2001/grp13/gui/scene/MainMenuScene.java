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
    HBox logoBox = createLogoBox();
    VBox mainButtonBox = createMainButtonBox(stage);
    BorderPane bottomInformation = createBottomInformation();
    Image background = new Image(
        Objects.requireNonNull(MainStage.class.getResourceAsStream(
            "/Image/MainMenu/Main_Menu.gif")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    BorderPane content = new BorderPane();
    content.getChildren().add(backgroundView);
    content.setTop(logoBox);
    content.setCenter(mainButtonBox);
    content.setBottom(bottomInformation);
    contentContainer = new StackPane(content);
    contentContainer.setPrefHeight(660);
    Rectangle clipRect = createClipRect();
    contentContainer.setClip(clipRect);

    if (USE_AI) {
      loadImage(content);
    }

    return contentContainer;
  }

  private HBox createLogoBox() {
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

    return logoBox;
  }

  private VBox createMainButtonBox(Stage stage) {
    VBox box = new VBox();
    box.setSpacing(10);
    box.setAlignment(Pos.CENTER);
    box.getChildren().addAll(
        createNewGameButton(stage),
        createLoadButton(stage),
        createCreateButton(),
        createSettingsButton(stage)
    );

    return box;
  }

  private Button createNewGameButton(Stage stage) {
    Button newGameButton = new FantasyButton("New Game");
    newGameButton.setOnMouseClicked(event -> {
      Scene newGameScene = NewGame.getNewGameScene(stage);
      contentContainer.getChildren().add(newGameScene.getRoot());
    });
    newGameButton.setPrefWidth(225);
    return newGameButton;
  }

  private Button createLoadButton(Stage stage) {
    Button loadButton = new FantasyButton("Load Adventure");
    loadButton.setOnMouseClicked(event -> {
      Scene filePickerScene = FilePicker.getFilePickerScene(stage);
      contentContainer.getChildren().add(filePickerScene.getRoot());
    });
    loadButton.setPrefWidth(225);
    return loadButton;
  }

  private Button createCreateButton() {
    Button createButton = new FantasyButton("Create Adventure");
    createButton.setPrefWidth(225);
    return createButton;
  }

  private Button createSettingsButton(Stage stage) {
    Button settingsButton = new FantasyButton("Settings");
    settingsButton.setPrefWidth(225);
    settingsButton.setOnMouseClicked(event -> {
      Scene settingScene = SettingsScene.getSettingScene(stage);
      contentContainer.getChildren().add(settingScene.getRoot());
    });
    return settingsButton;
  }

  private BorderPane createBottomInformation() {
    HBox versionInformation = createVersionInformation();
    HBox copyrightInformation = createCopyrightInformation();
    HBox emptySpacer = createEmptySpacer();

    BorderPane bottomInformation = new BorderPane();
    bottomInformation.setPadding(new Insets(20));
    bottomInformation.setLeft(versionInformation);
    bottomInformation.setCenter(copyrightInformation);
    bottomInformation.setRight(emptySpacer);

    return bottomInformation;
  }

  private HBox createVersionInformation() {
    HBox versionInformation = new HBox();
    Label version = new Label("Version: " + App.getVersion() + "\nBuild: " + App.getBuild());
    versionInformation.setAlignment(Pos.BOTTOM_LEFT);
    versionInformation.getChildren().add(version);
    return versionInformation;
  }

  private HBox createCopyrightInformation() {
    VBox companyLogoBox = createCompanyLogoBox();
    HBox copyrightInformation = new HBox();
    copyrightInformation.setAlignment(Pos.BOTTOM_CENTER);
    copyrightInformation.setSpacing(25);
    copyrightInformation.getChildren().add(companyLogoBox);
    return copyrightInformation;
  }

  private VBox createCompanyLogoBox() {
    Image companyLogo =
        new Image(
            String.valueOf(MainStage.class.getResource(
                "/Image/MainMenu/company_logo.png")));
    ImageView companyLogoview = new ImageView(companyLogo);
    companyLogoview.setFitWidth(225);
    companyLogoview.preserveRatioProperty().set(true);

    Label copyright = new Label("Copyright © 2023 - " + LocalDate.now().getYear()
        + ": Thunder Entertainment. All Rights Reserved.");

    VBox companyLogoBox = new VBox();
    companyLogoBox.setAlignment(Pos.CENTER);
    companyLogoBox.setSpacing(10);
    companyLogoBox.getChildren().addAll(companyLogoview, copyright);

    return companyLogoBox;
  }

  private HBox createEmptySpacer() {
    HBox emptySpacer = new HBox();
    emptySpacer.setPrefWidth(150);
    return emptySpacer;
  }

  private Rectangle createClipRect() {
    Rectangle clipRect = new Rectangle(1024, 660);
    clipRect.setArcWidth(30);
    clipRect.setArcHeight(30);
    return clipRect;
  }

  private void loadImage(BorderPane content) {
    Task<Image> loadImageTask = new Task<>() {
      @Override
      protected Image call() {
        String description =
            "Fantasy,"
                + " Realistic Digital Art,"
                + " You finally managed to climb the mountain,"
                + " in the distance you see a cave opening with a troll guarding the entrance.";
        try {
          return OpenAiImage.generateImage(description);
        } catch (Exception e) {
          ErrorLogger.LOGGER.log(Level.SEVERE,
              String.format("Failed to load AI Image: %s", e));
          return new Image(Objects.requireNonNull(
              MainStage.class.getResourceAsStream("/Image/Window/Background.png")));
        }
      }
    };

    loadImageTask.setOnSucceeded(event -> {
      Image roomImage = loadImageTask.getValue();
      ImageView roomImageView = new ImageView(roomImage);
      roomImageView.setFitWidth(1024);
      roomImageView.setFitHeight(768);
      content.getChildren().remove(0);
      content.getChildren().add(0, roomImageView);
    });

    Thread loadImageThread = new Thread(loadImageTask);
    loadImageThread.setDaemon(true);
    loadImageThread.start();
  }
}