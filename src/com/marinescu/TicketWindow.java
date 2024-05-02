package com.marinescu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class represents a window with information about a ticket.
 */
public class TicketWindow extends JFrame implements ActionListener {
    /**
     * A {@link Client} object.
     */
    private final Client client;

    /**
     * Constructs a new window.
     * @param name the name of the show
     * @param date the date of the show
     * @param time the time of the show
     * @param seats the chosen seats
     * @param client the client
     */
    TicketWindow(String name, String date, String time, ArrayList<String> seats, Client client)
    {
        setTitle("Ticket");

        JPanel inInfo = new JPanel(new GridBagLayout());
        JPanel infoPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        GridBagConstraints c=new GridBagConstraints();
        c.gridy=0;

        inInfo.add(new JLabel("Name: "),c);
        inInfo.add(new JLabel(name),c);
        c.gridy++;
        c.gridy++;

        inInfo.add(new JLabel("Date: "),c);
        inInfo.add(new JLabel(date),c);
        c.gridy++;

        inInfo.add(new JLabel("Time: "),c);
        inInfo.add(new JLabel(time),c);
        c.gridy++;

        inInfo.add(new JLabel("Seats: "),c);

        WindowManager windowManager = new WindowManager();
        inInfo.add(new JLabel(windowManager.transform(seats)),c);

        infoPanel.add(inInfo);

        JButton print = new JButton("Print");
        print.addActionListener(this);

        btnPanel.add(print);
        add(infoPanel);
        add(btnPanel,BorderLayout.SOUTH);

        this.client=client;

    }

    /**
     * Performs the action for the “Print” button.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            PrintWriter write=new PrintWriter("Ticket.txt");
            write.println("Show name: "+client.getName());
            write.println("Show time: "+client.getTime());
            write.println("Show date: "+client.getDate());
            write.println("Seats: " +client.getSeat());
            write.close();

        }   catch (Exception  ex) {
            throw new RuntimeException(ex);
        }
    }
}
