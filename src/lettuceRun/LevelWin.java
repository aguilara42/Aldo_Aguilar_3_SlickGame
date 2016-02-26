package lettuceRun;


import org.newdawn.slick.Color;
import org.newdawn.slick.Image;


import org.newdawn.slick.Game;


import org.newdawn.slick.GameContainer;


import org.newdawn.slick.Graphics;


import org.newdawn.slick.Input;


import org.newdawn.slick.SlickException;


import org.newdawn.slick.state.BasicGameState;


import org.newdawn.slick.state.StateBasedGame;


import org.newdawn.slick.state.transition.FadeInTransition;


import org.newdawn.slick.state.transition.FadeOutTransition;



public class LevelWin extends BasicGameState {
    private StateBasedGame game;
    public Image startimage;
    public LevelWin(int xSize, int ySize) {

    }
     
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	startimage = new Image("res/LossScreen.png");
        this.game = game;

    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	startimage.draw();
        g.setColor(Color.white);
        g.drawString("press 1 advance", 400, 320);
    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    public int getID() {
        return 4;
    }
    
    
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                LettuceRun.godLettuce = false;
                LettuceRun.player.health  = 100;
                LettuceRun.player.speed = .4f;
                LettuceRun.player.x = 96f;
                LettuceRun.player.y = 228f;
                LettuceRun.score = 0;
                GodLettuce.isvisible = true;
                //redo potions and reset cordinates of player
                game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                break;
            case Input.KEY_3:
                break;
            default:
                break;
        }
    }
}

