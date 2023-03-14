package asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
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
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);
        
        Polygon ship = new Polygon(-5,-5,10,0,-5,5);
        ship.setTranslateX(300);
        ship.setTranslateY(200);
        
        pane.getChildren().add(ship);
        
        Scene scene = new Scene(pane);
        win.setTitle("Asteroids");
        win.setScene(scene);
        win.show();
    }

}
