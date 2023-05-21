package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyCheckbox;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.MusicPlayer;
import no.ntnu.idatg2001.grp13.stage.MainStage;

@UtilityClass
public class SettingsScene {

  private Slider masterVolumeSlider;
  private Slider musicVolumeSlider;

  public static Scene getSettingScene(Stage stage) {
    BorderPane root = new BorderPane();
    root.getStylesheets().add(
        String.valueOf(SettingsScene.class.getResource("/CSS/WindowUi/FantasyStyle_Settings.css")));

    Image background = new Image(Objects.requireNonNull(
        SettingsScene.class.getResourceAsStream("/Image/Window/Background_Purple.png")));
    ImageView backgroundView = new ImageView(background);
    backgroundView.setFitWidth(1024);
    backgroundView.setFitHeight(768);

    root.getChildren().add(0, backgroundView);

    // sets label text for settings
    root.setBottom(setButtons(stage));

    VBox settingsBox = new VBox(setTitleLabel("Settings:"));
    settingsBox.setAlignment(Pos.CENTER);
    settingsBox.setSpacing(10);

    VBox vbox = new VBox();
    vbox.setSpacing(10);

    // Create master volume slider
    Label masterVolumeLabel = new Label("Master Volume");
    masterVolumeSlider = createSliderWithPercentageLabel();
    vbox.getChildren().addAll(masterVolumeLabel, masterVolumeSlider);

    // Create HBox for music volume and sound effect volume sliders
    HBox hbox = new HBox();
    hbox.setSpacing(10);

    // Create music volume slider
    Label musicVolumeLabel = new Label("Music Volume");
    musicVolumeSlider = createSliderWithPercentageLabel();
    hbox.getChildren().addAll(musicVolumeLabel, musicVolumeSlider);

    // Create sound effect volume slider
    Label soundEffectVolumeLabel = new Label("Sound Effect Volume");
    Slider soundEffectVolumeSlider = createSliderWithPercentageLabel();
    hbox.getChildren().addAll(soundEffectVolumeLabel, soundEffectVolumeSlider);

    masterVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateVolume());

    musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateVolume());

    // Add HBox to VBox
    vbox.getChildren().add(hbox);

    VBox mainMusicbox = new VBox();
    mainMusicbox.setAlignment(Pos.CENTER);

    CheckBox muteMusic = new FantasyCheckbox();
    muteMusic.setOnMouseClicked(event -> {
      if (muteMusic.isSelected()) {
        MusicPlayer.mainMenuMusicMute();
      } else {
        updateVolume();
      }
    });
    HBox muteMusicBox = new HBox(muteMusic);
    muteMusicBox.setAlignment(Pos.CENTER);
    muteMusicBox.setSpacing(5);
    Label muteMusicLabel = new Label("Mute Game Music  ");
    muteMusicBox.getChildren().add(muteMusicLabel);

    FantasyCheckbox muteSfx = new FantasyCheckbox();
    HBox muteSfxBox = new HBox(muteSfx);
    muteSfxBox.setAlignment(Pos.CENTER);
    muteSfxBox.setSpacing(5);
    Label muteSfxLabel = new Label("Mute Sound Effects");
    muteSfxBox.getChildren().add(muteSfxLabel);

    mainMusicbox.getChildren().addAll(vbox, muteMusicBox, muteSfxBox);

    settingsBox.getChildren().addAll(mainMusicbox);

    root.setCenter(settingsBox);
    //

    root.setPadding(new Insets(5));

    return new Scene(root);
  }

  private void updateVolume() {
    DoubleProperty masterVolume = masterVolumeSlider.valueProperty();
    DoubleProperty musicVolume = musicVolumeSlider.valueProperty();
    MusicPlayer.mainMenuMusicSetVolume(masterVolume, musicVolume);
  }

  private Slider createSliderWithPercentageLabel() {
    Slider slider = new Slider(0, 1, 1);
    slider.setBlockIncrement(0.01);
    Label valueLabel = new Label();

    // Bind the label's text property to the slider's value property, converting it to a percentage
    valueLabel.textProperty().bind(slider.valueProperty().multiply(100).asString("%.0f%%"));

    return slider;
  }

  public Label setTitleLabel(String labelName) {
    Label settingsLabel;
    settingsLabel = new Label(labelName.toUpperCase());
    settingsLabel.setStyle("-fx-font-size: 20px;");
    return settingsLabel;
  }

  public Label setLabel(String labelName) {
    Label settingsLabel;
    settingsLabel = new Label(labelName.toUpperCase());
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