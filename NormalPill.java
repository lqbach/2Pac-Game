
/**
 * Write a description of class NormalPill here.
 * NormalPill is a Pill. It is worth 10 points and gives no power ups.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalPill extends Pill
{
    public NormalPill()
    {
        setValue(10);
    }

   
    public void draw()
    {
    }
    public boolean powerUp()
    {
        return false;
    }
    
    public String toString()
    {
        return "Normal Pill";
    }
}
