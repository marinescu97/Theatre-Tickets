package com.marinescu;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents a manager for all windows.
 */
public class WindowManager {
    /**
     * Shows' files.
     */
    private File[] files;

    /**
     * A list of all shows.
     */
    private ShowManager[] showManager;

    /**
     * The main window.
     */
    private MainWindow mainWindow;

    /**
     * Constructs a new instance of the object.
     * Initializes the list of the shows' files.
     */
    WindowManager()
    {
        File f=new File("Shows");
        files=f.listFiles();
    }

    /**
     * Initializes the main window.
     */
    public void initializeMainWindow()
    {
        File f=new File("Shows");
        files=f.listFiles();

        showManager=new ShowManager[Objects.requireNonNull(files).length];
        for(int i=0;i<showManager.length;i++)
        {
            showManager[i]=new ShowManager(files[i]);
        }

        mainWindow=new MainWindow();

        mainWindow.setVisible(true);
        mainWindow.setSize(400,200);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setName(showManager);
    }

    /**
     * Initializes the seats window.
     * @param c a file
     * @param f the name of the show
     * @param a the date of the show
     * @param b the time of the show
     */
    public void initializeSeatsWindow(File c, int f,int a,int b)
    {
        SeatsWindow seatsWindow = new SeatsWindow(c, f, a, b);
        seatsWindow.setVisible(true);
        seatsWindow.setSize(400,260);
        seatsWindow.setResizable(false);
        seatsWindow.setLocationRelativeTo(null);
    }

    /**
     * Initializes the {@link DataWindow}.
     * @param name the name of the show
     * @param date the date of the show
     * @param time the time of the show
     * @param seats the seats
     * @param cost the cost of the ticket
     */
    public void initializeDataWindow(String name, String date, String time, ArrayList<String> seats, int cost)
    {
        DataWindow dataWindow = new DataWindow(name, date, time, seats, cost);
        dataWindow.setVisible(true);
        dataWindow.setSize(400,200);
        dataWindow.setResizable(false);
        dataWindow.setLocationRelativeTo(null);
    }

    /**
     * Initializes the {@link TicketWindow}.
     * @param name the name of the show
     * @param date the date of the show
     * @param time the time of the show
     * @param seats the seats
     * @param client the client
     */
    public void initializeTicketWindow(String name,String date,String time,ArrayList<String> seats,Client client)
    {
        TicketWindow ticketWindow = new TicketWindow(name, date, time, seats, client);
        ticketWindow.setVisible(true);
        ticketWindow.setSize(400,160);
        ticketWindow.setResizable(false);
        ticketWindow.setLocationRelativeTo(null);

    }

    /**
     * Gets a file.
     * @param i the index of the file
     * @return the file
     */
    public File getFile(int i)
    {
        return files[i];
    }

    /**
     * Gets the main window.
     * @return the main window
     */
    public MainWindow getMainWindow()
    {
        return mainWindow;
    }

    /**
     * Gets a show.
     * @param i the index of the show
     * @return the show
     */
    public ShowManager showManager(int i)
    {
        return showManager[i];
    }

    /**
     * Gets the number of the files (shows).
     * @return the number of the files
     */
    public int filesLength()
    {
        return files.length;
    }

    /**
     * Converts a list of seats into a string.
     * @param seats the list of seats
     * @return the list of seats as a string
     */
    public String transform(ArrayList<String> seats)
    {
        StringBuilder a= new StringBuilder();
        for(String s : seats)
        {
            a.append(s).append(" ");
        }

        return a.toString();
    }
}
