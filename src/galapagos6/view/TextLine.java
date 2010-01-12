package galapagos6.view;

import galapagos6.model.Biotope;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextLine extends JPanel {
	private static final long serialVersionUID = 1L;
	public Biotope biotope;
	public String finch_id;
	public JLabel selected_type;
	public JLabel selected_population;
	public JLabel selected_finches_killed;
	public JLabel selected_finches_deceased;
	public JLabel selected_finches_born;

	public TextLine(Color color, Biotope biotope, String finch_id) {
		this.biotope = biotope;
		this.finch_id = finch_id;
		
		selected_type = new JLabel();
		selected_population = new JLabel();
		selected_finches_killed = new JLabel();
		selected_finches_deceased = new JLabel();
		selected_finches_born = new JLabel();
		
		selected_type.setForeground(color);
		selected_population.setForeground(color);
		selected_finches_killed.setForeground(color);
		selected_finches_deceased.setForeground(color);
		selected_finches_born.setForeground(color);
		
		selected_type.setText(finch_id);
		setLayout(new GridLayout(5, 1));
		add(selected_type);
		add(selected_population);
		add(selected_finches_killed);
		add(selected_finches_deceased);
		add(selected_finches_born);
	}
	
	public void update_labels(){
		selected_population.setText(biotope.population(finch_id).toString());
		selected_finches_killed.setText(biotope.killedTicks(finch_id).toString());
		selected_finches_deceased.setText(biotope.killedNatural(finch_id).toString());
		selected_finches_born.setText(biotope.born(finch_id).toString());
	}
	
}
