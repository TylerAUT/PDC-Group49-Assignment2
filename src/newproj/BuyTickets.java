package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
    JButton buy;
    JTextField amountOfTickets;
    Destinations[] desArray = new Destinations[5];

    DefaultListModel destinations = new DefaultListModel<>();
    ArrayList<Destinations> destsReadFromFile = new ArrayList<Destinations>();

    ArrayList<Destinations> desList = new ArrayList<Destinations>();
    int[] availSeats = new int[5];
    JPanel topPanel, bottomPanel, centerPanel;
    String d[];
    JList list = new JList<>(destinations);

    public BuyTickets() {

        windowContent();
        panelManager();
        loadDestinations();
        actionListens();

        //list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    }

    public void changeSeatsAvail(){
        Destinations d1 = new Destinations(0, "Auckland", 100, availSeats[0]);
        Destinations d2 = new Destinations(1, "Christchurch", 200, availSeats[1]);
        Destinations d3 = new Destinations(2, "Dunedin", 245, availSeats[2]);
        Destinations d4 = new Destinations(3, "Hamilton", 95, availSeats[3]);
        Destinations d5 = new Destinations(4, "Greymouth", 220, availSeats[4]);

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

        System.out.println("destsReadFromFile: "+destsReadFromFile);

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
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BuyTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desList;

    }

    
    
    public void panelManager() {
        topPanel.add(titleLabel);
        centerPanel.add(chooseLabel);
        centerPanel.add(destinationList);
        bottomPanel.add(amountOfTickets);
        bottomPanel.add(buy);
        //panel.add(list, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        // this.add(list, BorderLayout.CENTER);

       

    }

    public void windowContent() {
        //Labels
        destinationList = new JComboBox();
        topPanel = new JPanel();
        amountOfTickets = new JTextField();
        buy = new JButton("Buy");
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        // this.add(panel, FlowLayout.CENTER);
        topPanel.setBackground(Color.orange);
        centerPanel.setBackground(Color.PINK);
        titleLabel = new JLabel("Buy Tickets here: ");
        chooseLabel = new JLabel("Select your destination: ");
        this.setBackground(Color.red);

        //list.setLocationRelavtiveTo(null);
        list.setSize(225, 125);
        destinationList.setPreferredSize(new Dimension((200), 25));
        amountOfTickets.setPreferredSize(new Dimension((200), 25));
        centerPanel.setPreferredSize(new Dimension((100), 100));
        topPanel.setPreferredSize(new Dimension((100), 50));
        bottomPanel.setPreferredSize(new Dimension((100), 250));

        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }

    int newNumOfSeats;
    
    public void actionListens() {
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = destinationList.getSelectedIndex();
                System.out.println("Buy Button Clicked! Index: " + selected);
                String amountDesired = amountOfTickets.getText();
                int convertedAmount = Integer.parseInt(amountDesired);
                                System.out.println("Amount of tickets requested: " + convertedAmount);
                                System.out.println("Currently: " + destinationList.getItemAt(selected) + " has " + availSeats[selected]);
                                newNumOfSeats = availSeats[selected] - convertedAmount;
                                System.out.println(destinationList.getItemAt(selected) + " now has " + newNumOfSeats);
                                desList.set(selected, (new Destinations(selected, destinationList.getItemAt(selected), 100, newNumOfSeats)));
                                availSeats[selected] = newNumOfSeats;
                                changeSeatsAvail();
                                System.out.println("Updated: " + desList);
            }
        });
    }
}
