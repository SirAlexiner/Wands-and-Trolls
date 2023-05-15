package no.ntnu.idatg2001.gr13.controller;

import no.ntnu.idatg2001.gr13.view.LanguageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static no.ntnu.idatg2001.gr13.controller.ResourceBundles.BUTTONS;

public class LanguageController {
    private ResourceBundle bundle;
    private List<LanguageListener> listeners;
    // TODO singleton

    public LanguageController() {
        listeners = new ArrayList<>();
    }

    public void setLanguage(String languageCode) {
        setLanguageForObject(BUTTONS, new Locale.Builder().setLanguage(languageCode).build());
    }

    public void setLanguageForObject(ResourceBundles resourceObject, Locale locale) {
        bundle = ResourceBundle.getBundle("languages/" + resourceObject.getBundleName(), locale);
        notifyLanguageChange();
    }

    public String getLocalizedString(String key) {
        return bundle.getString(key);
    }

    public void addLanguageChangeListener(LanguageListener listener) {
        listeners.add(listener);
    }

    public void notifyLanguageChange() {
        for (LanguageListener listener : listeners) {
            listener.languageChange();
        }
    }
}

