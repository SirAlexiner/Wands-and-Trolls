package no.ntnu.idatg2001.grp13.gui.scene;

import java.time.LocalDate;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
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
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.gui.util.App;

@UtilityClass
public class MainMenuScene {
  @Getter
  private StackPane contentContainer;

  public static StackPane getScene(Stage stage) {
    Image background = new Image(
        Objects.requireNonNull(MainStage.class.getResourceAsStream(
            "/Image/MainMenu/Main_Menu.gif")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    BorderPane content = new BorderPane();
    content.getChildren().add(backgroundView);
    HBox logoBox = createLogoBox();
    content.setTop(logoBox);
    VBox mainButtonBox = createMainButtonBox(stage);
    content.setCenter(mainButtonBox);
    BorderPane bottomInformation = createBottomInformation();
    content.setBottom(bottomInformation);
    contentContainer = new StackPane(content);
    contentContainer.setPrefHeight(660);
    Rectangle clipRect = createClipRect();
    contentContainer.setClip(clipRect);

    contentContainer.setDepthTest(DepthTest.ENABLE);

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
        createHelpButton(stage),
        createSettingsButton(stage)
    );

    return box;
  }

  private Button createNewGameButton(Stage stage) {
    Button newGameButton = new FantasyButton("button.newGame", true);
    newGameButton.setOnMouseClicked(event -> {
      Scene newGameScene = NewGame.getNewGameScene(stage);
      contentContainer.getChildren().add(newGameScene.getRoot());
    });
    newGameButton.setPrefWidth(230);
    return newGameButton;
  }

  private Button createLoadButton(Stage stage) {
    Button loadButton = new FantasyButton("button.loadAdventure", true);
    loadButton.setOnMouseClicked(event -> {
      Scene filePickerScene = LoadAdventureScene.getScene(stage);
      contentContainer.getChildren().add(filePickerScene.getRoot());
    });
    loadButton.setPrefWidth(230);
    return loadButton;
  }

  private Button createHelpButton(Stage stage) {
    Button settingsButton = new FantasyButton("button.help", true);
    settingsButton.setPrefWidth(230);
    settingsButton.setOnMouseClicked(event -> {
      Scene helpScene = HelpScreen.getHelpScene(stage);
      contentContainer.getChildren().add(helpScene.getRoot());
    });
    return settingsButton;
  }

  private Button createSettingsButton(Stage stage) {
    Button settingsButton = new FantasyButton("button.settings", true);
    settingsButton.setPrefWidth(230);
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
}