package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;


public class LoadStoryView extends Application {
    private BorderPane root;
    private ToggleButton buttonEnableDarkMode;
    private Scene scene;
    private Stage stage;
    private GridPane darkModeGrid;
    private VBox listViewBox;


    @Override
    public void start(Stage stage) throws Exception {
        setUp();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void setUp() {
        stage = new Stage();
        root = new BorderPane();

        layoutTableColumn();

        scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("WiNG");
        FxManager.setup(stage);
        DarkModeButton.setLayoutForToggleButton(root);
        root.setLeft(layoutTableColumn());

        stage.show();
    }

    public GridPane layoutTableColumn() {
        setupTableColumn();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(70));
        gridPane.getChildren().addAll(listViewBox);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


    public void setupTableColumn() {
        // Create a new ListView
        ListView<String> listView = new ListView<>();

        // Create a list of items to display
        ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");

        // Set the items in the ListView
        listView.setItems(items);

        // Add the ListView to a parent node, such as a VBox
        listViewBox = new VBox();
        listViewBox.getChildren().add(listView);
    }
}
