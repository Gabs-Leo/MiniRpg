package com.gabs.minirpg;
import java.awt.*;

public class Player extends Rectangle{
	public boolean right, up, down, left;
	public int spd = 4;
	
	public Player(int x, int y){
		super(x, y, 32, 32);
	}
	
	public void eventTick() {
		//Movimento do Player
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
		}else if(left && World.isFree(x-spd, y)) {
			x-=spd;
		}
		if(up && World.isFree(x, y-spd)) {
			y-=spd;
		}else if(down && World.isFree(x, y+spd)) {
			y+=spd;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, width, height);
	}
}
