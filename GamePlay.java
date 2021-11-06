import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class GamePlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePlay extends World
{
    Keyboard keyboard = new Keyboard();
    SimpleTimer levelTimer = new SimpleTimer();
    SimpleTimer timeTimer = new SimpleTimer();

    int level = 1;
    int [] difficulty;
    Label l;

    int current = 0;
    Label currentTime;

    int currentWordLength = 0;

    String ansKey = "";

    /**
     * Constructor for objects of class GamePlay.
     * 
     */
    public GamePlay()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        l = new Label(level, 45);
        l.setFillColor(greenfoot.Color.BLACK);
        addObject(l, 446,123);

        difficulty = new int [5];
        // seconds = new Label [difficulty.length][];

        setDifficultyOfLevel();
        update();
        currentTime = new Label (current, 40);
        // setTimeCounter();
        getWord(level);
        //setUnderscore();
        //print();

    }

    public void act()
    {
        getInput();

        increaseLevel();
        countTime();
    }

    /*
    public void setUnderscore()
    {
    Underscore line = new Underscore();
    int currX = 200;
    for (int i=0; i<currentWordLength; i++) {
    //System.out.println(currentWordLength);
    addObject(line, currX, 310); 
    currX += 20;
    line = new Underscore();
    }
    }
     */

    public void setDifficultyOfLevel()
    {
        difficulty[0] = 20;
        difficulty[1] = 15;
        difficulty[2] = 11;
        difficulty[3] = 8;
        difficulty[4] = 5;

    }

    public void update(){
        current = difficulty[level-1]+1;
    }

    public void countTime()
    {

        //System.out.println("hi");
        if (timeTimer.millisElapsed()>1000)
        {
            //System.out.println("Hi" + timeTimer.millisElapsed());
            current--;
            currentTime.setValue(current);
            currentTime.setFillColor(greenfoot.Color.RED);
            addObject(currentTime,850, 120);
            //  removeObject(currentTime);

            timeTimer.mark();

        }

        //timeTimer.mark();
        //System.out.println("no");

        //return;
    }

    public void increaseLevel()
    {
        if (levelTimer.millisElapsed()>120)
        {
            if (Greenfoot.isKeyDown("enter"))
            {
                level++;
                removeObject(l);
                l = new Label(level, 45);
                addObject(l, 446,123);
            }
            levelTimer.mark();
        }

    }

    public int setLevelWordLength()
    {
        
        switch (level)
        {
            case 1:
            return 10;
            
            case 2:
            return 14;
            
            case 3:
            return 20;
            
            case 4:
            return 30;
  
        }
        return 0;
    }

    public int setUnderscoreX()
    {
        switch (level)
        {
            case 1:
            return 305;
            
            case 2:
            return 300;
            
            case 3:
            return 20;
            
            case 4:
            return 30;
  
        }
        
        return 1;
    }
    public void getWord(int level)
    {
        try
        {
            int MAX_LENGTH = setLevelWordLength();
            ArrayList<String> test = Reader.read(level+1, 1000, MAX_LENGTH);
            int xIndex = 0;
            String storeStr = "";
            Underscore line = new Underscore();
            int currX = setUnderscoreX();

            for (String x : test)
            {
                for (int i=0; i<x.length(); i++) {

                    line = new Underscore();
                    addObject(line, currX, 363); 
                    currX += 20;

                }
                storeStr += x + " ";
                currX += 20;
            }
            //currentWordLength = storeStr.length()-1;
            ansKey = storeStr.trim();
            Label word = new Label(storeStr, 24);
            word.setFillColor(greenfoot.Color.BLACK);
            addObject(word, 415, 237);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void getInput()
    {

        if (Greenfoot.isKeyDown("a"))
        {
            keyboard.type(this, "a");
        }
        if (Greenfoot.isKeyDown("b"))
        {
            keyboard.type(this, "b");
        }
        if (Greenfoot.isKeyDown("c"))
        {
            keyboard.type(this, "c");
        }
        if (Greenfoot.isKeyDown("d"))
        {
            keyboard.type(this, "d");
        }

        if (Greenfoot.isKeyDown("e"))
        {
            keyboard.type(this, "e");
        }
        if (Greenfoot.isKeyDown("f"))
        {
            keyboard.type(this, "f");
        }
        if (Greenfoot.isKeyDown("g"))
        {
            keyboard.type(this, "g");
        }
        if (Greenfoot.isKeyDown("h"))
        {
            keyboard.type(this, "h");
        }
        if (Greenfoot.isKeyDown("i"))
        {
            keyboard.type(this, "i");
        }
        if (Greenfoot.isKeyDown("j"))
        {
            keyboard.type(this, "j");
        }
        if (Greenfoot.isKeyDown("k"))
        {
            keyboard.type(this, "k");
        }
        if (Greenfoot.isKeyDown("l"))
        {
            keyboard.type(this, "l");
        }
        if (Greenfoot.isKeyDown("m"))
        {
            keyboard.type(this, "m");
        }
        if (Greenfoot.isKeyDown("n"))
        {
            keyboard.type(this, "n");
        }
        if (Greenfoot.isKeyDown("o"))
        {
            keyboard.type(this, "o");
        }
        if (Greenfoot.isKeyDown("p"))
        {
            keyboard.type(this, "p");
        }
        if (Greenfoot.isKeyDown("q"))
        {
            keyboard.type(this, "q");
        }
        if (Greenfoot.isKeyDown("r"))
        {
            keyboard.type(this, "r");
        }
        if (Greenfoot.isKeyDown("s"))
        {
            keyboard.type(this, "s");
        }
        if (Greenfoot.isKeyDown("t"))
        {
            keyboard.type(this, "t");
        }
        if (Greenfoot.isKeyDown("u"))
        {
            keyboard.type(this, "u");
        }
        if (Greenfoot.isKeyDown("v"))
        {
            keyboard.type(this, "v");
        }
        if (Greenfoot.isKeyDown("w"))
        {
            keyboard.type(this, "w");
        }
        if (Greenfoot.isKeyDown("x"))
        {
            keyboard.type(this, "x");
        }
        if (Greenfoot.isKeyDown("y"))
        {
            keyboard.type(this, "y");
        }
        if (Greenfoot.isKeyDown("z"))
        {
            keyboard.type(this, "z");
        }
        if (Greenfoot.isKeyDown("space"))
        {
            keyboard.type(this, " ");
        }

    }

}
