package SpaceInvaders;

import java.util.Vector;

@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class Level04 {
    public static Vector<Barrier> barriers = new Vector<>();
    public static Vector<Invaders> invaders = new Vector<>();
    public static Vector<GreenInvaders> greenInvaders = new Vector<>();//这是绿色的
    public static Vector<BlueInvaders> blueInvaders = new Vector<>();//这是蓝色怪物
    public Hero hero = new Hero(550,800,15,3,1);

    public static Vector<Barrier> getBarriers() {
        return barriers;
    }

    public static void setBarriers(Vector<Barrier> barriers) {
        Level01.barriers = barriers;
    }

    public static Vector<Invaders> getInvaders() {
        return invaders;
    }

    public static void setInvaders(Vector<Invaders> invaders) {
        Level01.invaders = invaders;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }


    public void levelMap04(){
        for (int i = 0; i < 3; i++) {//可以写的更好看点
            Barrier barrier1 = new Barrier(155+225*i,780);
            Barrier barrier2 = new Barrier(175+225*i, 780);
            Barrier barrier3 = new Barrier(195+225*i, 780);
            Barrier barrier4 = new Barrier(235+225*i, 780);
            Barrier barrier5 = new Barrier(255+225*i, 780);
            Barrier barrier6 = new Barrier(275+225*i, 780);

            Barrier barrier7 = new Barrier(155+225*i,760);
            Barrier barrier8 = new Barrier(175+225*i, 760);
            Barrier barrier9 = new Barrier(195+225*i, 760);
            Barrier barrier10 = new Barrier(235+225*i, 760);
            Barrier barrier11 = new Barrier(255+225*i, 760);
            Barrier barrier12 = new Barrier(275+225*i, 760);

            Barrier barrier13 = new Barrier(175+225*i, 740);
            Barrier barrier14 = new Barrier(195+225*i, 740);

            Barrier barrier15 = new Barrier(235+225*i, 740);
            Barrier barrier16 = new Barrier(255+225*i, 740);

            Barrier barrier17 = new Barrier(195+225*i, 720);
            Barrier barrier18 = new Barrier(215+225*i, 720);
            Barrier barrier19 = new Barrier(235+225*i, 720);
            barriers.add(barrier1);barriers.add(barrier14);barriers.add(barrier15);barriers.add(barrier16);barriers.add(barrier3);barriers.add(barrier17);
            barriers.add(barrier2);barriers.add(barrier9);barriers.add(barrier10);barriers.add(barrier11);barriers.add(barrier12);barriers.add(barrier13);
            barriers.add(barrier3);barriers.add(barrier4);barriers.add(barrier5);barriers.add(barrier6);barriers.add(barrier7);barriers.add(barrier8);
            barriers.add(barrier18);barriers.add(barrier19);
        }
        for (int i = 0; i < 7; i++) {//列
            for (int j = 0;j < 2; j++) {//行
                Invaders invader = new Invaders(5*j+(100 * (i + 1)), (50 * (j + 1)), 1);
                invader.setInvaders(invaders);
                new Thread(invader).start();//启动
                invaders.add(invader);
            }
        }
        for (int i = 0; i < 7; i++) {//列
            for (int j = 0;j < 2; j++) {//行
                BlueInvaders blueInvader = new BlueInvaders(10+5*j+(100 * (i + 1)), (50 * (j + 3)), 1);
                blueInvader.setInvaders(blueInvaders);
                new Thread(blueInvader).start();//启动
                invaders.add(blueInvader);
            }
        }
        for (int i = 0; i < 7; i++) {//列
            for (int j = 0;j < 2; j++) {//行
                GreenInvaders greenInvader = new GreenInvaders((100 * (i + 1)), (50 * (j + 5)), 6);
                greenInvader.setInvaders(greenInvaders);
                new Thread(greenInvader).start();//启动
                invaders.add(greenInvader);
            }
        }
    }
}
