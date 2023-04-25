package carrentaltester;

import java.util.Scanner;
/**
 * @author Rama Nicholson
 * An instance of Customer prompts the user of the program to enter their name,
 * email and address and stores them in the instance variables.
 */
public class Customer {
    Scanner keyboard = new Scanner(System.in);
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    
    public Customer(){
        System.out.println("Your name: ");
            this.customerName = keyboard.nextLine();
            System.out.println("Your Email: ");
            this.customerEmail = keyboard.nextLine();
            System.out.println("Your residential address: ");
            this.customerAddress = keyboard.nextLine();
    }
    
    /*
    accessor method for customerName
    */
    public String getcustomerName(){
        return customerName;
    }
    
    /*
    //accessor method for customerAddress
    */
    public String getcustomerAddress(){
        return customerAddress;
    }
    
    /*
    //accessor method for customerEmail
    */
    public String getcustomerEmail(){
        return customerEmail;
    }
}