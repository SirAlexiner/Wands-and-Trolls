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
    setupScene(root);

    GameController.startGame();
    linkView.setItems(GameController.getLinkForPassage());

    setTextAreaContent();

    VBox containerPassageAndLink = setupContainerForPassageAndLink();
    FantasyButton nextButton = setupNextButton();

    HBox containerAction = setupActionContainer(containerPassageAndLink, nextButton);

    root.setBottom(containerAction);

    return new Scene(root);
  }

  private static void setupScene(BorderPane root) {
    root.getStylesheets().add(String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    ImageView backgroundView = setupBackgroundView();
    root.getChildren().add(backgroundView);
    backgroundView.toBack();

    linkView = setupLinkView();
    passageContentTextArea = setupPassageContentTextArea();
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
    passageContentTextArea.setText(GameController.getCurrentPassage().getTitle() + "\n"
        + GameController.getCurrentPassage().getContent());
  }

  private static VBox setupContainerForPassageAndLink() {
    VBox containerPassageAndLink = new VBox();
    containerPassageAndLink.setAlignment(Pos.CENTER);
    containerPassageAndLink.setSpacing(10);
    containerPassageAndLink.getChildren().addAll(passageContentTextArea, linkView);

    return containerPassageAndLink;
  }

  private static FantasyButton setupNextButton() {
    FantasyButton nextButton = new FantasyButton("Next!");

    nextButton.setOnMouseClicked(event -> {
      GameController.startGame();

      Link selectedLink = linkView.getSelectionModel().getSelectedItem();
      if (selectedLink != null) {
        passageContentTextArea.clear();
        linkView.refresh();

        Passage nextPassage = GameController.getNextPassage(selectedLink);
        passageContentTextArea.setText(nextPassage.getTitle() + "\n" + nextPassage.getContent());
        linkView.setItems(GameController.getLinkForPassage());
      }
    });
    return nextButton;
  }

  private static HBox setupActionContainer(VBox containerPassageAndLink, FantasyButton nextButton) {
    HBox containerAction = new HBox();
    containerAction.setSpacing(10);
    containerAction.getChildren().addAll(containerPassageAndLink, nextButton);
    containerAction.setAlignment(Pos.CENTER);

    return containerAction;
  }
}
