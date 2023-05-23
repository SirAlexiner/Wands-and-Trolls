package no.ntnu.idatg2001.grp13.gui.util.language;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.binding.ListBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import lombok.experimental.UtilityClass;
import no.ntnu.idatg2001.grp13.gui.scene.SettingsScene;
import no.ntnu.idatg2001.grp13.gui.util.settings.Settings;
import no.ntnu.idatg2001.grp13.util.ErrorLogger;

@UtilityClass
public class LanguageManager {
  private static final Map<String, ResourceBundle> resourceBundles = new HashMap<>();
  private static final StringProperty currentLanguage = new SimpleStringProperty();


  public static void loadLanguage(String language) {
    try {
      resourceBundles.put("buttons",
          ResourceBundle.getBundle("buttons", Locale.forLanguageTag(language)));
      resourceBundles.put("language",
          ResourceBundle.getBundle("language", Locale.forLanguageTag(language)));
      resourceBundles.put("alert",
          ResourceBundle.getBundle("alert", Locale.forLanguageTag(language)));
      resourceBundles.put("setting",
          ResourceBundle.getBundle("setting", Locale.forLanguageTag(language)));
      resourceBundles.put("text",
          ResourceBundle.getBundle("text", Locale.forLanguageTag(language)));
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE,
          String.format("Failed to load localized text: %s", e));
    }
    currentLanguage.set(language);
    Settings settings = SettingsScene.getSettings();
    settings.setCurrentLanguage(language);
  }

  public static ChoiceBox<String> getLanguageSelectionBox() {
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    ListBinding<String> itemsBinding = new ListBinding<>() {
      {
        bind(currentLanguage);
      }

      @Override
      protected ObservableList<String> computeValue() {
        currentLanguage.get();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(LanguageManager.getStringProperty("language.german")
            .get());
        items.add(LanguageManager.getStringProperty("language.english")
            .get());
        items.add(LanguageManager.getStringProperty("language.spanish")
            .get());
        items.add(LanguageManager.getStringProperty("language.norwegian")
            .get());
        return items;
      }
    };

    choiceBox.itemsProperty().bind(itemsBinding);
    switch (currentLanguage.get()) {
      case "de" -> choiceBox.getSelectionModel().select(0);
      case "en" -> choiceBox.getSelectionModel().select(1);
      case "es" -> choiceBox.getSelectionModel().select(2);
      default -> choiceBox.getSelectionModel().select(3);
    }
    return choiceBox;
  }

  public static StringBinding getStringProperty(String key) {
    return new StringBinding() {
      {
        bind(currentLanguage);
      }

      @Override
      protected String computeValue() {
        currentLanguage.get();
        for (Map.Entry<String, ResourceBundle> entry : resourceBundles.entrySet()) {
          ResourceBundle bundle = entry.getValue();
          if (bundle != null && bundle.containsKey(key)) {
            return bundle.getString(key).toUpperCase();
          }
        }
        return null;
      }
    };
  }
}