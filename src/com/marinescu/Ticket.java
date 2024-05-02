package com.marinescu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class represents a ticket for a show.
 */
public class Ticket {
    /**
     * The chosen seats.
     */
    private ArrayList<String> seats=new ArrayList<>();
    /**
     * The ticket data.
     */
    private String showName,showDate,showTime,firstName,lastName,phoneNumber;
    /**
     * The total cost.
     */
    private int theCost;
    /**
     * A {@link ShowManager} object.
     */
    private final ShowManager showManager=new ShowManager();
    /**
     * A sum.
     */
    private int sum=0;

    /**
     * Creates an empty {@link Ticket} object.
     */
    public Ticket() {
    }

    /**
     * Creates a new {@link Ticket} object and initializes its variables.
     * @param showName The name of the show.
     * @param showDate The date of the show.
     * @param showTime The time of the show.
     * @param seats The chosen seats.
     * @param theCost The total cost.
     */
    Ticket(String showName, String showDate, String showTime, ArrayList<String> seats, int theCost){
        this.showName = showName;
        this.showDate = showDate;
        this.showTime = showTime;
        this.seats = seats;
        this.theCost = theCost;
    }

    /**
     * Sets the client's data.
     * @param firstName the first name
     * @param lastName the last name
     * @param phoneNumber the phone number
     */
    public void setData(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Calculates the total cost.
     * @return total cost
     */
    public int calculateCost(){
        int cost = 12;
        return sum+= cost;
    }

    /**
     * Prints the ticket to a file in the “Clients” directory.
     */
    public void createTicket()
    {
        int id=showManager.addClientsNumber();
        id=id*100+15*2456+16;
        try(PrintWriter print = new PrintWriter("Clients/Client_"+id+".txt")){
            
            print.println("First name: " + firstName);
            print.println("Last name: " + lastName);
            print.println("Phone number: " + phoneNumber);
            print.println("ID Client: " + id);
            print.println("Show: " + showName);
            print.println("Date: " + showDate);
            print.println("Time: " + showTime);
            print.println("Seats: " + seats);
            print.println("Total payment: " + "$" + theCost);

        }catch(Exception e)
        {
            e.getStackTrace();
        }
    }
}
