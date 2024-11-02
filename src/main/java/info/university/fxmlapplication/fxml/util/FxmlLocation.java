package info.university.fxmlapplication.fxml.util;

public enum FxmlLocation {
    VIEWS("views"),
    PANELS("panels"),
    COMPONENTS("components"),
    DIALOGS("dialogs"),
    LAYOUTS("layouts");

    private final String path;

    FxmlLocation(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
