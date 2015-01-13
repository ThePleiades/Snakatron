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

    public Snake(LocationValidatorIntf validator){
        this.validator = validator;
    }
    
    
//<editor-fold defaultstate="collapsed" desc="Methods">
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        for (Point bodyPart : getSafeBody()) {
            Point systemCoordinate = drawData.getCellSystemCoordinate(bodyPart);
            graphics.fillOval(systemCoordinate.x, systemCoordinate.y, drawData.getCellWidth(), drawData.getCellHeight());
        }
    }
    
    public void move(){
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
        body.remove(body.size()-1);

    }
    
    
    // move
    // grow
    // pause
    // kill
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private GridDrawDataIntf drawData;
    private Color color = Color.BLUE;
    private Direction direction = Direction.RIGHT;
    private LocationValidatorIntf validator;

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
//</editor-fold>

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

}
