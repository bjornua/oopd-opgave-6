package galapagos6.model;

import galapagos6.model.behavior.*;

import java.util.*;
import java.io.*;


/**
 * this is the main model class of the Galapagos simulation.
 */

public class Biotope extends Observable
{
    public enum Notification { RESET, UPDATE }

    
    private World world;
    private SimulationConstants simulationConstants;
    private int turn;
    private Random random;
    private Map< String, Integer > populations;
    private List< Location > available;
    private int initialColoniesPerBehavior;
    private int initialMembersPerColony;
    private Map< String, Integer > borns;
    private Map< String, Integer > killedNatural;
    private Map< String, Integer > killedTicks;
    private File file;
    private boolean runFlag;
    
    /**
     * Construct a new biotope. It sets sane defaults for all options.
     */
    
    public Biotope()
    {
        world = new World( 200, 100 );

        //simulationConstants = new SimulationConstants( 6, 3, 20, 7, 10, 3 );
        //simulationConstants = new SimulationConstants( 12, 2, 20, 13, 11, 3 );
        //simulationConstants = new SimulationConstants( 6, 4, 20, 10, 20, 3 );
        simulationConstants = new SimulationConstants( 5, 5, 20, 8, 15, 3 );

        random = new Random();
        available = new ArrayList< Location >();
        initialColoniesPerBehavior = 40;
        initialMembersPerColony = 1;
        
        reset();        
    }

    /**
     * Command: Re initialize all options, this is called both from the constructor
     * and from the file reader.
     */
    public void reset()
    {
        world.reset();
        
        runFlag = false;
        
        turn = 0;
        populations = new HashMap< String, Integer >();
        borns = new HashMap< String, Integer >();
        killedNatural = new HashMap< String, Integer >();
        killedTicks = new HashMap< String, Integer >();
        
        BehaviorFactory.instance().active( BehaviorFactory.Type.SAMARITAN );
        BehaviorFactory.instance().active( BehaviorFactory.Type.COPYCAT );
        BehaviorFactory.instance().active( BehaviorFactory.Type.HALFNHALF );
        BehaviorFactory.instance().active( BehaviorFactory.Type.GRUDGE );
        BehaviorFactory.instance().active( BehaviorFactory.Type.TIT4TAT );
        BehaviorFactory.instance().active( BehaviorFactory.Type.RANDOM );
        BehaviorFactory.instance().active( BehaviorFactory.Type.CHEATER );
        BehaviorFactory.instance().active( BehaviorFactory.Type.TIT4TATPROBER );

        int initialx = 0;
        int initialy = 0;
        int deltax = 0;
        int deltay = 0;
        Location l = null;
        
        for( int i = 0; i < initialColoniesPerBehavior; ++i )
        {
            for( BehaviorFactory.Type t : BehaviorFactory.instance().active() )
            {
                initialx = random.nextInt( world.x() );
                initialy = random.nextInt( world.y() );
                
                int k = 0;
                
                while( k < initialMembersPerColony )
                {
                    deltax = random.nextInt( initialMembersPerColony + 1 ) - initialMembersPerColony / 2;
                    deltay = random.nextInt( initialMembersPerColony + 1 ) - initialMembersPerColony / 2;
                    
                    l = new Location( ( initialx + deltax + world.x() ) % world.x(),
                                      ( initialy + deltay + world.y() ) % world.y() );
                    
                    if( world.empty( l.x(), l.y() ) )
                    {
                        world.resurrectFink( l.x(),
                                             l.y(),
                                             simulationConstants.newBornLife,
                                             maxAge(),
                                             reproductionTurn(),
                                             BehaviorFactory.instance().create( t ) );
                        increasePopulation( world.getFinch( l.x(), l.y() ).behavior().id() );
                        ++k;
                    }
                }
            }
        }
                
        notifyMyObservers( Notification.RESET );
    }

    /**
     * Read the simulation constants from the file named in
     * instance variable fileName.
     * @ensure Errors in reading file will not modify state of object.
     */
    public void readConstants()
    {
    	// Read the simulation constants from file
    	
    	reset();
    }
    
    /**
     * Make a step in the simulation.
     */
    public void step()
    {
    	++turn;
    	doCycle();
    }

    /**
     * Query: get the torus circumfrence
     */
    public int getWidth()
    {
        return world.x();
    }
    
    /**
     * Query: get the crosssection circumfrence
     */
    public int getHeight()
    {
        return world.y();
    }
    
    /**
     * Query: is location in world empty?
     */
    public boolean empty( int x, int y )
    {
        return world.empty( x, y );
    }
    
