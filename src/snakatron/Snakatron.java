/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakatron;

import environment.ApplicationStarter;

/**
 *
 * @author david
 */
public class Snakatron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startWindow();
    }

    private static void startWindow() {
        ApplicationStarter.run("SNAKE-A-TRON", new SnakatronEnvironment());
    }
    
}
