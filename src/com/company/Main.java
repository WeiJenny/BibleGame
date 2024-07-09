package com.company;
import com.company.GameView.DisasterView;
import com.company.GameView.GameView;
import com.company.GameView.RedSeaView;
import com.company.GameView.TenCommandmentsView;
import com.company.Sprite.*;
import com.company.Sprite.DisasterViewSprite.*;
import com.company.Sprite.RedSeaViewSprite.Anubis;
import com.company.Sprite.RedSeaViewSprite.Cat;
import com.company.Sprite.RedSeaViewSprite.Pharaoh;
import com.company.Sprite.TencommandmentsView.TenCommandments;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;


public class Main extends JPanel implements KeyListener {
    public static final int CELL = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROW = WIDTH / CELL;
    public static final int COLUMN = HEIGHT / CELL;
    private int level;
    private Moses moses;
    private Cross cross;
    private Point crossPoint = new Point(0, 0);
    private Point doorPoint = new Point(10, 10);

    public static Door door ;
    private String direction = "UP";
    private Boolean drawCross = false;
    private Timer timer;
    private java.util.Timer spriteTimer;
    private java.util.Timer gameOverTimer;
    private Boolean gameOver = false;
    private static int speed = 1250;

    public static GameView gameView;

    public Main() {
        level = 1;
        reSetGame(new DisasterView());
        addKeyListener(this);
        door = new Door(doorPoint.x , doorPoint.y);

        //--觸發攻擊按鈕丟出十字架武器--
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (drawCross) {
                    moveCross();
                    repaint();
                }
            }
        });

        //--災難朝摩西方向移動--
        if (gameOver == false) {
            spriteTimer = new java.util.Timer();
            spriteTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (gameOver == false) {
                        followSprite(moses.getRelativePosition());
                        moveSprite();
                        repaint();
                    }
                }
            }, 0, speed);
        }

        //被災難碰到則Game Over
        gameOverTimer = new java.util.Timer();
        gameOverTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { setGameOver(); }}, 0, 1); }


    //跟隨災難
    public void followSprite(Point mosePoint) {
        for (Sprite s : gameView.getElements()) { //撈出每一個敵人的所在位置
            Point sPoint = s.getRelativePosition();
            if (sPoint != null) {
                if (s instanceof Bug || s instanceof Pharaoh) {
                    if (mosePoint.x > sPoint.x && !isOverlap(s,sPoint.x+1,sPoint.y)) {
                        s.setPosition(sPoint.x + 1, sPoint.y);
                    } else if (mosePoint.x < sPoint.x && !isOverlap(s,sPoint.x-1,sPoint.y)) {
                        s.setPosition(sPoint.x - 1, sPoint.y);
                    } else if (mosePoint.y > sPoint.y && !isOverlap(s,sPoint.x,sPoint.y+1)) {
                        s.setPosition(sPoint.x, sPoint.y + 1);
                    } else if (mosePoint.y < sPoint.y && !isOverlap(s,sPoint.x,sPoint.y-1)) {
                        s.setPosition(sPoint.x, sPoint.y - 1);
                    }
                }
            }
        }
    }

    //移動災難
    public void moveSprite() {
        for (Sprite s : gameView.getElements()) { //撈出每一個敵人的所在位置
            Point sPoint = s.getRelativePosition();
            if (sPoint != null) {
                if (s instanceof Frog || s instanceof Anubis) {
                    int i = s.getMoveCount();
                    if (i == 0 && !isOverlap(s,sPoint.x+1,sPoint.y)) {
                        s.setPosition(sPoint.x + 1, sPoint.y);
                        s.setMoveCount(1);
                    } else if (i == 1 && !isOverlap(s,sPoint.x-1,sPoint.y)) {
                        s.setPosition(sPoint.x - 1, sPoint.y);
                        s.setMoveCount(2);
                    } else if (i == 2 && !isOverlap(s,sPoint.x-1,sPoint.y)) {
                        s.setPosition(sPoint.x - 1, sPoint.y);
                        s.setMoveCount(3);
                    } else if (i == 3 && !isOverlap(s,sPoint.x+1,sPoint.y)) {
                        s.setPosition(sPoint.x + 1, sPoint.y);
                        s.setMoveCount(0);
                    }
                }
            }
        }
    }

    //檢查是否移動有與其他的Sprite重疊
    public boolean isOverlap(Sprite sprite , int x , int y){
        for (Sprite otherSprite : gameView.getElements()){
            if(otherSprite != sprite){
                Point otherPoint = otherSprite.getRelativePosition();
                if(otherPoint != null && otherPoint.x == x && otherPoint.y == y){
                    return true;
                }
            }
        }
        return false;
    }

    public void setGameOver(){
        for (Sprite s : gameView.getElements()) { //撈出每一個敵人的所在位置
            Point sPoint = s.getRelativePosition();
            if (sPoint != null) {
                if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                 if( s.getRelativePosition().x == moses.getRelativePosition().x && s.getRelativePosition().y == moses.getRelativePosition().y){
                     gameOver = true;
                     gameView.setDrawDoor(false);
                     JOptionPane.showMessageDialog(this,"You Die .\n" + "Please try again." );
                     level = 1 ;
                     reSetGame(new DisasterView());
                 }
                }
            }
        }
    }


    //判定武器開始丟出
    public void startCrossMovement(String direction){
        this.direction = direction;
        drawCross = true;
        timer.start();
    }

    //判定武器要往哪個方向前進
    public void moveCross(){
        if (drawCross){
            switch (direction){
                case "UP":
                    crossPoint.y -= 1;
                break;
                case "Down":
                    crossPoint.y += 1;
                break;
                case "Left":
                    crossPoint.x -= 1;
                break;
                case "Right":
                    crossPoint.x += 1;
                break;
            }

            //丟出的武器超出畫面範圍則消失
            if(crossPoint.x < 0 || crossPoint.x > getX() ||
               crossPoint.y < 0 || crossPoint.y > getY() ){
                timer.stop();
                drawCross = false;
            }

            //丟出的武器打到怪物跟牆壁則消失
            for(Sprite s : gameView.getElements()) { //撈出每一個敵人的所在位置
                if (s.getRelativePosition() != null && s.getRelativePosition().y == crossPoint.y && s.getRelativePosition().x == crossPoint.x) {
                    if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone || s instanceof TenCommandments) {
                        timer.stop();
                        drawCross = false;
                        return;
                    }
                    if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                        timer.stop();
                        drawCross = false;
                        return;
                    }
                }
            }
        }
    }



    public void reSetGame(GameView game){
        moses = new Moses(1,1);
        gameView = game ;
        gameOver = false;
        gameView.setDrawDoor(false);
        repaint();
    }

    private boolean changeLevel(String result){
        if(result.equals("Next Level")){
            level ++ ;
            gameView.setDrawDoor(false);
            if(level == 2){
                reSetGame(new RedSeaView());
            }else if(level == 3){
                reSetGame(new TenCommandmentsView());
            }
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g){
        gameView.drawView(g);
        moses.draw(g);
        if(drawCross){cross.draw(g);}
        if(gameView.isDrawDoor()){door.draw(g);}
        //專注視窗內的指令
        requestFocusInWindow();
    }

    @Override //設定視窗尺寸
    public Dimension getPreferredSize(){
        return new Dimension(WIDTH,HEIGHT);
    }

    public static void main(String[] args) {
        // 顯示遊戲說明
        showGameInstructions();

        JFrame window = new JFrame();
        //預設關閉指令
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //套用Class
        window.setContentPane(new Main());
        //調整窗口大小
        window.pack();
        //視窗在螢幕中間
        window.setLocationRelativeTo(null);
        //不可調整視窗大小
        window.setResizable(false);
        //視窗可視化
        window.setVisible(true);
        //設定武器攻擊監聽器
        JButton button = new JButton("Start Moving Cross");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main main = new Main();
                main.startCrossMovement(main.direction);
            }

        });

    }

    private static void showGameInstructions() {
        String instructions = "歡迎來到遊戲！\n\n" +
                "請幫助摩西集齊10顆十誡石以求得上天的庇佑\n"+
                "遊戲說明：\n" +
                "1. 使用箭頭鍵移動角色。\n" +
                "2. 按 W、S、A、Ｄ可射出武器攻擊會移動的威脅。\n" +
                "3. 每種威脅的承受傷害能力不同,若未能消滅威脅,請多嘗試攻擊次數。\n" +
                "4. 障礙物不會移動但會阻擋你的去路,請繞道而行。\n" +
                "5. 需要取得鑰匙才能開啟通往下一階段的大門。\n\n" +

                "祝你玩得愉快！";

        JOptionPane.showMessageDialog(null, instructions, "遊戲說明", JOptionPane.INFORMATION_MESSAGE);

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String walk ;
        Point mosesPoint = moses.getRelativePosition();
        switch(e.getKeyCode()){
            case  KeyEvent.VK_UP:
                if(mosesPoint.y > 1) {
                    walk = moses.overlapping(mosesPoint.x,mosesPoint.y-1);
                    if(walk.equals("Die")){
                        //reset the game
                        JOptionPane.showMessageDialog(this,"You Die .\n" + "Please try again." );
                        reSetGame(new DisasterView());
                        level = 1;
                        return; //停止所有後續動作
                    }
                    if(!walk.equals("Cannot move")) {
                        mosesPoint.y -= 1;
                    }
                    if(walk.equals("Completed")){
                        JOptionPane.showMessageDialog(this,"You win!!");
                        return;
                    }
                    if(changeLevel(walk))return;
                }
                break;
            case  KeyEvent.VK_DOWN:
                if(mosesPoint.y < ROW) {
                    walk = moses.overlapping(mosesPoint.x,mosesPoint.y+1);
                    if(walk.equals("Die")){
                        //reset the game
                        JOptionPane.showMessageDialog(this,"You Die .\n" + "Please try again." );
                        reSetGame(new DisasterView());
                        level = 1;
                        return; //停止所有後續動作
                    }
                    if(!walk.equals("Cannot move")) {
                        mosesPoint.y += 1;
                    }
                    if(walk.equals("Completed")){
                        JOptionPane.showMessageDialog(this,"You win!!");
                        return;
                    }
                    if(changeLevel(walk))return;
                }
                break;
            case  KeyEvent.VK_RIGHT:
                if(mosesPoint.x < COLUMN) {
                    walk = moses.overlapping(mosesPoint.x+1,mosesPoint.y);
                    if(walk.equals("Die")){
                        //reset the game
                        JOptionPane.showMessageDialog(this,"You Die .\n" + "Please try again." );
                        reSetGame(new DisasterView());
                        level = 1;
                        return; //停止所有後續動作
                    }
                    if(!walk.equals("Cannot move")) {
                        mosesPoint.x += 1;
                    }
                    if(walk.equals("Completed")){
                        JOptionPane.showMessageDialog(this,"You win!!");
                        return;
                    }
                    if(changeLevel(walk))return;
                }
                break;
            case  KeyEvent.VK_LEFT:
                if(mosesPoint.x > 1) {
                    walk = moses.overlapping(mosesPoint.x-1 ,mosesPoint.y);
                    if(walk.equals("Die")){
                        //reset the game
                        JOptionPane.showMessageDialog(this,"You Die .\n" + "Please try again." );
                        reSetGame(new DisasterView());
                        level = 1;
                        return; //停止所有後續動作
                    }
                    if(!walk.equals("Cannot move")) {
                        mosesPoint.x -= 1;
                    }
                    if(walk.equals("Completed")){
                        JOptionPane.showMessageDialog(this,"You win!!");
                        return;
                    }
                    if(changeLevel(walk))return;
                }
                break;
            //摩西攻擊消除敵人設定
            case KeyEvent.VK_W: //向上射矛
                for (int i  = mosesPoint.y ; i > 0 ; i--){ //射出的Y不能超出邊界
                    cross = new Cross((mosesPoint.x ),i);
                    Point crossPoint = cross.getRelativePosition();
                    startCrossMovement("UP");

                    for(Sprite s : gameView.getElements()){ //撈出每一個敵人的所在位置
                        //若摩西射出的子彈位置與敵人重合則消除敵人
                    if( s.getRelativePosition() != null && s.getRelativePosition().y == i && s.getRelativePosition().x == crossPoint.x) {
                        if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone || s instanceof TenCommandments) {
                            repaint();
                            return;
                        }
                        if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                            s.attacked();
                            if(s.getAlive() == "Dead"){ s.setNullPosition(); }
                            repaint();
                            return;
                              }
                          }
                        }
                    }
                break;
            case KeyEvent.VK_S:
                for (int i  = mosesPoint.y ; i <= ROW ; i++){
                    cross = new Cross((mosesPoint.x ),i);
                    crossPoint = cross.getRelativePosition();
                    startCrossMovement("Down");

                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().y == i && s.getRelativePosition().x == crossPoint.x) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone || s instanceof TenCommandments) {
                                repaint();
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.attacked();
                                if(s.getAlive() == "Dead"){ s.setNullPosition(); }
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_A:
                for (int i  = mosesPoint.x ; i > 0 ; i--){
                    cross = new Cross(i,(mosesPoint.y));
                    crossPoint = cross.getRelativePosition();
                    startCrossMovement("Left");

                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == crossPoint.y) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone || s instanceof TenCommandments) {
                                repaint();
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.attacked();
                                if(s.getAlive() == "Dead"){ s.setNullPosition(); }
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_D:
                for (int i  = mosesPoint.x ; i <= COLUMN ; i++){
                    cross = new Cross(i,(mosesPoint.y));
                    crossPoint = cross.getRelativePosition();
                    startCrossMovement("Right");
                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == crossPoint.y) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone || s instanceof TenCommandments) {
                                repaint();
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.attacked();
                                if(s.getAlive() == "Dead"){ s.setNullPosition(); }
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
                }
        moses.setPosition(mosesPoint);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
