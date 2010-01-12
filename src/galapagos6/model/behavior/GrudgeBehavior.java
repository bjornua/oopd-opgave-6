package galapagos6.model.behavior;

import galapagos6.model.Finch;

import java.util.*;

/**
 * This behavior holds a grudge. Meaning it keeps track of who helped it in the past.
 * If another fink did not prune us, we will not prune it ever. 
 * @author hartmann
 *
 */

public class GrudgeBehavior extends AbstractBehavior
{
    public GrudgeBehavior()
    {
    	super( "Grudge" );
        retaliateTo = new HashSet< Finch >();
    }
    
    public boolean groom( Finch f )
    {
        boolean result = true;
        
        if( retaliateTo.contains( f ) ) result = false;
        
        return result;
    }
    
    public Behavior clone()
    {
        return new GrudgeBehavior();
    }
    
    public void prunedBy( Finch f, boolean pruned, int turn )
    {
        if( ! pruned )
        {
            retaliateTo.add( f );
        }
    }
    
    private Set< Finch > retaliateTo;
}