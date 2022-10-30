package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author tscos
 */
public class BuyTickets extends JPanel {

    //Swing Components
    public JLabel titleLabel, chooseLabel;
    JComboBox<String> destinationList;
    JButton proceedButton, confirmButton;
    JTextField amountOfTickets;
    JLabel proceedLabel, confirmLabel, costLabel, purchaseConfirmationLabel;
    JPanel topPanel, bottomPanel, buyPanel, confirmPanel, centerPanel, textPanel;
    Font titleFont;

    //Lists
    ArrayList<Destinations> destsReadFromFile = new ArrayList<Destinations>();
    ArrayList<Destinations> desList = new ArrayList<Destinations>();
    //JList list = new JList<>(destinations);

    //Primitive Vars
    int selected, selectedCost, newNumOfSeats, convert, total;
    String loggedInAccount, enteredAmount;

    //Arrays
    int[] prices = new int[]{100, 200, 245, 95, 220};
    int[] availSeats = new int[5];
    Destinations[] desArray = new Destinations[5];

    public BuyTickets(String account) {
        loggedInAccount = account;
        windowContent();
        panelManager();
        loadDestinations();
        actionListens();
        //getIndex();

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
                int numSeats = Integer.parseInt(items[3]);

                availSeats[i] = numSeats;
                i++;

                Destinations newDest = new Destinations(index, name, price, numSeats);
                desList.add(newDest);
                destinationList.addItem(name);
                                getIndex();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BuyTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desList;

    }

    public void getIndex() {
        selected = destinationList.getSelectedIndex();
        selectedCost = prices[selected];
        costLabel.setText("$" + selectedCost);

    }

    public void panelManager() {

        topPanel();
        centerPanel();
        buyPanel();
        textPanel();
        bottomPanel();

    }

    public void topPanel() {
        topPanel.add(titleLabel);
        titleLabel.setFont(titleFont);
      //  topPanel.setBackground(Color.orange);

        this.add(topPanel, BorderLayout.NORTH);

    }

    public void centerPanel() {
        centerPanel.add(chooseLabel);
        centerPanel.add(destinationList);
        centerPanel.add(costLabel);
       // centerPanel.setBackground(Color.PINK);

        this.add(centerPanel, BorderLayout.CENTER);

    }

    public void buyPanel() {
        buyPanel.add(proceedLabel);
        buyPanel.add(amountOfTickets);
        buyPanel.add(proceedButton);
        
        //buyPanel.setBackground(Color.WHITE);

    }

    public void textPanel() {
        textPanel.add(purchaseConfirmationLabel);
    }

    public void bottomPanel() {
        bottomPanel.add(buyPanel, BorderLayout.NORTH);
        bottomPanel.add(confirmPanel, BorderLayout.CENTER);
        bottomPanel.add(textPanel, BorderLayout.SOUTH);

        this.add(bottomPanel, BorderLayout.SOUTH);
       // confirmPanel.setBackground(Color.cyan);

    }

    public void windowContent() {
        //Panels
        topPanel = new JPanel();
        textPanel = new JPanel();
        buyPanel = new JPanel();
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        confirmPanel = new JPanel();

        //Buttons
        proceedButton = new JButton("Proceed");
        confirmButton = new JButton("Confirm");

        //Labels
        titleLabel = new JLabel("Buy Tickets here: ");
        confirmLabel = new JLabel();
        proceedLabel = new JLabel("Enter amount of tickets: ");
        purchaseConfirmationLabel = new JLabel();
        costLabel = new JLabel();
        chooseLabel = new JLabel("Select your destination: ");

        //Combobox
        destinationList = new JComboBox();

        //Text Field
        amountOfTickets = new JTextField();

        //Fonts
        titleFont = new Font("Calibri", Font.BOLD, 18);
        
        //Border
         Border panelBorder = BorderFactory.createLineBorder(Color.black);
        this.setBorder(panelBorder);
        
        //Sizes
        destinationList.setPreferredSize(new Dimension((200), 25));
        amountOfTickets.setPreferredSize(new Dimension((200), 25));
        centerPanel.setPreferredSize(new Dimension((100), 100));
        topPanel.setPreferredSize(new Dimension((100), 50));
        confirmPanel.setPreferredSize(new Dimension((100), 50));
        buyPanel.setPreferredSize(new Dimension((100), 50));
        textPanel.setPreferredSize(new Dimension((100), 100));
        bottomPanel.setPreferredSize(new Dimension((100), 250));

        //Main panel properities
        this.setLayout(new BorderLayout());//new FlowLayout());
        bottomPanel.setLayout(new BorderLayout());
       // this.setBackground(Color.red);

        this.setVisible(true);

    }

    public void actionListens() {
        destinationList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getIndex();

                confirmPanel.removeAll();
                confirmPanel.repaint();
                confirmPanel.revalidate();
                amountOfTickets.setText("");

            }
        });

        amountOfTickets.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

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
                if (!amountOfTickets.getText().equals("") && amountOfTickets.getText().matches(".*[1-9].*")) {

                    beginBookingConfirmation();

                } else {
                    printInvalidInputMessage();
                }

            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buy Button Clicked! Index: " + selected);
                System.out.println("Amount of tickets requested: " + convert);
                System.out.println("Currently: " + destinationList.getItemAt(selected) + " has " + availSeats[selected]);

                if (availSeats[selected] == 0 || convert > availSeats[selected]) {
                    printErrorMessage();
                } else {
                    completeBookingConfirmation();
                }

            }

        });
    }

    public void beginBookingConfirmation() {
        enteredAmount = amountOfTickets.getText();
        convert = Integer.parseInt(enteredAmount);
        total = convert * prices[selected];
        confirmLabel.setText("You wish to purchase: " + enteredAmount + " " + destinationList.getItemAt(selected) + " tickets for $" + total + "? (@ $" + prices[selected] + " each)");
        getIndex();

        System.out.println("The total is: " + total);
        confirmPanel.add(confirmLabel);
        confirmPanel.add(confirmButton);
    }

    public void completeBookingConfirmation() {
        newNumOfSeats = availSeats[selected] - convert;

        System.out.println(destinationList.getItemAt(selected) + " now has " + newNumOfSeats);
        desList.set(selected, (new Destinations(selected, destinationList.getItemAt(selected), prices[selected], newNumOfSeats)));
        availSeats[selected] = newNumOfSeats;
        changeSeatsAvail();
        System.out.println("Updated: " + desList);

        printConfirmationMessage();
        storeBooking(destinationList.getItemAt(selected), prices[selected], convert);
    }

    public void printErrorMessage() {
        System.out.println("Error! cannot validate");

        purchaseConfirmationLabel.setText("Error! This destination has sold out or your request exceeds amount of tickets available");
        purchaseConfirmationLabel.setForeground(Color.red);
    }

    public void printInvalidInputMessage() {
        System.out.println("Input is invalid!");
        purchaseConfirmationLabel.setText("Error! Invalid input");
        purchaseConfirmationLabel.setForeground(Color.red);
    }

    public void printConfirmationMessage() {
        purchaseConfirmationLabel.setForeground(Color.GREEN);
        purchaseConfirmationLabel.setText(enteredAmount + " tickets for a total of " + total + " successfully purchased");
    }
}
