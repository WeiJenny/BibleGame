package com.company.Sprite.DisasterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class Frog extends Sprite {
    public Frog(int x, int y){
        setHp(1);
        setPosition(x,y);
        img = new ImageIcon("frog.png");
    }
    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
