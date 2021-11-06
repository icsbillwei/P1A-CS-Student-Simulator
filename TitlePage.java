import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @Steven, Bill
 * @Oct 28, 2021
 */
public class TitlePage extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    Keyboard key;
    StartAssignment start; 
    

    public TitlePage()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        key = new Keyboard();
        start = new StartAssignment();
        addObject(start,785, 199);

    }

    public void act(){
        
        
        
    }
}
