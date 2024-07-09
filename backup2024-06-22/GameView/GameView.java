package com.company.GameView;

import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.company.Main;
import com.company.Sprite.*;
import com.company.Sprite.TencommandmentsView.TenCommandments;
import com.sun.jdi.Value;

import javax.swing.*;

public abstract class GameView {
    protected ArrayList<Sprite> elements;
    protected TenCommandments stone;
    protected ImageIcon img;



    protected Key key;
    protected boolean drawDoor = false;

    public TenCommandments getStone(){
        return this.stone;
    }
    public ArrayList<Sprite> getElements() {
        return this.elements;
    }
    public void setDrawDoor(boolean drawDoor) { this.drawDoor = drawDoor; }
    public boolean isDrawDoor() { return drawDoor;}
    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }


    public void drawView(Graphics g ) {
        img.paintIcon(null,g,0,0);
        g.setColor(new Color(0f,0f,0f,.3f));
        g.fillRect(0,0, Main.WIDTH,Main.HEIGHT);
        for(Sprite s : elements){
            s.draw(g);
        }
    }
}
