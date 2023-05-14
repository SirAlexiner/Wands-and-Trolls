package no.ntnu.idatg2001.gr13.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import no.ntnu.idatg2001.gr13.controller.SettingsDialogController;
import no.ntnu.idatg2001.gr13.model.LanguageModel;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;


import static atlantafx.base.theme.Styles.*;

public class SettingsDialog extends Dialog<Void> {
    private Button cancelButton;
    private Button confirmButton;
    private final SettingsDialogController controller;
    private final BorderPane root;
    private final LanguageModel languageModel;
    public SettingsDialog(SettingsDialogController controller, Stage primaryStage,
                          BorderPane root, LanguageModel languageModel) {
        this.root = root;
        this.controller = controller;
        this.languageModel = languageModel;

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
        ChoiceBox<String> languages = languagePicker();
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(10);
        VBox vbox = new VBox(buttonBox, languages);
        vbox.setSpacing(10);
        vbox.setFillWidth(true);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(vbox);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(10, 10, 10, 10));
        getDialogPane().setContent(stackPane);
        getDialogPane().setMinHeight(120);
    }

    private void setBlur() {
        GaussianBlur blurEffect = new GaussianBlur();
        double blurRadius = 15;
        blurEffect.setRadius(blurRadius);
        root.setEffect(blurEffect);
    }


    private void createConfirmButton() {
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

    private ChoiceBox<String> languagePicker() {
        String norwegian = "Norwegian";
        String english = "English";
        ChoiceBox<String> languages = new ChoiceBox<>();
        languages.getItems().addAll(norwegian, english);

        languages.getItems().setAll(norwegian, english);
        languages.getSelectionModel().selectedIndexProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    int selectedIndex = newValue.intValue();
                    String languageCode = (selectedIndex == 0) ? "no" : "en";
                    languageModel.setLanguage(languageCode);
                }));

        return languages;
    }

}