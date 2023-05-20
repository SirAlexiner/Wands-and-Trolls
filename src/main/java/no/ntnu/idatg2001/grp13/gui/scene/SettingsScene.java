package no.ntnu.idatg2001.grp13.gui.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.stage.MainStage;

import java.util.Objects;
@UtilityClass
public class SettingsScene {
    public static Scene getSettingScene(Stage stage){
        BorderPane root = new BorderPane();
        root.getStylesheets().add(String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

        Image background = new Image(Objects.requireNonNull(
                SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitWidth(1024);
        backgroundView.setFitHeight(768);

        root.getChildren().add(0, backgroundView);

        // sets label text for settings
        root.setBottom(setButtons(stage));
        root.setCenter(setLabel("Settings:", Pos.CENTER));
        //

        root.setPadding(new Insets(5));

        return new Scene(root);
    }

    public Label setLabel(String labelName, Pos position) {
        Label settingsLabel;
        settingsLabel = new Label(labelName.toUpperCase());
        settingsLabel.setAlignment(position);
        return settingsLabel;
    }

    public HBox setButtons(Stage stage) {
        FantasyButton saveButton = new FantasyButton("Save Settings");
        saveButton.setFantasyButtonType(FantasyButtonType.BONE);
        saveButton.setPrefWidth(150);

        FantasyButton cancelButton = new FantasyButton("Back to Game");
        cancelButton.setFantasyButtonType(FantasyButtonType.BONE);
        cancelButton.setPrefWidth(150);
        cancelButton.setOnMouseClicked(event -> {
                    AudioClip buttonClick = new AudioClip(
                            Objects.requireNonNull(MainStage.class.getResource("/Audio/mouseclick_softer.wav"))
                                    .toString());
            buttonClick.play();
            FantasyAlert quitAlert = new FantasyAlert(stage);
            quitAlert.setAlertType(Alert.AlertType.CONFIRMATION);
            quitAlert.setHeader("Are you sure you want to go back?");

            quitAlert.showAndWait();

            if (FantasyAlert.getResult().equals(ButtonType.OK)) {
                MainMenuScene.getContentContainer().getChildren().remove(1);
            }
        });
        HBox bottomButtons = new HBox(saveButton, cancelButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(15);
        bottomButtons.setPadding(new Insets(10));
        return bottomButtons;
    }
}