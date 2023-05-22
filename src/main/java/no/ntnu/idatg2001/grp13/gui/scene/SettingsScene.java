package no.ntnu.idatg2001.grp13.gui.scene;

import java.util.Objects;
import java.util.logging.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyAlert;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyButton;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyCheckbox;
import no.ntnu.idatg2001.grp13.gui.elements.LocalizedLabel;
import no.ntnu.idatg2001.grp13.gui.elements.util.FantasyButtonType;
import no.ntnu.idatg2001.grp13.gui.util.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.MusicPlayer;
import no.ntnu.idatg2001.grp13.gui.util.SoundEffectPlayer;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

@UtilityClass
public class SettingsScene {
  private boolean settingsSaved = false;
  private Slider masterVolumeSlider;
  private Slider musicVolumeSlider;
  private Slider soundEffectVolumeSlider;
  private CheckBox muteMusic;
  private CheckBox muteSoundEffect;
  private CheckBox enableDisableAi;
  @Getter
  public Settings settings = new Settings();
  private double masterVolume;
  private double musicVolume;
  private double soundEffectVolume;
  private boolean musicMuted;
  private boolean soundEffectMuted;

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

    VBox volumeSliderBox = new VBox();
    volumeSliderBox.setSpacing(20);

    masterVolumeSlider = createSliderWithPercentageLabel();

    LocalizedLabel masterVolumeLabel = new LocalizedLabel("setting.masterVolume");
    VBox masterVolumeSliderBox = new VBox(masterVolumeLabel);
    masterVolumeSliderBox.setSpacing(10);
    masterVolumeSliderBox.getChildren().add(masterVolumeSlider);

    volumeSliderBox.getChildren().addAll(masterVolumeSliderBox);

    HBox individualVolumeSlidersBox = new HBox();
    individualVolumeSlidersBox.setSpacing(10);
    individualVolumeSlidersBox.setSpacing(10);

    LocalizedLabel musicVolumeLabel = new LocalizedLabel("setting.musicVolume");
    musicVolumeSlider = createSliderWithPercentageLabel();
    VBox musicVolumeBox = new VBox(musicVolumeLabel, musicVolumeSlider);
    musicVolumeBox.setPrefWidth(350);
    musicVolumeBox.setSpacing(10);
    individualVolumeSlidersBox.getChildren().add(musicVolumeBox);

    LocalizedLabel soundEffectVolumeLabel = new LocalizedLabel("setting.soundEffectVolume");
    soundEffectVolumeSlider = createSliderWithPercentageLabel();
    VBox soundEffectVolumeBox = new VBox(soundEffectVolumeLabel, soundEffectVolumeSlider);
    soundEffectVolumeBox.setPrefWidth(350);
    soundEffectVolumeBox.setSpacing(10);
    individualVolumeSlidersBox.getChildren().add(soundEffectVolumeBox);

    masterVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      settingsSaved = false;
      updateVolume();
    });

    musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      settingsSaved = false;
      updateVolume();
    });

    soundEffectVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
      settingsSaved = false;
      updateVolume();
    });

    volumeSliderBox.getChildren().add(individualVolumeSlidersBox);

    VBox soundSettingsbox = new VBox(setTitleLabel("setting.musicTitle"));
    soundSettingsbox.setPadding(new Insets(0, 0, 20, 0));
    soundSettingsbox.setSpacing(10);

    muteMusic = new FantasyCheckbox();
    muteMusic.setOnMouseClicked(event -> {
      settingsSaved = false;
      if (muteMusic.isSelected()) {
        MusicPlayer.muteMusic();
      } else {
        MusicPlayer.unmuteMusic();
      }
    });
    HBox muteMusicBox = new HBox(muteMusic);
    muteMusicBox.setAlignment(Pos.CENTER_LEFT);
    muteMusicBox.setPrefWidth(360);
    muteMusicBox.setSpacing(5);
    LocalizedLabel muteMusicLabel = new LocalizedLabel("setting.muteGameMusic");
    muteMusicBox.getChildren().add(muteMusicLabel);

    muteSoundEffect = new FantasyCheckbox();
    muteSoundEffect.setOnMouseClicked(event -> {
      settingsSaved = false;
      if (muteSoundEffect.isSelected()) {
        SoundEffectPlayer.muteSoundEffects();
      } else {
        SoundEffectPlayer.unmuteSoundEffects();
      }
    });
    HBox muteSfxBox = new HBox(muteSoundEffect);
    muteSfxBox.setAlignment(Pos.CENTER_LEFT);
    muteSfxBox.setSpacing(5);
    LocalizedLabel muteSfxLabel = new LocalizedLabel("setting.muteSoundEffects");
    muteSfxBox.getChildren().add(muteSfxLabel);

    HBox muteButtonsBox = new HBox(muteMusicBox, muteSfxBox);

    soundSettingsbox.getChildren().addAll(volumeSliderBox, muteButtonsBox);

    VBox settingsBox = new VBox(setTitleLabel("setting.title"), soundSettingsbox);
    settingsBox.setAlignment(Pos.CENTER);
    settingsBox.setSpacing(20);
    settingsBox.setMaxWidth(700);

    VBox aiSettingsBox = new VBox(setTitleLabel("setting.aiTitle"));
    aiSettingsBox.setPrefWidth(360);
    aiSettingsBox.setSpacing(5);

    enableDisableAi = new FantasyCheckbox();
    enableDisableAi.setOnMouseClicked(event -> {
      settingsSaved = false;
      settings.setAiImagesEnabled(enableDisableAi.isSelected());
    });
    HBox enableDisableAiBox = new HBox(enableDisableAi);
    enableDisableAiBox.setSpacing(5);
    LocalizedLabel enableDisableAiLabel = new LocalizedLabel("setting.enableAi");
    enableDisableAiBox.getChildren().add(enableDisableAiLabel);

    aiSettingsBox.getChildren().add(enableDisableAiBox);

    HBox leftRightSettingsBox = new HBox(aiSettingsBox);
    leftRightSettingsBox.setPadding(new Insets(0, 0, 20, 0));
    settingsBox.getChildren().add(leftRightSettingsBox);

    root.setCenter(settingsBox);

    ChoiceBox<String> languageSelectionChoiceBox = LanguageManager.getLanguageSelectionBox();

    languageSelectionChoiceBox.setOnAction(event -> {
      int selectedLanguage = languageSelectionChoiceBox.getSelectionModel().getSelectedIndex();
      FantasyAlert confirmationAlert = new FantasyAlert(stage);
      confirmationAlert.setAlertType(Alert.AlertType.CONFIRMATION);
      confirmationAlert.setTitle("alert.changeLanguage");
      confirmationAlert.setHeader("alert.languageChange");

      confirmationAlert.showAndWait();
      if (FantasyAlert.getResult() == ButtonType.OK) {
        settingsSaved = false;
        switch (selectedLanguage) {
          case 0 -> {
            LanguageManager.loadLanguage("de");
            languageSelectionChoiceBox.getSelectionModel().select(0);
          }
          case 1 -> {
            LanguageManager.loadLanguage("en");
            languageSelectionChoiceBox.getSelectionModel().select(1);
          }
          case 2 -> {
            LanguageManager.loadLanguage("es");
            languageSelectionChoiceBox.getSelectionModel().select(2);
          }
          default -> {
            LanguageManager.loadLanguage("no");
            languageSelectionChoiceBox.getSelectionModel().select(3);
          }
        }
      }
    });

    VBox languageSelectionBox = new VBox(setTitleLabel("setting.languageTitle"));
    languageSelectionBox.setSpacing(5);
    LocalizedLabel selectLanguageLabel = new LocalizedLabel("setting.languageSelect");
    languageSelectionBox.getChildren().addAll(selectLanguageLabel, languageSelectionChoiceBox);

    leftRightSettingsBox.getChildren().add(languageSelectionBox);

    settingsBox.getChildren().add(setButtons(stage));
    root.setPadding(new Insets(5));

    loadSettings();
    root.setTranslateY(15);

    return new Scene(root);
  }

  private void loadSettings() {
    Settings loadedSetting = SettingsDao.loadSettingsFromFile();

    masterVolumeSlider.setValue(loadedSetting.getMasterVolume());
    musicVolumeSlider.setValue(loadedSetting.getMusicVolume());
    soundEffectVolumeSlider.setValue(loadedSetting.getSoundEffectVolume());

    masterVolume = masterVolumeSlider.getValue();
    musicVolume = musicVolumeSlider.getValue();
    soundEffectVolume = soundEffectVolumeSlider.getValue();

    muteMusic.setSelected(loadedSetting.isGameMusicMuted());
    muteSoundEffect.setSelected(loadedSetting.isSoundEffectMuted());

    musicMuted = muteMusic.isSelected();
    soundEffectMuted = muteSoundEffect.isSelected();

    enableDisableAi.setSelected(loadedSetting.isAiImagesEnabled());
  }

  private void updateVolume() {
    MusicPlayer.setMusicVolume(masterVolumeSlider.getValue(), musicVolumeSlider.getValue());
    SoundEffectPlayer.setSoundEffectVolume(masterVolumeSlider.getValue(),
        soundEffectVolumeSlider.getValue());
  }

  private Slider createSliderWithPercentageLabel() {
    Slider slider = new Slider(0, 1, 1);
    slider.setBlockIncrement(1);
    return slider;
  }

  public Label setTitleLabel(String resourceKey) {
    LocalizedLabel settingsLabel;
    settingsLabel = new LocalizedLabel(resourceKey);
    settingsLabel.setStyle("-fx-font-size: 20px;");
    return settingsLabel;
  }

  public HBox setButtons(Stage stage) {
    FantasyButton saveButton = new FantasyButton("button.save");
    saveButton.setPrefWidth(200);
    saveButton.setFantasyButtonType(FantasyButtonType.BONE);
    saveButton.setOnMouseClicked(event -> {
      try {
        settings.setMasterVolume(masterVolumeSlider.getValue());
        settings.setMusicVolume(musicVolumeSlider.getValue());
        settings.setSoundEffectVolume(soundEffectVolumeSlider.getValue());
        settings.setGameMusicMuted(muteMusic.isSelected());
        settings.setSoundEffectMuted(muteSoundEffect.isSelected());
        SettingsDao.saveSettings();
        FantasyAlert saveSuccessAlert = new FantasyAlert(stage);
        saveSuccessAlert.setTitle("alert.settingsSaved");
        saveSuccessAlert.setAlertType(Alert.AlertType.INFORMATION);
        saveSuccessAlert.setHeader("alert.settingsSavedText");
        settingsSaved = true;
        saveSuccessAlert.showAndWait();
      } catch (Exception e) {
        FantasyAlert failedToSaveAlert = new FantasyAlert(stage);
        failedToSaveAlert.setTitle("alert.error");
        failedToSaveAlert.setAlertType(Alert.AlertType.ERROR);
        failedToSaveAlert.setHeader("alert.settingsErrorText");

        failedToSaveAlert.showAndWait();
        ErrorLogger.LOGGER.log(Level.WARNING, String.format("Failed to save settings; %s", e));
      }

    });

    FantasyButton cancelButton = new FantasyButton("alert.goBack");
    cancelButton.setPrefWidth(200);
    cancelButton.setFantasyButtonType(FantasyButtonType.BONE);
    cancelButton.setOnMouseClicked(event -> {
      if (!settingsSaved) {
        FantasyAlert quitAlert = new FantasyAlert(stage);
        quitAlert.setTitle("alert.goBack");
        quitAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        quitAlert.setHeader("alert.goBackText");

        quitAlert.showAndWait();

        if (FantasyAlert.getResult().equals(ButtonType.OK)) {
          MusicPlayer.setMusicVolume(masterVolume, musicVolume);
          SoundEffectPlayer.setSoundEffectVolume(masterVolume, soundEffectVolume);
          if (musicMuted) {
            MusicPlayer.muteMusic();
          } else {
            MusicPlayer.unmuteMusic();
          }
          if (soundEffectMuted) {
            SoundEffectPlayer.muteSoundEffects();
          } else {
            SoundEffectPlayer.unmuteSoundEffects();
          }
          MainMenuScene.getContentContainer().getChildren().remove(1);
        }
      } else {
        MainMenuScene.getContentContainer().getChildren().remove(1);
      }
    });
    HBox bottomButtons = new HBox(saveButton, cancelButton);
    bottomButtons.setAlignment(Pos.CENTER);
    bottomButtons.setSpacing(20);
    bottomButtons.setPadding(new Insets(10));
    return bottomButtons;
  }
}