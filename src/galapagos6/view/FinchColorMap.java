package galapagos6.view;

import galapagos6.model.Biotope;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Color map for finch behaviors. Maps finch behavior names to colors.
 * @author Philip
 *
 */
public class FinchColorMap 
{
    public Map<String, Color> finchColors = new HashMap<String, Color>();

    /**
     * Construct a new color map from the biotope bio.
     * @param bio The biotope to get names for behaviors from.
     */
    public FinchColorMap(Biotope bio)
    {
        populateColors(bio);
    }
    
    private void populateColors(Biotope bio)
    {
        double intv = (1.0 / (bio.populations().size())+2);
        float interval = (float)intv;
        float step = interval;
        finchColors = new HashMap<String,Color>();
        for(String s:bio.populations()){
            Color color;
            color = new Color(Color.HSBtoRGB(step,1,1));
            finchColors.put(s, color);
            step += interval;
        }
    } 
}
