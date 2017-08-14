/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steven.wavereflectionapp;

import com.steven.wavereflectionapp.logic.WaveHandler;
import com.steven.wavereflectionapp.gui.UserInterface;
import javax.swing.SwingUtilities;
/**
 *
 * @author steven
 */
public class Main {

    public static void main(String[] args) {
        WaveHandler handler = new WaveHandler(1300, 700);
        UserInterface ui = new UserInterface(handler);
        
        try {
            SwingUtilities.invokeAndWait(ui);
        } catch (Exception e) {
            
        }
        
        handler.setUpdatable(ui.getUpdatable());
        handler.start();
    }
    
}
