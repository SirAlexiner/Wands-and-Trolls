package no.ntnu.idatg2001.grp13.gui.util;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.AudioClip;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.stage.MainStage;

@UtilityClass
public class MusicPlayer {

  private static final AudioClip mainMenuMusic;

  static {
    mainMenuMusic = new AudioClip(
        Objects.requireNonNull(MainStage.class.getResource("/Audio/ThroughFire.wav"))
            .toString());
  }

  public static void playMainMenuMusic() {
    mainMenuMusic.setCycleCount(AudioClip.INDEFINITE);
    mainMenuMusic.play();
  }

  public static void mainMenuMusicMute() {
    mainMenuMusic.setVolume(-1);
  }

  public static void mainMenuMusicSetVolume(DoubleProperty masterVolume, DoubleProperty volume) {
    if (masterVolume.equals(0) || volume.equals(0)) {
      mainMenuMusicMute();
    } else {
      mainMenuMusic.setVolume(masterVolume.get() * volume.get() / 2);
    }
  }
}
