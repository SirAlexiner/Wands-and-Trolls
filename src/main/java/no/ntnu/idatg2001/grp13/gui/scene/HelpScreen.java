package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;

public class HelpScreen {

  public static Scene getHelpScene(Stage stage) {
    // Create a WebView component
    WebView webView = new WebView();

    // Create a WebEngine to manage the WebView
    WebEngine webEngine = webView.getEngine();

    // Load the HTML file
    webEngine.load(Objects.requireNonNull(HelpScreen.class.getResource("/HTML/help.html"))
        .toString());

    // Create a layout container
    BorderPane root = new BorderPane();
    Image background = new Image(Objects.requireNonNull(
        LoadAdventureScene.class.getResourceAsStream("/Image/Window/Background.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    FantasyButton cancelButton = new FantasyButton("button.goBack");
    cancelButton.setFantasyButtonType(FantasyButtonType.BONE);
    cancelButton.setPrefWidth(200);
    cancelButton.setOnMouseClicked(event -> {
      FantasyAlert quitAlert = new FantasyAlert(stage);
      quitAlert.setTitle("alert.goBack");
      quitAlert.setAlertType(Alert.AlertType.CONFIRMATION);
      quitAlert.setHeader("alert.goBackText");

      quitAlert.showAndWait();

      if (FantasyAlert.getResult().equals(ButtonType.OK)) {
        MainMenuScene.getContentContainer().getChildren().remove(1);
      }
    });

    HBox bottomButton = new HBox(cancelButton);
    bottomButton.setAlignment(Pos.CENTER_RIGHT);
    bottomButton.setPadding(new Insets(20));
    bottomButton.setSpacing(15);

    // Add the WebView to the center of the layout container
    root.setCenter(webView);
    root.setBottom(bottomButton);

    // Create a Scene with the layout container
    return new Scene(root, 1024, 700);
  }
}
