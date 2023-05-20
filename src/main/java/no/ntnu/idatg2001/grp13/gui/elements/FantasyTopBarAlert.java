package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
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
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenu;

public class FantasyTopBarAlert extends StackPane {
  private static final ImageView alertIconView = new ImageView();
  private double xoffset = 0;
  private double yoffset = 0;

  public FantasyTopBarAlert(Stage stage) {
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

    topBar.setMaxHeight(30);
    topBar.setPadding(new Insets(5, 0, 5, 10));
    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.setSpacing(2.5);

    alertIconView.setFitHeight(15);
    alertIconView.setPreserveRatio(true);
    topBar.getChildren().add(alertIconView);

    Image iconImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream("/Image/Window/Alert/Alert.png")));
    // Use the icon in the desired way
    ImageView iconImageView = new ImageView(iconImage);
    iconImageView.setFitHeight(15);
    iconImageView.setPreserveRatio(true);
    topBar.getChildren().add(iconImageView);

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
      AudioClip buttonClick = new AudioClip(
          Objects.requireNonNull(MainMenu.class.getResource("/Audio/mouseclick_softer.wav"))
              .toString());
      buttonClick.play();
      FantasyAlert.setResult(ButtonType.CANCEL);
      stage.close();
    });

    HBox exit = new HBox();
    exit.setPrefWidth(300);
    exit.setPadding(new Insets(0, 10, 0, 0));
    exit.setAlignment(Pos.CENTER_RIGHT);

    exit.getChildren().add(exitIconView);
    topBar.getChildren().add(exit);

    BorderPane innerBorderPane = new BorderPane();
    innerBorderPane.setTranslateY(-2.0);

    HBox topBox = new HBox();
    topBox.setPrefSize(300, 10.0);
    BorderPane.setAlignment(topBox, Pos.CENTER);

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
    topBarDecorationTop.setTranslateY(1.0);
    topBarDecorationTop.setPickOnBounds(true);
    topBarDecorationTop.setPreserveRatio(true);

    Image topDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Top.png")));
    topBarDecorationTop.setImage(topDecorationImage);

    ImageView topBarBorder2 = new ImageView();
    topBarBorder2.setFitHeight(20.0);
    topBarBorder2.setFitWidth(100);
    topBarBorder2.setPickOnBounds(true);
    topBarBorder2.setRotate(180.0);
    topBarBorder2.setImage(borderDecorationImage);
    topBarBorder2.setRotationAxis(new Point3D(0, 1, 0));

    topBox.getChildren().addAll(topBarBorder1, topBarDecorationTop, topBarBorder2);

    HBox bottomBox = new HBox();
    bottomBox.setPrefSize(300, 16.0);
    BorderPane.setAlignment(bottomBox, Pos.CENTER);

    ImageView topBarBorder3 = new ImageView();
    topBarBorder3.setFitHeight(20.0);
    topBarBorder3.setFitWidth(100);
    topBarBorder3.setPickOnBounds(true);
    topBarBorder3.setRotate(180.0);
    topBarBorder3.setTranslateY(5.0);
    topBarBorder3.setImage(borderDecorationImage);
    topBarBorder3.setRotationAxis(new Point3D(1, 0, 0));

    ImageView topBarDecorationBottom = new ImageView();
    topBarDecorationBottom.setFitHeight(35.0);
    topBarDecorationBottom.setFitWidth(100);
    topBarDecorationBottom.setTranslateY(6.0);
    topBarDecorationBottom.setPickOnBounds(true);
    topBarDecorationBottom.setPreserveRatio(true);

    Image bottomDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBarAlert.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Bottom.png")));
    topBarDecorationBottom.setImage(bottomDecorationImage);

    ImageView topBarBorder4 = new ImageView();
    topBarBorder4.setFitHeight(20.0);
    topBarBorder4.setFitWidth(100);
    topBarBorder4.setPickOnBounds(true);
    topBarBorder4.setRotate(180.0);
    topBarBorder4.setTranslateY(5.0);
    topBarBorder4.setImage(borderDecorationImage);

    bottomBox.getChildren().addAll(topBarBorder3, topBarDecorationBottom, topBarBorder4);

    innerBorderPane.setTop(topBox);
    innerBorderPane.setBottom(bottomBox);
    innerBorderPane.setMouseTransparent(true);
    innerBorderPane.setRotationAxis(new Point3D(1, 0, 0));
    innerBorderPane.setRotate(180);

    // Add topDecorationImageView and bottomDecorationImageView to the CustomTopBar
    getChildren().addAll(topBar, innerBorderPane);

    Rectangle windowShape = new Rectangle(300, 300);

    Rectangle barClip = new Rectangle(32, 32);
    barClip.setX(134);
    barClip.setY(18);
    barClip.setRotate(45);
    barClip.setArcWidth(10);
    barClip.setArcHeight(10);
    Shape invertedClip = Shape.subtract(windowShape, barClip);
    topBar.setClip(invertedClip);

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

  public static void setAlertIconView(Image image) {
    alertIconView.setImage(image);
  }
}
