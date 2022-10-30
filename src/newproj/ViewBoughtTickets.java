/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author tscos
 */
public class ViewBoughtTickets extends JPanel {
    
   public JLabel titleLabel;
    DefaultListModel destinations = new DefaultListModel<>();
    ArrayList<Destinations> destsReadFromFile = new ArrayList<Destinations>();
    ArrayList<String> test = new ArrayList<String>();
    ArrayList<Destinations> desList = new ArrayList<Destinations>();
    int[] availSeats = new int[5];
    JPanel panel, panel2;
    String loggedInAccount;
    
    JList list = new JList<>(destinations);

    public ViewBoughtTickets(String account) {
        this.loggedInAccount = account;
				
        windowContent();
        panelManager();
        loadDestinations();

        //list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    }

    public void loadDestinations() {
        destsReadFromFile = readDestinationsFromFile(loggedInAccount+"boughtTickets.txt");
        System.out.println(destsReadFromFile);
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
                int totalPrice = Integer.parseInt(items[2]);
                int numSeats = Integer.parseInt(items[3]);
                int individualPrice = Integer.parseInt(items[4]);

               // availSeats[i] = numSeats;
                i++;
                
                String in = "" +index+ " "+ name+ " "+totalPrice + " " + numSeats + " " +individualPrice;
                test.add(in);

                //desList.add(newDest);
                destinations.addElement(in);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewDestinations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desList;

    }

    public void panelManager() {
        panel.add(titleLabel);
        //panel.add(list, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        //this.add(panel2, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

        list.setFixedCellWidth(150);
        list.setBorder(BorderFactory.createLineBorder(Color.black));

    }
JScrollPane scrollPane;
    public void windowContent() {
        //Labels
         scrollPane = new JScrollPane();
scrollPane.setViewportView(list);
list.setLayoutOrientation(JList.VERTICAL);
        panel = new JPanel();
        panel2 = new JPanel();

        // this.add(panel, FlowLayout.CENTER);
        panel.setBackground(Color.orange);
        titleLabel = new JLabel("View bought tickets for user: "+loggedInAccount+" here: ");

        this.setBackground(Color.red);

        //list.setLocationRelavtiveTo(null);
       // list.setSize(225, 125);

       list.setPreferredSize(new Dimension((100), 400));
        panel.setPreferredSize(new Dimension((100), 50));
                panel2.setPreferredSize(new Dimension((100), 250));


        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }
    
}
