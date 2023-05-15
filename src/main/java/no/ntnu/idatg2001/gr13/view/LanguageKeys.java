package no.ntnu.idatg2001.gr13.view;

public enum LanguageKeys {
    KEY_NEW_GAME_BUTTON("newGameButton"),
    KEY_LOAD_GAME_BUTTON("loadGameButton"),
    KEY_SETTINGS_BUTTON("settingsButton"),
    KEY_CANCEL_BUTTON("cancelButton"),
    KEY_CONFIRM_BUTTON("confirmButton"),
    KEY_NORWEGIAN_TEXT("norwegianText"),
    KEY_ENGLISH_TEXT("englishText"),
    KEY_GERMAN_TEXT("germanText");


    private final String keyName;

    LanguageKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

    @Override
    public String toString() {
        return keyName;
    }
}
