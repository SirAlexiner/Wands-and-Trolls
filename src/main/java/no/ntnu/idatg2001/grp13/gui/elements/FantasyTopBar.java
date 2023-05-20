package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenu;

public class FantasyTopBar extends StackPane {

  private double xoffset = 0;
  private double yoffset = 0;

  public FantasyTopBar(Stage stage) {
    HBox topBar = new HBox();
    // Load and set the side gradients and middle gradients as the background
    Image backgroundImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream("/Image/FantasyBar/Fantasy_Bar_Background.png")));

    Image sideGradientImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Gradient_Side.png")));

    Image middleGradientImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream(
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
    topBar.setPadding(new Insets(5, 0, 5, 15));
    topBar.setAlignment(Pos.CENTER_LEFT);

    Image iconImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream("/Image/FantasyBar/Logo_simple.png")));
    // Use the icon in the desired way
    ImageView iconImageView = new ImageView(iconImage);
    iconImageView.setFitHeight(15);
    iconImageView.setPreserveRatio(true);
    topBar.getChildren().add(iconImageView);

    Image exitIcon = new Image(Objects.requireNonNull(
            FantasyTopBar.class.getResource("/Image/FantasyExitButton/Window_Exit_Button_Icon.png"))
        .toString());
    ImageView exitIconView = new ImageView(exitIcon);
    exitIconView.setFitWidth(15);
    exitIconView.setPreserveRatio(true);

    exitIconView.setOnMouseEntered(event -> {
      // Create a ColorAdjust effect
      ColorAdjust colorAdjust = new ColorAdjust();
      colorAdjust.setHue(-0.3); // Adjust the hue value to change the color
      colorAdjust.setSaturation(0.75); // Set the saturation to 1.0 for full color saturation

      // Apply the ColorAdjust effect to the ImageView
      exitIconView.setEffect(colorAdjust);
    });

    exitIconView.setOnMouseExited(event -> exitIconView.setEffect(null));

    exitIconView.setOnMouseClicked(event -> {
      AudioClip buttonClick = new AudioClip(
          Objects.requireNonNull(MainMenu.class.getResource("/Audio/mouseclick_softer.wav"))
              .toString());
      buttonClick.play();
      FantasyAlert quitAlert = new FantasyAlert(stage);
      quitAlert.setAlertType(Alert.AlertType.WARNING);
      quitAlert.setHeader("Are you certain you want to Exit?");

      quitAlert.showAndWait();

      if (FantasyAlert.getResult().equals(ButtonType.YES)) {
        System.exit(0);
      }
    });

    HBox exit = new HBox();
    exit.setPrefWidth(1024);
    exit.setPadding(new Insets(0, 10, 0, 0));
    exit.setAlignment(Pos.CENTER_RIGHT);

    exit.getChildren().add(exitIconView);
    topBar.getChildren().add(exit);

    BorderPane innerBorderPane = new BorderPane();
    innerBorderPane.setTranslateY(-2.0);

    HBox topBox = new HBox();
    topBox.setPrefSize(1024.0, 10.0);
    topBox.setTranslateY(10.0);
    BorderPane.setAlignment(topBox, javafx.geometry.Pos.CENTER);

    ImageView topBarBorder1 = new ImageView();
    topBarBorder1.setFitHeight(20.0);
    topBarBorder1.setFitWidth(440.0);
    topBarBorder1.setPickOnBounds(true);

    Image borderDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream("/Image/FantasyBar/Fantasy_Bar_Border.png")));
    topBarBorder1.setImage(borderDecorationImage);

    ImageView topBarDecorationTop = new ImageView();
    topBarDecorationTop.setFitHeight(35.0);
    topBarDecorationTop.setFitWidth(159.0);
    topBarDecorationTop.setTranslateY(-5.0);
    topBarDecorationTop.setPickOnBounds(true);
    topBarDecorationTop.setPreserveRatio(true);

    Image topDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Top.png")));
    topBarDecorationTop.setImage(topDecorationImage);

    ImageView topBarBorder2 = new ImageView();
    topBarBorder2.setFitHeight(20.0);
    topBarBorder2.setFitWidth(440.0);
    topBarBorder2.setPickOnBounds(true);
    topBarBorder2.setRotate(180.0);
    topBarBorder2.setImage(borderDecorationImage);
    topBarBorder2.setRotationAxis(new Point3D(0, 1, 0));

    topBox.getChildren().addAll(topBarBorder1, topBarDecorationTop, topBarBorder2);

    HBox bottomBox = new HBox();
    bottomBox.setPrefSize(1024.0, 16.0);
    bottomBox.setTranslateY(-5.0);
    BorderPane.setAlignment(bottomBox, javafx.geometry.Pos.CENTER);

    ImageView topBarBorder3 = new ImageView();
    topBarBorder3.setFitHeight(20.0);
    topBarBorder3.setFitWidth(440.0);
    topBarBorder3.setPickOnBounds(true);
    topBarBorder3.setRotate(180.0);
    topBarBorder3.setTranslateY(5.0);
    topBarBorder3.setImage(borderDecorationImage);
    topBarBorder3.setRotationAxis(new Point3D(1, 0, 0));

    ImageView topBarDecorationBottom = new ImageView();
    topBarDecorationBottom.setFitHeight(35.0);
    topBarDecorationBottom.setFitWidth(159.0);
    topBarDecorationBottom.setTranslateY(5.0);
    topBarDecorationBottom.setPickOnBounds(true);
    topBarDecorationBottom.setPreserveRatio(true);

    Image bottomDecorationImage = new Image(Objects.requireNonNull(
        FantasyTopBar.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Bottom.png")));
    topBarDecorationBottom.setImage(bottomDecorationImage);

    ImageView topBarBorder4 = new ImageView();
    topBarBorder4.setFitHeight(20.0);
    topBarBorder4.setFitWidth(440.0);
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
    setTranslateY(50);

    Rectangle windowShape = new Rectangle(1024, 768);

    Rectangle barClip = new Rectangle(32, 32);
    barClip.setFill(Color.GREEN);
    barClip.setX(494);
    barClip.setY(15);
    barClip.setRotate(45);
    barClip.setArcWidth(10);
    barClip.setArcHeight(10);
    Shape invertedClip = Shape.subtract(windowShape, barClip);
    topBar.setClip(invertedClip);

    Rectangle clip1 = new Rectangle(360, 50);
    clip1.setY(20);
    clip1.setArcWidth(40);
    clip1.setArcHeight(40);

    // Create the second clip path
    Rectangle clip2 = new Rectangle(360, 768);
    clip2.setX(340);
    clip2.setY(-50);

    // Create the second clip path
    Rectangle clip3 = new Rectangle(360, 50);
    clip3.setX(664);
    clip3.setY(20);
    clip3.setArcWidth(40);
    clip3.setArcHeight(40);

    HBox clip = new HBox(clip1, clip2, clip3);

    setClip(clip);

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
}
