
/**
 * Write a description of class Coordinate here.
 * This coordinate system will be very helpful in our virtual board of our game. You can see it implemented in our Board class.
 * The coordinate system will be set to Characters to see where they are on the board.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coordinate
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    public Coordinate(int a, int b)
    {
        x = a;
        y = b;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int a)
    {
        x = a;
    }
    public void setY(int b)
    {
        y = b;
    }
    public String toString()
    {
        return (x + ", " + y);
    }
}
