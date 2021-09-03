package com.gabs.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author gabs
 */
public class Player extends Rectangle{
    
    public boolean right, up, left, down;
    public int speed = 4;
    
    //Player constructor
    public Player(int x, int y){
        super(x, y, 32, 32);
    }
    
    //Player Structure
    public void eventTick(){
        //Player Movement
        if(right){
            x += speed;
        }else if(left){
            x -= speed;
        }
        if(up){
            y -= speed;
        }else if(down){
            y+= speed;
        }
        
    }
    public void render(Graphics g){
        g.setColor(Color.magenta);
        g.fillRect(x, y, width, height);
    }
}
