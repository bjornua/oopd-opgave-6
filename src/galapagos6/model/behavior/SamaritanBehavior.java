package galapagos6.model.behavior;

import galapagos6.model.Finch;

/**
 * This behavior is to always help.
 * 
 * @author hartmann
 *
 */

public class SamaritanBehavior extends AbstractBehavior
{
	public SamaritanBehavior()
	{
		super( "Samaritan" );
	}
	
    public boolean groom( Finch f )
    {
        return true;
    }
    
    public Behavior clone()
    {
        return new SamaritanBehavior();
    }
}
