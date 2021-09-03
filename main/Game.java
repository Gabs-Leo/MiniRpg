package com.gabs.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author gabs
 */
public class Game extends Canvas implements Runnable, KeyListener{
    //Screen Size
    public static int WIDTH = 1600, HEIGHT = 900;
    
    //TODO Comment the line above and uncomment the 2 lines below to get responsive full screen
    /*Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public int WIDTH = (int)size.getWidth(), HEIGHT = (int)size.getHeight();*/
    
    //Screen FPS and SCALE
    public static int SCALE = 1;
    public static int FPSLIMIT = 60;
    
    
    //Player Object
    public Player player;
    
    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        player = new Player(WIDTH/2 - 10, HEIGHT/2- 10);
    }
    
    public static void main(String[] args) {
        //Main class object for Rhread and JFrame
        Game main = new Game();
        JFrame frame = new JFrame();
        
        //JFrame Configs
        frame.add(main);
        frame.setTitle("MyGame!");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //Starting new Thread
        new Thread(main).start();
    }

    //Game Looping for logic
    public void eventTick(){
        player.eventTick();
    }
    //Game render
    public void render(){
        
        //Buffer strategy for graphic otimization
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        //Drawing Graphics
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        //Black Background [X, Y, Xsize, YYsize]
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        //Renderizando Player
        player.render(g);
        
        //Show on Screen
        bs.show();
    }
    
    //Game Thread
    @Override
    public void run() {
        //Looping for tick and render
        while(true){
            eventTick();
            render();
            
            try {
                //Limit fps for 60 for try and catch
                Thread.sleep(1000/FPSLIMIT);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("FPS LIMIT Error!");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            player.right = true;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            player.right = false;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = false;
        }
    }
}
