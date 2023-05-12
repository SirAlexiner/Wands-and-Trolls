package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;

import static atlantafx.base.theme.Styles.*;


public class MainMenuView extends Application{
    private ResourceBundle bundle;
    private BorderPane root;
    private ToggleButton buttonEnableDarkMode;
    private Stage stage;
    private static final String BACKGROUND_IMAGE = "WnT.png";
    private Locale locale;


    @Override
    public void start(Stage stage) throws Exception {
        try {
            locale = new Locale("no", "NO");
            bundle = ResourceBundle.getBundle("languages/buttons", locale);
            setUp();
        } catch (FileNotFoundException e) {
            // Handle the file not found exception
            e.printStackTrace();
            // You may choose to show an error dialog or handle the exception in a different way

        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void setUp() throws FileNotFoundException {
        stage = new Stage();
        root = new BorderPane();
        // Set up the stage
        stage.setScene(setUpScene());
        stage.setTitle("WiNG");
        stage.setResizable(false);
        stage.setFullScreen(true);
        FxManager.setup(stage);
        // Add buttons to the center of the root pane
        root.setCenter(layoutButtons());
        buttonDarkMode();
        stage.show();
    }

    public Scene setUpScene() throws FileNotFoundException {
        // Creates an image from the file
        FileInputStream input = new FileInputStream(BACKGROUND_IMAGE);
        Image image = new Image(input);

        // Create an ImageView and set its size to the screen size
        ImageView imageView = new ImageView(image);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        imageView.setFitWidth(screenBounds.getWidth());
        imageView.setFitHeight(screenBounds.getHeight());

        BackgroundImage backgroundImage =
                new BackgroundImage(image, null,
                        null, null,
                        new BackgroundSize(1.0, 1.0, true,
                                true, false, false));
        root.setBackground(new Background(backgroundImage));
        // Set the scene to fullscreen
        return new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
    }

    public VBox setUpButtons() {
        // Create button
        Button settingsButton = buttonCreator("Settings", Feather.SETTINGS, ACCENT);
        HBox topBox = topBoxButtons();
        // Create a vertical box for the new game and load game buttons and the settings button
        VBox vbox = new VBox(topBox, new Region(), settingsButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        // Set the vertical grow priority of the spacer region to ALWAYS
        VBox.setVgrow(new Region(), Priority.ALWAYS);
        return vbox;
    }

    public Button buttonCreator(String nameOfButton, Feather buttonIcon, String buttonState) {
        // Creates Buttons and styles them.
        Button button = new Button(nameOfButton, new FontIcon(buttonIcon));
        button.getStyleClass().addAll(LARGE, ROUNDED, BUTTON_OUTLINED, buttonState);

        return button;
    }

    /**
     * A method for creating a "horizontal box" that contains two buttons.
     * @return A HBox containing Buttons.
     */
    public HBox topBoxButtons() {
        Button newGameButton = buttonCreator(bundle.getString("newGameButton"), Feather.PLAY, SUCCESS);
        Button loadGameButton = buttonCreator("Load Game", Feather.FOLDER, SUCCESS);
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

    public static void main(String[] args) {
        launch();
    }
}
