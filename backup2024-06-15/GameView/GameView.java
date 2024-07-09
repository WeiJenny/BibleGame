package com.company.GameView;

import java.awt.*;

import java.security.Key;
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
    protected Door door;
    protected TenCommandments stone;
    protected ImageIcon img;


    public Door getDoor(){
        return this.door;
    }
    public TenCommandments getStone(){
        return this.stone;
    }
    public ArrayList<Sprite> getElements() {
        return this.elements;
    }


    public void drawView(Graphics g ) {
        img.paintIcon(null,g,0,0);
        g.setColor(new Color(0f,0f,0f,.3f));
        g.fillRect(0,0, Main.WIDTH,Main.HEIGHT);
        for(Sprite s : elements){
            s.draw(g);
        }
    }
}
