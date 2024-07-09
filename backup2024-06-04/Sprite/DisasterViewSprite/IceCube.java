package com.company.Sprite.DisasterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class IceCube extends Sprite {
    public IceCube(int x , int y){

        setPosition(x,y);
        img = new ImageIcon("ice.png");
    }
    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
