/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import java.awt.Point;

/**
 *
 * @author david
 */
public interface LocationValidatorIntf {
    public Point validate(Snake snake, Point location);
}
