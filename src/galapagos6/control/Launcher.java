package galapagos6.control;

import galapagos6.model.*;

/**
 * Launches the program.
 * @author Philip
 *
 */
public class Launcher
{
    private static Biotope biotope;
    private static BiotopeController control;

    public static void main(String[] args)
    {
        biotope = new Biotope();
        control = new BiotopeController(biotope);
    }

}
