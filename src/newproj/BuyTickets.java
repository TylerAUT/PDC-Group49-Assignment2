package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tscos
 */
public class BuyTickets extends JPanel {

    public JLabel titleLabel, chooseLabel;
    JComboBox<String> destinationList;
    JButton proceedButton, confirmButton;
    JTextField amountOfTickets;
    JLabel confirmLabel, costLabel, purchaseConfirmationLabel;
    Destinations[] desArray = new Destinations[5];

    DefaultListModel destinations = new DefaultListModel<>();
    ArrayList<Destinations> destsReadFromFile = new ArrayList<Destinations>();

    ArrayList<Destinations> desList = new ArrayList<Destinations>();
    int[] availSeats = new int[5];
    JPanel topPanel, bottomPanel, buyPanel, confirmPanel, centerPanel, textPanel;
    JList list = new JList<>(destinations);
    String loggedInAccount;
    String enteredAmount;
    int[] prices = new int[]{100, 200, 245, 95, 220};

    public BuyTickets(String account) {
        loggedInAccount = account;
        windowContent();
        panelManager();
        loadDestinations();
        actionListens();
        getIndex();

    }

    public void storeBooking(String location, int cost, int seats) {
        Random rand = new Random();
        int randNum = rand.nextInt(1000);

        String out = randNum + "|" + location + "|" + cost * seats + "|" + seats + "|" + cost;
        WriteToFile wtf = new WriteToFile(out, loggedInAccount + "boughtTickets", true);

    }

    public void changeSeatsAvail() {
        Destinations d1 = new Destinations(0, "Auckland", prices[0], availSeats[0]);
        Destinations d2 = new Destinations(1, "Christchurch", prices[1], availSeats[1]);
        Destinations d3 = new Destinations(2, "Dunedin", prices[2], availSeats[2]);
        Destinations d4 = new Destinations(3, "Hamilton", prices[3], availSeats[3]);
        Destinations d5 = new Destinations(4, "Greymouth", prices[4], availSeats[4]);

        desArray[0] = d1;
        desArray[1] = d2;
        desArray[2] = d3;
        desArray[3] = d4;
        desArray[4] = d5;

        try {
            PrintWriter pw = null;
            pw = new PrintWriter(new FileOutputStream("dests.txt"));

            for (Destinations desArray1 : desArray) {
                String output = desArray1.getIndex() + "|" + desArray1.getDestName() + "|" + desArray1.getPrice() + "|" + desArray1.getNumSeatsAvailable();
                pw.println(output);
            }

            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found!");
        }

    }

    public void loadDestinations() {
        destsReadFromFile = readDestinationsFromFile("dests.txt");

        System.out.println("destsReadFromFile: " + destsReadFromFile);

    }

