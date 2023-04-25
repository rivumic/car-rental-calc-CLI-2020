package carrentaltester;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.StringTokenizer;
/**
 * @author Rama Nicholson
 * MenuDisplay is tasked with holding methods that set the groundwork for the
 * rest of this program to function. It reads the dictionary given, formats and
 * displays its contents, notes the number of cars available, instantiates
 * objects of the Car class and accepts user input on whether they wish to make
 * a booking.
 */
public class MenuDisplay {
    /*
    displayGreeting() simply display an ASCII art welcome message
    */
    private Scanner keyboard = new Scanner(System.in);
    
    public void displayGreeting(){
        System.out.println("***************************************************"
                + "*********\n            Welcome to the Carrington Car Rental"
                + "\n**********************************************************"
                + "**");
    }
    /*
    displayCarList reads the dictionary, formats and displays each car listed
    therewith. All the while it keeps a count of the number of cars that are
    displayed and returns that value.
    */
    public int displayCarList(){
        int carCounter = 0;//stores the number of cars in the list
        try{//exception handling in case the dictionary can't be found
            File carList = new File("carlist.csv");//loads dictionary file
            Scanner fileScanner = new Scanner(carList);//Scanner reads said file
            System.out.println("Cars available for booking:\n"//ASCII art
                + "____________________________________________________________"
                    + "\n");
            System.out.println("CarNo.  Car Name       Seats Transmission Car "
                    + "Type Rate/Day($)\n------- --------       ----- ----"
                    + "-------- -------- -----------");//displays headings
            while (fileScanner.hasNextLine()){//prevent NoSuchElement exceptions
                String currentLine = fileScanner.nextLine();//loads next line
                StringTokenizer tokenizer =
                    new StringTokenizer(currentLine, ",");
                /*
                String Tokenizer allow the line to be broken into 6 distinct
                strings, each representing a different attribute of a car. The
                "," string denotes the end of a token in the dictionary.
                */
                String[] currentCar = new String[6];//array holding each token
                for (int i=0;i<6;i++){
                    currentCar[i]=tokenizer.nextToken();//stores tokens in array
                }
                System.out.printf("\n%-7s %-14s %-5s %-12s %-8s %-11s",currentCar[0]
                        ,currentCar[1],currentCar[2],currentCar[3],currentCar[4],
                        currentCar[5]);//formats and prints car information
                carCounter++;//adds the current car to overall count
            }
            System.out.println("\n______________________________________"
                    + "______________________");//formatting
        }   
        catch (FileNotFoundException file){//if dictionary is not found
            System.out.println("Car list not found; exiting...");
            //notification of error type
            System.exit(0);//exits
        }
        catch (Exception e){//catch all back up
            System.out.println("Unknown error; exiting...");
            //notification that FileNotFound wasn't the exception thrown
            System.exit(0);//exits
        }
    return carCounter;
    }
    /*
    menuHelper simply prints the number of cars available, as well as dislaimer
    that notifies the user of the %5 insurnace surcharge when booking a premium
    car
    */    
    public void menuHelper(int carsAvailable){
        System.out.println("There are "+carsAvailable+" models available"+"\n"
                + "**Note for premium cars, there is an additional 5% insurance"
                + " excess applied to the daily rate");
    }
    /*
    makeCars initialises an object of the Car/PremiumCar class for each car
    listed in the dictionary. The objects are returned in an array of type Car.
    PremiumCar extends Car, so they can both be stored in the same array.
    */
    public Car[] makeCars(int carsAvailable){
        Car[] cars = new Car[carsAvailable];/*defines array. The array is of
        size carsAvailable to increase program versatility in case the number of
        cars included in the dictionary changes in the future.
        */
        try{//exception handling in case the dictionary file is not found
            File carList = new File("carlist.csv");//loads file
            Scanner fileScanner = new Scanner(carList);//scans file
            int counter = 0;//used to cycle through the indexes of cars[]

            while (fileScanner.hasNextLine()){/*iterates for each line of the
                dictionary. Again, in an effort to make this method versatile,
                the while loop is used instead of a for loop in case the length
                of the dictionary changes.
                */
                String currentLine = fileScanner.nextLine();//loads next line
                StringTokenizer tokenizer =
                    new StringTokenizer(currentLine, ",");
                /*
                String Tokenizer allows a line to be broken into 6 distinct
                strings, each representing a different attribute of a car. The
                "," string denotes the end of a token in the dictionary.
                */
                String[] currentCar = new String[6];//will store "tokenized" Strings

                for (int i=0;i<6;i++){//iterates through all 6 tokens/line
                    currentCar[i]=tokenizer.nextToken();//stores each token
                }
                if (currentCar[4].equals("Standard")){/*
                    The if statement deliniates between Standard and Premium
                    cars, thus instantiating the appropriate class.
                    */
                    cars[counter]= new Car(currentCar[1],
                            Double.parseDouble(currentCar[5]));
                }
                else{
                    cars[counter]=new PremiumCar(currentCar[1],
                            Double.parseDouble(currentCar[5]));
                }
                counter++;//moves on to next index of cars[]
            }
        }
        catch (FileNotFoundException file){//if dictionary is not found
            System.out.println("Car list not found; exiting...");
            //notification of error type
            System.exit(0);//exits
        }
        catch (Exception e){//catch all back up
            System.out.println("Unknown error; exiting...");
            //notification that FileNotFound wasn't the exception thrown
            System.exit(0);//exits
        }
        return cars;
    }
    /*
    getSelection prompts the user to make a booking or exit the system, accepts
    an input and returns the input. Validation is included in case an unsuitable
    input entered.
    */
    public int getSelection(){
        System.out.println("************************************************\n"
                + "Please select from one of the following options:\n"
                + "\n\t1. To make a booking\n\t2. To exit the system\n\n"
                + "Please enter your selection: ");//prompts input
        int userChoice=0;//stores incumbent input
        while (userChoice==0){//keeps prompting until valid input is entered
            if (keyboard.hasNextInt()){//checks data type validity
                userChoice = keyboard.nextInt();//accepts input
                if (userChoice!=1&&userChoice!=2){
                    /*
                    if data type of the input is valid but the content is not
                    an expected value, the user is prompted to enter the input
                    again
                    */
                userChoice=0;
                System.out.println("\u001B[31mInvalid selection,"
                        + " please try again: \u001B[0m");
            }
            }
            else{//prompts re-input if data was unexcepted
                keyboard.next();
                System.out.println("\u001B[31mInvalid selection,"
                        + " please try again: \u001B[0m");
            }
        }
        return userChoice;
    }
}