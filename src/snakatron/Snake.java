/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Snake {
   
    {
        setBody(new ArrayList<>());
    }

    public Snake(LocationValidatorIntf validator) {
        this.validator = validator;
    }

//<editor-fold defaultstate="collapsed" desc="Methods">
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        for (Point bodyPart : getSafeBody()) {
            Point systemCoordinate = drawData.getCellSystemCoordinate(bodyPart);
            graphics.setColor(color);
            graphics.drawOval(systemCoordinate.x, systemCoordinate.y + 5, drawData.getCellWidth() - 1, drawData.getCellHeight() - 10);
            graphics.setColor(Color.RED);
            graphics.fillRect(systemCoordinate.x, systemCoordinate.y + 12, drawData.getCellWidth(), drawData.getCellHeight() - 22);
            //graphics.fillOval(systemCoordinate.x + 10, systemCoordinate.y + 10, drawData.getCellWidth(), drawData.getCellHeight() - 20);
        }
    }

    public void move() {
        if (!paused) {
            int x = getHead().x;
            int y = getHead().y;

            if (this.direction == Direction.RIGHT) {
                x++;
            } else if (this.direction == Direction.UP) {
                y--;
            } else if (this.direction == Direction.LEFT) {
                x--;
            } else if (this.direction == Direction.DOWN) {
                y++;
            }

            Point newHead = new Point(x, y);

            if (validator != null) {
                newHead = validator.validate(this, newHead);
            }

            body.add(HEAD_POSITION, newHead);
            if (growthCounter > 0) { // need to grow the snake, so dont delete the tail, but MUST reduce the growth counter...
                growthCounter--;
            } else { // dont grow, so need to delete the tail...
                body.remove(body.size() - 1);
            }
        }

    }
    
    public void grow(int length) {
        growthCounter += length;
    }

    // pause
    // kill
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private GridDrawDataIntf drawData;
    private Color color = Color.BLUE;
    private Direction direction = Direction.RIGHT;
    private LocationValidatorIntf validator;
    private int growthCounter;
    
    private boolean paused;


//    public Snake(ArrayList<Point> body)
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    private static int HEAD_POSITION = 0;

    /**
     * @return the head
     */
    public Point getHead() {
        return body.get(HEAD_POSITION);
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @return the body in a safe form
     */
    public ArrayList<Point> getSafeBody() {
        ArrayList<Point> safeBody = new ArrayList<>();
        for (Point bodySegement : body) {
            safeBody.add(bodySegement);
        }
        return safeBody;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the drawData
     */
    public GridDrawDataIntf getDrawData() {
        return drawData;
    }

    /**
     * @param drawData the drawData to set
     */
    public void setDrawData(GridDrawDataIntf drawData) {
        this.drawData = drawData;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    /**
     * stops the motion of the snake
     */
    public void pause() {
        this.paused = true;
    }
    
    /**
     * starts the snake moving...
     */
    public void start() {
        this.paused = false;
    }
    
    /**
     * @return returns true if the snake is paused, otherwise returns false
     */
    public boolean isPaused() {
        return paused;
    }
    
//</editor-fold>

}
