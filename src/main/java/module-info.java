/**
 * This is the module info for the WiNG application.
 *
 * @author Arthur Borger Thorkildsen
 * @since May 14. 2023
 */
module PathsModule {
    requires com.sun.jna.platform;
    requires javafx.graphics;
    requires lombok;
    requires com.sun.jna;
    requires javafx.controls;
    requires org.kordamp.ikonli.javafx;
    requires atlantafx.base;
    requires org.kordamp.ikonli.feather;
    requires com.jthemedetector;

    exports no.ntnu.idatg2001.gr13.view;
    exports no.ntnu.idatg2001.gr13.model;
    exports no.ntnu.idatg2001.gr13.controller;
}
