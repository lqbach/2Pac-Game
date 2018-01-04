
/**
 * Write a description of interface Character here.
 * This is an interface for characters. It helps characters be able to move around and shoot.
 * Later on, we will need to use Characters in our virtual board, which will be later explained.
 * Classes that will be branched off of Characters include Bullet, Pac, and Enemy.
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Character
{
    public int move(char s); 
    public void shoot();
    public char getDirection();
    public int getWorth();
}
