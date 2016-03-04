/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lettuceRun;

/**
 *
 * @author aguilara42
 */
public class ResetGame {

    public static void fullReset() {
        //Lettuce Run level
        LettuceRun.godLettuce = false;
        LettuceRun.player.health = 100;
        LettuceRun.player.speed = .4f;
        LettuceRun.player.x = 96f;
        LettuceRun.player.y = 228f;
        LettuceRun.score = 0;
        GodLettuce.isvisible = true;
                

        //Lettuce world
        LettuceWorld.godLettuce = false;
        LettuceWorld.player.health = 100;
        LettuceWorld.player.speed = .4f;
        LettuceWorld.player.x = 96f;
        LettuceWorld.player.y = 228f;
        LettuceWorld.score = 0;

    }

}
