package galapagos6.view;

import galapagos6.control.BiotopeController;
import galapagos6.model.Biotope;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * A window to control and watch progress of the simulation
 *
 */
public class MainWindow extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private ControlPanel controlpanel;
	private AreaPanel areapanel;
	private StatusPanel statuspanel;
	private Biotope biotope;
	private BiotopeController biotopeController;
	private FinchColorMap colormap;
	
	/**
	 * Spawns the window
	 * @param biotope
	 * @param biotopeController
	 */
	public MainWindow(Biotope biotope, BiotopeController biotopeController) {
		this.biotope = biotope;
		this.biotopeController = biotopeController;
		controlpanel = new ControlPanel(biotopeController);
		colormap = new FinchColorMap(biotope);
		areapanel = new AreaPanel();
		statuspanel = new StatusPanel(biotope, colormap);
		
		
		// The main window with the controlbuttons and statustext
        JFrame frame = new JFrame("Simulation");
		frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(statuspanel);
        frame.add(controlpanel);

        // A second window that displays areapanel
        JFrame frame2 = new JFrame();
        frame2.add(areapanel);
        frame2.pack();
        frame2.setVisible(true);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * Updates the statuspanel, and areapanel.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		statuspanel.update_lines();
		update_areapanel();
	}
	
	private void update_areapanel() {
		for( int i = 0; i < biotope.getWidth(); ++i )
        {
            for( int j = 0; j < biotope.getHeight(); ++j )
            {
            	if (biotope.empty(i, j)) {
        			areapanel.point(i,j, Color.black);
        		} else {
        			areapanel.point(i,j, colormap.finchColors.get(biotope.behaviorId(i,j)));
        		}
            }
        }
        areapanel.paint();
	}
}
