
/**
 * Write a description of class Enemy here.
 * Branches off of Character.
 * This class has other classes branching off, including all the other enemies.
 * All enemies behave relatively the same.
 * Enemies move the same as Pac. Nothing new is added here, except some methods were removed.
 * 
 * In the future, we might be able to add a shoot function for enemies, but sadly, time prohibits this.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy implements Character
{
    private char direction;
    private boolean canShoot;
    private final int speed;
    private final boolean canGetPoints;
    private final int worth;
    private boolean alive;
    public Enemy(char d, boolean cS)
    {
        direction = d;
        canShoot = cS;
        speed = 1;
        canGetPoints = false;
        worth = 2;
        alive = true;
    }

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
    
    public boolean ifCanShoot()
    {
        return canShoot;
    }
    
    public boolean ifCanGetPoints()
    {
        return canGetPoints;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public int getWorth()
    {
        return worth;
    }
    
    public void setDirection(char s)
    {
        direction = s;
    }
    
    public void setCanShoot(boolean cS)
    {
        canShoot = cS;
    }
    
    public void shoot()
    {
        if (canShoot)
        {
            Bullet bullet = new Bullet(direction);
        }
    }
    
    public void kill(){
    	alive = false;
    }
    
    public boolean getAlive(){
    	return alive;
    }
    
    public String toString()
    {
        return "Has Gun: " + canShoot;
    }
}
