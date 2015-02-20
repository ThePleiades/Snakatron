/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import environment.Environment;
import environment.GraphicsPalette;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

 
//
/**
 *
 * @author david
 */
class SnakatronEnvironment extends Environment implements GridDrawDataIntf, LocationValidatorIntf {
    
    /*
     TO DO list
      - point of the game is to get as high a score as 
      - eat apples that make you grow .
      - eat poison, loose less points than gained by apples. (strategy could be to eat poisons and apples in such a ratio that you
        have a high score, and you have a small body by the time the tetris levels start for better chance of survival, because
        every time you get hit by tetris blocks you loose a body and half your score).
      - certain amount of apples -> get a score
      - display score
      - grow snake when eat apple
      - snake health bar
      - tetris like blocks fall from North, South, East, West and have to be avoided (if snake head or body hits block intant death)
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
    private int score = 0;
    private ArrayList <Point> apples;
    private ArrayList <Point> walls;
    
    @Override
    public void initializeEnvironment() {
        
        this.setBackground(Color.BLACK);
        this.setBackground(ResourceTools.loadImageFromResource("resources/CoolBackground1.png"));
        grid = new Grid(26, 26, 25, 25, new Point(50, 50), Color.GREEN);
        
        snake = new Snake(this);
        snake.setColor(Color.BLUE);
        snake.setDrawData(this);
        snake.getBody().add(new Point(13, 0));
        snake.grow(10);
        
        //wall making
        walls = new ArrayList <Point>();
        //corner walls
        addWallRange(new Point(0, 0), new Point(5, 0), walls); //top left
        addWallRange(new Point(0, 0), new Point(0, 5), walls);
        
        addWallRange(new Point(20, 0), new Point(25, 0), walls); //top right
        addWallRange(new Point(25, 0), new Point(25, 5), walls);
        
        addWallRange(new Point(0, 20), new Point(0, 25), walls); //bottom left
        addWallRange(new Point(0, 25), new Point(5, 25), walls);
        
        addWallRange(new Point(25, 20), new Point(25, 25), walls); //bottom right
        addWallRange(new Point(20, 25), new Point(25, 25), walls);
        
        //The BIG Central WallChunk with the Super Fruit
        addWallRange(new Point(11, 11), new Point(15, 11), walls); //first layer
        addWallRange(new Point(11, 11), new Point(11, 15), walls);
        addWallRange(new Point(15, 11), new Point(15, 15), walls);
        addWallRange(new Point(11, 15), new Point(15, 15), walls);
        
        addWallRange(new Point(12, 12), new Point(14, 12), walls); //second layer
        addWallRange(new Point(12, 12), new Point(12, 14), walls);
        addWallRange(new Point(14, 12), new Point(14, 14), walls);
        addWallRange(new Point(12, 14), new Point(14, 14), walls);
        
        //The rest of the Walls
        addWallRange(new Point(0, 9), new Point(25, 9), walls);
        addWallRange(new Point(0, 17), new Point(25, 17), walls);
        addWallRange(new Point(9, 9), new Point(9, 17), walls);
        addWallRange(new Point(0, 10), new Point(9, 10), walls);
        
        
        //private Gamestate gamestate = gamestate

        apples = new ArrayList <Point>();
        for (int i = 0; i < 1; i++) {
            apples.add(getRandomClearGridPoint());
        }
        
    }
    
    public void addWallRange(Point startLocation, Point endLocation, ArrayList<Point> walls) {
        for (int x = startLocation.x; x <= endLocation.x; x++) {
            for (int y = startLocation.y; y <= endLocation.y; y++) {
                walls.add(new Point(x, y));
            }
        }
    }
    

    @Override
    public void timerTaskHandler() {
        if (snake != null && (counter >= 4)) { //get teacher to explain
            snakeMove();
            counter = 0;
        } else {
            counter++;
        }
        
        if (transparency <= 120) {
            transparency++;
        } else {
            transparency = 0;
        }
    }
    
    private void snakeMove(){
        if (snake != null) {
            
            snake.move();
            //check if the snake is hitting ANY of the fruits
            //if yes, then
            // increase score
            // grow the snake 2 spots
            // disappear the orange
            // move the orange to random spot
            
            for (Point apple : apples) {
                if (apple.equals(snake.getHead())) {
                    System.out.println("Hit!" + score);
                    snake.grow(1);
                    score += 5;
                    apple.setLocation(getRandomClearGridPoint());
                }
            }
            
            if (snake.getHead().equals(snake.getSafeBody())) {
                System.out.println("hit self!");
            }
            
            for (Point wall : walls) {
                if (score >= 1000) {
                    if (wall.equals(snake.getHead())) {
                    System.out.println("hit wall!");
                    wall.x = -1000;
                    snake.shrink(2);
                    score += 100;
                    }
                } else {
                    if (wall.equals(snake.getHead())) {
                    System.out.println("hit wall!");
                    wall.x = -1000;
                    }
                }
                    
            }
            
        }
    }
    
    private Point getRandomGridPoint(){
        return new Point((int) (Math.random() * grid.getColumns()), 
                         (int) (Math.random() * grid.getRows()) );
    }
    
    private Point getRandomClearGridPoint(){
        boolean pointInWall = true;
        Point point;
        
        do {
            point = getRandomGridPoint();
            pointInWall = walls.contains(point);
        } while (pointInWall);
        
        return point;
    }
    

    @Override
    public void keyPressedHandler(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_B) {
            snake.setColor(BLACK);  
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            snake.setColor(DEEP_RED);
        } //movement key
          else if (e.getKeyCode() == KeyEvent.VK_W && snake.getDirection() != Direction.DOWN) { //I did this on my own!!
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_A && snake.getDirection() != Direction.RIGHT) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_S && snake.getDirection() != Direction.UP) {
            snake.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_D && snake.getDirection() != Direction.LEFT) {
            snake.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("Snake's colour = " + snake.getColor());
        } //grow key
          else if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
            snake.grow(1);
        } //Pause and Unpause
          else if (e.getKeyCode() == KeyEvent.VK_P) {
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

    
    private int transparency = 0;
    
    private Font COOL_FONT = new Font("Calibri", Font.BOLD, 36);
    //All the colors 
    private Color DEEP_PURPLE = new Color(76, 0, 153);
    private Color DEEP_PURPLE_TRANSPARENT = new Color(76, 0, 153, 130);
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
        
        if (apples != null) {
            for (Point apple : apples) {
                GraphicsPalette.drawApple(graphics, grid.getCellSystemCoordinate(apple), grid.getCellSize(), Color.ORANGE);
            }
        }
        
        graphics.setFont(COOL_FONT);
        graphics.drawString("Score: " + score, 10, 30);
        
        graphics.setColor(new Color(76, 0, 153, 255 - transparency));
        if (walls != null) {
            for (Point wall : walls) {
                graphics.fill3DRect(grid.getCellSystemCoordinate(wall).x, 
                                    grid.getCellSystemCoordinate(wall).y, grid.getCellWidth(), grid.getCellHeight(), true);     
            }
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
