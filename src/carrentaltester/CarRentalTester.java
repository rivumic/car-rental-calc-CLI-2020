package carrentaltester;

import java.time.LocalDate;
import java.util.Scanner;
/**
 * @author Rama Nicholson
 * 11.10.2020
 * This program facilitates the creation of car rental bookings. The program
 * reads a dictionary file that contains a list of vehicles available for hire, as
 * well as information about them. The program then prompts and accepts user
 * input regarding the start and end date of the prospective hire, as well as
 * the user's name, address and email address. Finally, the total cost of the
 * booking is calculated and displayed to the user, in addition to the
 * selections and information that they've entered as a confirmation.
 */
public class CarRentalTester {
    /**
     * @param args the command line arguments
     * The main method calls methods for displaying the menu, prompting and
     * accepting user input, validating user input, calculating the cost of the
     * booking and displaying a summary of the booking.
     */
    public static void main(String[] args){        
        Scanner keyboard = new Scanner(System.in);//facilitates ueser input
        MenuDisplay menuDisplay = new MenuDisplay();//access to menuDisplay
        menuDisplay.displayGreeting();//displays ASCII art welcom message
        int carsAvailable = menuDisplay.displayCarList();/*
        reads dictionary, and records the number of available cars, while also
        displaying the name, cost and other information about each car
        */
        menuDisplay.menuHelper(carsAvailable);//prints premium car surcharge
        Car[] cars = menuDisplay.makeCars(carsAvailable);/*
        creates instance of the Car class for each car listed in the dictionary
        */
        int userChoice = menuDisplay.getSelection();//choice to book or exit
        while (userChoice==1){//facilitates consecutive bookings
            
            CarAndBookingDates carAndBookingDates = new CarAndBookingDates();
            //access to CarAndBookingDates            
            int carSelection = carAndBookingDates.carSelection(carsAvailable);
            //stores the user's choice of car            
            
            LocalDate startDate = carAndBookingDates.getStartDate();
            //prompts for and accepts start date            
            LocalDate endDate = carAndBookingDates.getEndDate(startDate);
            //prompts for and accepts endDate            
            CarBooking carBooking = new CarBooking(startDate,endDate,
            carSelection,cars[carSelection-1]);//computes total cost of booking            
            Customer customer = new Customer();//prompts/and accepts user info            
            PrintBookingDetails printer = new PrintBookingDetails
            (carBooking,customer);//formats and prints booking summary
            
            userChoice = menuDisplay.getSelection();//choice to book or exit
        }
    }        
}
