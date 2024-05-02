package com.marinescu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a manager for the {@link Show}.
 */
public class ShowManager {
    /**
     * The name of the show.
     */
    private String name;
    /**
     * The list of dates when the show takes place.
     */
    private final ArrayList<String> dates = new ArrayList<>();
    /**
     * The list of times when the show takes place.
     */
    private final ArrayList<String> times = new ArrayList<>();
    /**
     * The list of seats of the show.
     */
    private final ArrayList<String> seats = new ArrayList<>();
    /**
     * A list of all available shows.
     */
    private Show[] shows;

    /**
     * Constructs a new instance of the object.
     */
    ShowManager()
    {
    }

    /**
     * Constructs a new instance of the object.
     * Initializes the object's values with values from the given file.
     * @param f the given file
     */
    ShowManager(File f)
    {
        try{
            FileReader fr=new FileReader(f);
            BufferedReader in=new BufferedReader(fr);

            String row;
            name=in.readLine();

            row=in.readLine();
            String [] words=row.split(" ");
            times.addAll(Arrays.asList(words));

            row=in.readLine();
            words=row.split(" ");
            dates.addAll(Arrays.asList(words));

            while((row=in.readLine())!=null)
            {
                words=row.split(" ");
                seats.addAll(Arrays.asList(words));

            }

            initializeAllShows();

            in.close();
        } catch(Exception e)
        {
            e.getStackTrace();
        }
    }

    /**
     * Initializes the list of all available shows.
     */
    public void initializeAllShows()
    {
        int index=0;
        int index1=0;

        ArrayList<String> aux=new ArrayList<>();

        shows=new Show[dates.size()*times.size()];

        for(int i=0;i<dates.size()*times.size();i++){
            for (String time : times) {
                for (int x = 0; x < 15; x++) {
                    aux.add(seats.get(index1));
                    index1++;
                }

                shows[index] = new Show(name, dates.get(i), time, aux);

                aux.clear();

                index++;
            }
        }}

    /**
     * Prints the show information in a given file.
     * @param f the given file
     */
    public void writeShow(File f)
    {
        try{
            PrintWriter print=new PrintWriter(f);
            print.println(getShow(0).getName());
            for(int i=0;i<2;i++)
            {
                print.print(getShow(i).getDate()+" ");
            }
            print.println();
            for(int i=0;i<2;i++)
            {
                print.print(getShow(i*2).getTime()+" ");
            }
            print.println();
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<15;j++)
                {
                    print.print(getShow(i).getSeats(j)+" ");
                }
                print.println();
            }
            print.close();
        }catch (IOException ef)
        {
            ef.getMessage();
        }
    }

    /**
     * This method adds a client to the total number of clients in a file.
     * @return The total number of clients.
     */
    public int addClientsNumber()
    {
        int id=0;

        try {
            String filePath = "Clients/Number.txt";
            BufferedReader in = new BufferedReader(new FileReader(filePath));

            String line = in.readLine();
            if (line!= null) {
                id = Integer.parseInt(line);

                Files.write(Paths.get(filePath), String.valueOf(id+1).getBytes());
            } else {
                System.err.println("Error: unable to read from file");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: file not found");
        } catch (IOException e) {
            System.err.println("Error: I/O error");
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid number format");
        }

        return id;
    }

    /**
     * Gets a show by its index.
     * @param i the index of the show
     * @return the show
     */
    public Show getShow(int i)
    {
        return shows[i];
    }

    /**
     * Gets the name of the show.
     * @return the name of the show
     */
    public String getName()
    {
        return name;
    }
}
