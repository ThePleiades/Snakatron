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

//<editor-fold defaultstate="collapsed" desc="Methods">
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        for (Point bodyPart : body) {
            Point systemCoordinate = drawData.getCellSystemCoordinate(bodyPart);
            graphics.fillOval(systemCoordinate.x, systemCoordinate.y, drawData.getCellWidth(), drawData.getCellHeight());
        }
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

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
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

}
