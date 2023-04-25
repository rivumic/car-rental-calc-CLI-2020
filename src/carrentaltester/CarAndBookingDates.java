package carrentaltester;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.Year;
import java.time.Month;
import java.time.YearMonth;

/**
 * @author Rama Nicholson
 * CarAndBookingDates includes methods that prompt the user to enter which
 * car they wish to rent, as well as the start and end date for the prospective
 * rental. The obtained values are returned by said methods.
 */
public class CarAndBookingDates {
    private int year;
    private int month;
    private int day;    
    private Scanner keyboard = new Scanner(System.in);
    
    /*
    constructor which initialises instance variables to default values
    */
    public CarAndBookingDates (){
        this.year = 0;
        this.month = 0;
        this.day = 0;
    }
    
    /*
    carSelection prompts the user for their selection of car, and returns the
    value, once acquired.
    */
    public int carSelection(int carsAvailable){
        System.out.print("Please select a car number from the car list: ");
        int carSelection = 0;//holds user's input
        do {
            if (keyboard.hasNextInt()){//checks data type validity
                carSelection = keyboard.nextInt();//accepts input
                if (carSelection>carsAvailable||carSelection<1){
                    /*if data type of the input is valid but the content is not
                    an expected value, the user is prompted to enter the input
                    again*/
                    System.out.print("\u001B[31mInvalid car selection,"
                            + " please try again: \u001B[0m");
                }
            }
            else {//prompts re-input if data was unexcepted
                keyboard.next();
                System.out.print("\u001B[31mInvalid car selection,"
                        + " please try again: \u001B[0m");
            }
        } while (carSelection>carsAvailable||carSelection<1);
        //"while" keeps prompting until valid input is entered
            return carSelection;
    }
    /*
    getStartDate prompts the user for and returns the starting date of their
    booking. This type of method is not completely necessary for the startDate,
    but it is implemented so that it matches the getEndDate implementation which
    does have some benefits.
    */
    public LocalDate getStartDate(){
        System.out.println("Enter booking start date:\n");
        return getCarBookingDateFull();//returns full date
    }
    /*
    getEndDate prompts the user for and returns the end date of their booking.
    Also included is a while loop that checks if the endDate is before the start
    date. If so, the user must input the end date again.    
    */
    public LocalDate getEndDate(LocalDate startDate){
        System.out.println("Enter booking end date:\n");
            LocalDate endDate = getCarBookingDateFull();//returns full date
            while (!endDate.isAfter(startDate)){//checks if input is after start date
                System.out.println("\u001B[31mThe end date must be after the"
                        + " start date. Please enter an end date as such"
                        + ":\u001B[0m");
                endDate= getCarBookingDateFull();//gets input again if required
            }
        return endDate;
    }
    /*
    getCarBookingDateFull is implemented as a helper method that prompts the
    user for input of the date of either the starting or ending date of their
    booking.
    */
    public LocalDate getCarBookingDateFull(){
        promptForYear();//helper method that returns inputted year
        promptForMonth();//helper method that returns inputted month
        promptForDay();//helper method that returns inputted day
        LocalDate fullDate = LocalDate.of(year, month, day);//combines inputs
        return fullDate;
    }
    /*
    promptForYear is a helper method that prompts the user to input a year,
    then validates that the year is valid and returns the value.
    */
    public int promptForYear(){
        System.out.println("Please enter the year - for example '2020': ");
        year = 0;//re-initialised to zero with each call to allow repeat bookings
        while (year==0){//loops until suitable value is attained
            if (keyboard.hasNextInt()){//checks for unsuitable data types
                year = keyboard.nextInt();//accepts input
                
                /*if (year<Year.now().getValue()){//rejects years prior to current year
                year=0;
                System.out.println("\u001B[31mA date before the current date,"
                        + " cannot be selected. Please try again:\u001B[0m");
                }*/
                
            }
            else {//rejects non-integer inputs
                keyboard.next();//moving scanner to next token stops infinite loop
                System.out.println("\u001B[31mInvalid year entry, please"
                        + " try again: \u001B[0m");
            }
            
        }
        return year;
    }
    /*
    promptForMonth is a helper method that prompts the user to input a month of
    the year, then validates that the input is valid and returns the value.
    */
    public int promptForMonth(){
        System.out.println("Please enter the month - for example '4': ");
        month = 0;// as above
        while (month==0){//as above
            if (keyboard.hasNextInt()){//as above
                month = keyboard.nextInt();//as above
                /*
                checks if the combination of year/month is before the current
                year/month, and rejects it if it is. The check only occurs if
                the input is between 1-12 inclusive, becuase if it isn't, then
                a different message should be displayed - see lines 143-147.
                */
                /*if (YearMonth.of(year, month).isBefore(YearMonth.now())&&
                        (month>0&&month<13)                        ){
                    System.out.println("\u001B[31mA date before the current"
                            + " date, cannot be selected."
                            + " Please try again:\u001B[0m");
                    month = 0;//ensures loop will be re-entered
                }*/
                
                if (month>12||month<1){//checks for non-existent month values
                    System.out.println("\u001B[31mInvalid month entry,"
                            + " please try again: \u001B[0m");
                    month = 0;//ensures loop will be re-entered
                }
            }
            else {//rejects non-integer inputs
                keyboard.next();//moving scanner to next token stops infinite loop
                System.out.println("\u001B[31mInvalid month entry,"
                        + " please try again: \u001B[0m");
            }
        }
        return month;
    }
    /*
    promptForDay is a helper method that prompts the user to input a day of
    the month, then validates that the input is valid and returns the value.
    */
    public int promptForDay(){
        Month userMonth = Month.of(month);//ges user-inputted month
        System.out.println("Please enter the day - for example '5': ");
        day = 0;//as above
        while (day==0){//as above
            if (keyboard.hasNextInt()){//as above
                day = keyboard.nextInt();//as above
                if (day<1){//rejects inputs that are less than 1
                    System.out.println("\u001B[31mInvalid day entry,"
                        + " please try again:\u001B[0m");
                }
                /*
                rejects days that don't exist in the month selected, for exmaple
                November 32. It also checks the user-inputted year to check for
                leap years. Thus, February 29 will be accepted if a leap year
                is selected but rejected if otherwise.
                The if statement doesn't trigger is the valeu of day is 0 so 
                that it doesn't overlap with the previous if statement
                */
                if (day!=0&&day>userMonth.length(Year.isLeap(Long.valueOf(year)))){
                    day=0;
                    System.out.println("\u001B[31mPlease enter a day that"
                            + " exists in the month/year selected: \u001B[0m");
                }
                /*
                checks if the full date is before the current local date.
                */
                /*if (day!=0&&LocalDate.of(year, month,day).isBefore(LocalDate.now())){
                    System.out.println("\u001B[31mA date before the current"
                            + " date, cannot be selected."
                            + " Please try again:\u001B[0m");
                    day = 0;
                }*/
            }
            else {//rejects non-integer inputs
                keyboard.next();//moving scanner to next token stops infinite loop
                System.out.println("\u001B[31mInvalid day entry,"
                        + " please try again: \u001B[0m");
            }
        }
        return day;
    }
}
