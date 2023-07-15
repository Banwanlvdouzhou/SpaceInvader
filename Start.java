package SpaceInvaders;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
@SuppressWarnings({"all"})
public class Start extends JFrame {
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Start start = new Start();
    }
    public Start(){
        System.out.println("(1)简易\n(2)普通\n(3)困难\n(4)地狱？\n(5)继续上一次游戏。。。\n请输入选择关卡:");
        String key = scanner.next();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//面板  绘图区
        this.setSize(900,900);
        this.addKeyListener(mp);//监听键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到关闭窗口了！！！");
                Save.saveInfo();
                System.exit(0);
            }
        });
    }
}

