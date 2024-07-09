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

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int CELL = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROW = WIDTH/CELL;
    public static final int COLUMN = HEIGHT/CELL;
    private int level;
    Moses moses;
    public static GameView gameView;
    public Main(){
        level = 1;
        reSetGame(new DisasterView());
        addKeyListener(this);


    }
    public void reSetGame(GameView game){
        moses = new Moses(1,1);
        gameView = game ;
        repaint();
    }

    private boolean changeLevel(String result){
        if(result.equals("Next Level")){
            level ++ ;
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
        //專注視窗內的指令
        requestFocusInWindow();
    }

    @Override //設定視窗尺寸
    public Dimension getPreferredSize(){
        return new Dimension(WIDTH,HEIGHT);
    }

    public static void main(String[] args) {
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
                    for(Sprite s : gameView.getElements()){ //撈出每一個敵人的所在位置
                        //若摩西射出的子彈位置與敵人重合則消除敵人
                    if( s.getRelativePosition() != null && s.getRelativePosition().y == i && s.getRelativePosition().x == mosesPoint.x) {
                        if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone) {
                            return;
                        }
                        if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                            s.setNullPosition();
                            repaint();
                            return;
                              }
                          }
                        }
                    }
                break;
            case KeyEvent.VK_S:
                for (int i  = mosesPoint.y ; i <= ROW ; i++){
                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().y == i && s.getRelativePosition().x == mosesPoint.x) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_A:
                for (int i  = mosesPoint.x ; i > 0 ; i--){
                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == mosesPoint.y) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_D:
                for (int i  = mosesPoint.x ; i <= COLUMN ; i++){
                    for(Sprite s : gameView.getElements()){
                        if( s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == mosesPoint.y) {
                            if (s instanceof Cat || s instanceof IceCube || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Frog || s instanceof Pharaoh || s instanceof Anubis || s instanceof Bug) {
                                s.setNullPosition();
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
