package com.gabs.minirpg;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Tiles extends Rectangle{
	public Tiles(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
