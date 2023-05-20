/**
 * This is the module info for the WiNG application.
 *
 * @author Arthur Borger Thorkildsen
 * @since May 14. 2023
 */
module WandsAndTrolls {
    requires javafx.graphics;
    requires lombok;
    requires javafx.controls;
    requires com.google.gson;
    requires java.logging;
    requires javafx.media;

    exports no.ntnu.idatg2001.grp13.view;
    exports no.ntnu.idatg2001.grp13.model;
    exports no.ntnu.idatg2001.grp13.model.actions;
    exports no.ntnu.idatg2001.grp13.model.goals;
    exports no.ntnu.idatg2001.grp13.controller;
    exports no.ntnu.idatg2001.grp13.stage;
}
