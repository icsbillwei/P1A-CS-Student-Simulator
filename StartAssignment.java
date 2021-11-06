import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class StartAssignment here.
 * 
 * @Steven, Bill
 * @Oct 29, 2021
 */
public class StartAssignment extends Button
{
    /**
     * Act - do whatever the StartAssignment wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    GreenfootImage[] soundImage = new GreenfootImage[2]; //Stores "on" and "off" of sound figures
    static int currIndex = 1;
    public StartAssignment()
    {
        initMusicFigure();

    }

    public void act() 
    {
        SimpleTimer t = new SimpleTimer();
        checkHover();
        detectClick();

        //for testing:

       
  
    }    
    public void initMusicFigure()
    {
        soundImage[0] = new GreenfootImage("Start_Dark.png"); 
        soundImage[1] = new GreenfootImage("Start_Light.png"); 

    }

    public void detectClick()
    {
        if(Greenfoot.mouseClicked(this))
        {
            GamePlay game = new GamePlay();
            Greenfoot.setWorld(game);

        }

    }

    public void checkHover()
    {

        if (Greenfoot.mouseMoved(this))
        {
            setImage(soundImage[1]);
        }
        else if(Greenfoot.mouseMoved(null))
        {
            setImage(soundImage[0]);
        }

    }
}
