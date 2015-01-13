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
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javafx.scene.shape.Circle;


/**
 *
 * @author david
 */
class SnakatronEnvironment extends Environment implements GridDrawDataIntf, LocationValidatorIntf {
    

    private Snake snake;
    private Grid grid;
    private Image background;
    private static Color GREEN = new Color(14, 111, 17);

    
    
    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.WHITE);
        
        grid = new Grid(26, 26, 25, 25, new Point(50, 50), Color.BLACK);
        
        snake = new Snake(this);
        snake.setColor(GREEN);
        snake.setDrawData(this);

        snake.getBody().add(new Point(5, 5));
        snake.getBody().add(new Point(4, 5));
        snake.getBody().add(new Point(3, 5));
        snake.getBody().add(new Point(2, 5));
        
//        background = ResourceTools.loadImageFromResource("resources/Apple_Logo_blue_Transparent.png");
 
                
    }

    @Override
    public void timerTaskHandler() {
        if (snake != null) {
            snake.move();
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B) {
            snake.setColor(BLACK);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            snake.setColor(Color.WHITE);
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            snake.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            snake.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("Circle colour = " + snake.getColor());
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    
    private Font COOL_FONT = new Font("Calibri", Font.BOLD, 36);
    //All the colors for the circles
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
    private Color BLUE = new Color(1, 84, 125);
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

    
    
    
 

}
