
/**
 * Write a description of class Pac here.
 * Branches off of Character.
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Pac implements Character
{
    private char direction;
    private int points;
    private int lives;
    private boolean canShoot;
    private final int speed;
    private final boolean canGetPoints;
    private final int worth;
    private int ammo;
    
    public Pac(char d, int p, int l, boolean cS)
    {
        direction = d;
        points = p;
        lives = l;
        canShoot = cS;
        speed = 1;
        canGetPoints = true;
        worth = 1;
        ammo = 0;
    }
    //Accessor Methodsd\
    public int move(char s)
    {
        direction = s;
        if (s == 'a')
            return -20;
        else if (s == 'w')
            return -1;
        else if (s == 'd')
            return 20;
        else if (s == 's')
            return 1;
        return 0;
    }
    
    public char getDirection()
    {
        return direction;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public int getWorth()
    {
        return worth;
    }
    
    public boolean ifCanShoot()
    {
        return canShoot;
    }
    
    public boolean ifCanGetPoints()
    {
        return canGetPoints;
    }
    //Mutator Methods
    public void setDirection(char s)
    {
        direction = s;
    }
    
    public void shoot()
    {
        if (canShoot && ammo > 0)
        {
            Bullet bullet = new Bullet(direction);
            ammo--;
        }
    }
    
    public void addPoints(int n)
    {
        points += n;
    }
    
    public void subtractLife()
    {
        lives --;
    }
    
    public void setCanShoot(boolean cS)
    {
        canShoot = cS;
        ammo = 3;
    }
    
    public String toString()
    {
        return "Pac   Lives: " + lives + "   Points: " + points + "   Has Gun: " + canShoot;
    }
}
