/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Piotr
 */
public class Ship {
    
    private Polygon ship;
    private Point2D movement;

    public Ship(int x, int y) {
        this.ship = new Polygon(-5, -5, 10, 0, -5, 5);
        this.ship.setTranslateX(x);
        this.ship.setTranslateY(y);
        
        this.movement = new Point2D(0,0);
    }
    
    public Polygon getShip(){
        return this.ship;
    }
    
    public void trunLeft(){
        this.ship.setRotate(this.ship.getRotate()-5);
    }
    
    public void trunRight(){
        this.ship.setRotate(this.ship.getRotate()+5);
    }
    
    public void move(){
        this.ship.setTranslateX(this.ship.getTranslateX() + this.movement.getX());
        this.ship.setTranslateY(this.ship.getTranslateY() + this.movement.getY());
    }
    
    public void accelerate(){
        double changeX = Math.cos(Math.toRadians(this.ship.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.ship.getRotate()));
        
        changeX *= 0.05;
        changeY *= 0.05;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
}
