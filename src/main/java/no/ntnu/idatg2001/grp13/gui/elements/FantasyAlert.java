package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.language.LanguageManager;

/**
 * The FantasyAlert class is a custom Java alert dialog that allows for customization of the alert
 * type, header, and buttons.
 */
public class FantasyAlert {
  @Getter
  @Setter
  private static ButtonType result;

  private final Label header;

  private final HBox buttons;
  private final Stage stage;

  public FantasyAlert(Stage stageOwner) {
    stage = new Stage(StageStyle.TRANSPARENT);
    stage.initOwner(stageOwner);
    stage.initModality(Modality.WINDOW_MODAL);

    Image background = new Image(Objects.requireNonNull(
        FantasyAlert.class.getResourceAsStream("/Image/FantasyAlert/Alert_Frame.png")));
    ImageView backgroundView = new ImageView(background);

    Rectangle clip = new Rectangle(310, 15);
    Rectangle alertShape = new Rectangle(310, 300);

    Shape invertedClip = Shape.subtract(alertShape, clip);

    backgroundView.setClip(invertedClip);

    backgroundView.setFitWidth(310);
    backgroundView.setTranslateX(-5);
    backgroundView.setTranslateY(30);
    backgroundView.setPreserveRatio(true);

    BorderPane root = new BorderPane();
    root.getChildren().add(0, backgroundView);

    FantasyButton confirm = new FantasyButton("button.yes", true);
    confirm.setFantasyButtonType(FantasyButtonType.BONE);
    confirm.setButtonType(ButtonType.OK);
    confirm.setPrefWidth(150);
    confirm.setOnMouseClicked(event -> {
      setResult(confirm.getButtonType());
      stage.close();
    });

    FantasyButton cancel = new FantasyButton("button.no", true);
    cancel.setFantasyButtonType(FantasyButtonType.BONE);
    cancel.setButtonType(ButtonType.CANCEL);
    cancel.setPrefWidth(150);
    cancel.setOnMouseClicked(event -> {
      setResult(cancel.getButtonType());
      stage.close();
    });

    buttons = new HBox(confirm, cancel);
    buttons.setAlignment(Pos.CENTER);
    buttons.setSpacing(10);
    buttons.setTranslateY(5);

    header = new Label();
    header.setTextAlignment(TextAlignment.CENTER);
    header.setWrapText(true);
    header.setTranslateY(13);
    header.setMinHeight(85);
    header.setPadding(new Insets(0));

    VBox content = new VBox(header, buttons);
    content.setAlignment(Pos.CENTER);
    content.setSpacing(10);
    content.setPrefWidth(300);
    content.setMaxHeight(100);

    content.setScaleY(0.75);
    content.setScaleX(0.75);
    content.setTranslateY(-75);

    root.setTop(new FantasyTopBarAlert(stage));
    root.setCenter(content);

    Scene scene = new Scene(root, 300, 320, Color.TRANSPARENT);
    scene.getStylesheets().add(String.valueOf(FantasyButton.class.getResource(
        "/CSS/WindowUi/FantasyStyle_Alert.css")));
    stage.setScene(scene);
  }

  public void setTitle(String resourceKey) {
    FantasyTopBarAlert.updateTopBarTitle(resourceKey);
  }

  public void setHeader(String resourceKey) {
    header.textProperty().bind(LanguageManager.getStringProperty(resourceKey));
  }

  public void setAlertType(Alert.AlertType alertType) {
    switch (alertType) {
      case CONFIRMATION -> FantasyTopBarAlert.setAlertIconView(new Image(Objects.requireNonNull(
          FantasyAlert.class.getResourceAsStream(
              "/Image/FantasyAlert/WarningBox_Icon_Question.png"))));
      case WARNING, ERROR -> {
        addConfirmationButton();

        FantasyTopBarAlert.setAlertIconView(new Image(Objects.requireNonNull(
            FantasyAlert.class.getResourceAsStream(
                "/Image/FantasyAlert/WarningBox_Icon_Red.png"))));
      }
      default -> {
        addConfirmationButton();

        FantasyTopBarAlert.setAlertIconView(new Image(Objects.requireNonNull(
            FantasyAlert.class.getResourceAsStream(
                "/Image/FantasyAlert/WarningBox_Icon_Gray.png"))));
      }
    }
  }

  public void showAndWait() {
    stage.showAndWait();
  }

  private void addConfirmationButton() {
    FantasyButton ok = new FantasyButton("button.confirm", true);
    ok.setFantasyButtonType(FantasyButtonType.BONE);
    ok.setButtonType(ButtonType.OK);
    ok.setPrefWidth(150);
    ok.setOnMouseClicked(event -> {
      setResult(ok.getButtonType());
      stage.close();
    });

    buttons.getChildren().clear();
    buttons.getChildren().add(ok);
    buttons.setAlignment(Pos.CENTER_RIGHT);
  }

}
