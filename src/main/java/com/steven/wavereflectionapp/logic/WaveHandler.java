/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steven.wavereflectionapp.logic;

import com.steven.wavereflectionapp.gui.Updatable;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 *
 * @author steven
 */
public class WaveHandler extends Timer implements ActionListener {
    
    private List<Wave> waves;
    private List<Wave> toBeAdded;
    private Updatable updatable;
    private int width;
    private int height;
    private int rate;          // rate = 50 looks like actual water >>> BLUR???
    private Random rnd;
    private int rainP;
    
    public WaveHandler(int width, int height) {
        super(1000, null);
        addActionListener(this);
        setDelay(10);
        
        this.width = width;
        this.height = height;
        
        this.waves = new ArrayList<Wave>();
        this.rate = 2;
        this.rainP = 0;
        this.rnd = new Random();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        rain(rainP);    // cause random generation of waves based on rain-type selected
        
        if (!waves.isEmpty()) {
            newWaveCheck();    // generates new wave at border intercept if any current wave has hit border
            borderCheck();     // removes wave if no longer visible within window 
            colorCheck();      // removes wave with no more amplitude/color brighter than background
            
            moveWaves();
            
            updatable.update();
        }
    }
    
    private void moveWaves() {
        for (Wave wave : waves) {
            wave.move();
        }
    }
    
    private void colorCheck() {
        Iterator<Wave> iterator = waves.iterator();
        
        while (iterator.hasNext()) {
            Wave wave = iterator.next();
            if (wave.getGreen() <= 80 || wave.getBlue() <= 140) {
                iterator.remove();
            }
        }
    }
    
    private void borderCheck() {
        Iterator<Wave> iterator = waves.iterator();
        
        while (iterator.hasNext()) {
            if (iterator.next().getDiameter() / 2 > Math.sqrt(Math.pow(width * 2, 2) 
                                                + Math.pow(height * 2, 2))) {
                iterator.remove();
            }
        }
    }
    
    private void rain(int probability) {
        if (rnd.nextInt(11) < probability) {
            int x = rnd.nextInt(width + 1);
            int y = rnd.nextInt(height + 1);
            
            waves.add(new Wave(x, y, 0, rate));
            waves.add(new Wave(x, y, -60, rate));
            waves.add(new Wave(x, y, -120, rate));
        }
    }
    
    private void newWaveCheck() {
        toBeAdded = new ArrayList<Wave>();
        
        Iterator<Wave> iterator = waves.iterator();
        
        while (iterator.hasNext()) {
            Wave wave = iterator.next();
            
            int toTop = wave.getY();
            int toBottom = (height - 130) - wave.getY(); // adjusted for apparrent border
            int toLeft = wave.getX();
            int toRight = (width - 15) - wave.getX(); // adjusted for apparent border
            
            if (wave.getDiameter() > 0 + toTop && wave.original() && !wave.getTop()) {
                wave.setTop();
                Wave newWave = new Wave(wave.getX(), 0 - toTop, wave.getDiameter(), wave.getRate());
                newWave.setNotOriginal();
                newWave.setColors(wave.getGreen(), wave.getBlue());
                toBeAdded.add(newWave);
            }
            
            if (wave.getDiameter()/ 2 > toBottom && wave.original() && !wave.getBottom()) {
                wave.setBottom();
                Wave newWave = new Wave(wave.getX(), 660 + toBottom, wave.getDiameter(), wave.getRate());
                newWave.setNotOriginal();
                newWave.setColors(wave.getGreen(), wave.getBlue());
                toBeAdded.add(newWave);
            }
            
            if (wave.getDiameter() / 2 > 0 + toLeft && wave.original() && !wave.getLeft()) {
                wave.setLeft();
                Wave newWave = new Wave(0 - toLeft, wave.getY(), wave.getDiameter(), wave.getRate());
                newWave.setNotOriginal();
                newWave.setColors(wave.getGreen(), wave.getBlue());
                toBeAdded.add(newWave);
            }
            
            if (wave.getDiameter() / 2 > toRight && wave.original() && !wave.getRight()) {
                wave.setRight();
                Wave newWave = new Wave(1285 + toRight, wave.getY(), wave.getDiameter(), wave.getRate());
                newWave.setNotOriginal();
                newWave.setColors(wave.getGreen(), wave.getBlue());
                toBeAdded.add(newWave);
            }
        }
        
        waves.addAll(toBeAdded);
        
    }
    
    public List<Wave> getWaves() {
        return waves;
    }
    
    public void addWave(Wave wave) {
        waves.add(wave);
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }
    
    public void setExpansionRate(int rate) {
        this.rate = rate;
    }
    
    public int getExpansionRate() {
        return rate;
    }
    
    public void setRain(int probability) {
        this.rainP = probability;
    }
    
    public void click(int x, int y) {        // Creates new wave-triplet at mouse-tip location 
        waves.add(new Wave(x, y, 0, rate));    // if user clicks on a location within the borders
        waves.add(new Wave(x, y, -60, rate));
        waves.add(new Wave(x ,y, -120, rate));
    }
}
