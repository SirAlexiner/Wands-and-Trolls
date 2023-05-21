package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
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
import no.ntnu.idatg2001.grp13.model.Passage;

/**
 * This class is part of the "WiNG" application and represents the game scene within the application.
 * It is a utility class that is responsible for generating the game scene. The game scene is made up of various user interface elements including text area,
 * list view for links, buttons and background. These elements are structured in the layout for an interactive game
 * experience.
 */
public class GameScene {
  private static TextArea passageContentTextArea;
  private static ListView<Link> linkView;
  public static Scene getGameScene(Stage stage) {
    BorderPane root = new BorderPane();
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    // Initializes a ListView containing link title
    linkView = new ListView<>();
    linkView.setPrefSize(100, 50);


    GameController.startGame();
    linkView.setItems(GameController.getLinkForPassage());

    // Initializes the text area
    passageContentTextArea = new TextArea();
    passageContentTextArea.setPrefSize(300, 100);
    passageContentTextArea.setEditable(false);
    passageContentTextArea.setText(GameController.getCurrentPassage().getTitle() + "\n"
        + GameController.getCurrentPassage().getContent());

    // Initializes a vertical box containing passage and link
    VBox containerPassageAndLink = new VBox();
    containerPassageAndLink.setAlignment(Pos.CENTER);
    containerPassageAndLink.setSpacing(10);
    containerPassageAndLink.getChildren().addAll(passageContentTextArea, linkView);


    // Initializes a "next" button and adds a mouse click event
    // to clear the text.
    FantasyButton nextButton = new FantasyButton("Next!");
    nextButton.setOnMouseClicked(event -> {
      GameController.startGame();
      // sends the selected link to the controller
      Link selectedLink = linkView.getSelectionModel().getSelectedItem();
      if (selectedLink != null) {
        // clears the screen
        passageContentTextArea.clear();
        linkView.refresh();
        //the passage object, sends the title and content to the controller
        Passage nextPassage = GameController.getNextPassage(selectedLink);
        passageContentTextArea.setText(nextPassage.getTitle() + "\n" + nextPassage.getContent());
        //sets the links in the list view
        linkView.setItems(GameController.getLinkForPassage());
      }
    });

    linkView.setCellFactory(param -> new ListCell<>() {
      @Override
      protected void updateItem(Link item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.getText());
        }
      }
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



}
