package no.ntnu.idatg2001.grp13.gui.scene;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.LocalizedLabel;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.QuickButtons;
import no.ntnu.idatg2001.grp13.gui.util.stories.StoriesDao;
import no.ntnu.idatg2001.grp13.gui.util.stories.StoryReference;
import no.ntnu.idatg2001.grp13.model.Story;
import no.ntnu.idatg2001.grp13.model.StoryReader;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

/**
 * <p>LoadAdventureScene class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class LoadAdventureScene {

  private static File file;

  /**
   * <p>getScene.</p>
   *
   * @param stage a {@link javafx.stage.Stage} object
   * @return a {@link javafx.scene.Scene} object
   */
  public static Scene getScene(Stage stage) {
    BorderPane root = new BorderPane();
    Image background = new Image(Objects.requireNonNull(
        LoadAdventureScene.class.getResourceAsStream("/Image/Background/Background.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    Label dropLabel;
    dropLabel = new LocalizedLabel("button.dragDrop");
    dropLabel.setAlignment(Pos.CENTER);

    Label selectedLabel = new Label();
    selectedLabel.setPadding(new Insets(20));
    selectedLabel.setTextAlignment(TextAlignment.CENTER);

    FantasyButton addButton = new FantasyButton("button.selectFile", true);
    addButton.setFantasyButtonType(FantasyButtonType.BLUE);
    addButton.setOnMouseClicked(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle(LanguageManager.getStringProperty("button.selectFile").get());
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Paths Files", "*.paths")
      );

      file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        selectedLabel.setText(file.getName() + "\n" + file.getAbsolutePath());
      } else {
        selectedLabel.setText("");
      }
    });

    VBox fileDropBox = new VBox(dropLabel, selectedLabel, addButton);
    fileDropBox.setAlignment(Pos.CENTER);
    fileDropBox.setMaxWidth(975);
    fileDropBox.setMaxHeight(500);
    fileDropBox.setStyle(
        "-fx-effect: innershadow(three-pass-box, rgba(88,88,88,0.75), 50, -25.0, 0, 0);");

    Image fileDropperBackground = new Image(Objects.requireNonNull(
        LoadAdventureScene.class.getResourceAsStream("/Image/Background/Background_Purple.png")));

    fileDropBox.setBackground(new Background(
        new BackgroundImage(fileDropperBackground, BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false,
                true))));

    fileDropBox.setOnDragOver(event -> {
      boolean isValid = event.getDragboard().getFiles().stream()
          .allMatch(file -> file.getName().endsWith(".paths")); // Only accept .txt files

      if (isValid) {
        event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
      }
      event.consume();
    });

    fileDropBox.setOnDragEntered(event -> fileDropBox.setStyle(
        "-fx-effect: innershadow(three-pass-box, rgba(150,200,200,0.75), 50, -25.0, 0, 0);"));

    fileDropBox.setOnDragExited(event -> fileDropBox.setStyle(
        "-fx-effect: innershadow(three-pass-box, rgba(88,88,88,0.75), 50, -25.0, 0, 0);"));

    fileDropBox.setOnDragDropped(event -> {
      Dragboard dragboard = event.getDragboard();
      boolean success = false;

      if (dragboard.hasFiles()) {
        file = dragboard.getFiles().get(0);
        selectedLabel.setText(file.getName() + "\n" + file.getAbsolutePath());

        success = true;
      }

      event.setDropCompleted(success);
      fileDropBox.setStyle(
          "-fx-effect: innershadow(three-pass-box, rgba(88,88,88,0.75), 50, -25.0, 0, 0);");
      event.consume();
    });

    Rectangle clip = new Rectangle(1000, 500);
    clip.setArcHeight(25);
    clip.setArcWidth(25);

    fileDropBox.setClip(clip);

    Region pusher = new Region();
    pusher.setPrefWidth(310);
    pusher.setPrefHeight(40);

    FantasyButton cancelButton = QuickButtons.getGoBackButton(stage);

    FantasyButton saveButton = new FantasyButton("button.save", true);
    saveButton.setOnMouseClicked(event -> {
      if (file != null) {
        saveFilePath(stage);
      }
    });
    saveButton.setFantasyButtonType(FantasyButtonType.BONE);
    saveButton.setPrefWidth(200);

    HBox bottomButtons = new HBox(saveButton, cancelButton);
    bottomButtons.setAlignment(Pos.CENTER);
    bottomButtons.setPadding(new Insets(10));
    bottomButtons.setSpacing(15);

    root.setTop(pusher);
    root.setCenter(fileDropBox);
    root.setBottom(bottomButtons);

    root.setPadding(new Insets(5));

    return new Scene(root);
  }

  private static void saveFilePath(Stage stage) {
    try {
      Story story = StoryReader.readFromFile(file.getAbsolutePath());
      if (story != null && (story.getBrokenLinks().isEmpty())) {
        saveStoryReference(stage);
      } else {
        ErrorLogger.LOGGER.log(Level.SEVERE, "Failed to load story from file.");
        FantasyAlert brokenLinksAlert = new FantasyAlert(stage);
        brokenLinksAlert.setAlertType(Alert.AlertType.WARNING);
        brokenLinksAlert.setHeader("alert.storyEmptyOrBrokenLinks");
        brokenLinksAlert.setTitle("alert.error");
        brokenLinksAlert.showAndWait();
      }
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE, String.format("Failed to load story from file: %s", e));
    }
  }

  private static void saveStoryReference(Stage stage) {
    StoryReference newStory = new StoryReference(file.getAbsolutePath());
    try {
      List<StoryReference> stories = StoriesDao.loadStoryReferencesFromFile();
      if (stories.contains(newStory)) {
        FantasyAlert storyExistsAlert = new FantasyAlert(stage);
        storyExistsAlert.setAlertType(Alert.AlertType.INFORMATION);
        storyExistsAlert.setHeader("alert.storyExistsText");
        storyExistsAlert.setTitle("alert.storyExists");
        storyExistsAlert.showAndWait();
      } else {
        stories.add(newStory);
        FantasyAlert storySavedAlert = new FantasyAlert(stage);
        storySavedAlert.setAlertType(Alert.AlertType.INFORMATION);
        storySavedAlert.setHeader("alert.storySavedText");
        storySavedAlert.setTitle("alert.storySaved");
        storySavedAlert.showAndWait();
      }
      StoriesDao.saveStoryReferences();
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE, String.format(
          "Story should not be empty or contain broken links, but failed to load: %s", e));
    }
  }
}
