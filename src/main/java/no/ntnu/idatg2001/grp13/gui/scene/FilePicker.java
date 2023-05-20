package no.ntnu.idatg2001.grp13.gui.scene;

import java.io.File;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.stage.MainStage;

@UtilityClass
public class FilePicker {

  public static Scene getFilePickerScene(Stage stage) {
    BorderPane root = new BorderPane();
    Image background = new Image(Objects.requireNonNull(
        FilePicker.class.getResourceAsStream("/Image/Window/Background.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    Label dropLabel;
    dropLabel = new Label("Drag and drop a file here");
    dropLabel.setAlignment(Pos.CENTER);

    FantasyButton addButton = new FantasyButton("Add File");
    addButton.setPrefWidth(125);
    addButton.setFantasyButtonType(FantasyButtonType.BLUE);
    addButton.setOnMouseClicked(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choose File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("All Files", "*.*")
      );

      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        dropLabel.setText("Selected file: " + file.getAbsolutePath());
      } else {
        dropLabel.setText("Drag and drop a file here");
      }
    });

    VBox fileDropBox = new VBox(dropLabel, addButton);
    fileDropBox.setAlignment(Pos.CENTER);
    fileDropBox.setMaxWidth(975);
    fileDropBox.setMaxHeight(500);
    fileDropBox.setStyle(
        "-fx-effect: innershadow(three-pass-box, rgba(88,88,88,0.75), 50, -25.0, 0, 0);");

    Image fileDropperBackground = new Image(Objects.requireNonNull(
        FilePicker.class.getResourceAsStream("/Image/Window/Background_Purple.png")));

    fileDropBox.setBackground(new Background(
        new BackgroundImage(fileDropperBackground, BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false,
                true))));

    fileDropBox.setOnDragOver(event -> {
      if (event.getDragboard().hasFiles()) {
        event.acceptTransferModes(TransferMode.COPY);
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
        File file = dragboard.getFiles().get(0);
        dropLabel.setText("Selected file: " + file.getAbsolutePath());

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

    FantasyButton cancelButton = new FantasyButton("Go Back");
    cancelButton.setFantasyButtonType(FantasyButtonType.BONE);
    cancelButton.setPrefWidth(125);
    cancelButton.setOnMouseClicked(event -> {
      AudioClip buttonClick = new AudioClip(
          Objects.requireNonNull(MainStage.class.getResource("/Audio/mouseclick_softer.wav"))
              .toString());
      buttonClick.play();
      FantasyAlert quitAlert = new FantasyAlert(stage);
      quitAlert.setAlertType(Alert.AlertType.CONFIRMATION);
      quitAlert.setHeader("Are you sure you want to go back?\nUnsaved files will be lost!");

      quitAlert.showAndWait();

      if (FantasyAlert.getResult().equals(ButtonType.OK)) {
        MainMenuScene.getContentContainer().getChildren().remove(1);
      }
    });

    FantasyButton saveButton = new FantasyButton("Save");
    saveButton.setFantasyButtonType(FantasyButtonType.BONE);
    saveButton.setPrefWidth(125);

    HBox bottomButtons = new HBox(saveButton, cancelButton);
    bottomButtons.setAlignment(Pos.CENTER);
    bottomButtons.setSpacing(15);

    root.setTop(pusher);
    root.setCenter(fileDropBox);
    root.setBottom(bottomButtons);

    root.setPadding(new Insets(5));

    return new Scene(root);
  }
}