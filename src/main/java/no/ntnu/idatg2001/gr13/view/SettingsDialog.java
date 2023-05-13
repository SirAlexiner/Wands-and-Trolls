package no.ntnu.idatg2001.gr13.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import no.ntnu.idatg2001.gr13.Main;
import no.ntnu.idatg2001.gr13.controller.SettingsDialogController;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Locale;
import java.util.ResourceBundle;

import static atlantafx.base.theme.Styles.*;

public class SettingsDialog extends Dialog {
    //TODO Settings dialog contains singletonbuttons, for an example darkmode
    private Dialog<ToggleButton> dialog;
    private Button cancelButton;
    private Button confirmButton;
    private StackPane stackPane;
    private SettingsDialogController controller;
    private BorderPane root;
    private Scene scene;

    public SettingsDialog(SettingsDialogController controller, Stage primaryStage, BorderPane root, Scene scene) {
        this.root = root;
        this.controller = controller;
        this.scene = scene;

        setBlur();
        initOwner(primaryStage); // Set the primary stage as the owner
        initStyle(StageStyle.TRANSPARENT);
        getDialogPane().getScene().setFill(Color.TRANSPARENT);
        createCancelButton();
        createConfirmButton();
        createLayout();
    }

    private void createLayout() {
        HBox buttonBox = new HBox(cancelButton, confirmButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(10);
        VBox vbox = new VBox(buttonBox);
        vbox.setSpacing(10);
        vbox.setFillWidth(true);

        stackPane = new StackPane();
        stackPane.getChildren().add(vbox);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(10,10,10,10));
        getDialogPane().setContent(stackPane);
        getDialogPane().setMinHeight(120);
    }

    private void setBlur() {
        BoxBlur blurEffect = new BoxBlur(20, 20, 3);
        root.setEffect(blurEffect);
    }


    private void createConfirmButton(){
        confirmButton = buttonCreator("Confirm", Feather.CHECK, SUCCESS);
        confirmButton.setOnAction(event -> {
            controller.onExitApplication(event);
            root.setEffect(null);
        });
    }

    private void createCancelButton() {
        cancelButton = buttonCreator("Cancel", Feather.X, DANGER);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> {
            controller.onExitApplication(event);
            root.setEffect(null);
        });
    }

    public static Button buttonCreator(String nameOfButton, Feather buttonIcon, String buttonState) {
        // Creates Buttons and styles them.
        Button button = new Button(nameOfButton, new FontIcon(buttonIcon));
        button.getStyleClass().addAll(LARGE, ROUNDED, BUTTON_OUTLINED, buttonState);

        return button;
    }

}