package com.company.GameView;

import com.company.Sprite.DisasterViewSprite.*;
import com.company.Sprite.Door;
import com.company.Sprite.Sprite;

import javax.swing.*;
import java.util.ArrayList;

public class DisasterView extends GameView{

    public ArrayList<Bug> getBugs() {
        return bugs;
    }

    public ArrayList<Frog> getFrogs() {
        return frogs;
    }

    public ArrayList<IceCube> getIceCubes() {
        return iceCubes;
    }

    public ArrayList<Tombstone> getTombstones() {
        return tombstones;
    }

    private ArrayList<Bug> bugs = new ArrayList<>();
    private ArrayList<Frog> frogs = new ArrayList<>();
    private ArrayList<IceCube> iceCubes = new ArrayList<>();
    private ArrayList<Tombstone> tombstones = new ArrayList<>();

    public DisasterView(){
        img = new ImageIcon("egypt.jpg");
        elements = new ArrayList<>();
        door = new Door(10,10);

        bugs.add(new Bug(10,5));
        bugs.add(new Bug(9,5));
        bugs.add(new Bug(9,4));
        bugs.add(new Bug(6,6));
        bugs.add(new Bug(6,3));
        bugs.add(new Bug(6,4));
        bugs.add(new Bug(6,5));
        frogs.add(new Frog(4,2));
        frogs.add(new Frog(6,2));
        frogs.add(new Frog(5,8));
        frogs.add(new Frog(6,4));
        frogs.add(new Frog(8,3));
        iceCubes.add(new IceCube(7,5));
        iceCubes.add(new IceCube(3,5));
        iceCubes.add(new IceCube(5,5));
        iceCubes.add(new IceCube(4,5));
        iceCubes.add(new IceCube(8,5));
        tombstones.add(new Tombstone(6,6));
        tombstones.add(new Tombstone(7,8));
        tombstones.add(new Tombstone(8,8));
        tombstones.add(new Tombstone(9,8));
        tombstones.add(new Tombstone(3,8));

        elements.add(door);
        elements.addAll(bugs);
        elements.addAll(iceCubes);
        elements.addAll(frogs);
        elements.addAll(tombstones);
    }

}
