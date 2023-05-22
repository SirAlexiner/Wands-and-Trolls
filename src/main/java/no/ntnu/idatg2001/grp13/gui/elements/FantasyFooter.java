package no.ntnu.idatg2001.grp13.gui.elements;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FantasyFooter extends StackPane {

  public FantasyFooter() {
    HBox footer = new HBox();
    // Load and set the side gradients and middle gradients as the background
    Image backgroundImage = new Image(Objects.requireNonNull(
        FantasyFooter.class.getResourceAsStream("/Image/FantasyBar/Fantasy_Bar_Background.png")));

    Image sideGradientImage = new Image(Objects.requireNonNull(
        FantasyFooter.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Gradient_Side.png")));

    Image middleGradientImage = new Image(Objects.requireNonNull(
        FantasyFooter.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Gradient_Middle.png")));

    footer.setBackground(new Background(
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

    footer.setMaxHeight(30);
    footer.setPadding(new Insets(5, 10, 5, 10));
    footer.setAlignment(Pos.CENTER_LEFT);

    // Load and position the top and bottom decoration images

    BorderPane innerBorderPane = new BorderPane();
    innerBorderPane.setTranslateY(2.0);

    HBox topBox = new HBox();
    topBox.setPrefSize(1024.0, 10.0);
    topBox.setTranslateY(10.0);
    BorderPane.setAlignment(topBox, Pos.CENTER);

    ImageView topBarBorder1 = new ImageView();
    topBarBorder1.setFitHeight(20.0);
    topBarBorder1.setFitWidth(440.0);
    topBarBorder1.setPickOnBounds(true);

    Image borderDecorationImage = new Image(Objects.requireNonNull(
        FantasyFooter.class.getResourceAsStream("/Image/FantasyBar/Fantasy_Bar_Border.png")));
    topBarBorder1.setImage(borderDecorationImage);

    ImageView topBarDecorationTop = new ImageView();
    topBarDecorationTop.setFitHeight(35.0);
    topBarDecorationTop.setFitWidth(159.0);
    topBarDecorationTop.setTranslateY(-5.0);
    topBarDecorationTop.setPickOnBounds(true);
    topBarDecorationTop.setPreserveRatio(true);

    Image topDecorationImage = new Image(Objects.requireNonNull(
        FantasyFooter.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Bottom.png")));
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
    BorderPane.setAlignment(bottomBox, Pos.CENTER);

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
        FantasyFooter.class.getResourceAsStream(
            "/Image/FantasyBar/Fantasy_Bar_Decoration_Top.png")));
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

    // Add topDecorationImageView and bottomDecorationImageView to the CustomTopBar
    getChildren().addAll(footer, innerBorderPane);
    setTranslateY(-15);

    Rectangle windowShape = new Rectangle(1024, 768);

    Rectangle barClip = new Rectangle(32, 32);
    barClip.setFill(Color.GREEN);
    barClip.setX(494);
    barClip.setY(-15);
    barClip.setRotate(45);
    barClip.setArcWidth(10);
    barClip.setArcHeight(10);
    Shape invertedClip = Shape.subtract(windowShape, barClip);
    footer.setClip(invertedClip);

    Rectangle clip1 = new Rectangle(360, 50);
    clip1.setArcWidth(40);
    clip1.setArcHeight(40);

    // Create the second clip path
    Rectangle clip2 = new Rectangle(360, 768);
    clip2.setX(340);
    clip2.setY(-50);

    // Create the second clip path
    Rectangle clip3 = new Rectangle(360, 50);
    clip3.setX(664);
    clip3.setArcWidth(40);
    clip3.setArcHeight(40);

    HBox clip = new HBox(clip1, clip2, clip3);

    setClip(clip);
  }
}
