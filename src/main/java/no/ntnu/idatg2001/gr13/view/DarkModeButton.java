package no.ntnu.idatg2001.gr13.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class DarkModeButton {
    private static final DarkModeButton instance = new DarkModeButton();
    private static ToggleButton toggleButton;

    private DarkModeButton() {
        // Private constructor to prevent direct instantiation
        toggleButton = new ToggleButton("", new FontIcon(Feather.SUN));
    }

    public static DarkModeButton getInstance() {
        return instance;
    }

    public static ToggleButton getToggleButton() {
        return toggleButton;
    }

    public static GridPane getLayoutForToggleButton() {
        GridPane darkModeGrid = new GridPane();
        ToggleButton button = getToggleButton();

        // Setting buttonPlacement for darkMode
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(darkModeGrid);
        darkModeGrid.setAlignment(Pos.TOP_RIGHT);
        darkModeGrid.setVgap(10);
        darkModeGrid.setHgap(10);
        darkModeGrid.setPadding(new Insets(10));

        darkModeGrid.getChildren().addAll(button);

        return darkModeGrid;
    }

    public static void setLayoutForToggleButton(BorderPane root) {
        root.setTop(DarkModeButton.getLayoutForToggleButton());
    }
}
