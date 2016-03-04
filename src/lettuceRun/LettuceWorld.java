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

public class LettuceWorld extends BasicGameState {

    public Ninja Morse, Giavanna, Weber;
    public Box deathBox;
    public TrapLettuce trap;
    public EnemyTwo Aldo;
    public EnemyTwo Aldo1;
    public EnemyTwo Aldo2;
    public EnemyTwo Aldo3;
    public GodLettuce god;
    static public Player player;
    public Fire fireBall;
    public OrbPickUp orbObject;

    public ArrayList<TrapLettuce> traps = new ArrayList();
    public ArrayList<OrbPickUp> orbUp = new ArrayList();
    public ArrayList<GodLettuce> lettuce = new ArrayList();
    public ArrayList<Ninja> ninjas = new ArrayList();
    public ArrayList<Box> boxs = new ArrayList();
    public ArrayList<EnemyTwo> enemies = new ArrayList();
    private boolean[][] hostiles;
    private boolean hasFire;
    private boolean iPressed;
    private boolean reset = true;
    private TiledMap worldMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static boolean godLettuce = false;
    public static int score = 0;
    public static int inv = 45;
    public static int trapsHit = 0;
    private static final int SIZE = 64;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;

    public LettuceWorld(int xSize, int ySize) {
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {
        if (reset) {
            gc.setTargetFrameRate(60);

            gc.setShowFPS(false);

            worldMap = new TiledMap("res/d4.tmx");

            camera = new Camera(gc, worldMap);

            player = new Player();

            BlockedTwo.blocked = new boolean[worldMap.getWidth()][worldMap.getHeight()];

            for (int xAxis = 0; xAxis < worldMap.getWidth(); xAxis++) {

                for (int yAxis = 0; yAxis < worldMap.getHeight(); yAxis++) {

                    int tileID = worldMap.getTileId(xAxis, yAxis, 1);

                    String value = worldMap.getTileProperty(tileID,
                            "blocked", "false");

                    if ("true".equals(value)) {

                        BlockedTwo.blocked[xAxis][yAxis] = true;

                    }

                }

            }

            hostiles = new boolean[worldMap.getWidth()][worldMap.getHeight()];

            for (int xAxis = 0; xAxis < worldMap.getWidth(); xAxis++) {
                for (int yAxis = 0; yAxis < worldMap.getHeight(); yAxis++) {
                    int xBlock = (int) xAxis;
                    int yBlock = (int) yAxis;
                    if (!BlockedTwo.blocked[xBlock][yBlock]) {
                        if (yBlock % 17 == 0 && xBlock % 15 == 0) {
                            EnemyTwo i = new EnemyTwo(xAxis * SIZE, yAxis * SIZE);
                            enemies.add(i);

                            hostiles[xAxis][yAxis] = true;
                        }
                    }
                }
            }

            for (int xAxis = 0; xAxis < worldMap.getWidth(); xAxis++) {
                for (int yAxis = 0; yAxis < worldMap.getHeight(); yAxis++) {
                    int xBlock = (int) xAxis;
                    int yBlock = (int) yAxis;
                    if (!BlockedTwo.blocked[xBlock][yBlock]) {
                        if (yBlock % 7 == 0 && xBlock % 15 == 0) {
                            Box i = new Box(xAxis * SIZE, yAxis * SIZE);
                            boxs.add(i);
                            hostiles[xAxis][yAxis] = true;
                        }
                    }
                }
            }
            for (int xAxis = 0; xAxis < worldMap.getWidth(); xAxis++) {
                for (int yAxis = 0; yAxis < worldMap.getHeight(); yAxis++) {
                    int xBlock = (int) xAxis;
                    int yBlock = (int) yAxis;
                    if (!BlockedTwo.blocked[xBlock][yBlock]) {
                        if (yBlock % 13 == 0 && xBlock % 7 == 0) {
                            TrapLettuce i = new TrapLettuce(xAxis * SIZE, yAxis * SIZE);
                            traps.add(i);
                            hostiles[xAxis][yAxis] = true;
                        }
                    }
                }
            }

            deathBox = new Box(128, 388);
            Aldo1 = new EnemyTwo(100, 3000);
            Aldo2 = new EnemyTwo(200, 3500);
            Aldo3 = new EnemyTwo(400, 4000);
            orbObject = new OrbPickUp(128, 388);
            fireBall = new Fire((int) player.x, (int) player.y);;
            enemies.add(Aldo1);
            enemies.add(Aldo2);
            enemies.add(Aldo3);
            boxs.add(deathBox);
            orbUp.add(orbObject);
            god = new GodLettuce(1000, 5000);
            lettuce.add(god);
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
                    camera.cameraY + inv);
            g.drawString("Lettuce: " + (int) (score), camera.cameraX + 10,
                    camera.cameraY + inv + 15);
            if (godLettuce) {
                g.drawString("Super Lettuce ", camera.cameraX + 10,
                        camera.cameraY + (inv + 15));
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

        for (TrapLettuce tp : traps) {
            if (tp.isvisible) {
                tp.currentImage.draw(tp.x, tp.y);
            }
        }

        for (OrbPickUp op : orbUp) {
            if (op.isvisible) {
                op.currentImage.draw(op.x, op.y);
            }
        }

        for (GodLettuce l : lettuce) {
            if (l.isvisible) {
                l.currentImage.draw(l.x, l.y);

            }
        }
        for (EnemyTwo e : enemies) {
            if (e.isvisible) {
                e.currentanime.draw(e.Bx, e.By);
            }
        }
        if (fireBall.isIsVisible()) {
            fireBall.orbpic.draw(fireBall.getX(), fireBall.getY());
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {

        Input input = gc.getInput();

        float fdelta = delta * player.speed;

        player.setpdelta(fdelta);

        double rightlimit = (worldMap.getWidth() * SIZE) - (SIZE * 0.75);

        float projectedright = player.x + fdelta + SIZE;

        boolean cangoright = projectedright < rightlimit;

        for (EnemyTwo e : enemies) {
            if (fireBall.HitBox.intersects(e.rect)) {
                e.isvisible = false;
            }

            e.move();
        }

        if (fireBall.isIsVisible()) {
            if (fireBall.getTime() > 0) {
                if (player.getDirection() == 0) {
                    fireBall.setX(fireBall.getX());
                    fireBall.setY(fireBall.getY() - 5);
                } else if (player.getDirection() == 1) {
                    fireBall.setX(fireBall.getX() + 5);
                    fireBall.setY(fireBall.getY());
                } else if (player.getDirection() == 2) {
                    fireBall.setX(fireBall.getX());
                    fireBall.setY(fireBall.getY() + 5);
                } else if (player.getDirection() == 3) {
                    fireBall.setX(fireBall.getX() - 5);
                    fireBall.setY(fireBall.getY());
                }
                fireBall.HitBox.setX(fireBall.getX());
                fireBall.HitBox.setY(fireBall.getY());
                fireBall.countdown();

            } else {
                fireBall.setIsVisible(false);
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

        } else if (input.isKeyPressed((Input.KEY_I))) {
            iPressed = !iPressed;
        }

        if (hasFire) {
            if (input.isKeyDown((Input.KEY_SPACE))) {
                fireBall.setTime(20);
                fireBall.setX((int) player.x);
                fireBall.setY((int) player.y);
                fireBall.HitBox.setX(player.x);
                fireBall.HitBox.setY(player.y);
                fireBall.setIsVisible(true);
                if (!(isBlocked(fireBall.getX(), fireBall.getY() - fdelta) || isBlocked((float) (fireBall.getX() + SIZE + 1.5), fireBall.getY() - fdelta))) {

                    //orb1.setOrbpic().update(delta);
                    // player.y -= fdelta;
                }
                //orb1.setHitboxX((int)player.x);
                //orb1.setHitboxY((int)player.y);
                //orb1.setIsVisible(!orb1.isIsVisible());

            }
        }

        player.rect.setLocation(player.getplayershitboxX(),
                player.getplayershitboxY());

        if (player.health <= 0) {
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        if (score == 10 && godLettuce == true) {
            sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        for (EnemyTwo e : enemies) {

            if (player.rect.intersects(e.rect)) {
                if (e.isvisible) {

                    player.health -= 30;
                    e.isvisible = false;
                }

            }

        }

        for (OrbPickUp op : orbUp) {

            if (player.rect.intersects(op.hitbox)) {
                if (op.isvisible) {
                    hasFire = true;
                    op.isvisible = false;
                }

            }
        }

        for (TrapLettuce tp : traps) {

            if (player.rect.intersects(tp.hitbox)) {
                if (tp.isvisible) {
                    trapsHit++;
                    player.health -= 30;
                    tp.isvisible = false;
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

        return 5;

    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return BlockedTwo.blocked[xBlock][yBlock];
    }

}
