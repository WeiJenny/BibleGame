package com.company.Sprite.DisasterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;

public class Bug  extends Sprite {
    private static  Integer hp = 2;

    public Bug(int x, int y){
        setHp(2);
        setPosition(x,y);
        img = new ImageIcon("bug.png");
    }

    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
