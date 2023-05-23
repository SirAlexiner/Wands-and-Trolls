package no.ntnu.idatg2001.grp13.gui.util.sound;

import java.util.Objects;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;

/**
 * <p>MusicPlayer class.</p>
 *
 * @author Sir_A
 * @version $Id: $Id
 */
@UtilityClass
public class MusicPlayer {

  private static MediaPlayer audioPlayer;

  /**
   * <p>playMainMenuMusic.</p>
   */
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

  /**
   * <p>playVictoryMusic.</p>
   */
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

  /**
   * <p>playDefeatMusic.</p>
   */
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

  /**
   * <p>muteMusic.</p>
   */
  public static void muteMusic() {
    audioPlayer.setMute(true);
  }

  /**
   * <p>setMusicVolume.</p>
   *
   * @param masterVolume a double
   * @param volume a double
   */
  public static void setMusicVolume(double masterVolume, double volume) {
    if (masterVolume == 0.0 || volume == 0.0) {
      muteMusic();
    } else {
      audioPlayer.setVolume(masterVolume * volume);
    }
  }

  /**
   * <p>unmuteMusic.</p>
   */
  public static void unmuteMusic() {
    audioPlayer.setMute(false);
  }

  /**
   * <p>stop.</p>
   */
  public static void stop() {
    audioPlayer.stop();
  }

  /**
   * <p>playAdventureMusic.</p>
   */
  public static void playAdventureMusic() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    audioPlayer.stop();
    audioPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Adventure.wav"))
            .toString()));
    audioPlayer.setVolume(settings.getMusicVolume() * settings.getMasterVolume());
    audioPlayer.setMute(settings.isGameMusicMuted());
    audioPlayer.play();
  }
}
