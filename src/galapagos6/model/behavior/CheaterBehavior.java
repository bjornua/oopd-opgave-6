package galapagos6.model.behavior;

import galapagos6.model.*;

/**
 * A behavior that just will not help others.
 */
public class CheaterBehavior extends AbstractBehavior
{
	CheaterBehavior()
	{
		super( "cheater" );
	}
	    
    /**
     * Query: Do we prune this fink
     * @param f Fink to decide pruning for
     * @return false
     */
    public boolean groom( Finch f )
    {
        return false;
    }
    
    /**
     * Return a new instance of the behavior
     */
    public CheaterBehavior clone()
    {
        return new CheaterBehavior();
    }
}