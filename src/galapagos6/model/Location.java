package galapagos6.model;

/**
 * A location on a map.
 * @author Philip
 *
 */
public class Location implements Cloneable
{
    public Location( int x, int y )
    {
        this.x = x;
        this.y = y;
    }
        
//    public boolean equals( Object o )
//    {
//        Location l = ( Location ) o;
//        
//        if( l == null ) 
//            return false; 
//        else
//            return l.x == x && l.y == y;
//    }
//    
//    public int compare( Object o1, Object o2 )
//    {
//        Location lhs = (Location) o1;
//        Location rhs = (Location) o2;
//        
//        int result = 0;
//        
//        if( lhs.x < rhs.x ) result = -1;
//        if( lhs.x == rhs.x )
//        {
//            if( lhs.y < rhs.y ) result = -1;
//            if( lhs.y == rhs.y ) result = 0;
//            if( lhs.y > rhs.y ) result = 1;
//        }
//        if( lhs.x > rhs.x ) result = 1;
//        
//        return result;
//    }

    public Location clone()
    {
        return new Location( x, y );
    }
    
    public String toString()
    {
        return "( " + x + ", " + y + " )";
    }
    
    public int x()
    {
        return x;
    }
    
    public void x( int x )
    {
        this.x = x;
    }
    
    public int y()
    {
        return y;
    }
    
    public void y( int y )
    {
        this.y = y;
    }
    
    private int x;
    private int y;
}