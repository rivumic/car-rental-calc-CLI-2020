/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentaltester;

/**
 * @author Rama Nicholson
 * PremiumCar holds information about a specific car that is of type "Premium".
 * "Premium" cars have a must have their daily rate increased by 5% before cost
 * calculation, which this class facilitates.
 */
public class PremiumCar extends Car {
    private final double INSURANCE_RATE = 1.05;//holds premium insurance rate
    
    /*
    This constructor initialises the carName and carRate, and subsequently
    multiplies the rate by the INSURANCE_RATE constant to add the appropriate
    "premium" surcharge.
    */
    public PremiumCar(String carName, double carRate){
        super(carName, carRate);//constructor from Car
        this.carRate = carRate*INSURANCE_RATE;//adds the extra insurance cost
    }
    /*
    overrides the carRate accessor method from Car; this method retrieves the
    updated "premium" rate
    */
    public double getCarRate(){
        
        return carRate;
    }
}
