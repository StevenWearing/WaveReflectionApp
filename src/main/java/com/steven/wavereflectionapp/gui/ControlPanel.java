/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.steven.wavereflectionapp.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import main.java.com.steven.wavereflectionapp.logic.*;
/**
 *
 * @author steven
 * 
 * The Control Panel, within which the  
 * user specifies the current rate of 
 * expansion along with the current 
 * probability of randomly generated waves
 */
public class ControlPanel extends JPanel {
    
    private JRadioButton noRain;     // 0% chance of droplet
    private JRadioButton lightRain;  // 10% chance of droplet
    private JRadioButton heavyRain;  // 40% chance of droplet
    private JTextField rateOfExpansion;
    private JButton enterButton;
    private WaveHandler handler;
    
    public ControlPanel(WaveHandler handler) {
        this.handler = handler;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        JPanel panel = controlPanel();
        
        add(panel);
        
        addActionListeners();
    }
    
    private JPanel controlPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(new Color(140, 140, 140));
        
        JPanel expansionInputPanel = expansionInputPanel();
        JPanel rainPanel = rainPanel();
        
        panel.add(new JLabel());
        panel.add(expansionInputPanel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(rainPanel);
        panel.add(new JLabel());
        
        return panel;
    }
    
    private JPanel expansionInputPanel() {
        JPanel expansionInputPanel = new JPanel();
        expansionInputPanel.setBackground(new Color(140, 140, 140));
        
        JLabel expansionLabel = new JLabel("Rate of expansion (e.g. < 5): ");  // > 5 becomes overly messy
        expansionLabel.setForeground(new Color(240, 240, 240));
        rateOfExpansion = new JTextField("2");
        rateOfExpansion.setPreferredSize(new Dimension(50, 30));
        enterButton = new JButton("ENTER");
        enterButton.setBackground(new Color(180, 180, 180));
        enterButton.setForeground(new Color(100, 100, 100));
        
        expansionInputPanel.add(expansionLabel);
        expansionInputPanel.add(rateOfExpansion);
        expansionInputPanel.add(enterButton);
        
        return expansionInputPanel;
    }
    
    private JPanel rainPanel() {
        JPanel rainPanel = new JPanel();
        rainPanel.setBackground(new Color(140, 140, 140));
        
        ButtonGroup rain = new ButtonGroup();
        noRain = new JRadioButton("No Rain");
        lightRain = new JRadioButton("Light Rain");
        heavyRain = new JRadioButton("Heavy Rain");
        noRain.setBackground(new Color(140, 140, 140));
        noRain.setForeground(new Color(240, 240, 240));
        lightRain.setBackground(new Color(140, 140, 140));
        lightRain.setForeground(new Color(240, 240, 240));
        heavyRain.setBackground(new Color(140, 140, 140));
        heavyRain.setForeground(new Color(240, 240, 240));
        rain.add(noRain);
        rain.add(lightRain);
        rain.add(heavyRain);
        noRain.setSelected(true);
        
        rainPanel.add(noRain);
        rainPanel.add(lightRain);
        rainPanel.add(heavyRain);
        
        return rainPanel;
    }
    
    private void addActionListeners() {
        ControlPanelListener cpl = new ControlPanelListener(handler, noRain,
                                                           lightRain, heavyRain, 
                                                   rateOfExpansion, enterButton);
        
        noRain.addActionListener(cpl);
        lightRain.addActionListener(cpl);
        heavyRain.addActionListener(cpl);
        rateOfExpansion.addActionListener(cpl);
        enterButton.addActionListener(cpl);
    }
}
