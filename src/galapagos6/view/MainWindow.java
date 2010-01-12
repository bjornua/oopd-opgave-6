package galapagos6.view;

import galapagos6.control.BiotopeController;
import galapagos6.model.Biotope;
import galapagos6.model.Finch;
import galapagos6.model.Location;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class MainWindow extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	ControlPanel controlpanel;
	AreaPanel areapanel;
	StatusPanel statuspanel;
	Biotope biotope;
	BiotopeController biotopeController;
	FinchColorMap colormap;
	
	
	public MainWindow(Biotope biotope, BiotopeController biotopeController) {
		this.biotope = biotope;
		this.biotopeController = biotopeController;
		controlpanel = new ControlPanel(biotopeController);
		colormap = new FinchColorMap(biotope);
		areapanel = new AreaPanel();
		statuspanel = new StatusPanel(biotope, colormap);
		
        //Create and set up the window.
        JFrame frame = new JFrame("Assignment #6");
		frame.setLayout(new GridLayout(3, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(statuspanel);
        frame.add(areapanel);
        frame.add(controlpanel);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		statuspanel.update_lines();
		update_finches();
	}

	private void update_finches() {
		for( int i = 0; i < biotope.getWidth(); ++i )
        {
            for( int j = 0; j < biotope.getHeight(); ++j )
            {
            	if (biotope.empty(i, j) == true) {
        			areapanel.point(i,j, Color.black);
        		} else {
        			areapanel.point(i,j, colormap.finchColors.get(biotope.behaviorId(i,j)));
        		}
            }
        }
        areapanel.paint();
	}
}
