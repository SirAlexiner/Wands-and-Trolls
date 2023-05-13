package no.ntnu.idatg2001.gr13.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageModel {
    private ResourceBundle bundle;
    // TODO singleton

    public void setLanguage(String languageCode) {
        Locale locale = new Locale.Builder().setLanguage(languageCode).build();
        bundle = ResourceBundle.getBundle("languages/buttons", locale);
        // Notify the views about the language change
    }

    public String getLocalizedString(String key) {
        return bundle.getString(key);
    }

    public void addLanguageChangeListener(LanguageChangeListener listener) {
        listeners.add(listener);
    }
}

