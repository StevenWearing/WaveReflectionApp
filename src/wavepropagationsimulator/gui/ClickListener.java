/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wavepropagationsimulator.gui;

import wavepropagationsimulator.logic.WaveHandler;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
/**
 *
 * @author steven
 */
public class ClickListener implements MouseListener {
    
    private WaveHandler handler;
    
    public ClickListener(WaveHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        handler.click(me.getX() - 5, me.getY() - 30);
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }
            
}
