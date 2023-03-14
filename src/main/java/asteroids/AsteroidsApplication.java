package asteroids;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application{
    
    public static int WIDTH = 640;
    public static int HEIGHT = 480;
    

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
        pane.setPrefSize(WIDTH, HEIGHT);
        
        Text text = new Text(10, 20, "Points: 0");
        pane.getChildren().add(text);
        
        AtomicInteger points = new AtomicInteger();
        
        Ship ship = new Ship(WIDTH/2,HEIGHT/2);
        List<Character> projectiles = new ArrayList<>();
        List<Character> asteroids = new ArrayList<>();
        for(int i=0; i<5; i++){
            Random rand = new Random();
            Asteroid asteroid = new Asteroid(rand.nextInt(WIDTH/3),rand.nextInt(HEIGHT));
            asteroids.add(asteroid);
        }
        
        pane.getChildren().add(ship.getCharacter());
        asteroids.forEach((asteroid) -> pane.getChildren().add(asteroid.getCharacter()));
        
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
                    ship.turnLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)){
                    ship.turnRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.UP, false)){
                    ship.accelerate();
                }
                if(pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size()<3){
                    Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());    
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    
                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    
                    pane.getChildren().add(projectile.getCharacter());
                }
                
                ship.move();
                asteroids.forEach((asteroid) -> asteroid.move());
                projectiles.forEach((projectile) -> projectile.move());
                asteroids.forEach((asteroid) -> {
                    if(ship.collide(asteroid)){
                        stop();
                    }
                });
                projectiles.forEach((projectile) -> {
                    asteroids.forEach((asteroid) -> {
                        if(projectile.collide(asteroid)){
                            projectile.setAlive(false);
                            asteroid.setAlive(false);
                        }
                    });
                    if(!projectile.isAlive()){
                        text.setText("Points: " + points.addAndGet(1000));
                    }
                });
                projectiles.removeAll(removeDead(projectiles, pane));
                asteroids.removeAll(removeDead(asteroids, pane));
                
                
                if(Math.random() < 0.005){
                    Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
                    if(!asteroid.collide(ship)){
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }
                
            }
        }.start();
    }
    
    public Collection removeDead(List<Character> list, Pane pane){
        list.stream()
                .filter(obj -> !obj.isAlive())
                .forEach(obj -> pane.getChildren().remove(obj.getCharacter()));
        return list.stream().filter(item -> !item.isAlive()).collect(Collectors.toList());
    }
    

}
