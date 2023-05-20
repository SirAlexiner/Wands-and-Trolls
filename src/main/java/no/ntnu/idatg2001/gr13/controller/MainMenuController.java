package no.ntnu.idatg2001.gr13.controller;

import io.github.siralexiner.fxmanager.FxManager;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class MainMenuController {
    public MainMenuController() {
    }

    public void darkModeButtonOnPressed(Stage stage, ToggleButton toggleButton){
        FxManager.enableDarkMode(stage);
        if (toggleButton.isSelected()){
            FxManager.enableLightMode(stage);
        }
        else {
            FxManager.enableDarkMode(stage);
        }
    }
}
