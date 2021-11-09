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
    GreenfootSound gwailSound = new GreenfootSound("gwail_sound.mp3");     
    Keyboard keyboard;
    Marks mark;
    Percentage percentage;

    Gmail_Accepted accept;
    Gmail_Late late;
    Gmail_Typo typo;

    Reader reader = new Reader();

    SimpleTimer levelTimer = new SimpleTimer();
    SimpleTimer timeTimer = new SimpleTimer();
    SimpleTimer cursorTimer = new SimpleTimer();

    int level = 1;
    int currentAssignment = 1;
    int [] difficulty;
    int [] lettersSize;
    Label currAssign;

    int current = 0;
    Label currentTime;

    int currUnderscoreX;

    String ansKey = "";
    Cursor_On on;
    Cursor_Off off;
    int currentCursorIndex = 0;
    int cursor_X = 295;
    int cursor_Y = 350;

    int nextLevelChoice = 0;
    //Time out: 0
    //Typo: 1
    //Accepted: 2
    Label word;

    List<Underscore> storeUnderscore; 
    /**
     * Constructor for objects of class GamePlay.
     * 
     */
    public GamePlay()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        mark = new Marks();
        percentage = new Percentage();
        keyboard = new Keyboard();
        on = new Cursor_On();
        off = new Cursor_Off();
        accept = new Gmail_Accepted();
        late = new Gmail_Late();
        typo = new Gmail_Typo();
        storeUnderscore = new ArrayList<Underscore>();

        currAssign = new Label(currentAssignment, 45);

        addObject(currAssign, 446,123);
        addObject(percentage, 1145, 395);

        addObject(mark.numberOfAssignmentsFinished, 1132, 447);
        addObject(mark.numberOfAssignmentsMissed, 1132, 500);
        displayLevel();
        displayMark();

        difficulty = new int [4];
        lettersSize = new int[5];
        // seconds = new Label [difficulty.length][];

        setDifficultyOfLevel();
        setRestrictionOfLevel();
        update();
        currentTime = new Label (current, 40);
        // setTimeCounter();
        getWord(level);
        //setUnderscore();
        //print();

    }

    public void displayLevel()
    {
        currAssign.setValue(currentAssignment);
        currAssign.setFillColor(greenfoot.Color.BLACK);
    }

    public void displayMark()
    {
        addObject(mark.currentMarks, 1077, 395);
    }

    public void displayCursor()
    {
        if (cursorTimer.millisElapsed() > 300)
        {

            if(currentCursorIndex == 0)
            {
                removeObject(off);

                on = new Cursor_On();
                addObject(on, cursor_X, cursor_Y);

                currentCursorIndex = 1 - currentCursorIndex;
            }
            else if (currentCursorIndex == 1)
            {
                removeObject(on);

                off = new Cursor_Off();
                addObject(off, cursor_X, cursor_Y);

                currentCursorIndex = 1 - currentCursorIndex;
            }
            cursorTimer.mark();
        }

    }

    public void act()
    {
        getInput();
        displayCursor();
        increaseLevel();
        countTime();
        displayLevel();
        displayMark();
    }

    public void setDifficultyOfLevel()
    {
        difficulty[0] = 20;
        difficulty[1] = 15;
        difficulty[2] = 11;
        difficulty[3] = 6;

    }

    public void setRestrictionOfLevel()
    {
        lettersSize[1] = 10;
        lettersSize[2] = 13;
        lettersSize[3] = 16;
        lettersSize[4] = 20;
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
            if (current < 0)
            {
                if (level < 5 && currentAssignment <= 12)
                {
                    gwailSound.play();
                    nextLevelChoice = 0;

                    updateTime();
                    late = new Gmail_Late();
                    addObject(late, 760, 550);
                    Greenfoot.delay(200);
                    removeObject(late);
                    mark.update(this);
                    reset();

                    getWord(level);

                }
                else
                {
                    if (keyboard.currWord.equals(ansKey))
                    {
                        nextLevelChoice = 2;
                    }
                    else
                    {
                        nextLevelChoice = 1;
                    }
                    mark.update(this);
                    endGame();
                }
            }
            currentTime.setValue(current);
            currentTime.setFillColor(greenfoot.Color.RED);
            addObject(currentTime,850, 120);
            //  removeObject(currentTime);

            timeTimer.mark();

        }

    }

    public void endGame()
    {
        EndScreen end = new EndScreen(mark);
        Greenfoot.setWorld(end);
    }

    public void updateTime()
    {
        if (currentAssignment % 3 == 0)
        {
            level++;
        }
        if (level < 5)
        {
            current = difficulty[level-1]+1;
        }
    }

    public void increaseLevel()
    {
        if (levelTimer.millisElapsed()>120)
        {

            if (Greenfoot.isKeyDown("enter"))
            {
                gwailSound.play();
                /*
                removeObject(l);
                l = new Label(level, 45);
                addObject(l, 446,123);
                 */
                //System.out.println(keyboard.currWord);
                if (level < 5 && currentAssignment < 12)
                {
                    if (keyboard.currWord.equals(ansKey))
                    {
                        nextLevelChoice = 2;
                        accept = new Gmail_Accepted();
                        addObject(accept, 760, 550);
                        Greenfoot.delay(200);
                        removeObject(accept);
                    }
                    else
                    {
                        nextLevelChoice = 1;
                        typo = new Gmail_Typo();
                        addObject(typo, 760, 550);
                        Greenfoot.delay(200);
                        removeObject(typo);
                    }

                    updateTime();
                    displayLevel();
                    mark.update(this);

                    reset();

                    getWord(level);
                }
                else
                {
                    endGame();
                }
                currentAssignment++;
            }

            levelTimer.mark();
        }

    }

    public void reset()
    {

        removeObject(word);
        clearUnderscoreSign();
        keyboard.indexOfCurrentLetter = 0;
        removeObject(on);
        removeObject(off);
        clearStack();
        clearQueue();

        cursor_X = setCursorX();
        keyboard.setWordX(this);
        keyboard.currWord = "";

        keyboard.numberOfPressingTime = 0;

    }

    public void clearStack()
    {
        while (!keyboard.letters.isEmpty())
        {
            removeObject(keyboard.letters.pop());
        }

    }

    public void clearQueue()
    {
        while (!keyboard.mistakeLetter.isEmpty())
        {
            keyboard.mistakeLetter.poll();
        }

    }

    public void clearUnderscoreSign()
    {

        while(!storeUnderscore.isEmpty()) 
        {
            removeObject(storeUnderscore.remove(0));
        }
    }

    public int setLevelWordLength()
    {
        // Number of letters in each level
        switch (level)
        {
            case 1:
            return 10;

            case 2:
            return 13;

            case 3:
            return 16;

            case 4:
            return 20;

        }
        return 0;
    }

    public int setUnderscoreX()
    {
        switch (level)
        {
            case 1:
            return 303;

            case 2:
            return 270;

            case 3:
            return 237;

            case 4:
            return 187;

        }

        return 1;
    }

    public int setCursorX()
    {
        switch (level)
        {
            case 1:
            return 295;

            case 2:
            return 260;

            case 3:
            return 225;

            case 4:
            return 175;

        }
        return 0;
    }

    public void displayUnderscoreSign(Underscore l, String x)
    {
        for (int i=0; i<x.length(); i++) {
            l = new Underscore();
            storeUnderscore.add(l);
            addObject(l, currUnderscoreX, 363); 
            currUnderscoreX += 20;

        }
    }

    public void getWord(int level)
    {
        try
        {
            int MAX_LENGTH = setLevelWordLength();
            ArrayList<String> test = reader.read(level+1, 1000, MAX_LENGTH, this);
            //System.out.println("Level: " + level);
            //System.out.println(test);
            // ArrayList<String> test = newWords(MAX_LENGTH);

            String storeStr = "";
            Underscore line = new Underscore();
            currUnderscoreX = setUnderscoreX(); // set the underscore sign's beginning x-index

            for (String x : test)
            {
                displayUnderscoreSign(line, x);
                storeStr += x + " ";
                currUnderscoreX += 20; // Whitespace 
            }
            //currentWordLength = storeStr.length()-1;
            ansKey = storeStr.trim();
            word = new Label(storeStr, 24);

            word.setFillColor(greenfoot.Color.BLACK);
            addObject(word, 415, 237);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /*
    public ArrayList<String> newWords(int MAX_LENGTH)
    {

    return test;
    }
     */

    public void getInput()
    {
        //Letters
        String str = Greenfoot.getKey();

        if (str != null){
            if (str.equals("backspace"))
            {
                keyboard.remove(this);
            }

            else if (str.equals("space"))
            {
                keyboard.type(this, " ");
            }
            else if (onlyLetters(str))
            {
                keyboard.type(this, str);
            }

        }

    }
    
    public boolean onlyLetters (String str)
    {
        return !str.equals("enter") && !str.equals("caps lock") && !str.equals("shift") && !str.equals("command") && !str.equals("control") && !str.equals("alt") && !str.equals("tab") && !str.equals("escape")
            && !str.equals("up") && !str.equals("down") && !str.equals("right") && !str.equals("left");
    }
}
