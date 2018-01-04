
/**
 * Write a description of class Pill here.
 * This abstract pill class is the pills that 2Pac has to collect 
 * Each pill is worth a certain amount of points and can give you power-ups
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Pill
{
    private int value; 

    public Pill() 
    {
    }
    public void setValue(int n) 
    {
        value = n;
    }
    public int getValue()
    {
        return value;
    }
    public abstract void draw();
    public abstract boolean powerUp();
}
