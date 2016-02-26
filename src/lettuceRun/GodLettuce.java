package lettuceRun;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aguilara42
 */
public class GodLettuce {
    public int x;
	public int y;
	public static boolean isvisible = true;
	Image currentImage;
	Shape hitbox;
	Image storeBox = new Image(
			"res/god.png");

	GodLettuce(int a, int b) throws SlickException {
		this.x = a;
		this.y = b;
		this.hitbox = new Rectangle(this.x, this.y, 64, 64);// 64 is the width of the item
		this.currentImage = storeBox;

	}

    
}
