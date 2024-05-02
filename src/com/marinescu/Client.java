package com.marinescu;

import java.util.ArrayList;

/**
 * This class represents a client object.
 */
public class Client {
    /**
     * The show's data.
     */
    private final String name, date, time, seat;

    /**
     * Constructs a new instance of the object.
     * @param name the name of the show
     * @param date the date of the show
     * @param time the time of the show
     * @param seats the chosen seats
     */
    Client(String name, String date, String time, ArrayList<String> seats){
        this.name = name;
        this.date = date;
        this.time = time;

        WindowManager windowManager = new WindowManager();
        seat = windowManager.transform(seats);
    }

    /**
     * Gets the name of the show.
     * @return the name of the show
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the show.
     * @return the date of the show
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time of the show.
     * @return the time of the show
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the chosen seats.
     * @return the chosen seats
     */
    public String getSeat() {
        return seat;
    }
}
