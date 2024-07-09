package com.company.Sprite;

import com.company.Main;
import com.company.Sprite.DisasterViewSprite.Bug;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Sprite {
    protected ImageIcon img;
    //設定相對位置
    protected Point relativePosition;
    //設定絕對位置
    protected Point absolutePosition;


    //在絕對位置畫出Sprite
    public void draw(Graphics g) {
        if (relativePosition != null) {
            img.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
        }
    }

    //overloading
    public void setPosition(Point p){
        setPosition(p.x,p.y);
    }
    public void setPosition(int x, int y){
        //圖案顯示的視覺位置 (10＊10表格的 第10,10位置)
        relativePosition = new Point(x,y);
        //實際生成圖案的實際座標(因座標起點是0,0 ;故想在第10,10的位置畫出圖案就需在(10-1),(10-1)的位置開始畫
        absolutePosition = new Point((x-1)* Main.CELL,(y-1)* Main.CELL);
    }
    public void setNullPosition(){
        relativePosition = null;
        absolutePosition = null;
    }

    public Point getRelativePosition(){
        if (relativePosition == null){
            return null;
        }else {
            return new Point(relativePosition); //不直接return relativePosition是為了避免copy by reference
        }
    }
    public abstract String overlapping(int x , int y);

}

