/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrentaltester;
import java.text.NumberFormat;
/**
 * @author Rama Nicholson
 * PrintBookingDetails collates all the information that has been gathered from
 * the dictionary and user inputs, that is relevant to the booking, and displays
 * it in an orderly format.
 */
public class PrintBookingDetails {
    
    //formats the currency values
    private NumberFormat fCurrency = NumberFormat.getCurrencyInstance();
    
    /*
    PrintBookingDetails is the constructor for the class and also displays the
    values contained within its parameters.
    */
    public PrintBookingDetails(CarBooking carBooking, Customer customer){
        System.out.printf("----------------------------------------------------"
            + "--------\nThanks for your booking, "+customer.getcustomerName()
            + "\nEmail: "+customer.getcustomerEmail()+"\nAddress: "
            +customer.getcustomerAddress()+"\n---------------------------------"
            + "---------------------------\n\nBooking Confirmed\n\nHere's your "
            + "booking summary:\n.............................................."
            + "..............\n\nCar Selected:                   %-10s\nStart d"
            + "ate:                     %-10s\nEnd date:                       "
            + "%-10s\nNumber of days booked:          %-10d\nCar rate per day: "
            + "              %-10s\nTotal cost:                     %-10s\n",
            carBooking.getCarName(),carBooking.getStartDate(),
            carBooking.getEndDate(),carBooking.getTotalDays(),
            fCurrency.format(carBooking.getNewRate()),
            fCurrency.format(carBooking.getCost()));
    }
}
