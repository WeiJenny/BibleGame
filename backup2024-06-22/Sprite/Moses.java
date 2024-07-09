package com.company.Sprite;

import com.company.GameView.DisasterView;
import com.company.GameView.GameView;
import com.company.GameView.RedSeaView;
import com.company.GameView.TenCommandmentsView;
import com.company.Main;
import com.company.Sprite.DisasterViewSprite.*;
import com.company.Sprite.RedSeaViewSprite.*;
import com.company.Sprite.TencommandmentsView.TenCommandments;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Moses extends Sprite  {

    //設定摩西的起始位置
    public Moses(int x , int y){
        setPosition(x,y);
        img = new ImageIcon("Moses.png");

    }

    @Override
    public String overlapping(int x, int y) {
        if(Main.gameView instanceof DisasterView) {
            //Check for frogs and bugs
            ArrayList<Frog> frogs = ((DisasterView) Main.gameView).getFrogs();
            ArrayList<Bug> bugs = ((DisasterView) Main.gameView).getBugs();

            //拿到鑰匙畫出門
            Key key = Main.gameView.getKey();
            if (key.getRelativePosition() != null && key.getRelativePosition().x == x && key.getRelativePosition().y == y) {
                key.setNullPosition();
                Main.gameView.setDrawDoor(true);
            }

            for (Frog f : frogs) {
                if (f.relativePosition != null && x == f.getRelativePosition().x && y == f.getRelativePosition().y) {
                    return "Die";
                }
                for (Bug b : bugs) {
                    if (b.relativePosition != null && x == b.getRelativePosition().x && y == b.getRelativePosition().y) {
                        return "Die";
                    }
                }
                ArrayList<IceCube> iceCubes = ((DisasterView) Main.gameView).getIceCubes();
                ArrayList<Tombstone> tombstones = ((DisasterView) Main.gameView).getTombstones();
                for (IceCube i : iceCubes) {
                    if (i.relativePosition != null && x == i.getRelativePosition().x && y == i.getRelativePosition().y) {
                        return "Cannot move";
                    }
                }
                for (Tombstone t : tombstones) {
                    if (t.relativePosition != null && x == t.getRelativePosition().x && y == t.getRelativePosition().y) {
                        return "Cannot move";
                    }
                }
            }
            //Check for Door
            Door door = Main.door;
            if (door != null && x == door.getRelativePosition().x && y == door.getRelativePosition().y && Main.gameView.isDrawDoor()) {
                return "Next Level";
            }
        }else if(Main.gameView instanceof RedSeaView) {
                //Check for anubis and cat and pharaoh
                ArrayList<Anubis> anubis = ((RedSeaView) Main.gameView).getAnubis();
                ArrayList<Cat> cats = ((RedSeaView) Main.gameView).getCats();
                ArrayList<Pharaoh> pharaohs  = ((RedSeaView) Main.gameView).getPharaohs();

                //拿到鑰匙畫出門
                Key key = Main.gameView.getKey();
                if (key.getRelativePosition() != null && key.getRelativePosition().x == x && key.getRelativePosition().y == y) {
                    key.setNullPosition();
                    Main.gameView.setDrawDoor(true);
                }
                for (Anubis a : anubis) {
                    if (a.relativePosition != null && x == a.getRelativePosition().x && y == a.getRelativePosition().y) {
                        return "Die";
                    }
                for (Pharaoh p : pharaohs) {
                    if (p.relativePosition != null && x == p.getRelativePosition().x && y == p.getRelativePosition().y) {
                        return "Die";
                        }
                for (Cat c : cats) {
                    if (c.relativePosition != null && x == c.getRelativePosition().x && y == c.getRelativePosition().y) {
                        return "Cannot move";
                            }
                        }
                    }
                }
                //Check for Door
                Door door = Main.door;
                if (door != null && x == door.getRelativePosition().x && y == door.getRelativePosition().y && Main.gameView.isDrawDoor()) {
                    return "Next Level";
                }
            }else if(Main.gameView instanceof TenCommandmentsView) {
                ArrayList<TenCommandments> stones =((TenCommandmentsView) Main.gameView).getStones();
                for( TenCommandments stone : stones) {
                    if (stone.getRelativePosition() != null && stone.getRelativePosition().x == x && stone.getRelativePosition().y == y) {
                       stone.setNullPosition();
                        ((TenCommandmentsView) Main.gameView).setCounts(1);
                       if(((TenCommandmentsView) Main.gameView).getCounts() == 10){
                           return "Completed" ;
                       }else {return "None";}
                    }
                }
        }
        return "None";
    }
}
