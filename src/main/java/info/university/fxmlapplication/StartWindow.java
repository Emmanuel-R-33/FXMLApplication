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
        
        super.setStageWidth(SCREEN_WIDTH / 2);
        super.setStageHeight(SCREEN_HEIGHT / 2);
        
        scene = new Scene(loadFXML(FIRST_VIEW, FxmlLocation.VIEWS), stageWidth, stageHeight);
        
        super.setStageX(SCREEN_MIN_X + (SCREEN_WIDTH / 2) - (stageWidth / 2));
        super.setStageY(SCREEN_MIN_Y + (SCREEN_HEIGHT / 2) - (stageHeight / 2) - 50);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
