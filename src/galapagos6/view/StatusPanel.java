package galapagos6.view;

import java.awt.Color;
import java.util.ArrayList;

import galapagos6.model.Biotope;

import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Biotope biotope;
	FinchColorMap colorMap;
	ArrayList<TextLine> lines; 
	
	public StatusPanel(Biotope biotope, FinchColorMap colorMap){
		this.biotope = biotope;
		this.colorMap = colorMap;
		lines = new ArrayList<TextLine>();
		Iterable<String> test = biotope.populations();
		for(String id : test){
			Color color = colorMap.finchColors.get(id);
			TextLine line = new TextLine(color, biotope, id);
			lines.add(line);
			add(line);
		}
	}
	
	public void update_lines(){
		for(TextLine line : lines){
			line.update_labels();
		}
	}
}
