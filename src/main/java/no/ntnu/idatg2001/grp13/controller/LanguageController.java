package no.ntnu.idatg2001.grp13.controller;

import no.ntnu.idatg2001.grp13.view.LanguageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static no.ntnu.idatg2001.grp13.controller.ResourceBundles.*;
/**
 * The LanguageController class is responsible for managing the language settings of the program.
 * It ensures that the program's user interface elements are displayed in the selected language.
 * <p>
 * This class follows the singleton design pattern to provide a single instance throughout the application.
 * Use the static method `getInstance()` to retrieve the instance of this class.
 * <p>
 * The LanguageController supports loading language-specific resource bundles for buttons and text elements.
 * It allows adding language change listeners to be notified when the language is changed.
 * <p>
 * The language code used follows the ISO standard 639-1.
 * <p>
 * Part of the WiNG application in the controller package.
 *
 * @author Arthur Borger Thorkildsen
 * @version 1.0
 */
public class LanguageController {
    private ResourceBundle buttonBundle;
    private ResourceBundle textBundle;
    private final List<LanguageListener> listeners;
    private static LanguageController instance = null;
    // private constructor
    private LanguageController() {
        listeners = new ArrayList<>();
    }

    /**
     * A method for getting an instance of this class.
     * Creates an instance if it does not exist.
     * @return the instance of this class.
     */
    public static LanguageController getInstance()
    {
        if (instance == null)
            instance = new LanguageController();

        return instance;
    }

    /**
     * A method for setting the language code for the program to use.
     * @param languageCode the language code uses the ISO standard 639-1.
     */
    public void setLanguage(String languageCode) {
        String directory = "languages/";
        Locale locale = new Locale.Builder().setLanguage(languageCode).build();
        buttonBundle = ResourceBundle.getBundle(directory + BUTTONS.getBundleName(), locale);
        textBundle = ResourceBundle.getBundle(directory + TEXT.getBundleName(), locale);
        notifyLanguageChange();
    }

    /**
     * A method for getting the button value.
     * The button value is contained in language hierarchy.
     * If the value does not exist, it will traverse to the parent class, example "button.properties".
     * @param key the string key contained in the bundle.
     * @return the value of the key.
     */
    public String getButtonBundleString(String key) {
        return buttonBundle.getString(key);
    }
    /**
     * A method for getting the text value.
     * The button value is contained in language hierarchy.
     * If the value does not exist, it will traverse to the parent class, example "text.properties".
     * @param key the string key contained in the bundle.
     * @return the value of the key.
     */
    public String getTextBundleString(String key) {
        return textBundle.getString(key);
    }

    /**
     * Adds a listener to be notified of language changes.
     * @param listener the listener object to be added
     */
    public void addLanguageChangeListener(LanguageListener listener) {
        listeners.add(listener);
    }

    /**
     * A method for notify if there was a language change for the listeners.
     */
    public void notifyLanguageChange() {
        listeners.forEach(LanguageListener::languageChange);
    }
}

