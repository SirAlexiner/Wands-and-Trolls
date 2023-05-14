/**
 * This is the module info for the WiNG application.
 *
 * @author Arthur Borger Thorkildsen
 * @since May 14. 2023
 */
module PathsModule {

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires atlantafx.base;
    requires com.jthemedetector;
    requires java.xml;
    requires org.kordamp.ikonli.feather;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires org.jetbrains.annotations;
    requires java.logging;

    exports no.ntnu.idatg2001.gr13.controller;
    exports no.ntnu.idatg2001.gr13.view;
    exports no.ntnu.idatg2001.gr13.model;
}