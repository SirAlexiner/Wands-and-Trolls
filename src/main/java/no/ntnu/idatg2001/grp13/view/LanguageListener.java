package no.ntnu.idatg2001.grp13.view;

import no.ntnu.idatg2001.grp13.controller.LanguageController;

/**
 * The LanguageListener interface defines the contract for objects that listen for language changes.
 * Implement this interface to receive notifications when the language is changed.
 * <p>
 * Part of the WiNG application in the view package.
 * <p>
 * Note: Implementing classes should provide the necessary logic to handle language changes,
 * such as updating localized strings or refreshing the user interface.
 *
 * @see LanguageController
 * @author Arthur Borger Thorkildsen
 * @version 1.0
 */
public interface LanguageListener {
    /**
     * This method is implemented for language change events.
     */
    void languageChange();

    /**
     * This method is implemented for updating local strings.
     */
    void updateLocalizedStrings();
}
