package galapagos6.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ControlPanel(ActionListener listener) {
		
		JButton button_start = new JButton("Start");
		JButton button_stop  = new JButton("Stop");
		JButton button_step  = new JButton("Step");
		JButton button_reset  = new JButton("Reset");
		
		button_start.setActionCommand("start");
		button_stop.setActionCommand("stop");
		button_step.setActionCommand("step");
		button_reset.setActionCommand("reset");
		
		button_start.addActionListener(listener);
		button_stop.addActionListener(listener);
		button_step.addActionListener(listener);
		button_reset.addActionListener(listener);

		this.add(button_start);
		this.add(button_stop);
		this.add(button_step);
		this.add(button_reset);
	}
}
