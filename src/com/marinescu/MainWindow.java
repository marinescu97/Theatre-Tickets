package com.marinescu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents the first window.
 */
public class MainWindow extends JFrame implements ActionListener {

    /**
     * A button of the window.
     */
    private final JButton okBtn, cancelBtn;

    /**
     * A combo box with data about a show.
     */
    private final JComboBox nameBox, dateBox, timeBox;

    /**
     * A file object.
     */
    private File file;

    /**
     * A variable with a value indicating whether a data was set or not.
     */
    private int setName = 0, setDate = 0, setTime = 0;

    /**
     * A {@link WindowManager} object.
     */
    private final WindowManager windowManager = new WindowManager();

    /**
     * Constructs the window.
     */
    MainWindow()
    {
        setTitle("Buy a ticket");

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10,10));

        formPanel.add(new JLabel("Select the show:"));

        nameBox = new JComboBox();
        formPanel.add(nameBox);

        dateBox = new JComboBox();
        formPanel.add(new JLabel("Select the date:"));
        formPanel.add(dateBox);

        formPanel.add(new JLabel("Select the time:"));
        timeBox = new JComboBox();
        formPanel.add(timeBox);

        add(formPanel);

        JPanel btnPanel = new JPanel();
        okBtn = new JButton("OK");
        okBtn.addActionListener(this);
        btnPanel.add(okBtn);

        cancelBtn=new JButton("CANCEL");
        cancelBtn.addActionListener(this);
        btnPanel.add(cancelBtn);

        add(btnPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets the values of the first choice box.
     * @param shows all shows
     */
    public void setName(ShowManager[] shows)
    {
        for(int i=0 ; i<shows.length;i++)
        {
            nameBox.addItem(shows[i].getName());
        }
        nameBox.addActionListener(this);
        nameBox.setSelectedIndex(0);
    }

    /**
     * Sets the values of the second choice box.
     * @param f the file from which the data is read
     */
    public void setDate(File f)
    {
        try{
            FileReader a = new FileReader(f);
            BufferedReader in = new BufferedReader(a);
            String row=" ";
            row = in.readLine();
            row = in.readLine();
            String [] words = row.split(" ");
            dateBox.removeAllItems();
            for(int i=0;i<words.length;i++)
            {
                dateBox.addItem(words[i]);
            }
        }catch (IOException e)
        {
            e.getStackTrace();
        }

        dateBox.addActionListener(this);
        dateBox.setSelectedIndex(0);
    }

    /**
     * Sets the values of the third choice box.
     * @param f the file from which the data is read
     */
    public void setTime(File f)
    {
        try{
            FileReader file = new FileReader(f);
            BufferedReader in = new BufferedReader(file);
            String row;

            row=in.readLine();
            row=in.readLine();
            row=in.readLine();
            String [] words = row.split(" ");
            timeBox.removeAllItems();
            for(int i=0;i<words.length;i++)
            {
                timeBox.addItem(words[i]);
            }
        }catch (IOException e)
        {
            e.getStackTrace();
        }

        timeBox.addActionListener(this);
        timeBox.setSelectedIndex(0);

    }

    /**
     * Performs the action for every selected item.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==nameBox)
        {
            JComboBox testName=(JComboBox) e.getSource();
            int choice= testName.getSelectedIndex();

            switch(choice)
            {
                case 0, 1, 2, 3, 4 ->
                {
                    file = windowManager.getFile(choice);
                    setName = choice;
                    setDate(file);
                    setTime(file);
                }
                case 5, 6, 7, 8 ->
                {
                    file = windowManager.getFile(choice);
                    setDate(file);
                    setTime(file);
                }
            }

        }

        if(e.getSource()==dateBox)
        {
            JComboBox testDate=(JComboBox)e.getSource();
            int choice= testDate.getSelectedIndex();

            switch(choice)
            {
                case 0, 1 -> setDate=choice;
            }
        }

        if(e.getSource()==timeBox)
        {
            JComboBox testTime=(JComboBox)e.getSource();
            int choice= testTime.getSelectedIndex();

            switch(choice)
            {
                case 0, 1, 2 -> setTime=choice;
            }
        }

        if(e.getSource()==cancelBtn)
            System.exit(0);

        if(e.getSource()==okBtn)
        {
            windowManager.initializeSeatsWindow(file,setName,setDate,setTime);
            this.setVisible(false);
        }
    }
}
