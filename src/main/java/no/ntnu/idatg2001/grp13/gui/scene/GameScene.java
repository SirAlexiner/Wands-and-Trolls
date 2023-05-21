package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
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
  private static TextArea passageTextArea;
  private static ListView<Link> linkView;
  private static Stage stage;

  public static Scene getGameScene(Stage stage) {

    BorderPane root = new BorderPane();
    setupScene(root);

    GameController.startGame(stage);
    linkView.setItems(GameController.getLinkForPassage());

    setTextAreaContent();

    VBox containerPassageAndLink = setupContainerForPassageAndLink();
    FantasyButton nextButton = setupNextButton();
    FantasyButton restartButton = setupRestartButton(stage);

    HBox containerAction = setupActionContainer(restartButton, containerPassageAndLink, nextButton);

    root.setBottom(containerAction);

    return new Scene(root);
  }

  private static void setupScene(BorderPane root) {
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    ImageView backgroundView = setupBackgroundView();
    root.getChildren().add(backgroundView);
    backgroundView.toBack();

    linkView = setupLinkView();
    passageTextArea = setupPassageContentTextArea();
  }

  private static ImageView setupBackgroundView() {
    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    return backgroundView;
  }

  private static ListView<Link> setupLinkView() {
    ListView<Link> linkView = new ListView<>();
    linkView.setPrefSize(100, 50);

    return linkView;
  }

  private static TextArea setupPassageContentTextArea() {
    TextArea passageContentTextArea = new TextArea();
    passageContentTextArea.setPrefSize(300, 100);
    passageContentTextArea.setEditable(false);

    return passageContentTextArea;
  }

  private static void setTextAreaContent() {
    passageTextArea.setText(GameController.getCurrentPassage().getTitle() + "\n"
        + GameController.getCurrentPassage().getContent());
  }

  private static VBox setupContainerForPassageAndLink() {
    VBox containerPassageAndLink = new VBox();
    containerPassageAndLink.setAlignment(Pos.CENTER);
    containerPassageAndLink.setSpacing(10);
    containerPassageAndLink.getChildren().addAll(passageTextArea, linkView);

    return containerPassageAndLink;
  }

  private static FantasyButton setupNextButton() {
    FantasyButton nextButton = new FantasyButton("Next!");

    nextButton.setOnMouseClicked(event -> {

      Link selectedLink = linkView.getSelectionModel().getSelectedItem();
      if (selectedLink != null) {
        passageTextArea.clear();
        linkView.refresh();

        Passage nextPassage = GameController.getNextPassage(selectedLink);
        passageTextArea.setText(nextPassage.getTitle() + "\n" + nextPassage.getContent());
        linkView.setItems(GameController.getLinkForPassage());
      }
    });
    return nextButton;
  }

  private static FantasyButton setupRestartButton(Stage stage) {
    FantasyButton nextButton = new FantasyButton("Restart!");
    //TODO bug where brokenLinks appears
    nextButton.setOnMouseClicked(event -> {
      // Reset the game state
      GameController.startGame(stage);

      // Reset passage content text area and link view
      passageTextArea.clear();
      // Sets the text
      passageTextArea.setText(GameController.getCurrentPassage().getTitle() + "\n"
          + GameController.getCurrentPassage().getContent());

      linkView.setItems(GameController.getLinkForPassage());
    });
    return nextButton;
  }

  private static HBox setupActionContainer(FantasyButton restartButton,
                                           VBox containerPassageAndLink, FantasyButton nextButton) {
    HBox containerAction = new HBox();
    containerAction.setSpacing(10);
    containerAction.getChildren().addAll(restartButton, containerPassageAndLink, nextButton);
    containerAction.setAlignment(Pos.CENTER);

    return containerAction;
  }

  public static FantasyAlert storyContainingBrokenLinks(Stage stage) {
    FantasyAlert brokenLinksAlert = new FantasyAlert(stage);
    brokenLinksAlert.setAlertType(Alert.AlertType.INFORMATION);
    brokenLinksAlert.setHeader("Story contains broken links! "
        + "\n" + "Playing the game will not include those links.");
    return brokenLinksAlert;
  }
}
