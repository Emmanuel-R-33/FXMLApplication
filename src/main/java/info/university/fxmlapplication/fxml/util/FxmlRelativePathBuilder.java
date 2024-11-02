package info.university.fxmlapplication.fxml.util;

public class FxmlRelativePathBuilder {
    
    public static String buildPath(String fxmlFile, String packageRelative) {
        return packageRelative.replace(".", "/") + 
                "/" + 
                fxmlFile + 
                ".fxml";
    }
    
}
