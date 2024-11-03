package info.university.fxmlapplication;

import info.university.fxmlapplication.windowbuilder.Window;
import info.university.fxmlapplication.fxml.util.FxmlLocation;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartWindow extends Window {
    
    private static final String FIRST_VIEW = "Start";
    
    public StartWindow() {
        super();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        
        setStageWidth(screenWidth / 2);
        setStageHeight(screenHeight / 2);
        
        scene = new Scene(loadFXML(FIRST_VIEW, FxmlLocation.VIEWS), stageWidth, stageHeight);
        
        setStageX(screenMinX + (screenWidth / 2) - (stageWidth / 2));
        setStageY(screenMinY + (screenHeight / 2) - (stageHeight / 2) - 50);
        
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
