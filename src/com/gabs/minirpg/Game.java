package com.gabs.minirpg;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.event.MenuKeyEvent;

public class Game extends Canvas implements Runnable, KeyListener{

	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 720;
	public static int FPS_LIMIT = 75;
	Player player;
	World world;
	
	//Constructor
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		
		player = new Player((SCREEN_WIDTH/2 - 16), (SCREEN_HEIGHT/2 - 16));
		world = new World(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public void eventTick() {
		player.eventTick();
		world.eventTick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		player.render(g);
		world.render(g);
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		//Propriedades da Janela
		frame.add(game);
		frame.setTitle("Game #1");
	
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Chama o Método Run
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		while(true) {
			eventTick();
			render();
			try {
				Thread.sleep(1000/FPS_LIMIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == MenuKeyEvent.VK_DOWN) {
			player.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == MenuKeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}

}
