package no.ntnu.idatg2001.grp13.gui.stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyFooter;
import no.ntnu.idatg2001.grp13.gui.elements.FantasyTopBar;
import no.ntnu.idatg2001.grp13.gui.scene.MainMenu;

import java.util.Objects;

public class MainMenuStage extends Application {
    @Getter
    private static final BorderPane root = new BorderPane();
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

    @Override
    public void start(Stage primaryStage) {
        Image icon = new Image(
                Objects.requireNonNull(MainMenu.class.getResourceAsStream(
                        "/Image/MainMenu/Logo_Icon.png")));

        primaryStage.getIcons().add(icon);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Wands and Trolls");
        primaryStage.setResizable(true);

        root.setCenter(MainMenu.getMainMeuScene(primaryStage));

        FantasyTopBar topBar = new FantasyTopBar(primaryStage);
        root.setTop(topBar);

        FantasyFooter bottomBar = new FantasyFooter();
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 1024, 768, Color.TRANSPARENT);

        scene.getStylesheets()
                .add(String.valueOf(MainMenu.class.getResource("/CSS/WindowUi/FantasyStyle.css")));

        AudioClip startPlayer = new AudioClip(
                Objects.requireNonNull(MainMenu.class.getResource("/Audio/ThroughFire.wav"))
                        .toString());
        startPlayer.setCycleCount(AudioClip.INDEFINITE);
        startPlayer.setVolume(0.25);
        startPlayer.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
