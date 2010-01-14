package galapagos6.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * A controlpanel with start, stop, step and reset buttons
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of ControlPanel
	 * @param listener
	 */
	public ControlPanel(ActionListener listener) {
		JButton button_start = new JButton("Start");
		JButton button_stop  = new JButton("Stop");
		JButton button_step  = new JButton("Step");
		JButton button_reset = new JButton("Reset");
		
		// Sets an id for each button
		button_start.setActionCommand("start");
		button_stop.setActionCommand("stop");
		button_step.setActionCommand("step");
		button_reset.setActionCommand("reset");
		
		// Sets the handler of buttonactions
		button_start.addActionListener(listener);
		button_stop.addActionListener(listener);
		button_step.addActionListener(listener);
		button_reset.addActionListener(listener);
        
		add(button_start);
		add(button_stop);
		add(button_step);
		add(button_reset);
	}
}