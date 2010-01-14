package galapagos6.view;

import galapagos6.model.Biotope;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A frame with text, describing a group of finches
 *
 */
public class TextLine extends JPanel {
	private static final long serialVersionUID = 1L;
	private Biotope biotope;
	private String finch_id;
	private JLabel type;
	private JLabel population;
	private JLabel finches_killed;
	private JLabel finches_deceased;
	private JLabel finches_born;
	
	/**
	 * Constructor of TextLine
	 * @param color The desired color of the text
	 * @param biotope The biotope object, to pull information from
	 * @param finch_id The finch behavior type
	 */
	public TextLine(Color color, Biotope biotope, String finch_id) {
		this.biotope = biotope;
		this.finch_id = finch_id;
		
		type = new JLabel();
		population = new JLabel();
		finches_killed = new JLabel();
		finches_deceased = new JLabel();
		finches_born = new JLabel();
		
		// Sets text color
		type.setForeground(color);
		population.setForeground(color);
		finches_killed.setForeground(color);
		finches_deceased.setForeground(color);
		finches_born.setForeground(color);
		
		// Finch id only have to set once, so here we go
		type.setText(finch_id);
		
		setLayout(new GridLayout(5, 1));
		
		add(type);
		add(population);
		add(finches_killed);
		add(finches_deceased);
		add(finches_born);
	}
	
	/**
	 * Pulls information from the biotope object, updates the labels with
	 * the given information 
	 */
	public void update_labels(){
		population.setText(biotope.population(finch_id).toString());
		finches_killed.setText(biotope.killedTicks(finch_id).toString());
		finches_deceased.setText(biotope.killedNatural(finch_id).toString());
		finches_born.setText(biotope.born(finch_id).toString());
	}
	
}
