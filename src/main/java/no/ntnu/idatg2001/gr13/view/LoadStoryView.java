package no.ntnu.idatg2001.gr13.view;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoadStoryView extends Application {
    private BorderPane root;
    private ToggleButton buttonEnableDarkMode;
    private TableView<String> storyTableView;
    private TableColumn<String, String> tableColumn;
    private ObservableList<String> list;
    private Scene scene;
    private Stage window;
    private GridPane gridPane;
    private GridPane darkModeGrid;
    private BorderPane borderPane;
    private HBox hBox;
    private VBox listViewBox;

    private Tooltip tooltip;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            setUp(stage);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void setUp(Stage stage) {
        root = new BorderPane();

        layoutTableColumn();


        scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("WiNG");
        FxManager.setup(stage);


        root.setLeft(layoutTableColumn());

        stage.show();
    }

    public GridPane layoutTableColumn() {
        setupTableColumn();
        gridPane = new GridPane();
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
