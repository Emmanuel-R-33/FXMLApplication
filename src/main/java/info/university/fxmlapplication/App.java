package info.university.fxmlapplication;

import info.university.fxmlapplication.fxml.util.FxmlRelativePathBuilder;
import info.university.fxmlapplication.fxml.util.FxmlLocation;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private final String FIRST_VIEW = "Start";
    
    private Rectangle2D SCREEN_BOUNDS;
    private double SCREEN_MIN_X;
    private double SCREEN_MIN_Y;
    private double SCREEN_MAX_X;
    private double SCREEN_MAX_Y;
    private double SCREEN_WIDTH;
    private double SCREEN_HEIGHT;

    private static Scene scene;
    private Stage stage;
    
    private double stageWidth;
    private double stageHeight;

    
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        
        initScreenBounds();
        
        setStageWidth(SCREEN_WIDTH / 2);
        setStageHeight(SCREEN_HEIGHT / 2);
        
        scene = new Scene(loadFXML(FIRST_VIEW, FxmlLocation.VIEWS), stageWidth, stageHeight);
        
        setStageX(SCREEN_MIN_X + (SCREEN_WIDTH / 2) - (stageWidth / 2));
        setStageY(SCREEN_MIN_Y + (SCREEN_HEIGHT / 2) - (stageHeight / 2) - 50);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, FxmlLocation location) throws IOException {
        scene.setRoot(loadFXML(fxml, location));
    }

    /**
     * Método para cargar archivos FXML en diferentes ubicaciones.
     * @param fxml Nombre del archivo FXML.
     * @param location Enumeración que indica el subdirectorio donde se encuentra el archivo FXML.
     * @return El objeto Parent del FXML cargado.
     * @throws IOException Si no se puede cargar el archivo FXML.
     */
    private static Parent loadFXML(String fxml, FxmlLocation location) throws IOException {
        String fxmlPath = FxmlRelativePathBuilder.buildPath(fxml, location.getPath());
        URL url = App.class.getResource(fxmlPath);
        
        if (url == null) throw new IOException("No se pudo encontrar el archivo FXML: " + fxmlPath);
        
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }
    
    private void initScreenBounds() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        double mouseX = mouseLocation.getX();
        double mouseY = mouseLocation.getY();
        
        Screen.getScreens().stream()
        .filter(screen -> screen.getBounds().contains(mouseX, mouseY))
        .findFirst()
        .ifPresent(screen -> {
            SCREEN_BOUNDS = screen.getVisualBounds();
            SCREEN_MIN_X = (screen.getVisualBounds().getMinX());
            SCREEN_MIN_Y = (screen.getVisualBounds().getMinY());
            SCREEN_MAX_X = (screen.getVisualBounds().getMaxX());
            SCREEN_MAX_Y = (screen.getVisualBounds().getMaxY());
            SCREEN_WIDTH = SCREEN_BOUNDS.getWidth();
            SCREEN_HEIGHT = SCREEN_BOUNDS.getHeight();
        });
    }

    public void setStageWidth(double stageWidth) {
        this.stageWidth = stageWidth;
        if (scene != null) {
            stage.setWidth(this.stageWidth);
        }
    }

    public void setStageHeight(double stageHeight) {
        this.stageHeight = stageHeight;
        if (scene != null) {
            stage.setHeight(this.stageHeight);
        }
    }
    
    public void setStageX(double stageX) {
        if (scene != null) {
            stage.setX(stageX);
        }
    }

    public void setStageY(double stageY) {
        if (scene != null) {
            stage.setY(stageY);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}