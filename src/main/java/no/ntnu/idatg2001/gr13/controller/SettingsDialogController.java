package no.ntnu.idatg2001.gr13.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsDialogController {

    public void onExitApplication(ActionEvent event) {

        // Close the application window
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
