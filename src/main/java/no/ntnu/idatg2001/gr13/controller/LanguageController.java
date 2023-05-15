package no.ntnu.idatg2001.gr13.controller;

import no.ntnu.idatg2001.gr13.view.LanguageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static no.ntnu.idatg2001.gr13.controller.ResourceBundles.*;

public class LanguageController {
    private ResourceBundle buttonBundle;
    private ResourceBundle textBundle;
    private final List<LanguageListener> listeners;
    public LanguageController() {
        listeners = new ArrayList<>();
    }

    public void setLanguage(String languageCode) {
        String directory = "languages/";
        Locale locale = new Locale.Builder().setLanguage(languageCode).build();
        buttonBundle = ResourceBundle.getBundle(directory + BUTTONS.getBundleName(), locale);
        textBundle = ResourceBundle.getBundle(directory + TEXT.getBundleName(), locale);
        notifyLanguageChange();
    }

    public String getButtonBundleString(String key) {
        return buttonBundle.getString(key);
    }
    public String getTextBundleString(String key) {
        return textBundle.getString(key);
    }

    public void addLanguageChangeListener(LanguageListener listener) {
        listeners.add(listener);
    }

    public void notifyLanguageChange() {
        listeners.forEach(LanguageListener::languageChange);
    }
}

