package no.ntnu.idatg2001.gr13.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageModel {
    private ResourceBundle bundle;
    private List<LanguageListener> listeners;
    // TODO singleton

    public LanguageModel() {
        listeners = new ArrayList<>();
    }

    public void setLanguage(String languageCode) {
        Locale locale = new Locale.Builder().setLanguage(languageCode).build();
        bundle = ResourceBundle.getBundle("languages/buttons", locale);
        notifyLanguageChange();
    }

    public String getLocalizedString(String key) {
        return bundle.getString(key);
    }

    public void addLanguageChangeListener(LanguageListener listener) {
        listeners.add(listener);
    }

    public void removeLanguageChangeListener(LanguageListener listener) {
        listeners.remove(listener);
    }

    public void notifyLanguageChange() {
        for (LanguageListener listener : listeners) {
            listener.languageChange();
        }
    }
}

