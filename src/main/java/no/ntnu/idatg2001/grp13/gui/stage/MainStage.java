package no.ntnu.idatg2001.grp13.gui.stage;

import java.util.Objects;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyFooter;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyTopBar;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenuScene;
import no.ntnu.idatg2001.grp13.gui.util.LanguageManager;
import no.ntnu.idatg2001.grp13.gui.util.MusicPlayer;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.gui.util.settings.SettingsDao;

public class MainStage extends Application {

  /**
   * <p>main.</p>
   *
   * @param args an array of {@link java.lang.String} objects
   */
  public static void startGame(String[] args) {
    launch(args);
  }

  @Override
  public void stop() {
    System.exit(0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(Stage primaryStage) {
    Settings savedSettings = SettingsDao.loadSettingsFromFile();
    LanguageManager.loadLanguage(savedSettings.getCurrentLanguage());
    Image icon = new Image(
        Objects.requireNonNull(MainStage.class.getResourceAsStream(
            "/Image/MainMenu/Logo_Icon.png")));

    primaryStage.getIcons().add(icon);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    primaryStage.setFullScreen(false);
    primaryStage.setTitle("Wands and Trolls");
    primaryStage.setResizable(true);

    BorderPane root = new BorderPane();
    root.setCenter(MainMenuScene.getScene(primaryStage));

    FantasyTopBar topBar = new FantasyTopBar(primaryStage);
    root.setTop(topBar);

    FantasyFooter bottomBar = new FantasyFooter();
    root.setBottom(bottomBar);

    Scene scene = new Scene(root, 1024, 768, Color.TRANSPARENT);
    scene.getStylesheets()
        .add(String.valueOf(MainStage.class.getResource("/CSS/WindowUi/FantasyStyle.css")));

    MusicPlayer.playMainMenuMusic();

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
