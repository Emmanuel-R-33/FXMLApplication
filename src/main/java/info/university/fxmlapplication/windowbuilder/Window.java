package info.university.fxmlapplication.windowbuilder;

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
 * JavaFX Window
 */
public abstract class Window extends Application {
    
    protected Rectangle2D SCREEN_BOUNDS;
    protected double SCREEN_MIN_X;
    protected double SCREEN_MIN_Y;
    protected double SCREEN_MAX_X;
    protected double SCREEN_MAX_Y;
    protected double SCREEN_WIDTH;
    protected double SCREEN_HEIGHT;

    protected Stage STAGE;
    protected Scene scene;
    
    protected double stageWidth;
    protected double stageHeight;
    protected double stageX;
    protected double stageY;

    public Window() {
        this.STAGE = null;
    }
    
    public Window initWindow() throws IOException {
        if (this.STAGE == null) {
            this.STAGE = new Stage();
            start(STAGE);
        }
        return this;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        this.STAGE = stage;
        
        initScreenBounds();
    }

    public void setRoot(String fxml, FxmlLocation location) throws IOException {
        scene.setRoot(loadFXML(fxml, location));
    }

    /**
     * Método para cargar archivos FXML en diferentes ubicaciones.
     * @param fxml Nombre del archivo FXML.
     * @param location Enumeración que indica el subdirectorio donde se encuentra el archivo FXML.
     * @return El objeto Parent del FXML cargado.
     * @throws IOException Si no se puede cargar el archivo FXML.
     */
    protected Parent loadFXML(String fxml, FxmlLocation location) throws IOException {
        String fxmlPath = FxmlRelativePathBuilder.buildPath(fxml, location.getPath());
        URL url = getClass().getResource(fxmlPath);
        
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
        
        addMethodToListenerBounds();
        addMethodToListenerPosition();
    }
    
    private void actualizarBoundsStage() {
        this.stageWidth = STAGE.getWidth();
        this.stageHeight = STAGE.getHeight();
    }
    
    private void actualizarPositionStage() {
        this.stageX = STAGE.getX();
        this.stageY = STAGE.getY();
    }
    
    private void addMethodToListenerBounds() {
        STAGE.widthProperty().addListener((obs, odlVal, newVal) -> actualizarBoundsStage());
        STAGE.heightProperty().addListener((obs, odlVal, newVal) -> actualizarBoundsStage());
    }
    
    private void addMethodToListenerPosition() {
        STAGE.xProperty().addListener((obs, odlVal, newVal) -> actualizarPositionStage());
        STAGE.yProperty().addListener((obs, odlVal, newVal) -> actualizarPositionStage());
    }

    public void setStageWidth(double stageWidth) {
        this.stageWidth = stageWidth;
        if (scene != null) {
            STAGE.setWidth(this.stageWidth);
        }
    }

    public void setStageHeight(double stageHeight) {
        this.stageHeight = stageHeight;
        if (scene != null) {
            STAGE.setHeight(this.stageHeight);
        }
    }

    public double getStageWidth() {
        return stageWidth;
    }

    public double getStageHeight() {
        return stageHeight;
    }

    public void setStageX(double xPosition) {
        this.stageX = xPosition;
        if (scene != null) {
            STAGE.setX(this.stageX);
        }
    }
    
    public void setStageY(double yPosition) {
        stageY = yPosition;
        if (scene != null) {
            STAGE.setY(this.stageY);
        }
    }
    
    public double getStageX() {
        return stageX;
    }

    public double getStageY() {
        return stageY;
    }

    public Rectangle2D getSCREEN_BOUNDS() {
        return SCREEN_BOUNDS;
    }

    public double getSCREEN_MIN_X() {
        return SCREEN_MIN_X;
    }

    public double getSCREEN_MIN_Y() {
        return SCREEN_MIN_Y;
    }

    public double getSCREEN_MAX_X() {
        return SCREEN_MAX_X;
    }

    public double getSCREEN_MAX_Y() {
        return SCREEN_MAX_Y;
    }

    public double getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public double getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public Stage getStage() {
        return STAGE;
    }
    
    public Scene getScene() {
        return scene;
    }

}