    //Method populates arraylist with data from file
    public ArrayList<Destinations> readDestinationsFromFile(String fileName) {

        try {
            File file = new File(fileName);
            Scanner s = new Scanner(file);
            int i = 0;

            while (s.hasNextLine()) {

                String line = s.nextLine();
                String[] items = line.split("\\|");

                int index = Integer.parseInt(items[0]);
                String name = items[1];
                int price = Integer.parseInt(items[2]);
                // prices[i] = price;
                int numSeats = Integer.parseInt(items[3]);

                availSeats[i] = numSeats;
                i++;

                Destinations newDest = new Destinations(index, name, price, numSeats);
                desList.add(newDest);
                destinationList.addItem(name);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BuyTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desList;

    }

    int selected;
    int selectedCost;

    public void getIndex() {
        selected = destinationList.getSelectedIndex();
        selectedCost = prices[selected];
        costLabel.setText("$" + selectedCost);

    }

    public void panelManager() {
        topPanel.add(titleLabel);
        centerPanel.add(chooseLabel);
        centerPanel.add(destinationList);
        centerPanel.add(costLabel);
        textPanel.add(purchaseConfirmationLabel);
        buyPanel.add(amountOfTickets);
        buyPanel.add(proceedButton);
        bottomPanel.add(buyPanel, BorderLayout.NORTH);
        bottomPanel.add(confirmPanel, BorderLayout.CENTER);
        bottomPanel.add(textPanel, BorderLayout.SOUTH);
        //panel.add(list, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        // this.add(list, BorderLayout.CENTER);

    }

    public void windowContent() {
        //Labels
        destinationList = new JComboBox();
        confirmLabel = new JLabel();
        purchaseConfirmationLabel = new JLabel();
        costLabel = new JLabel();
        topPanel = new JPanel();
        textPanel = new JPanel();
        buyPanel = new JPanel();
        amountOfTickets = new JTextField();
        proceedButton = new JButton("Proceed");
        confirmButton = new JButton("Confirm");
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        confirmPanel = new JPanel();
        // this.add(panel, FlowLayout.CENTER);
        topPanel.setBackground(Color.orange);
        confirmPanel.setBackground(Color.blue);
        centerPanel.setBackground(Color.PINK);
        buyPanel.setBackground(Color.BLACK);
        confirmPanel.setBackground(Color.cyan);

        titleLabel = new JLabel("Buy Tickets here: ");
        chooseLabel = new JLabel("Select your destination: ");

        this.setBackground(Color.red);

        //list.setLocationRelavtiveTo(null);
        list.setSize(225, 125);
        destinationList.setPreferredSize(new Dimension((200), 25));
        amountOfTickets.setPreferredSize(new Dimension((200), 25));
        centerPanel.setPreferredSize(new Dimension((100), 100));
        topPanel.setPreferredSize(new Dimension((100), 50));
        confirmPanel.setPreferredSize(new Dimension((100), 50));
        buyPanel.setPreferredSize(new Dimension((100), 50));
        textPanel.setPreferredSize(new Dimension((100), 100));

        bottomPanel.setPreferredSize(new Dimension((100), 250));

        this.setLayout(new BorderLayout());//new FlowLayout());
        bottomPanel.setLayout(new BorderLayout());
        this.setVisible(true);

    }

    int newNumOfSeats;
    int convert, total;

    public void actionListens() {
        destinationList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getIndex();

                confirmPanel.removeAll();
                confirmPanel.repaint();
                confirmPanel.revalidate();
//                               
                amountOfTickets.setText("");
                //confirmLabel.setText("");

            }

        });

        amountOfTickets.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
//                                 textPanel.removeAll();
//            textPanel.repaint();
//            textPanel.revalidate ();
            purchaseConfirmationLabel.setText("");
            }

           

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Proceed Button Clicked!");
                if (!amountOfTickets.getText().equals("") && amountOfTickets.getText().matches(".*[0-9].*")) {

                    enteredAmount = amountOfTickets.getText();
                    convert = Integer.parseInt(enteredAmount);
                    total = convert * prices[selected];
                    confirmLabel.setText("You wish to purchase: " + enteredAmount + " " + destinationList.getItemAt(selected) + " tickets for $" + total + "? (@ $" + prices[selected] + " each)");

                    System.out.println("The total is: " + total);
                    confirmPanel.add(confirmLabel);
                    getIndex();
                    confirmPanel.add(confirmButton);
                } else {
                    System.out.println("Input is blank!");
                    purchaseConfirmationLabel.setText("Error! Invalid input");
                    purchaseConfirmationLabel.setForeground(Color.red);
                }

            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = destinationList.getSelectedIndex();
                System.out.println("Buy Button Clicked! Index: " + selected);
                String amountDesired = amountOfTickets.getText();
                int convertedAmount = Integer.parseInt(amountDesired);
                System.out.println("Amount of tickets requested: " + convertedAmount);
                System.out.println("Currently: " + destinationList.getItemAt(selected) + " has " + availSeats[selected]);

                if (availSeats[selected] == 0 || convertedAmount > availSeats[selected]) {
                    System.out.println("Cannot Do it!");

                    purchaseConfirmationLabel.setText("Error! This destination has sold out or your request exceeds amount of tickets available");
                    purchaseConfirmationLabel.setForeground(Color.red);
                } else {
                    newNumOfSeats = availSeats[selected] - convertedAmount;

                    System.out.println(destinationList.getItemAt(selected) + " now has " + newNumOfSeats);
                    desList.set(selected, (new Destinations(selected, destinationList.getItemAt(selected), prices[selected], newNumOfSeats)));
                    availSeats[selected] = newNumOfSeats;
                    changeSeatsAvail();
                    System.out.println("Updated: " + desList);

                    purchaseConfirmationLabel.setForeground(Color.green);
                    purchaseConfirmationLabel.setText(enteredAmount + " tickets for a total of " + total + " successfully purchased");
                    storeBooking(destinationList.getItemAt(selected), prices[selected], convertedAmount);
                }
//                
//               
            }

        });
    }

}
