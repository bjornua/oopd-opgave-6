package galapagos6.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import galapagos6.model.*;
import galapagos6.control.*;

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
		//Color color = colormap.finchColors.get(id);
		areapanel.point(2,4, Color.black);
        areapanel.paint();
	}
}
