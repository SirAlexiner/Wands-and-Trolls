package no.ntnu.idatg2001.gr13.controller;

public enum ResourceBundles {
    BUTTONS("Buttons"),
    TEXT("text");

    private final String bundleName;

    ResourceBundles(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }

    @Override
    public String toString() {
        return bundleName;
    }
}
