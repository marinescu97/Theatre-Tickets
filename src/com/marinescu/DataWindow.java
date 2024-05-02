package com.marinescu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a window for entering the client's data.
 */
public class DataWindow extends JFrame implements ActionListener {
    /**
     * The client's data.
     */
    private final JTextField firstName, lastName,phoneNumber,cost1,pay;
    /**
     * The "Confirm" button.
     */
    private final JButton confirm;
    /**
     * A {@link WindowManager} object.
     */
    private final WindowManager windowManager=new WindowManager();
    /**
     * A {@link Ticket} object.
     */
    private final Ticket ticket;
    /**
     * The show's data.
     */
    private final String showName, showDate, showTime;
    /**
     * The list of chosen seats.
     */
    private final ArrayList<String> seats;

    /**
     * Creates a new window and initializes its properties.
     * @param showName the name of the show
     * @param showDate the date of the show
     * @param showTime the time of the show
     * @param seats the list of selected seats
     * @param cost the cost of the ticket
     */
    DataWindow(String showName, String showDate, String showTime, ArrayList<String> seats,int cost)
    {
        this.showName = showName;
        this.showDate = showDate;
        this.showTime = showTime;

        seats.sort(Comparator.comparingInt(Integer::parseInt));
        this.seats = seats;

        String c = ""+cost;

        setTitle("Personal data");

        JPanel confirmPanel = new JPanel();
        JPanel formPanel = new JPanel();

        JPanel inForm = new JPanel(new GridBagLayout());

        formPanel.add(inForm);

        GridBagConstraints constraints=new GridBagConstraints();

        constraints.gridx=0;
        constraints.gridy=1;

        add(formPanel);

        inForm.add(new JLabel("First name"),constraints);
        constraints.gridy++;

        inForm.add(new JLabel("Last name"),constraints);
        constraints.gridy++;

        inForm.add(new JLabel("Phone number"),constraints);
        constraints.gridy++;

        inForm.add(new JLabel("Cost"),constraints);
        constraints.gridy++;

        inForm.add(new JLabel("Payment"),constraints);
        constraints.gridx++;
        constraints.gridy=1;

        firstName=new JTextField(8);
        inForm.add(firstName,constraints);
        constraints.gridy++;

        lastName=new JTextField(8);
        inForm.add(lastName,constraints);
        constraints.gridy++;

        phoneNumber=new JTextField(8);
        inForm.add(phoneNumber,constraints);
        constraints.gridy++;

        cost1=new JTextField(8);
        cost1.setText(c);
        cost1.setEditable(false);
        inForm.add(cost1,constraints);
        constraints.gridy++;

        pay=new JTextField(8);
        inForm.add(pay,constraints);

        confirm=new JButton("Confirm");
        confirm.addActionListener(this);

        confirmPanel.add(confirm);

        add(confirmPanel,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticket=new Ticket(showName, showDate, showTime, seats,cost);

    }

    /**
     * Performs the action for the “Confirm” button.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirm)

            if(validPayment(cost1.getText(), pay.getText()))
            {

                if(validPhoneNumber(phoneNumber.getText()) &&!firstName.getText().equals("")&&!lastName.getText().equals(""))
                {
                    Client client = new Client(showName, showTime, showDate, seats);
                    windowManager.initializeTicketWindow(showName,showTime,showDate,seats, client);
                    ticket.setData(firstName.getText(), lastName.getText(),phoneNumber.getText());
                    ticket.createTicket();
                }
                else {
                    JOptionPane.showMessageDialog(null, "All fields must be filled in correctly", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
    }

    /**
     * Validates the phone number.
     * @param str the phone number
     * @return true if the phone number is valid, false otherwise
     */
    public static boolean validPhoneNumber(String str)
    {
        try
        {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "The phone number is not correct", "Phone number error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if the amount of payment is valid or not.
     * @param amount1 The cost of the ticket.
     * @param amount2 The entered payment amount.
     * @return true if the payment amount is valid, false otherwise.
     */
    public static boolean validPayment(String amount1,String amount2)
    {
        try
        {
            double cost = Double.parseDouble(amount1);
            double payment=Double.parseDouble(amount2);

            if(payment > cost)
            {
                JOptionPane.showMessageDialog(null, "The amount entered is too high, it will return you $"+(payment-cost), "Returned amount", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

            if(payment < cost)
            {
                JOptionPane.showMessageDialog(null, "The entered amount is too small", "Payment error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }
        catch(NumberFormatException ne)
        {
            JOptionPane.showMessageDialog(null, "Please enter the correct amount", "Payment error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
