package galapagos6.model.behavior;

import galapagos6.model.Finch;

/**
 * Behavior that copies the action done to it last.
 * If the last action was to prune, this will make a fink using
 * this behavior prune the next time it is asked. If the last action was
 * not to prune, then the fink will decline to prune.
 */
public class CopyCatBehavior extends AbstractBehavior
{
    /**
     * Constructor: initialize the first behavior to prune.
     */
    public CopyCatBehavior()
    {
    	super( "CopyCat" );
        lastPruned = true;
    }
    
    /**
     * Query: Prune the given fink.
     * @param f the fink to decide about
     * @return the last action done to this fink
     */
    public boolean groom( Finch f )
    {
        return lastPruned;
    }
    
    /**
     * Command: Create a new instance of the behavior
     */
    public Behavior clone()
    {
        return new CopyCatBehavior();
    }
    
    private boolean lastPruned;
}