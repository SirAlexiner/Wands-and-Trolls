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
import no.ntnu.idatg2001.gr13.controller.LanguageController;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;


import static atlantafx.base.theme.Styles.*;
import static no.ntnu.idatg2001.gr13.view.LanguageKeys.*;

public class SettingsDialog extends Dialog<Void> implements LanguageListener {
    private final BorderPane root;
    private Button cancelButton;
    private Button confirmButton;
    private final SettingsDialogController controller;
    private final LanguageController languageController = LanguageController.getInstance();
    private String englishSelectorText = languageController.getTextBundleString(KEY_ENGLISH_TEXT.getKeyName());
    private String norwegianSelectorText = languageController.getTextBundleString(KEY_NORWEGIAN_TEXT.getKeyName());
    private final String germanSelectorText = languageController.getTextBundleString(KEY_GERMAN_TEXT.getKeyName());

    public SettingsDialog(SettingsDialogController controller, Stage primaryStage,
                          BorderPane root, LanguageController languageController) {
        confirmButton = new Button();
        cancelButton = new Button();
        languageController.addLanguageChangeListener(this);
        this.root = root;
        this.controller = controller;

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
        confirmButton = buttonCreator(languageController.getButtonBundleString(KEY_CONFIRM_BUTTON.getKeyName()), Feather.CHECK, SUCCESS);
        confirmButton.setOnAction(event -> {
            languageController.notifyLanguageChange();
            controller.onExitApplication(event);
            root.setEffect(null);
        });
    }

    private void createCancelButton() {
        cancelButton = buttonCreator(languageController.getButtonBundleString(KEY_CANCEL_BUTTON.getKeyName()), Feather.X, DANGER);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> {
            languageController.notifyLanguageChange();
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
        ChoiceBox<String> languages = new ChoiceBox<>();

        languages.getItems().addAll(norwegianSelectorText, englishSelectorText, germanSelectorText);
        languages.getItems().setAll(norwegianSelectorText, englishSelectorText, germanSelectorText);
        languages.getSelectionModel().selectedIndexProperty().addListener((
                (observableValue, oldValue, newValue) -> {
                    int selectedIndex = newValue.intValue();
                    String languageCode = switch (selectedIndex) {
                        case 0 -> "no";
                        case 2 -> "de";
                        default -> "en";
                    };

                    languageController.setLanguage(languageCode);
                }));
        return languages;
    }
    @Override
    public void updateLocalizedStrings() {
        String confirmButtonText = languageController.getButtonBundleString(KEY_CONFIRM_BUTTON.getKeyName());
        confirmButton.setText(confirmButtonText);

        String cancelButtonText = languageController.getButtonBundleString(KEY_CANCEL_BUTTON.getKeyName());
        cancelButton.setText(cancelButtonText);

        norwegianSelectorText = languageController.getTextBundleString(KEY_NORWEGIAN_TEXT.getKeyName());
        englishSelectorText = languageController.getTextBundleString(KEY_ENGLISH_TEXT.getKeyName());
    }
    @Override
    public void languageChange() {
        updateLocalizedStrings();
    }

}