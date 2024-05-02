package com.marinescu;

import java.util.ArrayList;

/**
 * This class represents a show.
 */
public class Show {
    /**
     * The name of the show.
     */
    private final String name;
    /**
     * A date when the show takes place.
     */
    private final String date;
    /**
     * A time when the show takes place.
     */
    private final String  time;
    /**
     * The seats of the show.
     */
    private final int [] seats;

    /**
     * Constructs a new instance of the show.
     * @param name The name of the show.
     * @param date The date when the show takes place.
     * @param time The time when the show takes place.
     * @param seats The seats of the show.
     */
    Show(String name,String date,String time,ArrayList<String>seats)
    {
        this.name=name;
        this.date=date;
        this.time=time;
        this.seats=new int[seats.size()];

        for(int i=0;i<seats.size();i++)
        {
            this.seats[i]=Integer.parseInt(seats.get(i));
        }
    }

    /**
     * Gets the name of the show.
     * @return the name of the show
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date when the show takes place.
     * @return the date when the show takes place
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time when the show takes place.
     * @return the time when the show takes
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets a seat of the show.
     * @param i the seat index
     * @return the seat of the show
     */
    public int  getSeats(int i) {
        return seats[i];
    }

    /**
     * Sets a seat of the show.
     * @param i the seat index
     */
    public void setSeat(int i) {
        this.seats[i]=1;
    }
}
