package galapagos6.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import galapagos6.model.Biotope;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * Displays all the groups of finches with TextLines
 */
public class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Biotope biotope;
	private FinchColorMap colorMap;
	private ArrayList<TextLine> lines; 
	private JLabel total_population;
	private JLabel total_finches_killed;
	private JLabel total_finches_deceased;
	private JLabel total_finches_born;
	
	/**
	 * Constructor of StatusPanel
	 * @param biotope
	 * @param colorMap
	 */
	public StatusPanel(Biotope biotope, FinchColorMap colorMap){
		this.biotope = biotope;
		this.colorMap = colorMap;
		lines = new ArrayList<TextLine>();
		
		// The total statistics labels
		JLabel total_label = new JLabel();
		total_population = new JLabel();
		total_finches_killed = new JLabel();
		total_finches_deceased = new JLabel();
		total_finches_born = new JLabel();
		total_label.setText("Total");
		
		// Creates a new panel for the total statistics
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));

		panel.add(total_label);
		panel.add(total_population);
		panel.add(total_finches_killed);
		panel.add(total_finches_deceased);
		panel.add(total_finches_born);
		
		for(String id : biotope.populations()){
			Color color = colorMap.finchColors.get(id);
			TextLine line = new TextLine(color, biotope, id);
			lines.add(line);
			add(line);
		}

		add(panel);
	}
	
	/**
	 * Commands the TextLines and total panel to refresh their data
	 */
	public void update_lines(){
		for(TextLine line : lines){
			line.update_labels();
		}
		update_total_panel();
	}
	
	private void update_total_panel(){
		Integer sum_population = 0;
		Integer sum_killedTicks = 0;
		Integer sum_killedNatural = 0;
		Integer sum_born = 0;
		
		for(String finch_id : biotope.populations()){
			sum_population += biotope.population(finch_id);
			sum_killedTicks += biotope.killedTicks(finch_id);
			sum_killedNatural += biotope.killedNatural(finch_id);
			sum_born += biotope.born(finch_id);
		}
		
		total_population.setText(sum_population.toString());
		total_finches_killed.setText(sum_killedTicks.toString());
		total_finches_deceased.setText(sum_killedNatural.toString());
		total_finches_born.setText(sum_born.toString());
	}
}
