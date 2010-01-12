package galapagos6.model;

import galapagos6.model.behavior.*;

/**
 * A class implementing a finch model.
 * @author Philip
 *
 */
public class Finch implements Comparable<Finch>
{
    public enum State { DEAD, ALIVE };
    
    private int energy;
    private Behavior behavior;
    private int age;
    private int maxAge;
    private int reproductionTurn;
    private boolean groomedThisTurn;
    private State state;
    private int lastBirth;
    
    /**
     * Constructs a new, dead finch.
     */
    public Finch()
    {
        state = State.DEAD;
    }
    
    /**
     * Constructs a new finch with parameters.
     * @param energy
     * @param maxAge
     * @param reproductionTurn
     * @param strategy
     */
    public Finch( int energy, int maxAge, int reproductionTurn, Behavior strategy )
    {
        this.energy = energy;
        this.behavior = strategy;
        age = 0;
        this.maxAge = maxAge;
        this.reproductionTurn = reproductionTurn;
        groomedThisTurn = false;
        state = State.ALIVE;
        lastBirth = 0;
    }
    
    /**
     * Compare to another finch.
     */
    public int compareTo( Finch o )
    {
        int result = 0;
        
        if( (Object)this != o )
        {
            result = 1;
        }
        
        return result;
    }
    
    /**
     * Make the finch give birth.
     */
    public void giveBirth()
    {
        lastBirth = age;
    }
    
    /**
     * The last age at which the finch gave birth.
     * @return Last birth age.
     */
    public int lastBirthAge()
    {
        return lastBirth;
    }
    
    /**
     * The amount of energy the finch has.
     * @return energy
     */
    public int energyAmount()
    {
        return energy;
    }
    
    /**
     * Remove energy.
     * @param amount The amount of energy to be removed
     */
    public void removeEnergy( int amount )
    {
        energy -= amount;
    }
    
    /**
     * Add energy to finch.
     * @param amount Amount to add.
     */
    public void addEnergy( int amount )
    {
        energy += amount;
    }
    
    /**
     * Try grooming.
     * @param f Finch to possibly groom.
     * @return If finch was groomed.
     */
    public boolean tryGroom( Finch f )
    {
        return behavior.groom( f );
    }
    
    /**
     * Behavior of finch.
     * @return The behavior.
     */
    public Behavior behavior()
    {
        return behavior;
    }
    
    /**
     * Who groomed the finch last.
     * @param f 
     * @param groomed
     */
    public void groomedBy( Finch f, boolean groomed )
    {
        behavior.groomedBy( f, groomed );
    }
    
    /**
     * Did we groom this turn.
     * @param b Did we groom this turn.
     */
    public void setGroomedThisTurn( boolean b )
    {
        groomedThisTurn = b;
    }
   
    /**
     * Did we groom this turn.
     * @return Did we groom this turn.
     */
    public boolean getGroomedThisTurn()
    {
        return groomedThisTurn;
    }
    
    /**
     * The finch is one year older.
     */
    public void birthday()
    {
        ++age;
    }
    
    /**
     * Age of finch. 
     * @return Age of finch.
     */
    public int age()
    {
        return age;
    }
        
    /**
     * Was the finch killed naturally.
     * @return Was the finch killed naturally.
     */
    public boolean getKilledNatural()
    {
        return age > maxAge;
    }
    
    /**
     * Was the finch killed by ticks.
     * @return Was the finch killed by ticks.
     */
    public boolean getKilledTicks()
    {
        return energy < 0;
    }
    
    /**
     * 
     * @return
     */
    public int reproductionTurn()
    {
        return reproductionTurn;
    }
    
    /**
     * Kill the finch.
     */
    public void kill()
    {
        state = State.DEAD;
    }
    
    /**
     * Resurrects the finch.
     * @param energy
     * @param maxAge
     * @param reproductionTurn
     * @param strategy
     */
    public void resurrect( int energy, int maxAge, int reproductionTurn, Behavior strategy )
    {
        this.energy = energy;
        this.maxAge = maxAge;
        this.reproductionTurn = reproductionTurn;
        this.behavior = strategy;
        age = 0;
        groomedThisTurn = false;
        state = State.ALIVE;
    }
    
    /**
     * Gets the state of the finch.
     * @return the state of the finch.
     */
    public State state()
    {
        return state;
    }

}