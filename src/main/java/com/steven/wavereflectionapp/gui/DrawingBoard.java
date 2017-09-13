/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.steven.wavereflectionapp.gui;

import main.java.com.steven.wavereflectionapp.logic.WaveHandler;
import main.java.com.steven.wavereflectionapp.logic.Wave;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;
import java.awt.Color;
/**
 *
 * @author steven
 */
public class DrawingBoard extends JPanel implements Updatable {
    
    private List<Wave> waves;
    
    public DrawingBoard(WaveHandler handler) {
        this.waves = handler.getWaves();
        
        super.setBackground(new Color(0, 80, 140));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 80, 140));
        
        if (!waves.isEmpty()) {
            
            for (Wave wave : waves) {
                
                if (wave.getDiameter() < 0) {  // Delays the painting of the initially negative diameter
                    continue;                  // waves, allowing 3 waves to emerge from one click
                }
                
                if (wave.getGreen() >= 80 || wave.getBlue() >= 140) {          // Only decrease colour if lighter than
                    g.setColor(new Color(0, wave.getGreen(), wave.getBlue()));  // the backgound colour
                    wave.decreaseColors();
                }
                
                g.drawOval(wave.getX() - wave.getDiameter() / 2, // Adjustment so wave expands in
                           wave.getY() - wave.getDiameter() / 2,  // all directions equally
                           wave.getDiameter(),wave.getDiameter());
            }
        }
    }
    
    @Override
    public void update() {
        repaint();
    }
}
