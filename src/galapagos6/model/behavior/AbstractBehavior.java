package galapagos6.model.behavior;

import galapagos6.model.Finch;

/**
 * Basic implementation of strategies
 */

public abstract class AbstractBehavior implements Behavior
{
	/**
	 * The unique string that identifies the behavior
	 */
	private String id;
	
	public AbstractBehavior( String id )
	{
		this.id = id;
	}
	
	/**
	 * Query: What is the unique string that identifies this behavior.
	 */
	public String id()
	{
		return id;
	}
	
    /**
     * Default implementation of prunedBy that does nothing.
     * This can be overridden by concrete strategies that needs to
     * keep track of the individuals that might have helped it in the past.
     */
    public void groomedBy( Finch f, boolean pruned )
    {
        
    }

    /**
     * Clone needs to be public, not protected.
     */
    public abstract Behavior clone();
}
