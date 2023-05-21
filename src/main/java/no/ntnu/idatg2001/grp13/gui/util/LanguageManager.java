package no.ntnu.idatg2001.grp13.gui.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LanguageManager {
  private static final Map<String, ResourceBundle> resourceBundles = new HashMap<>();
  private static final StringProperty currentLanguage = new SimpleStringProperty();

  public static void loadLanguage(String language) {
    String resourceFolder = "properties/";
    resourceBundles.put("buttons",
        ResourceBundle.getBundle(resourceFolder + "buttons", Locale.forLanguageTag(language)));
    resourceBundles.put("description",
        ResourceBundle.getBundle(resourceFolder + "description", Locale.forLanguageTag(language)));
    currentLanguage.set(language);
  }

  StringBinding getStringProperty(String key) {
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
            return bundle.getString(key);
          }
        }
        return null;
      }
    };
  }
}