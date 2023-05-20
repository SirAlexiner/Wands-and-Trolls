package no.ntnu.idatg2001.grp13.gui.scene;

import java.io.File;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;

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

    Button addButton = new FantasyButton("Add File");

    Label dropLabel;
    dropLabel = new Label("Drag and drop a file here");
    dropLabel.setGraphic(addButton);
    dropLabel.setAlignment(Pos.CENTER);

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

    root.setOnDragOver(event -> {
      if (event.getDragboard().hasFiles()) {
        event.acceptTransferModes(TransferMode.COPY);
      }
      event.consume();
    });

    root.setOnDragDropped(event -> {
      Dragboard dragboard = event.getDragboard();
      boolean success = false;

      if (dragboard.hasFiles()) {
        File file = dragboard.getFiles().get(0);
        dropLabel.setText("Selected file: " + file.getAbsolutePath());

        success = true;
      }

      event.setDropCompleted(success);
      event.consume();
    });

    root.setCenter(dropLabel);

    return new Scene(root);
  }
}