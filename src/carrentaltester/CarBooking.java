package carrentaltester;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
/*
see comment for makeBooking()
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.StringTokenizer;
*/

/**
 * @author Rama Nicholson
 * An instance of CarBooking represents a complete booking; it holds the start
 * and end date as well as the car number
 */
public class CarBooking {
    private LocalDate startDate;
    private LocalDate endDate;
    private Car car;
    private String carName;
    private int carNumber;
    private long totalDays;
    private double newRate;
    private double cost;
    
    public CarBooking(LocalDate startDate, LocalDate endDate, int carNumber,Car car){
        this.carNumber = carNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        calculateTotalDays();
        this.carName= car.getCarName();
        this.newRate = car.getCarRate();
        calculateCost(newRate, totalDays);        
    }
    
    /*originally I had implemented the following method which retrieved the
    required information about the user's selected car, but it is redundant now
    because I implemented the makeCars() methodin menuDisplay and made use of
    the Car class (as I was advised by Alfie Punoose). If this method is
    implemented, the Car and PremiumCar class become completely redundant.
    However, because both are included in the task sheet, I included this method
    as a comment
    */
    /*private void makeBooking(){
        try{
            File carList = new File("carlist.csv");
            Scanner fileScanner = new Scanner(carList);
            String[] carSelected = new String[6];
            for (int i = 0;i<(carNumber-1);i++){
                fileScanner.nextLine();
            }
            String currentLine = fileScanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(currentLine, ",");
            for (int i=0;i<6;i++){
                carSelected[i]=tokenizer.nextToken();
            }
            if (carSelected[4].equals("Premium")){
                newRate = Double.parseDouble(carSelected[5]) *1.05;
            }
            else{
                newRate = Double.parseDouble(carSelected[5]);
            }
            carName = carSelected[1];
        }
        catch (FileNotFoundException file){
            System.out.println("Car list not found; exiting...");
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Unknown error; exiting...");
            System.exit(0);
        }
    }*/
    
    /*
   Calculates the length of the booking in days
    */
    private void calculateTotalDays(){
        totalDays=(startDate.until(endDate, ChronoUnit.DAYS));
    }
    private void calculateCost(double newRate, long totalDays){
        cost = newRate * totalDays;
    }
    
    /*
    accessor method for the total cost of the booking
    */
    public double getCost(){
        return cost;
    }
    /*
    accessor method for the starting date of the booking
    */
    public LocalDate getStartDate(){
        return startDate;
    }
    /*
    accessor method for ending date of the booking
    */
    public LocalDate getEndDate(){
        return endDate;
    }
    /*
    accessor method for the car number
    */
    public int getCarNumber(){
        return carNumber;
    }
    /*
    accessor method for the length of the booking in days
    */
    public long getTotalDays(){
        return totalDays;
    }
    /*
    accessor method for the daily cost of the car selected
    */
    public double getNewRate(){
        return newRate;
    }
    /*
    accessor method for the selected car's name
    */
    public String getCarName(){
        return car.getCarName();
    }
}
