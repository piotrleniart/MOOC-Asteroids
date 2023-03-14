/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.Random;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Piotr
 */
public class Asteroid extends Character{

    private double rotationalMov;
    

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);
        
        Random rand = new Random();
        
        super.getCharacter().setRotate(rand.nextInt(360));
        
        int accAmount = 1 + rand.nextInt(10);
        for(int i = 0; i<accAmount; i++){
            accelerate();
        }
        
        this.rotationalMov = 0.5 - rand.nextDouble();
    }
    
    @Override
    public void move(){
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotationalMov);
    }
    
    
    
}
