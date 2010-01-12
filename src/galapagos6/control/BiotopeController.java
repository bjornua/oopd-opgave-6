package galapagos6.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import galapagos6.model.Biotope;

/**
 * Class for controlling the biotope.
 * @author Philip
 *
 */
public class BiotopeController implements  ActionListener
{
    Biotope biotope;
    private Timer timer;
    private ActionListener stepBiotopeCommand;
    
    /**
     * Constructs a new biotope controller and sets up a timer
     * to step the biotope at appropriate intervals.
     * @param bio
     */
    public BiotopeController(Biotope bio)
    {
        this.biotope = bio;
        
        stepBiotopeCommand = new ActionListener()
        {
            public void actionPerformed( ActionEvent ev )
            {
                biotope.step();
            }
        };
        
        timer = new Timer( 100, stepBiotopeCommand );
        timer.setRepeats( true );
    }

    /**
     * Starts the simulation.
     */
    public void start()
    {
        timer.start();
    }
    
    /**
     * Stops the simulation.
     */
    public void stop()
    {
        timer.stop();
    }
    
    @Override
    public void actionPerformed(ActionEvent actionevent)
    {
        String command = actionevent.getActionCommand();
        if(command == "start"){
        	start();
        } else if(command == "stop"){
        	stop();
        } else if(command == "reset"){
        	biotope.reset();
        } else if(command == "step"){
        	this.biotope.step();
        }else {
        	assert false;
        }
    }
}
