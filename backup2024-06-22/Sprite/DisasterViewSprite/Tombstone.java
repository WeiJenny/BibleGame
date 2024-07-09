package com.company.Sprite.DisasterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class Tombstone extends Sprite {
    public Tombstone(int x , int y){
        setPosition(x,y);
        img = new ImageIcon("tombstone.png");
    }
    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
