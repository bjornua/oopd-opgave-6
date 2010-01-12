package galapagos6.model.behavior;

import java.util.*;

/**
 * Behavior factory is a singleton used to produce the behaviors.
 * this is used in the simulation to instantiate the beahviors.
 * 
 * @author hartmann
 *
 */

public class BehaviorFactory
{
    public enum Type { TIT4TAT, CHEATER, RANDOM, GRUDGE, HALFNHALF, COPYCAT, SAMARITAN, TIT4TATPROBER }
   
    /**
     * Constructor is private in singleton pattern.
     * To gain access to this class, use the static method instance()
     */
    
    private BehaviorFactory()
    {
        random = new Random();
        active = new ArrayList< Type >();
    }
    
    /**
     * Creates a new instance if none exists, and return a reference to this instance
     * @return
     */
    
    public static BehaviorFactory instance()
    {
        if( ref == null )
        {
            ref = new BehaviorFactory();
        }
        
        return ref;
    }
    
    /**
     * Make especially sure that the object references is not clonable.
     */
    
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException(); 
    }
    
    /**
     * Factory method for generating strategies
     */
    public Behavior create( Type t )
    {
        Behavior result = null;
        
        switch( t )
        {
            case TIT4TAT:
                result = new Tit4TatStrategy();
                break;
            case CHEATER:
                result = new CheaterBehavior();
                break;
            case RANDOM:
                result = new RandomBehavior( random );
                break;
            case GRUDGE:
                result = new GrudgeBehavior();
                break;
            case HALFNHALF:
                result = new HalfNHalfBehavior();
                break;
            case COPYCAT:
                result = new CopyCatBehavior();
                break;
            case SAMARITAN:
                result = new SamaritanBehavior();
                break;
            case TIT4TATPROBER:
                result = new Tit4TatProbingBehavior( random );
                break;
            default:
                System.out.println( "The behavior type " + t + " cannot be created" );
        }
        
        return result;
    }
    
    /**
     * Let the control from outside descide which behaviors that are active.
     * @param t
     */
    
    public void active( Type t )
    {
        if( ! active.contains( t ) )
            active.add( t );
    }
    
    /**
     * return the number of active strategies
     * @return
     */
    
    public int activeCount()
    {
        return active.size();
    }

    /**
     * return a list of the active behaviors
     * @return
     */
    
    public List< Type > active()
    {
        return active;
    }
    
    /**
     * Create a random behavior.
     * @return
     */
    
    public Behavior createRandom()
    {
        return create( active.get( random.nextInt( active.size() ) ) );
    }

    private static BehaviorFactory ref = null;
    private Random random;
    private List< Type > active;
}