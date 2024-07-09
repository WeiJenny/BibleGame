package com.company.Sprite.RedSeaViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class Pharaoh extends Sprite {

    public Pharaoh(int x , int y){
        setHp(3);
        setPosition(x,y);
        img = new ImageIcon("pharaoh.png");
    }

    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
