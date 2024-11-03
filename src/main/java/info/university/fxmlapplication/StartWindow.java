package info.university.fxmlapplication;

import info.university.fxmlapplication.windowbuilder.Window;
import info.university.fxmlapplication.fxml.util.FxmlLocation;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartWindow extends Window {
    
    private final String FIRST_VIEW = "Start";
    
    public StartWindow() {
        super();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        
        setStageWidth(screenWidth / 2);
        setStageHeight(screenHeight / 2);
        
        scene = new Scene(loadFXML(FIRST_VIEW, FxmlLocation.VIEWS), stageWidth, stageHeight);
        
        centerWindowIntoScreen();
        
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
