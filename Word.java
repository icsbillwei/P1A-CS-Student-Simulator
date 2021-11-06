import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

/**
 * Store the word list
 * 
 * @Steven, Bill, Eric (your name) 
 * @Oct 28, 2021
 */
public class Word extends Actor
{
    /**
     * Act - do whatever the Word wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private Stack<String> wordList = new Stack<String>();
    
    public Word (int num){
        wordList.push("Hi");
        
    }
    
    
    
    public void act() 
    {
        // Add your action code here.
    }    
}
