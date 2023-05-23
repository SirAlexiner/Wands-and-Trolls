package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.util.QuickButtons;

@UtilityClass
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
        LoadAdventureScene.class.getResourceAsStream("/Image/Background/Background.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    FantasyButton cancelButton = QuickButtons.getGoBackButton(stage);

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
