package galapagos6.model.behavior;

import galapagos6.model.Finch;

import java.util.*;

/**
 * This behavior will randomly choose to prune or not.
 * @author hartmann
 *
 */

public class RandomBehavior extends AbstractBehavior
{
    public RandomBehavior( Random random)
    {
    	super( "Random" );
        this.random = random;
    }
    
    public boolean groom( Finch f )
    {
        return random.nextInt( 2 ) == 0;
    }
    
    public Behavior clone()
    {
        return new RandomBehavior( random );
    }

    private Random random;
}