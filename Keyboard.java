import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Keyboard here.
 * 
 * @author (your name) 
 * Oct 28, 2021
 */
public class Keyboard extends Actor
{
    /**
     * Act - do whatever the Keyboard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    SimpleTimer timer = new SimpleTimer();
    GamePlay world;
    int word_X_Index = 300;
    int word_Y_Index = 350;
    public Keyboard(){
        world = (GamePlay)getWorld();

    }

    public void act() 
    {

    }   

    public void type(GamePlay world, String str)
    {   
        if (timer.millisElapsed()>60){
            Label label = new Label(str, 25);
            label.setFillColor(greenfoot.Color.BLACK);
            world.addObject(label, word_X_Index, word_Y_Index);
            word_X_Index += 20;
        }
        timer.mark();
    }
}
