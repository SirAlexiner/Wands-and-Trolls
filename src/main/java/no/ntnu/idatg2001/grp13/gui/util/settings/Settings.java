package no.ntnu.idatg2001.grp13.gui.util.settings;

import lombok.Getter;
import lombok.Setter;

public class Settings {
  @Getter
  @Setter
  public double masterVolume = 1;
  @Getter
  @Setter
  public double musicVolume = 1;
  @Getter
  @Setter
  public double soundEffectVolume = 1;
  @Getter
  @Setter
  public boolean gameMusicMuted = false;
  @Getter
  @Setter
  public boolean soundEffectMuted = false;
  @Getter
  @Setter
  public boolean aiImagesEnabled = false;
  @Getter
  @Setter
  public String currentLanguage = "no";
}
