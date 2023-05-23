package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.model.Link;
import no.ntnu.idatg2001.grp13.model.Passage;

@UtilityClass
public class GameScene {
  private static TextArea passageTextArea;
  private static ListView<Link> linkView;
  private static HBox linkButtonsContainer;

  public static Scene getGameScene(Stage stage) {

    BorderPane root = new BorderPane();
    setupScene(root);

    GameController.startGame(stage);
    //linkView.setItems(GameController.getLinkForPassage());

    setTextAreaContent();

    linkButtonsContainer = new HBox();
    linkButtonsContainer.setSpacing(10);
    setupLinkButtons();

    VBox containerPassageAndLink = setupContainerForPassageAndLink();
    FantasyButton nextButton = setupNextButton();
    FantasyButton restartButton = setupRestartButton(stage);

    HBox containerAction = setupActionContainer(restartButton, containerPassageAndLink, nextButton);

    root.setBottom(containerAction);


    return new Scene(root);
  }

  public static void setupLinkButtons() {
    linkButtonsContainer = new HBox();
    linkButtonsContainer.setSpacing(10); // Space between buttons

    for (Link link : GameController.getLinksForCurrentPassage()) {
      FantasyButton linkButton = new FantasyButton(link.getText());

      linkButton.setOnAction(event -> {
        // Clears the screen
        passageTextArea.clear();

        // Get the next passage text from the controller
        Passage nextPassage = GameController.getNextPassage(link);
        passageTextArea.setText(nextPassage.getTitle() + "\n" + nextPassage.getContent());

        // Refresh the links
        refreshLinkButtons();
      });

      linkButtonsContainer.getChildren().add(linkButton);
    }
  }

  public static void refreshLinkButtons() {
    // Clears the existing buttons
    linkButtonsContainer.getChildren().clear();

    // Adds new buttons based on the new links
    setupLinkButtons();

    // Force layout
    linkButtonsContainer.layout();
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
    linkView.setCellFactory(param -> new ListCell<>() {
      @Override
      protected void updateItem(Link link, boolean empty) {
        super.updateItem(link, empty);
        if (empty || link == null) {
          setText(null);
        } else {
          setText(link.getText());
        }
      }
    });

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
    containerPassageAndLink.getChildren().addAll(passageTextArea, linkButtonsContainer);

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
    FantasyButton restartButton = new FantasyButton("Restart!");
    restartButton.setOnMouseClicked(event -> {
      // Clear the existing buttons
      linkButtonsContainer.getChildren().clear();
      // Reset the game state
      GameController.restartGame(stage);

      // Reset passage text area and link view
      passageTextArea.clear();
      // Sets the text
      passageTextArea.setText(GameController.getCurrentPassage().getTitle() + "\n"
          + GameController.getCurrentPassage().getContent());

      linkView.setItems(GameController.getLinkForPassage());
      refreshLinkButtons();
    });
    return restartButton;
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
