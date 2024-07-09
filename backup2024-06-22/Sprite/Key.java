package com.company.Sprite;

import javax.swing.*;
import java.awt.*;

public class Key extends Sprite {
    public Key(int x , int y){
        setPosition(x,y);
        ImageIcon original= new ImageIcon("key.png");
        Image originalImage = original.getImage();
        int height = 40;
        int weight = 30;
        Image resizeCross = originalImage.getScaledInstance(height,weight,Image.SCALE_SMOOTH);
        img = new ImageIcon(resizeCross);

    }

    @Override
    public String overlapping(int x, int y) {
        return null;
    }
}
