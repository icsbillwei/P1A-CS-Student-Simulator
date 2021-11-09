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
    StartAssignment start; //Pressing button "start" to start the game
    

    public TitlePage()
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        start = new StartAssignment();
        addObject(start, 785, 199); //The blue "Start Assignment" button on the screen

    }

}
