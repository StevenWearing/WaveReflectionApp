/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wavepropagationsimulator.gui;


import wavepropagationsimulator.logic.WaveHandler;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
/**
 *
 * @author steven
 */
public class UserInterface implements Runnable {
    
    private WaveHandler handler;
    private int width;
    private int height;
    private JFrame frame;
    private DrawingBoard board;
    
    public UserInterface(WaveHandler handler) {
        this.handler = handler;
        this.width = handler.getWidth();
        this.height = handler.getHeight();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Wave Reflection Simulator");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        board = new DrawingBoard(handler);
        container.add(board);
        
        ControlPanel cPanel = new ControlPanel(handler);
        container.add(cPanel, BorderLayout.SOUTH);
        
        ClickListener cL = new ClickListener(handler);
        frame.addMouseListener(cL);
    }
    
    public Updatable getUpdatable() {
        return board;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
