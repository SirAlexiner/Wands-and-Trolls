package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static atlantafx.base.theme.Styles.*;


public class MainMenuView extends Application {
    private BorderPane root;
    private ToggleButton buttonEnableDarkMode;
    private Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        setUp();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void setUp() throws FileNotFoundException {
        stage = new Stage();
        root = new BorderPane();

        // Load the background image with an initial size of 800x400
        Background background = loadImageScene(800, 400);

        // Set the background of the root pane
        root.setBackground(background);

        // Add a listener to the scene to handle resizing events
        Scene scene = new Scene(root, 800, 400);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            double diagonalLength = calculateDiagonalLength(scene.getWidth(), scene.getHeight());
            root.setBackground(resizeBackgroundImage(background, diagonalLength));
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double diagonalLength = calculateDiagonalLength(scene.getWidth(), scene.getHeight());
            root.setBackground(resizeBackgroundImage(background, diagonalLength));
        });

        // Add buttons to the center of the root pane
        root.setCenter(layoutButtons());

        // Set up the stage
        stage.setScene(scene);
        stage.setTitle("WiNG");
        FxManager.setup(stage);
        buttonDarkMode();
        stage.show();
    }

    private Background resizeBackgroundImage(Background background, double diagonalLength) {
        BackgroundImage backgroundImage = background.getImages().get(0);
        Image image = backgroundImage.getImage();
        double ratio = diagonalLength / Math.sqrt(Math.pow(image.getWidth(), 2) + Math.pow(image.getHeight(), 2));
        double width = image.getWidth() * ratio;
        double height = image.getHeight() * ratio;
        BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        BackgroundImage resizedBackgroundImage = new BackgroundImage(backgroundImage.getImage(),
                backgroundImage.getRepeatX(),
                backgroundImage.getRepeatY(),
                backgroundImage.getPosition(),
                backgroundSize);
        return new Background(resizedBackgroundImage);
    }
    private double calculateDiagonalLength(double width, double height) {
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    public Background loadImageScene(double containerWidth, double containerHeight) throws FileNotFoundException {
        FileInputStream input = new FileInputStream("WnT.png");
        Image image = new Image(input);

        // Create an ImageView and set its size to the container size
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(containerWidth);
        imageView.setFitHeight(containerHeight);

        // Create a region and set its size to the container size
        Region region = new Region();
        region.setPrefSize(containerWidth, containerHeight);

        // Set the background image of the region to the ImageView
        region.setBackground(new Background(new BackgroundImage(imageView.getImage(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(containerWidth, containerHeight, false, false, false, false))));

        // Return the background
        return region.getBackground();
    }


    public VBox setUpButtons() {
        // Creates buttons
        Button settingsButton = new Button("Settings", new FontIcon(Feather.SETTINGS));
        settingsButton.getStyleClass().addAll(LARGE, ROUNDED, BUTTON_OUTLINED, ACCENT);
        // Create a vertical box for the new game and load game buttons and the settings button
        HBox topBox = topBoxButtons();

        VBox vbox = new VBox(topBox, new Region(), settingsButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        // Set the vertical grow priority of the spacer region to ALWAYS
        VBox.setVgrow(new Region(), Priority.ALWAYS);

        return vbox;
    }

    /**
     *
     * @return
     */
    public HBox topBoxButtons() {
        // Creates Buttons and styles them.
        Button newGameButton = new Button("New Game", new FontIcon(Feather.PLAY));
        newGameButton.getStyleClass().addAll(LARGE, ROUNDED, BUTTON_OUTLINED, SUCCESS);

        Button loadGameButton = new Button("Load Game", new FontIcon(Feather.FOLDER));
        loadGameButton.getStyleClass().addAll(LARGE, ROUNDED, BUTTON_OUTLINED, SUCCESS);

        // Create a horizontal box for the new game and load game buttons
        HBox topHBox = new HBox(newGameButton, loadGameButton);
        topHBox.setSpacing(20);
        topHBox.setAlignment(Pos.CENTER);

        return topHBox;
    }

    public GridPane layoutButtons() {
        setUpButtons();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(70));
        gridPane.getChildren().addAll(setUpButtons());
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setGridLinesVisible(false);
        return gridPane;
    }

    private void buttonDarkMode() {
        // dark mode / light mode / following OS-theme
        buttonEnableDarkMode = new ToggleButton("", new FontIcon(Feather.SUN));
        GridPane darkModeGrid = new GridPane();

        // Setting buttonPlacement for darkMode
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(darkModeGrid);
        darkModeGrid.setAlignment(Pos.TOP_RIGHT);
        darkModeGrid.setVgap(10);
        darkModeGrid.setHgap(10);
        darkModeGrid.setPadding(new Insets(10));

        darkModeGrid.getChildren().addAll(buttonEnableDarkMode);
        root.setTop(darkModeGrid);

        buttonEnableDarkMode.setOnAction(this::darkModeButtonOnPressed);
    }

    public void darkModeButtonOnPressed(ActionEvent event){
        FxManager.enableDarkMode(stage);
        if (buttonEnableDarkMode.isSelected()){
            FxManager.enableLightMode(stage);
        }
        else {
            FxManager.enableDarkMode(stage);
        }
    }

}
