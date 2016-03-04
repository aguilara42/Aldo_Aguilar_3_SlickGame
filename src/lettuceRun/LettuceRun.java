package lettuceRun;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class LettuceRun extends BasicGameState {

    public Ninja Morse, Giavanna, Weber;
    public Box deathBox;
    public Enemy Aldo;
    public Enemy Aldo1;
    public Enemy Aldo2;
    public Enemy Aldo3;
    public GodLettuce god;
    static public Player player;
    public Orb orb1;

    public ArrayList<GodLettuce> lettuce = new ArrayList();
    public ArrayList<Ninja> ninjas = new ArrayList();
    public ArrayList<Box> boxs = new ArrayList();
    public ArrayList<Enemy> enemies = new ArrayList();
    private boolean[][] hostiles;
    private boolean iPressed;
    private boolean reset = true;
    private TiledMap grassMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static boolean godLettuce = false;
    public static int score = 0;
    public static int inv = 45;
    private static final int SIZE = 64;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;

    public LettuceRun(int xSize, int ySize) {
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        while (reset) {

            gc.setTargetFrameRate(60);

            gc.setShowFPS(false);

            grassMap = new TiledMap("res/d6.tmx");

            camera = new Camera(gc, grassMap);

            player = new Player();

            Blocked.blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

            for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

                for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

                    int tileID = grassMap.getTileId(xAxis, yAxis, 1);

                    String value = grassMap.getTileProperty(tileID,
                            "blocked", "false");

                    if ("true".equals(value)) {

                        Blocked.blocked[xAxis][yAxis] = true;

                    }

                }

            }

            hostiles = new boolean[grassMap.getWidth()][grassMap.getHeight()];

            for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
                for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                    int xBlock = (int) xAxis;
                    int yBlock = (int) yAxis;
                    if (!Blocked.blocked[xBlock][yBlock]) {
                        if (yBlock % 17 == 0 && xBlock % 15 == 0) {
                            Enemy i = new Enemy(xAxis * SIZE, yAxis * SIZE);
                            enemies.add(i);

                            hostiles[xAxis][yAxis] = true;
                        }
                    }
                }
            }

            for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
                for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                    int xBlock = (int) xAxis;
                    int yBlock = (int) yAxis;
                    if (!Blocked.blocked[xBlock][yBlock]) {
                        if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                            Box i = new Box(xAxis * SIZE, yAxis * SIZE);
                            boxs.add(i);
                            hostiles[xAxis][yAxis] = true;
                        }
                    }
                }
            }
            deathBox = new Box(128, 388);
            Aldo = new Enemy(100, 100);
            Aldo1 = new Enemy(100, 3000);
            Aldo2 = new Enemy(200, 3500);
            Aldo3 = new Enemy(400, 4000);
            orb1 = new Orb((int) player.x, (int) player.y);
            enemies.add(Aldo);
            enemies.add(Aldo1);
            enemies.add(Aldo2);
            enemies.add(Aldo3);
            boxs.add(deathBox);
            god = new GodLettuce(100, 300);
            //  god = new GodLettuce(1000, 5000);
            lettuce.add(god);
            reset = false;
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) player.x, (int) player.y);

        camera.drawMap();

        camera.translateGraphics();

        player.sprite.draw((int) player.x, (int) player.y);

        g.drawString("Health: " + player.health, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("Speed: " + (int) (player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 28);

        if (iPressed) {
            g.drawString("--------------", camera.cameraX + 10,
                    camera.cameraY + 45);
            g.drawString("Lettuce: " + (int) (score), camera.cameraX + 10,
                    camera.cameraY + 55);
            if (godLettuce) {
                g.drawString("Super Lettuce ", camera.cameraX + 10,
                        camera.cameraY + 70);
            }
        }

        for (Ninja a : ninjas) {
            if (a.isvisible) {
                a.currentImage.draw(a.x, a.y);
            }
        }

        for (Box b : boxs) {
            if (b.isvisible) {
                b.currentImage.draw(b.x, b.y);

            }
        }

        for (GodLettuce l : lettuce) {
            if (l.isvisible) {
                l.currentImage.draw(l.x, l.y);

            }
        }
        for (Enemy e : enemies) {
            if (e.isvisible) {
                e.currentanime.draw(e.Bx, e.By);
            }
        }

        if (orb1.isIsVisible()) {
            orb1.orbpic.draw(orb1.getX(), orb1.getY());
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        Input input = gc.getInput();

        float fdelta = delta * player.speed;

        player.setpdelta(fdelta);

        double rightlimit = (grassMap.getWidth() * SIZE) - (SIZE * 0.75);

        float projectedright = player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

        for (Enemy e : enemies) {
            if (orb1.HitBox.intersects(e.rect)) {
                e.isvisible = false;
            }

            e.move();
        }

        if (orb1.isIsVisible()) {
            if (orb1.getTime() > 0) {
                if (player.getDirection() == 0) {
                    orb1.setX(orb1.getX());
                    orb1.setY(orb1.getY() - 5);
                } else if (player.getDirection() == 1) {
                    orb1.setX(orb1.getX() + 5);
                    orb1.setY(orb1.getY());
                } else if (player.getDirection() == 2) {
                    orb1.setX(orb1.getX());
                    orb1.setY(orb1.getY() + 5);
                } else if (player.getDirection() == 3) {
                    orb1.setX(orb1.getX() - 5);
                    orb1.setY(orb1.getY());
                }
                orb1.HitBox.setX(orb1.getX());
                orb1.HitBox.setY(orb1.getY());
                orb1.countdown();

            } else {
                orb1.setIsVisible(false);
            }
        }

        if (input.isKeyDown(Input.KEY_UP)) {

            player.setDirection(0);

            player.sprite = player.up;

            float fdsc = (float) (fdelta - (SIZE * .15));

            if (!(isBlocked(player.x, player.y - fdelta) || isBlocked((float) (player.x + SIZE + 1.5), player.y - fdelta))) {

                player.sprite.update(delta);

                player.y -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_DOWN)) {

            player.setDirection(2);

            player.sprite = player.down;

            if (!isBlocked(player.x, player.y + SIZE + fdelta)
                    || !isBlocked(player.x + SIZE - 1, player.y + SIZE + fdelta)) {

                player.sprite.update(delta);

                player.y += fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_LEFT)) {

            player.setDirection(3);

            player.sprite = player.left;

            if (!(isBlocked(player.x - fdelta, player.y) || isBlocked(player.x
                    - fdelta, player.y + SIZE - 1))) {

                player.sprite.update(delta);

                player.x -= fdelta;

            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {

            player.setDirection(1);

            player.sprite = player.right;

            if (cangoright
                    && (!(isBlocked(player.x + SIZE + fdelta,
                            player.y) || isBlocked(player.x + SIZE + fdelta, player.y
                            + SIZE - 1)))) {

                player.sprite.update(delta);

                player.x += fdelta;

            }

        } else if (input.isKeyDown((Input.KEY_SPACE))) {
            orb1.setTime(20);
            orb1.setX((int) player.x);
            orb1.setY((int) player.y);
            orb1.HitBox.setX(player.x);
            orb1.HitBox.setY(player.y);
            orb1.setIsVisible(true);
            if (!(isBlocked(orb1.getX(), orb1.getY() - fdelta) || isBlocked((float) (orb1.getX() + SIZE + 1.5), orb1.getY() - fdelta))) {

                //orb1.setOrbpic().update(delta);
                // player.y -= fdelta;
            }
            //orb1.setHitboxX((int)player.x);
            //orb1.setHitboxY((int)player.y);
            //orb1.setIsVisible(!orb1.isIsVisible());

        } else if (input.isKeyPressed((Input.KEY_I))) {
            iPressed = !iPressed;

        }

        player.rect.setLocation(player.getplayershitboxX(),
                player.getplayershitboxY());

        if (player.health <= 0) {
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        if (score == 1 && godLettuce == true) {
            sbg.enterState(4, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        for (Enemy e : enemies) {

            if (player.rect.intersects(e.rect)) {
                if (e.isvisible) {

                    player.health -= 30;
                    e.isvisible = false;
                }

            }

        }

        for (Box d : boxs) {

            if (player.rect.intersects(d.hitbox)) {
                if (d.isvisible) {
                    player.speed += .01f;
                    player.health += 5;
                    score += 1;
                    d.isvisible = false;
                }

            }
        }

        for (GodLettuce l : lettuce) {

            if (player.rect.intersects(l.hitbox)) {
                if (l.isvisible) {
                    player.speed += .08f;
                    player.health += 10;
                    godLettuce = true;
                    l.isvisible = false;
                }

            }
        }
    }

    public int getID() {

        return 1;

    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return Blocked.blocked[xBlock][yBlock];
    }

}
