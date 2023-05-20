package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoadStoryView {
    private final BorderPane root;
    private final Stage stage;
    private VBox listViewBox;


    public LoadStoryView(Stage stage) {
        this.stage = stage;
        root = new BorderPane();

        layoutTableColumn();

        Scene scene = new Scene(root, 800, 400);
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
