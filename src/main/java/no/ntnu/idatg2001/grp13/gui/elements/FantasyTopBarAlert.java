package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.util.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.SoundEffectPlayer;

public class FantasyTopBarAlert extends StackPane {
  private static final ImageView alertIconView = new ImageView();
  private double xoffset = 0;
  private double yoffset = 0;

  private static final Label title = new Label();

  public FantasyTopBarAlert(Stage stage) {
    this.setTranslateY(3);
    HBox topBar = new HBox();
    // Load and set the side gradients and middle gradients as the background
    Image backgroundImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Background.png")));

    Image sideGradientImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Gradient_Side.png")));

    Image middleGradientImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Gradient_Middle.png")));

    topBar.setBackground(new Background(
        new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false, true)),
        new BackgroundImage(sideGradientImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false, true)),
        new BackgroundImage(middleGradientImage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, true, false, true))
    ));

    topBar.setMaxHeight(35);
    topBar.setPadding(new Insets(5));
    topBar.setAlignment(Pos.CENTER_RIGHT);
    topBar.setSpacing(2.5);

    alertIconView.setFitHeight(15);
    alertIconView.setPreserveRatio(true);

    HBox leftAlignmentBox = new HBox(alertIconView, title);
    leftAlignmentBox.setPrefWidth(300);
    leftAlignmentBox.setSpacing(2.5);
    leftAlignmentBox.setAlignment(Pos.CENTER_LEFT);
    topBar.getChildren().add(leftAlignmentBox);

    Image exitIcon = new Image(Objects.requireNonNull(
            FantasyTopBarAlert.class.getResource(
                "/Image/FantasyExitButton/Window_Exit_Button_Icon.png"))
        .toString());
    ImageView exitIconView = new ImageView(exitIcon);
    exitIconView.setFitWidth(15);
    exitIconView.setPreserveRatio(true);

    exitIconView.setOnMouseEntered(event -> {
      ColorAdjust colorAdjust = new ColorAdjust();
      colorAdjust.setHue(-0.3);
      colorAdjust.setSaturation(0.75);

      exitIconView.setEffect(colorAdjust);
    });

    exitIconView.setOnMouseExited(event -> exitIconView.setEffect(null));

    exitIconView.setOnMouseClicked(event -> {
      SoundEffectPlayer.playMouseClickSoundEffect();
      FantasyAlert.setResult(ButtonType.CANCEL);
      stage.close();
    });

    HBox exit = new HBox();
    exit.setPadding(new Insets(0, 10, 0, 0));
    exit.setAlignment(Pos.CENTER_RIGHT);

    exit.getChildren().add(exitIconView);
    topBar.getChildren().add(exit);

    BorderPane innerBorderPane = new BorderPane();
    innerBorderPane.setTranslateY(-2.0);

    HBox bottomBox = new HBox();
    bottomBox.setPrefSize(300, 10.0);
    bottomBox.setTranslateY(-3.5);
    BorderPane.setAlignment(bottomBox, Pos.CENTER);

    ImageView topBarBorder1 = new ImageView();
    topBarBorder1.setFitHeight(20.0);
    topBarBorder1.setFitWidth(100);
    topBarBorder1.setPickOnBounds(true);

    Image borderDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream("/Image/FantasyBar/Fantasy_Bar_Border.png")));
    topBarBorder1.setImage(borderDecorationImage);

    ImageView topBarDecorationTop = new ImageView();
    topBarDecorationTop.setFitHeight(35.0);
    topBarDecorationTop.setFitWidth(100);
    topBarDecorationTop.setTranslateY(8.0);
    topBarDecorationTop.setPickOnBounds(true);
    topBarDecorationTop.setPreserveRatio(true);

    ImageView bottomBarDecorationTop = new ImageView();
    bottomBarDecorationTop.setFitHeight(35.0);
    bottomBarDecorationTop.setFitWidth(100);
    bottomBarDecorationTop.setRotate(180.0);
    bottomBarDecorationTop.setTranslateY(-8.0);
    bottomBarDecorationTop.setPickOnBounds(true);
    bottomBarDecorationTop.setPreserveRatio(true);

    ImageView topBarBorder2 = new ImageView();
    topBarBorder2.setFitHeight(20.0);
    topBarBorder2.setFitWidth(100);
    topBarBorder2.setPickOnBounds(true);
    topBarBorder2.setRotate(180.0);
    topBarBorder2.setImage(borderDecorationImage);
    topBarBorder2.setRotationAxis(new Point3D(0, 1, 0));

    bottomBox.getChildren().addAll(topBarBorder1, bottomBarDecorationTop, topBarBorder2);

    HBox topBox = new HBox();
    topBox.setPrefSize(300, 16.0);
    BorderPane.setAlignment(topBox, Pos.CENTER);

    ImageView topBarBorder3 = new ImageView();
    topBarBorder3.setFitHeight(20.0);
    topBarBorder3.setFitWidth(100);
    topBarBorder3.setPickOnBounds(true);
    topBarBorder3.setRotate(180.0);
    topBarBorder3.setTranslateY(5.0);
    topBarBorder3.setImage(borderDecorationImage);
    topBarBorder3.setRotationAxis(new Point3D(1, 0, 0));

    Image topDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Top.png")));
    topBarDecorationTop.setImage(topDecorationImage);
    bottomBarDecorationTop.setImage(topDecorationImage);

    ImageView topBarBorder4 = new ImageView();
    topBarBorder4.setFitHeight(20.0);
    topBarBorder4.setFitWidth(100);
    topBarBorder4.setPickOnBounds(true);
    topBarBorder4.setRotate(180.0);
    topBarBorder4.setTranslateY(5.0);
    topBarBorder4.setImage(borderDecorationImage);

    topBox.getChildren().addAll(topBarBorder3, topBarDecorationTop, topBarBorder4);

    innerBorderPane.setTop(bottomBox);
    innerBorderPane.setBottom(topBox);
    innerBorderPane.setMouseTransparent(true);
    innerBorderPane.setRotationAxis(new Point3D(1, 0, 0));
    innerBorderPane.setRotate(180);

    // Add topDecorationImageView and bottomDecorationImageView to the CustomTopBar
    getChildren().addAll(topBar, innerBorderPane);

    topBar.setOnMouseEntered(event -> setCursor(Cursor.HAND));

    // Event handler for mouse pressed event
    topBar.setOnMousePressed(event -> {
      // Store the initial mouse cursor position
      xoffset = event.getScreenX();
      yoffset = event.getScreenY();
    });

    // Event handler for mouse dragged event
    topBar.setOnMouseDragged(event -> {
      // Calculate the new position of the stage based on the mouse movement
      double deltaX = event.getScreenX() - xoffset;
      double deltaY = event.getScreenY() - yoffset;
      stage.setX(stage.getX() + deltaX);
      stage.setY(stage.getY() + deltaY);

      // Update the mouse cursor position
      xoffset = event.getScreenX();
      yoffset = event.getScreenY();
    });
  }

  protected static void updateTopBarTitle(String resourceKey) {
    title.textProperty().bind(LanguageManager.getStringProperty(resourceKey));
  }

  public static void setAlertIconView(Image image) {
    alertIconView.setImage(image);
  }
}
