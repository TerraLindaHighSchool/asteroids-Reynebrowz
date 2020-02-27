import greenfoot.*;
import java.util.List;
/**
 * A proton wave that expands and destroys asteroids in its path.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class ProtonWave extends Actor
{
    /** The damage this wave will deal */
    private static final int DAMAGE = 32;
    private static final int pointsToAdd = 16;
    
    /** How many images should be used in the animation of the wave */
    private static final int NUMBER_IMAGES= 30;
    
    private int i = 0;
    /** 
     * The images of the wave. This is static so the images are not
     * recreated for every object (improves performance significantly).
     */
    private static GreenfootImage[] images;
    
    private int currentImage = 0;
    
    /**
     * Create a new proton wave.
     */
    public ProtonWave() 
    {
        initializeImages();
        setImage(images[0]);
        Greenfoot.playSound("proton.wav");
    }
    
    /** 
     * Create the images for expanding the wave.
     */
    public static void initializeImages() 
    {
        if(images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("wave.png");
            images = new GreenfootImage[NUMBER_IMAGES];
            for(int i = 0; i < NUMBER_IMAGES; i++) 
            {
                int size = (i+1) * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Act for the proton wave is: grow and check whether we hit anything.
     */
    public void act()
    { 
        checkCollision();
        grow();
    }
    /**
     * Causes the proton wave image to grow by incrementing the size of the image
     */
    private void grow()
    {
        if(currentImage >= NUMBER_IMAGES)
        {
            getWorld().removeObject(this);
        }
        else
        {
            setImage(images[currentImage]);
            currentImage++;
        }
    }
    
    private void checkCollision()
    {
        int range = getImage().getWidth() / 2;
        List<Asteroid> asteroids = getObjectsInRange(range, Asteroid.class);
        
        for(Asteroid asteroid : asteroids)
        {
            asteroid.hit(DAMAGE);
            ((Space) getWorld()).updateScore(pointsToAdd);
        }
    }
}
