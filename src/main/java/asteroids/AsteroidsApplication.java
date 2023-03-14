package asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application{

    public static void main(String[] args) {
        launch(AsteroidsApplication.class);
    }

    public static int partsCompleted() {
        // State how many parts you have completed using the return value of this method
        return 0;
    }

    @Override
    public void start(Stage win) throws Exception {
        Pane layout = new Pane();
        layout.setPrefSize(600, 400);
        
        Scene scene = new Scene(layout);
        win.setTitle("Asteroids");
        win.setScene(scene);
        win.show();
    }

}
