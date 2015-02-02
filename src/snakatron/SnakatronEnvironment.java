/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

 
//
/**
 *
 * @author david
 */
class SnakatronEnvironment extends Environment implements GridDrawDataIntf, LocationValidatorIntf {
    
    /*
     TO DO list
      - eat apples that make you grow .
      - certain amount of apples -> get a score
      - display score
      - grow snake when eat apple
      - snake health bar
      - tetris like blocks fall from the top of the screen (hey, should these 
        come from other directions?!) and have to be avoided (if snake head hits block intant death)
      - hit block 
        - reduce snake health, then snake dies if 0
      -Death scenario, with insults
      - levels 
        - difficulty (number of objects, rate of growth of snake, speed obstacles move at)
      - sounds
        - eat apple
        - music (background)
        - end game
    
      - snake customization -> select from few (5) heads, body doesn't change, 
        but head does
      -strategic poison bottles to stunt you
    */
    
    
    private Snake snake;
    private Grid grid;
    private Image background;
    private Color BLUE = new Color(1, 84, 125);
    private static Color GREEN = new Color(14, 111, 17);
    private GameState gamestate = GameState.PAUSED;
    private int counter = 0;
    private Image coolBackground;
    
    @Override
    public void initializeEnvironment() {
        
        this.setBackground(Color.BLACK);
        this.setBackground(ResourceTools.loadImageFromResource("resources/CoolBackground1.png"));
        grid = new Grid(26, 26, 25, 25, new Point(50, 50), Color.BLACK);
        
        snake = new Snake(this);
        snake.setColor(Color.BLUE);
        snake.setDrawData(this);
        
        snake.getBody().add(new Point(5, 5));
        snake.getBody().add(new Point(4, 5));
        snake.getBody().add(new Point(3, 5));
        snake.getBody().add(new Point(2, 5));
        
        //private Gamestate gamestate = gamestate
        
//        background = ResourceTools.loadImageFromResource("resources/Apple_Logo_blue_Transparent.png");
 
                
    }

    @Override
    public void timerTaskHandler() {
        if (snake != null && (counter >= 3)) { //get teacher to explain
            snake.move();
            counter = 0;
        } else {
            counter++;
        }
        
        
    }

    @Override
    public void keyPressedHandler(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_B) {
            snake.setColor(BLACK);  
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            snake.setColor(DEEP_RED);
        } //movement 
          else if (e.getKeyCode() == KeyEvent.VK_W) {
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            snake.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            snake.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("Circle colour = " + snake.getColor());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            snake.getBody().add(new Point());
            snake.grow(5);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            if (snake.isPaused()){
                snake.start();
            } else {
                snake.pause();
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    
    private Font COOL_FONT = new Font("Calibri", Font.BOLD, 36);
    //All the colors 
    private Color DEEP_PURPLE = new Color(76, 0, 153);
    private Color DEEP_PURPLE_TRANSPARENT = new Color(76, 0, 153, 230);
    private Color RED = new Color(131, 7, 36);
    private Color RED_TRANSPARENT = new Color(131, 7, 36, 200);
    private Color DEEP_RED = new Color(150, 1, 1);
    private Color DEEP_RED_TRANSPARENT = new Color(150, 1, 1, 230);
    private Color GREEN_TRANSPARENT = new Color(14, 111, 17, 230);
//    private static Color GREEN = new Color(14, 111, 17);
    private Color YELLOW = new Color(214, 255, 1);
    private Color YELLOW_TRANSPARENT = new Color(250, 255, 1, 230);
    private Color ORANGE = new Color(255, 129, 1);
    private Color ORANGE_TRANSPARENT = new Color(255, 129, 1, 128);
    private Color BLUE_TRANSPARENT = new Color(1, 84, 125, 128);
    private Color LIGHTGREY = new Color(220, 220, 245, 180);
    private Color WHITE = new Color(255, 255, 255, 100);
    private Color BLACK = new Color(0, 0, 0);
    private Color WEIRD = new Color (255, 0, 80);
    //private Color WEIRD_TRANSPARENT = new Color(244, 0, 110, 128);

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        
        if (snake != null) {
            snake.draw(graphics);
        }
        graphics.drawImage(coolBackground, 100, 100, 0, 0, this);
    }

//<editor-fold defaultstate="collapsed" desc="GridDrawDataIntf Methods">
    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }
    
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }
    
    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        return grid.getCellSystemCoordinate(cellLocation);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="LocationValidatorIntf Methods">
    @Override
    public Point validate(Snake snake, Point location) {
        if (location.x >= grid.getColumns()) {
            location.x = 0;
        } else if (location.x < 0) {
            location.x = grid.getColumns()-1;
        } else if (location.y < 0) {
            location.y = grid.getRows()-1;
        } else if (location.y >= grid.getRows()) {
            location.y = 0;
        }
        
        return location;
    }
//</editor-fold>

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    
    
    
 

}
