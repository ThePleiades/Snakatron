/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import environment.Environment;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


/**
 *
 * @author david
 */
class SnakatronEnvironment extends Environment {

    Snake mySnakeyDude;
    Image appleLogo;

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.WHITE);
        
        appleLogo = ResourceTools.loadImageFromResource("resources/Apple_Logo_blue_Transparent.png");
        mySnakeyDude = new Snake();
                
    }

    @Override
    public void timerTaskHandler() {

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_B) {
            mySnakeyDude.setColor(Color.BLUE);
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            mySnakeyDude.setColor(Color.MAGENTA);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Snake colour = " + mySnakeyDude.getColor());
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
    private Color GREEN_TRANSPARENT = new Color(14, 111, 17, 230);
    private Color GREEN = new Color(14, 111, 17);
    private Color YELLOW = new Color(214, 255, 1);
    private Color YELLOW_TRANSPARENT = new Color(250, 255, 1, 230);
    private Color ORANGE = new Color(255, 129, 1);
    private Color ORANGE_TRANSPARENT = new Color(255, 129, 1, 128);
    private Color BLUE = new Color(1, 84, 125);
    private Color BLUE_TRANSPARENT = new Color(1, 84, 125, 128);
    private Color LIGHTGREY = new Color(225, 225, 225, 100);
    private Color WHITE = new Color(250, 250, 250);
    //private Color BLACK = new Color(214, 255, 1);
    //private Color  = new Color (
    //private Color _TRANSPARENT = new Color(214, 255, 1, 128);

    
    
    
    public void paintEnvironment(Graphics graphics) {
        graphics.setFont(COOL_FONT);
        graphics.drawString("circles are all the rage now", 480, 800);
        //PURPLE Ovals
        graphics.setColor(DEEP_PURPLE);
        graphics.fillOval(1008, 408, 84, 84);
        graphics.fillOval(100, 300, 100, 100);
        graphics.setColor(DEEP_PURPLE_TRANSPARENT);
        graphics.fillOval(1000, 400, 100, 100);
        graphics.fillOval(108, 308, 84, 84);
        //RED Ovals
        graphics.setColor(RED_TRANSPARENT);
        graphics.fillOval(200, 350, 100, 100);
        graphics.fillOval(808, 558, 84, 84);
        graphics.setColor(RED);
        graphics.fillOval(208, 358, 84, 84);
        graphics.fillOval(800, 550, 100, 100);
        //BLUE Ovals
        graphics.setColor(BLUE);
        graphics.fillOval(538, 898, 84, 84);
        graphics.fillOval(358, 638, 84, 84);
        graphics.fillOval(870, 230, 100, 100);
        graphics.setColor(BLUE_TRANSPARENT);
        graphics.fillOval(350, 630, 100, 100);
        graphics.fillOval(878, 238, 84, 84);
        graphics.fillOval(530, 890, 100, 100);
        
        graphics.fillOval(878, 238, 84, 84);
        //YELLOW Ovals
        graphics.setColor(YELLOW);
        graphics.fillOval(300, 300, 100, 100);
        graphics.fillOval(908, 108, 84, 84);
        graphics.setColor(YELLOW_TRANSPARENT);
        graphics.fillOval(308, 308, 84, 84);
        graphics.fillOval(900, 100, 100, 100);
        //ORANGE Ovals
        graphics.setColor(ORANGE);
        graphics.fillOval(508, 108, 84, 84);
        graphics.setColor(ORANGE_TRANSPARENT);
        graphics.fillOval(500, 100, 100, 100);
        //GREY and WHITE Ovals
        graphics.setColor(LIGHTGREY);
        graphics.fillOval(1250, 750, 100, 100);
        graphics.fillOval(1000, 200, 100, 100);
        graphics.fillOval(740, 400, 100, 100);
        graphics.setColor(WHITE);
        graphics.fillOval(1258, 758, 84, 84);
        graphics.fillOval(1008, 208, 84, 84);
        graphics.fillOval(748, 408, 84, 84);
        
        
        graphics.drawImage(appleLogo, 550, 250, 350, 350, this);
        
        
        
    }

}
