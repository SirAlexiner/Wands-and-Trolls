package no.ntnu.idatg2001.grp13.gui.util;

import java.util.Objects;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;

@UtilityClass
public class MusicPlayer {

  private static MediaPlayer audioPlayer;

  public static void playMainMenuMusic() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    audioPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Main_Menu.wav"))
            .toString()));
    audioPlayer.setVolume(settings.getMusicVolume() * settings.getMasterVolume());
    audioPlayer.setMute(settings.isGameMusicMuted());
    audioPlayer.setCycleCount(AudioClip.INDEFINITE);
    audioPlayer.play();
  }

  public static void playVictoryMusic() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    audioPlayer.stop();
    audioPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Victory_A.wav"))
            .toString()));
    audioPlayer.setVolume(settings.getMusicVolume() * settings.getMasterVolume());
    audioPlayer.setMute(settings.isGameMusicMuted());
    audioPlayer.play();
    audioPlayer.setOnEndOfMedia(() -> {
      audioPlayer = new MediaPlayer(new Media(
          Objects.requireNonNull(MainStage.class.getResource("/Audio/Victory_B.wav"))
              .toString()));
      audioPlayer.setVolume(settings.getMusicVolume() * settings.getMasterVolume());
      audioPlayer.setMute(settings.isGameMusicMuted());
      audioPlayer.setCycleCount(AudioClip.INDEFINITE);
      audioPlayer.play();
    });
  }

  public static void playDefeatMusic() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    audioPlayer.stop();
    audioPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Defeat.wav"))
            .toString()));
    audioPlayer.setVolume(settings.getMusicVolume() * settings.getMasterVolume());
    audioPlayer.setMute(settings.isGameMusicMuted());
    audioPlayer.play();
  }

  public static void muteMusic() {
    audioPlayer.setMute(true);
  }

  public static void setMusicVolume(double masterVolume, double volume) {
    if (masterVolume == 0.0 || volume == 0.0) {
      muteMusic();
    } else {
      audioPlayer.setVolume(masterVolume * volume);
    }
  }

  public static void unmuteMusic() {
    audioPlayer.setMute(false);
  }

  public static void stop() {
    audioPlayer.stop();
  }
}
