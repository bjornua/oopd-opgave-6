package galapagos6.model.behavior;

import galapagos6.model.Finch;

import java.util.*;

/**
 * This behavior will retaliate if the other fink will not prune it, after one turn of
 * not helping it returns to the friendly state and helps regardless of the other finch's
 * action.
 * @author hartmann
 *
 */

public class Tit4TatStrategy extends AbstractBehavior
{
    public Tit4TatStrategy()
    {
    	super( "Tit4Tat" );
        pruneOther = new HashMap< Finch, Boolean >();
    }
    
    public boolean groom( Finch f )
    {
        boolean result = true;
        
        if( pruneOther.containsKey( f ) )
        {
            Boolean b = pruneOther.get( f );
            result = b;
            pruneOther.put( f, true );
        }
        
        return result;
    }
    
    public void prunedBy( Finch f, boolean pruned, int turn )
    {
    	pruneOther.put( f, pruned );
    }

    public Behavior clone()
    {
        return new Tit4TatStrategy();
    }
    
    private Map< Finch, Boolean > pruneOther;
}