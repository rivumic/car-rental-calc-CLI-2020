/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentaltester;

/**
 *@author Rama Nicholson
 * The Car class holds information about a specific car.
 */
public class Car {
    /*
    both instance variables are package-private so that they can be accessed by
    the PremiumCar class, which extendes this class.
    */    
    double carRate;
    String carName;
    
    public Car(String carName,double carRate){
        this.carName = carName;
        this.carRate = carRate;
    }
    
    public double getCarRate(){//accessor method for carRate
        return carRate;
    }
    public String getCarName(){//accessor method for carName
        return carName;
    }
}
