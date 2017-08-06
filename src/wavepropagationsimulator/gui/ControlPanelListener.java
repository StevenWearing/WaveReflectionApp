/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wavepropagationsimulator.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import wavepropagationsimulator.logic.*;
/**
 *
 * @author steven
 */
public class ControlPanelListener implements ActionListener {
    
    private WaveHandler handler;
    private JRadioButton noRain;
    private JRadioButton lightRain;
    private JRadioButton heavyRain;
    private JTextField expansionRate;
    private JButton enterButton;
    
    public ControlPanelListener(WaveHandler handler, JRadioButton noRain,
                                JRadioButton lightRain, JRadioButton heavyRain,
                                JTextField expansionRate, JButton enterButton) {
        
        this.handler = handler;
        this.noRain = noRain;
        this.lightRain = lightRain;
        this.heavyRain = heavyRain;
        this.expansionRate = expansionRate;
        this.enterButton = enterButton;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == enterButton && !expansionRate.getText().isEmpty()) {
            handler.setExpansionRate(Integer.parseInt(expansionRate.getText()));
        }
        
        if (noRain.isSelected()) {
            handler.setRain(0);
        } else if (lightRain.isSelected()) {
            handler.setRain(1);
        } else {
            handler.setRain(4);
        }
    }
}
