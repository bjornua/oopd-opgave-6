package galapagos6.model;

/**
 * Constants for controlling the simulation
 */

public class SimulationConstants
{
    public final int birthFreq;      // the reciprocal of the birth frequency
    public final int infectionPrice; // The price all finks pay for infections
    public final int lifeMax;        // The maximum amount of life points
    public final int newBornLife;    // The life points given to new borns
    public final int meanAge;        // The mean age of a fink when it dies
    public final int spreadAge;      // The spread of the age when a fink dies;
    
    public SimulationConstants( int birthFreq, 
                                int infectionPrice, 
                                int lifeMax, 
                                int newBornLife,
                                int meanAge,
                                int spreadAge )
    {
        this.birthFreq = birthFreq;
        this.infectionPrice = infectionPrice;
        this.lifeMax = lifeMax;
        this.newBornLife = newBornLife;
        this.meanAge = meanAge;
        this.spreadAge = spreadAge;
    }
}
