package com.company.Sprite.TencommandmentsView;

import com.company.Sprite.Sprite;

import javax.swing.*;
import java.util.ArrayList;

public class TenCommandments extends Sprite {
    public TenCommandments(int x, int y){
        setPosition(x,y);
        img = new ImageIcon("stone.png");
    }

    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
