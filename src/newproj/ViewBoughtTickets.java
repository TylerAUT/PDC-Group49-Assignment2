package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

    //Swing components
    public JLabel titleLabel;
    JPanel topPanel, bottomPanel;
    JScrollPane scrollPane;
    Font titleFont;
    
    //Lists
    DefaultListModel destinations = new DefaultListModel<>();
    ArrayList<Destinations> desList = new ArrayList<Destinations>();
    JList list = new JList<>(destinations);
    
    //Primitive var
    String loggedInAccount;

    public ViewBoughtTickets(String account) {
        this.loggedInAccount = account;

        windowContent();
        panelManager();
        loadDestinations();

    }

    public void loadDestinations() {
        readDestinationsFromFile(loggedInAccount + "boughtTickets.txt");
        //System.out.println(destsReadFromFile);
    }

    //Method populates arraylist with data from file
    public ArrayList<Destinations> readDestinationsFromFile(String fileName) {

        try {
            File file = new File(fileName);
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {

                String line = s.nextLine();
                String[] items = line.split("\\|");

                int index = Integer.parseInt(items[0]);
                String name = items[1];
                int totalPrice = Integer.parseInt(items[2]);
                int numSeats = Integer.parseInt(items[3]);
                int individualPrice = Integer.parseInt(items[4]);

                String input = "Ticket ID: " + index + ", To: " + name + ", $ " + totalPrice + " total (" + numSeats + " @ " + individualPrice + " each)";

                destinations.addElement(input);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewBoughtTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desList;

    }

    public void panelManager() {
        topPanel.add(titleLabel);
        titleLabel.setFont(titleFont);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    public void windowContent() {
        //Labels
        titleLabel = new JLabel("Ticket purchase history for " + loggedInAccount);

        //ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);

        //Panels
        topPanel = new JPanel();
        bottomPanel = new JPanel();
       // topPanel.setBackground(Color.orange);

        //List
        list.setLayoutOrientation(JList.VERTICAL);
        list.setFixedCellWidth(150);
        list.setFixedCellHeight(25);
        list.setBorder(BorderFactory.createLineBorder(Color.black));

        //Fonts
        titleFont = new Font("Calibri", Font.BOLD, 18);
        
       
        //this.setBackground(Color.red);

        //Sizes
        list.setPreferredSize(new Dimension((100), 400));
        topPanel.setPreferredSize(new Dimension((100), 50));
        bottomPanel.setPreferredSize(new Dimension((100), 250));

        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }

}
