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

import lombok.Getter;
/**
 * JavaFX Window
 */
public abstract class Window extends Application {
    
    @Getter
    protected Rectangle2D screenBounds;
    
    @Getter
    protected double screenMinX;
    
    @Getter
    protected double screenMinY;
    
    @Getter
    protected double screenMaxX;
    
    @Getter
    protected double screenMaxY;
    
    @Getter
    protected double screenWidth;
    
    @Getter
    protected double screenHeight;
    
    @Getter
    protected Stage stage;
    
    @Getter
    protected Scene scene;
    
    @Getter
    protected double stageWidth;
    
    @Getter
    protected double stageHeight;
    
    @Getter
    protected double stageX;
    
    @Getter
    protected double stageY;

    public Window() {
        this.stage = null;
    }
    
    public Window initWindow() throws IOException {
        if (this.stage == null) {
            this.stage = new Stage();
            start(stage);
        }
        return this;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        
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
            screenBounds = screen.getVisualBounds();
            screenMinX = (screen.getVisualBounds().getMinX());
            screenMinY = (screen.getVisualBounds().getMinY());
            screenMaxX = (screen.getVisualBounds().getMaxX());
            screenMaxY = (screen.getVisualBounds().getMaxY());
            screenWidth = screenBounds.getWidth();
            screenHeight = screenBounds.getHeight();
        });
        
        addMethodToListenerBounds();
        addMethodToListenerPosition();
    }
    
    private void actualizarBoundsStage() {
        this.stageWidth = stage.getWidth();
        this.stageHeight = stage.getHeight();
    }
    
    private void actualizarPositionStage() {
        this.stageX = stage.getX();
        this.stageY = stage.getY();
    }
    
    private void addMethodToListenerBounds() {
        stage.widthProperty().addListener((obs, odlVal, newVal) -> actualizarBoundsStage());
        stage.heightProperty().addListener((obs, odlVal, newVal) -> actualizarBoundsStage());
    }
    
    private void addMethodToListenerPosition() {
        stage.xProperty().addListener((obs, odlVal, newVal) -> actualizarPositionStage());
        stage.yProperty().addListener((obs, odlVal, newVal) -> actualizarPositionStage());
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

    public void setStageX(double xPosition) {
        this.stageX = xPosition;
        if (scene != null) {
            stage.setX(this.stageX);
        }
    }
    
    public void setStageY(double yPosition) {
        stageY = yPosition;
        if (scene != null) {
            stage.setY(this.stageY);
        }
    }
}