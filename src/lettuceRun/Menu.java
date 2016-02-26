package lettuceRun;


import org.newdawn.slick.Color;
import org.newdawn.slick.Image;


import org.newdawn.slick.Music;


import org.newdawn.slick.GameContainer;


import org.newdawn.slick.Graphics;


import org.newdawn.slick.Input;


import org.newdawn.slick.SlickException;


import org.newdawn.slick.state.BasicGameState;


import org.newdawn.slick.state.StateBasedGame;


import org.newdawn.slick.state.transition.FadeInTransition;


import org.newdawn.slick.state.transition.FadeOutTransition;



public class Menu extends BasicGameState {
    private StateBasedGame game;
    public Image startimage;
    public Music startmusic;
    public Menu(int xSize, int ySize) {
    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	startimage = new Image("res/Lorelei.png");
        startmusic = new Music("res/Hello.ogg");
        this.game = game;


    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setColor(Color.white);
        startimage.draw();
        startmusic.play();
    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }
    
    public int getID() {
        return 0;
    }
    
    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                game.enterState(4, new FadeOutTransition(Color.black), new FadeInTransition(Color.black)); 
                break;
            case Input.KEY_3:
                break;
            default:
                break;
        }
    }
}

