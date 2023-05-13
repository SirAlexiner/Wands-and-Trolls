package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.application.Application;
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
import no.ntnu.idatg2001.gr13.controller.MainMenuController;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;

import static atlantafx.base.theme.Styles.*;


public class MainMenuView extends Application{
    private MainMenuController mainMenuController;
    private SettingsView settingsView;
    private ResourceBundle bundle;
    private BorderPane root;
    private Stage stage;
    private static final String BACKGROUND_IMAGE = "WnT.png";


    @Override
    public void start(Stage stage) throws Exception {
        try {
            Locale locale = new Locale.Builder().setLanguage("no").build();
            bundle = ResourceBundle.getBundle("languages/buttons", locale);
            setUp();
        } catch (FileNotFoundException e) {
            // Handle the file not found exception
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void setUp() throws FileNotFoundException {
        mainMenuController = new MainMenuController();
        // Initializes the stage
        stage = new Stage();
        // Sets the root as a borderpane
        root = new BorderPane();
        // Set up the stage
        stage.setScene(setUpScene());
        stage.setTitle("WiNG");
        stage.setResizable(false);
        stage.setFullScreen(true);
        FxManager.setup(stage);
        // Add buttons to the center of the root pane
        root.setCenter(layoutButtons());
        buttonDarkMode(root);
        stage.show();
    }

    /**
     * A method for setting up a scene with a background.
     * @return A scene correctly set up.
     * @throws FileNotFoundException if it cant find the background.
     */
    public Scene setUpScene() throws FileNotFoundException {
        Image image = getBackgroundImage();
        // Create an ImageView and set its size to the screen size
        ImageView imageView = new ImageView(image);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        imageView.setFitWidth(screenBounds.getWidth());
        imageView.setFitHeight(screenBounds.getHeight());
        root.setBackground(setUpBackground(image));
        // Set the scene to fullscreen
        return new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
    }

    /**
     * A method for setting up a background.
     * @param image to be used as a background.
     * @return the background.
     */
    public static Background setUpBackground(Image image)  {
        BackgroundImage backgroundImage =
                new BackgroundImage(image, null,
                        null, null,
                        new BackgroundSize(1.0, 1.0, true,
                                true, false, false));
        return new Background(backgroundImage);
    }

    /**
     * A method for getting the background image.
     * @return The image object containing the background.
     * @throws FileNotFoundException If it cannot find the image.
     */
    public Image getBackgroundImage() throws FileNotFoundException {
        FileInputStream input = new FileInputStream(BACKGROUND_IMAGE);
        return new Image(input);
    }

    /**
     * A method for setting up the buttons in a vertical box grid.
     * @return a vertical box containing all the buttons.
     */
    public VBox setUpButtons() {
        // Create button
        Button settingsButton = buttonCreator(bundle.getString("settingsButton"), Feather.SETTINGS, ACCENT);
        HBox topBox = topBoxButtons();
        // Create a vertical box for the new game and load game buttons and the settings button
        VBox vbox = new VBox(topBox, new Region(), settingsButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        // Set the vertical grow priority of the spacer region to ALWAYS
        VBox.setVgrow(new Region(), Priority.ALWAYS);
        return vbox;
    }

    /**
     * A method for initializing buttons and style them appropriately. The method uses AtlantaFx
     * for styling, and it creates large, rounded, outlined buttons and buttons gets a state.
     * @param nameOfButton The name of the button to be displayed.
     * @param buttonIcon The icon of the button to be displayed.
     * @param buttonState The state of the button to be displayed.
     * @return A button object.
     */
    public static Button buttonCreator(String nameOfButton, Feather buttonIcon, String buttonState) {
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
        Button loadGameButton = buttonCreator(bundle.getString("loadGameButton"), Feather.FOLDER, SUCCESS);
        // Create a horizontal box for the new game and load game buttons
        HBox topHBox = new HBox(newGameButton, loadGameButton);
        topHBox.setSpacing(20);
        topHBox.setAlignment(Pos.CENTER);

        return topHBox;
    }

    public GridPane layoutButtons() {
        // Vertical box containing three buttons
        VBox vBox = setUpButtons();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(70));
        gridPane.getChildren().addAll(vBox);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setGridLinesVisible(false);
        return gridPane;
    }

    private void buttonDarkMode(BorderPane root) {
        DarkModeButton.setLayoutForToggleButton(root);
        ToggleButton buttonEnableDarkMode = DarkModeButton.getToggleButton();
        buttonEnableDarkMode.setOnAction(event ->
                mainMenuController.darkModeButtonOnPressed(stage, buttonEnableDarkMode));
    }

    public static void main(String[] args) {
        launch();
    }
}
