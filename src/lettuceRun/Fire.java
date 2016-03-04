package lettuceRun;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Fire {
    private int x, y, width, height;
    public int time;
    private int dmg, hitboxX, hitboxY;
    private boolean isVisible;
    Image orbpic;
    Shape HitBox;
   
    public Fire(int a,int b) throws SlickException{
        this.x = a;
        this.y = b;
        this.isVisible = false;
        this.orbpic = new Image ("res/Orbs/fire.png");
        this.HitBox = new Rectangle (a, b, 32, 32); 
        this.time = 0;
    }
    
    public void countdown(){
        this.time--;
    }

    public int getX() {
        return x;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setHitBox(Shape HitBox) {
        this.HitBox = HitBox;
    }

    public int getTime() {
        return this.time;
    }

    public Shape getHitBox() {
        return HitBox;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getHitboxX() {
        return hitboxX;
    }

    public void setHitboxX(int hitboxX) {
        this.hitboxX = hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public void setHitboxY(int hitboxY) {
        this.hitboxY = hitboxY;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getOrbpic() {
        return orbpic;
    }

    public void setOrbpic(Image orbpic) {
        this.orbpic = orbpic;
    }

    public Shape getHitbox() {
        return HitBox;
    }

    public void setHitbox(Shape hitbox) {
        this.HitBox = hitbox;
    }
    
        public void setLocation(int a, int b, Player player) {
        this.setX((int) player.x);
        this.setY((int) player.y);
        this.setHitboxX((int) (player.x + 5));
        this.setHitboxY((int) (player.y - 10));
    }

    /**
     * Getters and setters are a common concept in Java.
     * A design guideline in Java, and object oriented
     * programming in general, is to encapsulate/isolate
     * values as much as possible. 
     * Getters - are methods used to query the value of 
     * instance variables.
     * this.getLocationX();
     * Setters - methods that set values for instance 
     * variables. 
     * orb1.setLocation(Player.x, Player.y);
     */
    
}

