/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lettuceRun;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author aguilara42
 */
public class Orb {
    private int x,y,width,height;
    private int dmg,hitBoxX,hitBoxY;
    private boolean isVisible;
    Image orbpic;
    Shape hitbox;
    
    public Orb(int a, int b) throws SlickException{
        this.x = a;
        this.y = a;
        this.isVisible = false;
        this.orbpic = new Image ("res/Orbs/Ninja_12.png");
        this.hitbox = new Rectangle (a,b, 32, 32);
    }
    /*
    Getters and setters are a common concept in Java.
    A desgin guideline in Java, and object oriented 
    programming in general, is to encapsulate/isolate
    vales as much as possible.
    Getters - are methods used to query the value of
    instance variables.
    this.getLocationX();
    Setters - methods taht set values for instance 
    variables.
    */
}
