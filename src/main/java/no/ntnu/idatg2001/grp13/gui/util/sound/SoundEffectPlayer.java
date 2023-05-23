package no.ntnu.idatg2001.grp13.gui.util.sound;

import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.stage.MainStage;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;

@UtilityClass
public class SoundEffectPlayer {
  private static MediaPlayer effectPlayer;

  public static void playMouseClickSoundEffect() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    effectPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Mouse_Click.wav"))
            .toString()));
    effectPlayer.setVolume(settings.getMasterVolume() * settings.getSoundEffectVolume());
    effectPlayer.setMute(settings.isSoundEffectMuted());
    effectPlayer.play();
    effectPlayer.setOnEndOfMedia(effectPlayer::stop);
  }

  public static void muteSoundEffects() {
    effectPlayer.setMute(true);
  }

  public static void setSoundEffectVolume(double masterVolume, double volume) {
    if (masterVolume == 0.0 || volume == 0.0) {
      muteSoundEffects();
    } else {
      effectPlayer.setVolume(masterVolume * volume);
      unmuteSoundEffects();
    }
  }

  public static void unmuteSoundEffects() {
    effectPlayer.setMute(false);
  }

  public static void playTypeWriterSoundEffect() {
    Settings settings = SettingsDao.loadSettingsFromFile();
    effectPlayer = new MediaPlayer(new Media(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/Typewriter.wav"))
            .toString()));
    effectPlayer.setVolume(settings.getMasterVolume() * settings.getSoundEffectVolume());
    effectPlayer.setMute(settings.isSoundEffectMuted());
    effectPlayer.play();
    effectPlayer.setOnEndOfMedia(effectPlayer::stop);
  }
}
