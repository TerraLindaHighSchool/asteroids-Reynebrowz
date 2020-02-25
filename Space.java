import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 3;    
    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addAsteroids(startAsteroids);
        paintStars(250);
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }
    /**
     *  Creates stars in the world. Only for cosmetics and just only adds to the background.
     */
    private void paintStars(int count)
    {   
        GreenfootImage background = getBackground();
        for(int i = 0; i < count; i++)
        {   
            // gives random coordinates of the stars
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            // gives a random size of the stars
            int starSize = (Greenfoot.getRandomNumber(2)+2);
            
            // creates random colors of the stars
            int red = (255 - Greenfoot.getRandomNumber(125));
            int green = (255 -Greenfoot.getRandomNumber(125));
            int blue = (255 - Greenfoot.getRandomNumber(125));
            int alpha = Greenfoot.getRandomNumber(255);
            int whiteBrightness = Greenfoot.getRandomNumber(256);
            
            background.setColor(new Color(red,green,blue,255));            
            background.fillOval(x, y, 3, 3);
            background.setColor(new Color(255,255,255,alpha));
            background.fillOval(x, y, starSize, starSize);
        }
    }
    
        public void updateScore(int addToScore)
    {
        scoreCounter.add(addToScore);
    }
    
    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int currentScore = scoreCounter.getValue();
        addObject(new ScoreBoard(currentScore),x ,y);
    }

}