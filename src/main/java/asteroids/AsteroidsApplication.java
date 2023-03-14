package asteroids;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
        
        Ship ship = new Ship(150,100);
        
        
        pane.getChildren().add(ship.getShip());
        
        Scene scene = new Scene(pane);
        
        Map<KeyCode,Boolean> pressedKeys = new HashMap<>();
        
        scene.setOnKeyPressed((event) -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        
        scene.setOnKeyReleased((event) -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        win.setTitle("Asteroids");
        win.setScene(scene);
        win.show();
        
        new AnimationTimer(){

            @Override
            public void handle(long now){
                if(pressedKeys.getOrDefault(KeyCode.LEFT, false)){
                    ship.trunLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)){
                    ship.trunRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.UP, false)){
                    ship.accelerate();
                }
                
                ship.move();
            }
        }.start();
    }
    

}
