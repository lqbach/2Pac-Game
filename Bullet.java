
/**
 * Write a description of class Bullet here.
 * Bullets branches off of Character. It travels faster than other characters since it's a bullet.
 * Bullet is not worth any points.
 * The constructor and move class sets its direction.
 * The bullet should always travel in the direction of Pac.
 * The bullet does no have the ability to shoot.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet implements Character
{
    private final int speed;
    private char direction;
    private final boolean canGetPoints;
    private final int worth;
    public Bullet(char d)
    {
        direction = d;
        speed = 2;
        canGetPoints = false;
        worth = 3;
    }

    public int move(char s) //?
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
    
    public void shoot()
    {
    }
    
    public char getDirection()
    {
        return direction;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    public boolean ifCanGetPoints()
    {
        return canGetPoints;
    }
    public int getWorth()
    {
        return worth;
    }
}
