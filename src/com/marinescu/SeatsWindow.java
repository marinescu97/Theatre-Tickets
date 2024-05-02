package com.marinescu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a window for choosing the seats at a show.
 */
public class SeatsWindow extends JFrame implements ActionListener {
    /**
     * The list of buttons representing the seats.
     */
    private final JButton[] seatsBtn = new JButton[15];
    /**
     * The buttons from the bottom of the window.
     */
    private final JButton nextBtn, backBtn;
    /**
     * The list of chosen seats.
     */
    private final ArrayList<String> chosen = new ArrayList<>();
    /**
     * A {@link WindowManager} object.
     */
    private WindowManager windowManager;
    /**
     * A list of shows.
     */
    private final ShowManager[] shows;
    /**
     * A {@link File} object.
     */
    private final File f;
    /**
     * An index of the show's data.
     */
    private final int name, date, time;
    /**
     * A {@link Ticket} object.
     */
    private final Ticket ticket = new Ticket();
    /**
     * The cost of the ticket.
     */
    private int cost = 0;

    /**
     * Initializes the window.
     * @param file a file
     * @param name the name of the show
     * @param date the date of the show
     * @param time the time of the show
     */
    SeatsWindow(File file,int name,int date,int time)
    {
        this.f=file;
        this.date=date;
        this.time=time;
        this.name=name;

        setTitle("Choose the seats");

        JPanel stagePanel = new JPanel();
        stagePanel.setBackground(Color.GREEN);

        JLabel stageLbl = new JLabel("STAGE");
        stageLbl.setFont(new Font("Serif",Font.BOLD,30));
        stageLbl.setForeground(Color.DARK_GRAY);

        stagePanel.add(stageLbl);

        JPanel seatsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        for(int i=0;i<seatsBtn.length;i++)
        {
            seatsBtn[i]=new JButton("L"+(i+1));
            seatsPanel.add(seatsBtn[i]);
            seatsBtn[i].addActionListener(this);
        }

        JPanel btnPanel = new JPanel();
        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        btnPanel.add(nextBtn);
        btnPanel.add(backBtn);

        add(seatsPanel);
        add(stagePanel,BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);

        windowManager = new WindowManager();
        shows = new ShowManager[windowManager.filesLength()];

        windowManager.initializeMainWindow();
        windowManager.getMainWindow().setVisible(false);
        for(int i=0;i<shows.length;i++)
        {
            shows[i]=windowManager.showManager(i);
        }
        for(int i=0;i<seatsBtn.length;i++)
        {
            if(shows[name].getShow(date+2*time).getSeats(i)==1)
            {
                seatsBtn[i].setEnabled(false);
            }
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Performs an action for every selected item in the window.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nextBtn&& chosen.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please select a seat", "Warning", JOptionPane.ERROR_MESSAGE);
        }

        if(e.getSource()==nextBtn&& !chosen.isEmpty())
        {
            windowManager.initializeDataWindow(shows[name].getName(), shows[name].getShow(date+2*time).getDate(),shows[name].getShow(date+2*time).getTime(),chosen,cost);
            shows[name].writeShow(f);
            this.dispose();
        }
        if(e.getSource()==backBtn)
        {
            this.dispose();
            windowManager=new WindowManager();
            windowManager.initializeMainWindow();
        }
        for(int i=0;i<seatsBtn.length;i++)
            if(e.getSource()==seatsBtn[i]){
                seatsBtn[i].setEnabled(false);
                String seat=""+(i+1);
                chosen.add(seat);
                shows[name].getShow(date+2*time).setSeat(i);
                cost=ticket.calculateCost();
            }
    }
}
