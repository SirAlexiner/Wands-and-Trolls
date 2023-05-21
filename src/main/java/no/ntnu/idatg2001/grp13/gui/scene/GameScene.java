package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.model.Link;

/**
 * This class is part of the "WiNG" application and represents the game scene within the application.
 * It is a utility class that is responsible for generating the game scene. The game scene is made up of various user interface elements including text area,
 * list view for links, buttons and background. These elements are structured in the layout for an interactive game
 * experience.
 */
public class GameScene {
  private static TextArea passageContentText;
  public static Scene getGameScene(Stage stage) {
    BorderPane root = new BorderPane();
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);


    // Initializes a ListView containing link
    ListView<Link> linkView = new ListView<>();
    linkView.setPrefSize(100, 50);

    // Initializes the text area, containing passage content
    passageContentText = new TextArea(GameController.getPassageContent());
    passageContentText.setPrefSize(300, 100);
    passageContentText.setEditable(false);


    // Initializes a vertical box containing passage and link
    VBox containerPassageAndLink = new VBox();
    containerPassageAndLink.setAlignment(Pos.CENTER);
    containerPassageAndLink.setSpacing(10);
    containerPassageAndLink.getChildren().addAll(passageContentText, linkView);

    FantasyButton nextButton = new FantasyButton("Next!");
    nextButton.setOnMouseClicked(event -> {
      passageContentText.clear();
      String nextPassageContent = GameController.getNextPassage();
      passageContentText.setText(nextPassageContent);
    });

    // Initializes a container containing passages and button
    HBox containerAction = new HBox();
    containerAction.setSpacing(10);
    containerAction.getChildren().addAll(containerPassageAndLink, nextButton);
    containerAction.setAlignment(Pos.CENTER);

    // sets it to the bottom of the screen
    root.setBottom(containerAction);

    // Sends the background the back
    root.getChildren().add(backgroundView);
    backgroundView.toBack();

    return new Scene(root);
  }

  private static TextArea getPassageText(String passageContent) {
    // Initializes the text area, containing passage content
    passageContentText = new TextArea(passageContent);
    passageContentText.setPrefSize(300, 100);
    passageContentText.setEditable(false);
    return passageContentText;
  }


}
