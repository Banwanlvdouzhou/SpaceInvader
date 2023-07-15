package SpaceInvaders;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class MyPanel extends JPanel implements KeyListener,Runnable{
    Hero hero = null;//你
    Vector<Invaders> invadersVector = new Vector<>();//敌人
    //int invadersNum = 5;//敌人数量
    Vector<Barrier> barriers = new Vector<>();//墙
    //存放Node 对象的Vector ,用于恢复
    //Vector<Node> nodes = new Vector<>();
    Level01 level01 = null;
    Level02 level02 = null;
    Level03 level03 = null;
    Level04 level04 = null;
    Image image = null; //背景
    public static final int rightEdge = 800;
    public static final int leftEdge = 0;
    private boolean isSoundEffectPlayed = false;
    public  MyPanel(String key) {
        File file = new File(Save.getRecordFile());
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/SpaceInvaders/微信图片_20230603190532.png"));
        switch (key){
            case "1":
                Save.setAllInvader(0);
                level01 =  new Level01();
                level01.levelMap01();
                hero = level01.getHero();
                barriers = Level01.getBarriers();
                invadersVector = Level01.getInvaders();
                break;
            case "2":
                level02 = new Level02();
                level02.levelMap02();
                hero = level02.getHero();
                barriers = level02.getBarriers();
                invadersVector = Level02.getInvaders();
                break;
            case "3":
                level03 = new Level03();
                level03.levelMap03();
                hero = level03.getHero();
                barriers = level03.getBarriers();
                invadersVector = Level03.getInvaders();
                break;
            case "4":
                level04 = new Level04();
                level04.levelMap04();
                hero = level04.getHero();
                barriers = level04.getBarriers();
                invadersVector = Level04.getInvaders();
                break;
            case "5":   //继续
                if (file.exists()) {
                    invadersVector = Save.getInvadersAndallInvader();
                    barriers = Save.getBarrierandHeroLife();
                }else {
                    System.out.println("文件不存在，只能开新游戏");
                }
                Save.setBarrierVector(barriers);
                Save.setInvadersVector(invadersVector);
                hero = new Hero(450,800,15,Save.getHeroLife(),Save.getBulletNum());
                for (int i = 0; i < invadersVector.size(); i++) {
                    Invaders invader = invadersVector.get(i);
                    invader.setInvaders(invadersVector);
                    new Thread(invader).start();
                }
                break;
            default:
                System.out.println("请重新输入！！！");
                System.exit(0);
        }
        Save.setInvadersVector(invadersVector);
        Save.setBarrierVector(barriers);
    }
    //画画
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.drawImage(image,0,0,900,950,this);
        g.fill3DRect(0,0,900,950,false);
        if (hero != null  && Save.getHeroLife()>0 && hero.islive) {
            hero.drawHero(hero.getX(),hero.getY(),g);
        } else {
                gameOver(g);//输了
                if (!isSoundEffectPlayed) {
                    playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\414209__jacksonacademyashmore__death.wav");
                    isSoundEffectPlayed = true;
                }
        }
        //将Hero的子弹集合 bullets ,遍历取出绘制
        for (int i = 0; i < hero.bullets.size(); i++) {
            Bullet bullet = hero.bullets.get(i);
            if (bullet != null && bullet.isIslive() == true) {
                g.setColor(Color.orange);
                g.fillOval(bullet.getX(), bullet.getY(), 6, 15);
            } else {//如果bullets对象已经无效，就要从集合中拿掉
                hero.bullets.remove(i);
            }
        }
        //画敌人
        for (int i = 0; i < invadersVector.size(); i++) {
            //取出
            Invaders invaders = invadersVector.get(i);
            //首先判断当前是否死亡
            if (invaders.isIslive() == true) {
                try {
                    invaders.drawinvaders(invaders.getX(), invaders.getY(),g);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //画出所有的子弹
                for (int j = 0; j < invaders.getBullets().size(); j++) {
                    //取出子弹
                    Bullet bullet = invaders.getBullets().get(j);
                    //绘制
                    if (bullet.isIslive() == true) {
                        g.fillOval(bullet.getX(), bullet.getY(), 5, 10);
                    } else {
                        //islive时错误 从Vector 移除
                        invaders.getBullets().remove(bullet);
                    }
                }
            }
        }
        drawBarrier(barriers,g);
        showInfo(g);
        if (invadersVector.size() == 0 ) {
            gameWin(g);
            if (!isSoundEffectPlayed) {
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\341985__unadamlar__goodresult.wav");
                isSoundEffectPlayed = true;
            }
        }
    }
    public void showInfo(Graphics g) {
        //画出玩家的总成绩，和生命值
        g.setColor(Color.BLACK);
        Font font =new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("Score:",100,30);
        g.drawString("Life:",300,30);
        g.setColor(Color.WHITE);//一个敌人50分
        g.drawString(Save.getAllInvader()+"", 200,30);
        g.drawString(Save.getHeroLife()+"", 400,30);
    }
    public void gameOver(Graphics g)  {
        g.setColor(Color.RED);
        Font font =new Font("宋体",Font.BOLD,100);
        g.setFont(font);
        g.drawString("You Died",100,300);
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        timer.setRepeats(false); // 只触发一次
        timer.start(); // 启动计时器
    }
    public void gameWin(Graphics g)  {
        g.setColor(Color.ORANGE);
        Font font =new Font("宋体",Font.BOLD,100);
        g.setFont(font);
        if (Save.getHeroLife() == 3) {
            g.drawString("一命通关 !!!",100,300);
        } else if (Save.getHeroLife() == 2) {
            g.drawString("You Win !!!",100,300);
        } else if (Save.getHeroLife() == 1) {
            g.drawString("Stage Clear ?",100,300);
        }
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        timer.setRepeats(false); // 只触发一次
        timer.start(); // 启动计时器
    }
    public void drawBarrier(Vector<Barrier> barriers , Graphics g){
        g.setColor(Color.gray);
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (barrier.islive) {
                g.fill3DRect(barrier.getX(),barrier.getY(),barrier.getSize(),barrier.getSize(),false);
            }
        }
    }
    //判断是否击中了木墙，击中后，木墙与子弹都消失
    public void hitBarrier(Bullet bullet){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (bullet.getX() >= barrier.getX()
                    &&  bullet.getX() <= barrier.getX()+20
                    &&  bullet.getY() >= barrier.getY()
                    &&  bullet.getY() <= barrier.getY()+20) {
                bullet.setIslive(false);
                barrier.setIslive(false);
                barriers.remove(barrier);
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\411443__abbasgamez__powerup2.wav");
            }
        }
    }
    //判断敌我是否击中了barrier
    public void heroHitBarrier(){
        for (int i = 0; i < hero.bullets.size(); i++) {
            Bullet bullet = hero.bullets.get(i);
            hitBarrier(bullet);
        }
    }
    public void invaderHitBarrier(){
        for (int i = 0; i < invadersVector.size(); i++) {
            Invaders invaders = invadersVector.get(i);
            for (int j = 0; j < invaders.getBullets().size(); j++) {
                Bullet bullet = invaders.getBullets().get(j);
                hitBarrier(bullet);
            }
        }
    }
    //自己被击中
    public void hitHero(){
        //遍历所有敌人
        for (int i = 0; i < invadersVector.size(); i++) {
            Invaders invaders = invadersVector.get(i);
            for (int j = 0; j < invaders.getBullets().size(); j++) {
                //取出子弹
                Bullet bullet = invaders.getBullets().get(j);
                //判断是否击中自己
                if (hero.islive && bullet.isIslive() && bullet.getX() > hero.getX() && bullet.getX() < hero.getX()+40
                        && bullet.getY()>hero.getY() && bullet.getY() < hero.getY()+60) {
                        bullet.setIslive(false);
                        Save.heroLife();
                        playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\340003__hmmm101__pixel-sound-effect-3 (1).wav");
                }
            }
        }
    }
    public void hitInvaders(){
        //遍历取出子弹
        for (int i = 0; i < hero.bullets.size(); i++) {
            Bullet bullet = hero.bullets.get(i);
            //判断是否击中
            if (bullet !=null && bullet.isIslive()==true) {//当前我的子弹还存活
                //遍历
                for (int j = 0; j < invadersVector.size(); j++) {
                    //取出所有敌人
                    Invaders invaders = invadersVector.get(j);
                    if (bullet.getX() > invaders.getX() && bullet.getX() < invaders.getX()+60
                            && bullet.getY()>invaders.getY() && bullet.getY() < invaders.getY()+40) {
                        bullet.setIslive(false);
                        invaders.setIslive(false);
                        //击中敌人后就从集合中去除
                        invadersVector.remove(invaders);
                        Save.allInvader();
                        playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\319200__18hiltc__pixel-game-beep.wav");
                    }
                }
            }
        }
    }
    //敌人碰到墙也死一死,算了，还是墙应该更坚挺
    public void invaderTouchBarrier(){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            for (int j = 0; j < invadersVector.size(); j++) {
                Invaders invaders = invadersVector.get(j);
                //左下角
                if (invaders.getX() >= barrier.getX()
                        &&  invaders.getX()  <= barrier.getX() + 20
                        &&  invaders.getY()+40 >= barrier.getY()
                        &&  invaders.getY()+40 <= barrier.getY() + 20) {
                    barrier.setIslive(false);
                    barriers.remove(barrier);
                    playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
                }
                //右下角
                if (invaders.getX()+60 >= barrier.getX()
                        &&  invaders.getX()+60  <= barrier.getX() + 20
                        &&  invaders.getY()+40 >= barrier.getY()
                        &&  invaders.getY()+40 <= barrier.getY() + 20) {
                    barrier.setIslive(false);
                    barriers.remove(barrier);
                    playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
                }
                //左上角
                if (invaders.getX() >= barrier.getX()
                        &&  invaders.getX()  <= barrier.getX() + 20
                        &&  invaders.getY() >= barrier.getY()
                        &&  invaders.getY() <= barrier.getY() + 20) {
                    barrier.setIslive(false);
                    barriers.remove(barrier);
                    playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
                }
                //右上角
                if (invaders.getX()+60 >= barrier.getX()
                        &&  invaders.getX()+60  <= barrier.getX() + 20
                        &&  invaders.getY() >= barrier.getY()
                        &&  invaders.getY() <= barrier.getY() + 20) {
                    barrier.setIslive(false);
                    barriers.remove(barrier);
                    playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
                }
            }
        }
    }
    //敌人近身就死，蛤？
    public void invaderTouchHero(){
        for (int j = 0; j < invadersVector.size(); j++) {
            Invaders invaders = invadersVector.get(j);
            //左下角
            if (invaders.getX() >= hero.getX()
                    &&  invaders.getX()  <= hero.getX() + 40
                    &&  invaders.getY()+40 >= hero.getY()
                    &&  invaders.getY()+40 <= hero.getY() + 60) {
                hero.setIslive(false);
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
            }
            //右下角
            if (invaders.getX()+60 >= hero.getX()
                    &&  invaders.getX()+60  <= hero.getX() + 40
                    &&  invaders.getY()+40 >= hero.getY()
                    &&  invaders.getY()+40 <= hero.getY() + 60) {
                hero.setIslive(false);
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
            }
            //左上角
            if (invaders.getX() >= hero.getX()
                    &&  invaders.getX()  <= hero.getX() + 40
                    &&  invaders.getY() >= hero.getY()
                    &&  invaders.getY() <= hero.getY() + 60) {
                hero.setIslive(false);
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
            }
            //右上角
            if (invaders.getX()+60 >= hero.getX()
                    &&  invaders.getX()+60  <= hero.getX() + 40
                    &&  invaders.getY() >= hero.getY()
                    &&  invaders.getY() <= hero.getY() + 60) {
                hero.setIslive(false);
                playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\398068__happyparakeet__pixel-death.wav");
            }
        }
    }
    public void invaderTurnLeft(){
//        int theRight = -1;
//        for (Invaders invader: invadersVector) {
//            if (!(invader instanceof GreenInvaders) && invader.getX() > theRight) {
//                theRight = invader.getX();
//            }
//        }
//        if (theRight == rightEdge ) {
//            for (Invaders invader : invadersVector) {
//                if (!(invader instanceof GreenInvaders)) {
//                    invader.setY(invader.getY()+50);
//                    invader.setxSpeed(-(invader.xSpeed));
//                }
//            }
//        }
        // 添加一个变量，用于跟踪最右边的敌人
        Invaders rightMostInvader = null;
        for (Invaders invader : invadersVector) {
            if (!(rightMostInvader instanceof GreenInvaders) && rightMostInvader == null || invader.getX() > rightMostInvader.getX()) {
                rightMostInvader = invader;
            }
        }

        if (!(rightMostInvader instanceof GreenInvaders) && rightMostInvader != null && rightMostInvader.getX() >= rightEdge) {
            // 如果最右边的敌人碰到边界，所有敌人向左移动
            for (Invaders invader : invadersVector) {
                if (!(invader instanceof GreenInvaders)) {
                    invader.setY(invader.getY() + 50);
                    invader.setxSpeed(-(invader.xSpeed));
                }
            }
        }
    }

    public void invaderTurnRight(){
//        int theLeft  = 150;
//        for (Invaders invader : invadersVector) {
//            if (!(invader instanceof GreenInvaders) && invader.getX() < theLeft) {
//                theLeft = invader.getX();
//            }
//        }
//        if (theLeft == leftEdge) {
//            for (Invaders invader : invadersVector) {
//                if (!(invader instanceof GreenInvaders)) {
//                    invader.setY(invader.getY()+50);
//                    invader.setxSpeed(-(invader.xSpeed));
//                }
//            }
//        }
        // 添加一个变量，用于跟踪最左边的敌人
        Invaders leftMostInvader = null;
        for (Invaders invader : invadersVector) {
            if (!(leftMostInvader instanceof GreenInvaders) && leftMostInvader == null || invader.getX() < leftMostInvader.getX()) {
                leftMostInvader = invader;
            }
        }

        if (!(leftMostInvader instanceof GreenInvaders) && leftMostInvader != null && leftMostInvader.getX() == leftEdge) {
            // 如果最左边的敌人碰到边界，所有敌人向左移动
            for (Invaders invader : invadersVector) {
                if (!(invader instanceof GreenInvaders)) {
                    invader.setY(invader.getY() + 50);
                    invader.setxSpeed(-(invader.xSpeed));
                }
            }
        }
    }
    // 加载和播放音乐的方法
//    public void playMusic(String musicFilePath) {
//        try {
//            // 加载音乐文件
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFilePath).getAbsoluteFile());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//
//            // 播放音乐文件
//            clip.start();
//        } catch(Exception ex) {
//            System.out.println("Error playing music: " + ex.getMessage());
//        }
//    }
    // 加载和播放音效的方法
    public void playSoundEffect(String soundFilePath) {
        try {
            // 加载音效文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // 播放音效文件
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing sound effect: " + ex.getMessage());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (hero.getX()+60<860 ) {
                hero.moveRight();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (hero.getX()>0 ) {
                hero.moveLeft();
            }

        }
        //发射子弹
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            hero.shotInvader();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public synchronized void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (hero.bullet != null && hero.bullet.isIslive()) {
                for (int i = 0; i < invadersVector.size(); i++) {
                    Invaders invaders = invadersVector.get(i);
                    hitInvaders();
                }
            }
            //是否击中我
            hitHero();
            invaderHitBarrier();
            heroHitBarrier();
            invaderTouchBarrier();
            invaderTouchHero();

            invaderTurnLeft();
            invaderTurnRight();
            this.repaint();
        }
    }
}
