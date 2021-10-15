package com.gabs.minirpg;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;

public class World {
	static ArrayList<Tiles> tiles = new ArrayList<Tiles>();
	
	public World(int worldWidth, int worldHeight) {
		//Gerando blocos e posicionando-os um ao lado do outro
		for(int i = 0; i < worldWidth/32; i++) {
			tiles.add(new Tiles(i*32, 0));
		}
		for(int i = 0; i < worldWidth/32; i++) {
			tiles.add(new Tiles(i*32, worldHeight - 32));
		}
		
		for(int i = 0; i < worldHeight/32; i++) {
			tiles.add(new Tiles(0, i*32));
		}
		for(int i = 0; i < worldHeight/32; i++) {
			tiles.add(new Tiles(worldWidth - 32, 32*i));
		}
	}
	
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < tiles.size(); i++){
			Tiles tileAtual = tiles.get(i);
			if(tileAtual.intersects(new Rectangle(x, y, 32, 32))){
				return false;
			}
		}
		return true;
	}
	
	public void eventTick() {
		
	}
	
	public void render(Graphics g) {
		tiles.forEach((i) -> {i.render(g);});
	}
}
