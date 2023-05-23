package no.ntnu.idatg2001.grp13.gui.scene;

import java.io.File;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyPlayerUi;
import no.ntnu.idatg2001.grp13.gui.elements.LocalizedLabel;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.QuickButtons;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.stories.StoriesDao;
import no.ntnu.idatg2001.grp13.gui.util.stories.StoryReference;
import no.ntnu.idatg2001.grp13.model.GameHandler;

@UtilityClass
public class NewGame {

  public static Scene getNewGameScene(Stage stage) {
    BorderPane root = new BorderPane();
    Image background = new Image(Objects.requireNonNull(
        NewGame.class.getResourceAsStream("/Image/Background/Background.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    VBox storySelectBox = new VBox();
    storySelectBox.setAlignment(Pos.CENTER);
    storySelectBox.setPadding(new Insets(20, 10, 10, 10));
    storySelectBox.setStyle(
        "-fx-effect: innershadow(three-pass-box, rgba(88,88,88,0.75), 35, -25.0, 0, 0);");

    ListView<StoryReference> listView = new ListView<>();
    listView.setPrefHeight(690);
    List<StoryReference> storiesList = StoriesDao.loadStoryReferencesFromFile();
    ObservableList<StoryReference> observableList = FXCollections.observableList(storiesList);
    listView.setItems(observableList);

    ContextMenu contextMenu = new ContextMenu();
    MenuItem deleteItem = new MenuItem("Delete");
    contextMenu.getItems().add(deleteItem);

    listView.setOnContextMenuRequested(event ->
        contextMenu.show(listView, event.getScreenX(), event.getScreenY()));

    deleteItem.setOnAction(event -> {
      FantasyAlert deleteStoryAlert = new FantasyAlert(stage);
      deleteStoryAlert.setAlertType(Alert.AlertType.CONFIRMATION);
      deleteStoryAlert.setHeader("alert.deleteStoryText");
      deleteStoryAlert.setTitle("alert.deleteStory");
      deleteStoryAlert.showAndWait();
      if (FantasyAlert.getResult().equals(ButtonType.OK)) {
        StoryReference selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
          try {
            storiesList.remove(selectedItem);
            observableList.remove(selectedItem);
            listView.setItems(observableList);
            StoriesDao.saveStoryReferences();
          } catch (Exception e) {
            FantasyAlert failedToDeleteAlert = new FantasyAlert(stage);
            failedToDeleteAlert.setAlertType(Alert.AlertType.WARNING);
            failedToDeleteAlert.setHeader("alert.failedToDelete");
            failedToDeleteAlert.setTitle("alert.error");
            failedToDeleteAlert.showAndWait();
          }
        }
      }
    });
    storySelectBox.getChildren().add(listView);

    Image fileDropperBackground = new Image(Objects.requireNonNull(
        NewGame.class.getResourceAsStream("/Image/Background/Background_Purple.png")));

    storySelectBox.setBackground(new Background(
        new BackgroundImage(fileDropperBackground, BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false,
                true))));

    storySelectBox.setPrefWidth(512);
    storySelectBox.setPrefHeight(780);
    storySelectBox.setTranslateY(15);
    storySelectBox.setTranslateX(5.5);


    FantasyPlayerUi playerUi = new FantasyPlayerUi(100, 0, 1.25);
    HBox playerBox = new HBox(playerUi);
    playerBox.setPadding(new Insets(50, 0, 0, 100));

    Button addAvatarButton = new FantasyButton("button.selectAvatar", true);
    addAvatarButton.setTranslateX(145);
    addAvatarButton.setTranslateY(-25);
    addAvatarButton.setOnMouseClicked(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choose File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
      );

      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        playerUi.setPlayerAvatar(new Image(file.getAbsolutePath()));
      }
    });

    TextField adventurerName = new TextField();
    adventurerName.setPromptText(LanguageManager.getStringProperty("newGame.adventurer").get());

    TextField health = new TextField();
    health.setPromptText(LanguageManager.getStringProperty("newGame.healthPrompt").get());

    TextField goldGoals = new TextField();
    goldGoals.setPromptText(LanguageManager.getStringProperty("newGame.goldPrompt").get());

    TextField inventoryGoals = new TextField();
    inventoryGoals.setPromptText(
        LanguageManager.getStringProperty("newGame.inventoryPrompt").get());

    TextField scoreGoal = new TextField();
    scoreGoal.setPromptText(LanguageManager.getStringProperty("newGame.scorePrompt").get());

    VBox nameBox = new VBox();
    nameBox.setPadding(new Insets(0, 0, 0, 30));
    nameBox.setSpacing(10);
    nameBox.setAlignment(Pos.CENTER_LEFT);
    Label nameLabel = new LocalizedLabel("newGame.setName");
    Label goalsLabel = new LocalizedLabel("newGame.setGoldGoal");
    nameBox.getChildren()
        .addAll(nameLabel, adventurerName, health, goalsLabel, goldGoals, inventoryGoals,
            scoreGoal);
    nameBox.setTranslateX(30);

    FantasyButton startAdventure = new FantasyButton("button.startAdventure", true);
    startAdventure.setFantasyButtonType(FantasyButtonType.BONE);
    startAdventure.setPrefWidth(200);
    startAdventure.setOnMouseClicked(mouseEvent -> {
      if (listView.getSelectionModel().getSelectedItem() != null) {
        new GameHandler(playerUi,
            listView.getSelectionModel().getSelectedItem().getAbsolutePath(),
            adventurerName.getText(), goldGoals.getText(), inventoryGoals.getText(),
            scoreGoal.getText());
        Scene gameScene = GameScene.getGameScene(stage);
        MainMenuScene.getContentContainer().getChildren().clear();
        MainMenuScene.getContentContainer().getChildren().add(gameScene.getRoot());
      } else {
        FantasyAlert brokenLinksAlert = new FantasyAlert(stage);
        brokenLinksAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        brokenLinksAlert.setHeader("alert.needFileText");
        brokenLinksAlert.setTitle("alert.needFile");
        brokenLinksAlert.showAndWait();
      }
    });

    FantasyButton cancelButton = QuickButtons.getGoBackButton(stage);

    HBox bottomButtons = new HBox(startAdventure, cancelButton);
    bottomButtons.setAlignment(Pos.CENTER);
    bottomButtons.setSpacing(15);
    bottomButtons.setTranslateX(40);
    bottomButtons.setPadding(new Insets(30, 0, 0, 0));

    VBox playerInformation = new VBox(playerBox, addAvatarButton, nameBox, bottomButtons);
    playerInformation.setAlignment(Pos.TOP_LEFT);
    playerInformation.setPadding(new Insets(10));
    playerInformation.setSpacing(10);
    playerInformation.setPrefWidth(400);
    playerInformation.setPrefHeight(768);

    root.setLeft(playerInformation);
    root.setRight(storySelectBox);

    root.setPadding(new Insets(5));
    root.setOnMouseClicked(event -> listView.refresh());

    return new Scene(root);
  }
}