package galapagos6.model.behavior;

import galapagos6.model.Finch;

/**
 * The interface for the behaviors of the Finchs.
 * 
 * It is used as part of a strategy pattern by the Finch class.
 */

public interface Behavior extends Cloneable
{
	/**
	 * Unique identifier for the strategy
	 */
    public String id();
    
    /**
     * Determine if the given finch f is pruned
     * @param f
     * @return
     */
    public boolean groom( Finch f );
    
    /**
     * This method is called from the simulation to inform the fink about which other fink
     * it interacted with, and if it pruned us or not.
     * @param f
     * @param pruned
     */
    public void groomedBy( Finch f, boolean pruned );
    
    /**
     * The strategy is clonable by using this call.
     * @return
     */
    public Behavior clone();
}
