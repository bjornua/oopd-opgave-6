package galapagos6.model.behavior;

import galapagos6.model.Finch;

import java.util.*;

/**
 * This behavior will retaliate if the other fink will not prune it, after one turn of
 * not helping it returns to the friendly state and helps regardless of the other finch's
 * action. Every once in a while it will try to max its return by not pruning.
 * @author hartmann
 *
 */

public class Tit4TatProbingBehavior extends AbstractBehavior
{
    public Tit4TatProbingBehavior( Random random )
    {
    	super( "Tit4TatProbing" );
    	this.random = random;
        pruneOther = new HashMap< Finch, Boolean >();
    }
    
    public boolean groom( Finch f )
    {
        boolean result = true;

        if( random.nextInt( 4 ) != 0 )
        {
            if( pruneOther.containsKey( f ) )
            {
                Boolean b = pruneOther.get( f );
                result = b;
                pruneOther.put( f, true);
            }
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public void prunedBy( Finch f, boolean pruned, int turn )
    {
    	pruneOther.put( f, pruned );
    }

    public Behavior clone()
    {
        return new Tit4TatProbingBehavior( random );
    }
    
    private Random random;
    private Map< Finch, Boolean > pruneOther;
}