    /**
     * Query: Behavior ID for fink in position i, j
     */
    public String behaviorId( int i, int j )
    {
        return world.getFinch( i, j ).behavior().id();
    }
        
    /**
     * Query: Which turn is it
     */
    public int turn()
    {
        return turn;
    }
    
    /**
     * Query: What is the total population of the world
     */
    public int population()
    {
        return world.population();
    }
    
    /**
     * Query: population for specific behavior
     */
    public Integer population( String id )
    {
        return populations.get( id );
    }
        
    /**
     * Query: how many of given behavior was born last turn
     */
    public Integer born( String id )
    {
        if( borns.containsKey( id ) )
        {
            return borns.get( id );
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Query: How many finks are killed by old age the last turn.
     * @param id
     * @return
     */
    
    public Integer killedNatural( String id )
    {
        if( killedNatural.containsKey( id ) )
        {
            return killedNatural.get( id );
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Query: How many of the given behavior was killed due to infection last turn
     * @param id
     * @return
     */
    
    public Integer killedTicks( String id )
    {
        if( killedTicks.containsKey( id ) )
        {
            return killedTicks.get( id );
        }
        else
        {
            return 0;
        }
    }
            
    /**
     * Query: What is name of the populations
     * @return
     */
    
    public Set< String > populations()
    {
        return populations.keySet();
    }
    
    /**
     * Command: set file to do load store from
     */
    public void setFile( File f )
    {
    	file = f;
    }
    
    /**
     * Command: Load galapagos5.model from file
     */
    public void load()
    {
        System.out.println( "Must be implemented" );
    }

    /**
     * Is the model running continously
     * @return
     */
    public boolean isRunning()
    {
    	return runFlag;
    }

    /**
     * Start the simulation
     */
    public void start()
    {
    	runFlag = true;
    }
    
    /**
     * Stop the simulation
     */
    public void stop()
    {
    	runFlag = false;
    }
    
    /**
     * Main turn loop.
     */
    private void doCycle()
    {
        resetBorns();
        resetKilledNatural();
        resetKilledTicks();
        
        Finch f;
        
        ListIterator< Finch > fit = world.finchIterator();        
        
        while( fit.hasNext() )
        {        
            f = fit.next();
            f.setGroomedThisTurn( false );
        }
        
        fit = world.finchIterator();
        
        while( fit.hasNext() )
        {
            f = fit.next();
            
            birthday( f );
            reproduction( f, fit );
            groom( f, fit );
            infection( f );
        }
        
        fit = world.finchIterator();
        
        while( fit.hasNext() )
        {
           kill( fit.next(), fit );
        }
        
        notifyMyObservers( Notification.UPDATE );
    }
    
    /**
     * Age f by calling its birthday method
     * @param f
     */
    
    private void birthday( Finch f )
    {
        f.birthday();
    }

    /**
     * command: instruct the finches to reproduce
     * @param f
     * @param fit
     */
    
    private void reproduction( Finch f, ListIterator< Finch > fit )
    {   
        if( f.age() % simulationConstants.birthFreq == f.reproductionTurn() )
        {
            Location[] adjacent = world.adjacentLocations( f );
            
            available.clear();
            
            for( World.Direction d : World.directions() )
            {
                if( world.empty( adjacent[ d.ordinal() ].x(), adjacent[ d.ordinal() ].y() ) )
                {
                    available.add( adjacent[ d.ordinal() ] );
                }
            }
            
            if( ! available.isEmpty() )
            {
                int choise = random.nextInt( available.size() );
                Location birthLocation = available.get( choise );
                
                world.resurrectFink( birthLocation.x(),
                                     birthLocation.y(),
                                     simulationConstants.newBornLife,
                                     maxAge(),
                                     reproductionTurn(),
                                     behavior( f ), fit );
                increasePopulation( f.behavior().id() );
                increaseBorns( f.behavior().id() );
            }
        }
    }
    
    /**
     * Command: instruct the finch f to make a decision about pruning.
     * @param f
     * @param fit
     */
    
    private void groom( Finch f, ListIterator< Finch > fit )
    {
        if( ! f.getGroomedThisTurn() )
        {
            f.setGroomedThisTurn( true );
            
            available.clear();
            
            Location[] adjacent = world.adjacentLocations( f );
            
            for( World.Direction d : World.directions() )
            {
                if( ! world.empty( adjacent[ d.ordinal() ].x(), adjacent[ d.ordinal() ].y() ) )
                {
                    available.add( adjacent[ d.ordinal() ] );
                }
            }
            
            if( ! available.isEmpty() )
            {
                int choise = random.nextInt( available.size() );
                
                Finch other = world.getFinch( available.get( choise ) );
                
                other.setGroomedThisTurn( true );
                
                boolean firstPrune = f.tryGroom( other );
                boolean secondPrune = other.tryGroom( f );
                
                f.groomedBy( other, secondPrune );
                other.groomedBy( f, firstPrune );
                
                if( firstPrune && secondPrune ) 
                {
                    f.addEnergy( 3 );
                    other.addEnergy( 3 );
                }
                
                if( firstPrune && ! secondPrune )
                {
                    f.addEnergy( 0 );
                    other.addEnergy( 5 );
                }
                
                if( ! firstPrune && secondPrune )
                {
                    f.addEnergy( 5 );
                    other.addEnergy( 0 );
                }
                
                if( ! firstPrune && ! secondPrune )
                {
                    f.addEnergy( 1 );
                    other.addEnergy( 1 );
                }
            }
        }
    }
    
    /**
     * Command: Make finch loose energy to infection
     * @param f
     */
    
    private void infection( Finch f )
    {
        f.removeEnergy( simulationConstants.infectionPrice );
    }
    
    /**
     * Command: Make finch die if energy < 0 or age > maxAge.
     * @param f
     * @param fit
     */
    
    private void kill( Finch f, Iterator< Finch > fit )
    {
        if( f.getKilledNatural() )
        {
            decreasePopulation( f.behavior().id() );
            increaseKilledNatural( f.behavior().id() );
            world.killFinch( f, fit );
        } else if( f.getKilledTicks() )
        {
            decreasePopulation( f.behavior().id() );
            increaseKilledTicks( f.behavior().id() );
            world.killFinch( f, fit );
        }
    }
    
    /**
     * Generate a new maxAge
     * @return
     */
    
    private int maxAge()
    {
        return random.nextInt( simulationConstants.spreadAge ) + simulationConstants.meanAge;
    }

    /**
     * Query: Generate a reproduction turn. A random number between 1 and birthFreq.
     * @return
     */
    
    private int reproductionTurn()
    {
        return random.nextInt( simulationConstants.birthFreq );
    }
    
    /**
     * Notify all observers of changes
     * @param n
     */
    
    private void notifyMyObservers( Notification n )
    {
        setChanged();
        notifyObservers( n );
    }
    
    /**
     * Query: Get a new behavior object based on the finch's behavior.
     * @param f
     * @return
     */
    
    private Behavior behavior( Finch f )
    {
    	return f.behavior().clone();
    }
    
    /**
     * Command: Increase the population counter associated with the behavior.
     * @param id
     */
    
    private void increasePopulation( String id )
    {
        if( ! populations.containsKey( id ) )
        {
            populations.put( id, 0 );
        }
            
        populations.put( id, populations.get( id ) + 1 );
    }
    
    /**
     * Command: Decrease the population counter associated with the behavior.
     * @param id
     */
    
    private void decreasePopulation( String id )
    {
        populations.put( id, populations.get( id ) - 1 );
    }
    
    /**
     * Reset all the born counters.
     */
    
    private void resetBorns()
    {
        for( String s : borns.keySet() )
        {
            borns.put( s, 0 );
        }
    }
    
    /**
     * Reset the killed by natural causes counter.
     */
    private void resetKilledNatural()
    {
        for( String s : killedNatural.keySet() )
        {
            killedNatural.put( s, 0 );
        }
    }
    
    /**
     * Reset the killed by infection counter.
     */
    
    private void resetKilledTicks()
    {
        for( String s : killedTicks.keySet() )
        {
            killedTicks.put( s, 0 );
        }
    }

    /**
     * Increase the behaviors born counter
     * @param id
     */
    
    private void increaseBorns( String id )
    {
        if( borns.containsKey( id ) )
        {
            borns.put( id, borns.get( id ) + 1 );
        }
        else
        {
            borns.put( id, 0 );
        }
    }
    
    /**
     * Increase the killed by old age counter for the behavior
     * @param id
     */
    
    private void increaseKilledNatural( String id )
    {
        if( killedNatural.containsKey( id ) )
        {
            killedNatural.put( id, killedNatural.get( id ) + 1 );
        }
        else
        {
            killedNatural.put( id, 0 );
        }
    }
    
    /**
     * Increase the counter for the behaviors killed by infection counter.
     * @param id
     */
    
    private void increaseKilledTicks( String id )
    {
        if( killedTicks.containsKey( id ) )
        {
            killedTicks.put( id, killedTicks.get( id ) + 1 );
        }
        else
        {
            killedTicks.put( id, 0 );
        }
    }

}
