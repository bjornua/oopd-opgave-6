package galapagos6.model;

import galapagos6.model.behavior.*;

import java.util.*;


/**
 * The world of Galapagos, modelled as a torus with a cross section circumfrence 
 * of x and torus circumfrence of y
 */

public class World
{
    public enum Direction { N, NE, E, SE, S, SW, W, NW }
    private int x;
    private int y;
    
    private Finch area[][];

    private List< Finch > liveFinches;
    
    private Map< Finch, Location > locator;
    
    private Location[] adjacent;
    private static Direction[] directions = null;
    
    /**
     * Create a new World.
     */
    public World( int x, int y )
    {
        this.x = x;
        this.y = y;

        adjacent = new Location[ Direction.values().length ];

        for( Direction d : Direction.values() )
        {
            adjacent[ d.ordinal() ] = new Location( 0, 0 );
        }
        
        reset();
    }
    
    /**
     * Resets the world.
     */
    public void reset()
    {
        area = new Finch[ x ][ y ];
        
        locator = new HashMap< Finch, Location >();
        
        for( int i = 0; i < x; ++i )
        {
            for( int j = 0; j < y; ++j )
            {
                area[ i ][ j ] = new Finch();
                locator.put( area[ i ][ j ], new Location( i, j ) );
            }
        }
        liveFinches = new ArrayList< Finch >( x * y );
    }
        
    /**
     * Query: get list of all adjacent locations
     */
    public Location[] adjacentLocations( Finch f )
    {
        Location fl = locator.get( f );
        
        for( Direction d: directions() )
        {
            Location l = adjacent[ d.ordinal() ];
            
            switch( d )
            {
                case N:
                    l.x( fl.x() );
                    l.y( ( fl.y() + y - 1 ) % y );
                    break;
                case NE:
                    l.x( ( fl.x() + 1 ) % x );
                    l.y( ( fl.y() + y - 1 ) % y );
                    break;
                case E:
                    l.x( ( fl.x() + 1 ) % x );
                    l.y( fl.y() );
                    break;
                case SE:
                    l.x( ( fl.x() + 1 ) % x );
                    l.y( ( fl.y() + 1 ) % y );
                    break;
                case S:
                    l.x( fl.x() );
                    l.y( ( fl.y() + 1 ) % y );
                    break;
                case SW:
                    l.x( ( fl.x() + x - 1 ) % x );
                    l.y( ( fl.y() + 1 ) % y );
                    break;
                case W:
                    l.x( ( fl.x() + x - 1 ) % x );
                    l.y( fl.y() );
                    break;
                case NW:
                    l.x( ( fl.x() + x - 1 ) % x );
                    l.y( ( fl.y() + y - 1 ) % y );
                    break;
            }
        }
        
        return adjacent;
    }
    
    /**
     * Query: torus circumfrence
     */
    public int x()
    {
        return x;
    }
    
    /**
     * Query: crosssection circumfrence
     */
    public int y()
    {
        return y;
    }
    
    /**
     * Query: location in world is empty. That is occupied by a fink in state DEAD
     */
    public boolean empty( int x, int y )
    {
        return area[ x ][ y ].state() == Finch.State.DEAD;
    }
    
    /**
     * Size of population.
     * @return Population size.
     */
    public int population()
    {
        return liveFinches.size();
    }

    /**
     * An iterator for the live finches. 
     * @return
     */
    public ListIterator<Finch> finchIterator()
    {
        return liveFinches.listIterator();
    }
            
    /**
     * Query Finch at location
     */
    
    public Finch getFinch(int x, int y)
    {
        return area[ x ][ y ];
    }
    
    /**
     * Gets finch at location l.
     * @param l The location
     * @return Finch.
     */
    public Finch getFinch( Location l )
    {
        return area[ l.x() ][ l.y() ];
    }
    
    /**
     * Directions supported.
     * @return The directions supported.
     */
    public static Direction[] directions()
    {
        if( directions == null )
        {
            directions = Direction.values();
        }
        
        return directions;
    }
    
    /**
     * Kill finch f in collection iterated by fit.
     * @param f Finch to be killed.
     * @param fit Iterator for finches.
     */
    public void killFinch( Finch f, Iterator< Finch > fit )
    {
        Location l = locator.get( f );
        
        if( area[ l.x() ]
                  [ l.y() ].state() == Finch.State.ALIVE )
        {
            fit.remove();
            area[ l.x() ][ l.y() ].kill();
        }
        else
        {
            System.out.println( "Killing already dead fink" );
        }
    }
    
    /**
     * Kill finch at location.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void killFinch( int x, int y )
    {
        if( area[ x ][ y ].state() == Finch.State.ALIVE )
        {
            liveFinches.remove( area[ x ][ y ] );
            area[ x ][ y ].kill();
        }
        else
        {
            System.out.println( "Killing already dead fink" );
        }
    }
    
    /**
     * Resurrect finch at (x,y) with parameters.
     * @param x
     * @param y
     * @param energy
     * @param maxAge
     * @param reproductionTurn
     * @param strategy
     * @param fit
     */
    public void resurrectFink( int x, 
                               int y, 
                               int energy, 
                               int maxAge, 
                               int reproductionTurn, 
                               Behavior strategy,
                               ListIterator< Finch > fit )
    {
        if( area[ x ][ y ].state() == Finch.State.DEAD )
        {
            area[ x ][ y ].resurrect( energy, maxAge, reproductionTurn, strategy );
            fit.add( area[ x ][ y ] );
        }
        else
        {
            System.out.println( "Resurecting alive fink" );
        }
    }    
    
    /**
     * Resurrect finch at (x,y) with parameters.
     * @param x
     * @param y
     * @param energy
     * @param maxAge
     * @param reproductionTurn
     * @param strategy
     * @param fit
     */
    public void resurrectFink( int x, 
                               int y, 
                               int energy, 
                               int maxAge, 
                               int reproductionTurn, 
                               Behavior strategy )
    {
        if( area[ x ][ y ].state() == Finch.State.DEAD )
        {
            area[ x ][ y ].resurrect( energy, maxAge, reproductionTurn, strategy );
            liveFinches.add( area[ x ][ y ] );
        }
        else
        {
            System.out.println( "Resurecting alive fink" );
        }
    }
        

}
