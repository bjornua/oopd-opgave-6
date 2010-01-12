package galapagos6.control;

import galapagos6.model.*;
import galapagos6.view.MainWindow;

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
        new Launcher();
    }
    public Launcher(){
    	MainWindow mainwindow = new MainWindow(biotope, control);
    	biotope.addObserver(mainwindow);
    }

}
