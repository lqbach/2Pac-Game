
/**
 * Write a description of class PowerPill here.
 * PowerPill is a pill. It is worth 100 points and gives a power up.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerPill extends Pill
{
    public PowerPill()
    {
        setValue(100);
    }

    public void draw()
    {
    }
    public boolean powerUp()
    {
        return true;
    }
    public String toString()
    {
        return "Power Pill";
    }
}
