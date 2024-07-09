package com.company.GameView;

import com.company.Sprite.TencommandmentsView.TenCommandments;

import javax.swing.*;
import java.util.ArrayList;

public class TenCommandmentsView extends GameView{
    private ArrayList<TenCommandments> stones = new ArrayList<>();
    private int counts;
    public TenCommandmentsView(){
        img = new ImageIcon("mountain.jpg");
        elements = new ArrayList<>();
        stones.add(new TenCommandments(3,3));
        stones.add(new TenCommandments(4,4));
        stones.add(new TenCommandments(5,4));
        stones.add(new TenCommandments(6,6));
        stones.add(new TenCommandments(7,8));
        stones.add(new TenCommandments(9,9));
        stones.add(new TenCommandments(3,10));
        stones.add(new TenCommandments(6,10));
        stones.add(new TenCommandments(5,5));
        stones.add(new TenCommandments(2,4));
        elements.addAll(stones);
        counts = 0 ;
    }
    public ArrayList<TenCommandments> getStones() {
        return stones;
    }
    public void setCounts(int count){
        this.counts += count;
    }

    public int getCounts() {
        return counts;
    }
}
