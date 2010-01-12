package galapagos6.model.behavior;

import galapagos6.model.Finch;

/**
 * This behavior is to change behavior each turn.
 * @author hartmann
 *
 */

public class HalfNHalfBehavior extends AbstractBehavior
{
	public HalfNHalfBehavior()
	{
		super( "FlipFlop" );
	}
	
    public boolean groom( Finch f )
    {
        this.prune = ! this.prune;
        return this.prune;
    }
    
    public Behavior clone()
    {
        return new HalfNHalfBehavior();
    }
    
    private boolean prune;
}