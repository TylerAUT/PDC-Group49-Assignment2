package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Tyler Costa
 */
public class ViewDestinations extends JPanel {

    public JLabel titleLabel;
    JPanel northPanel, southPanel;
    Font titleFont;

    DefaultListModel destinations = new DefaultListModel<>();
    JList list = new JList<>(destinations);

    public ViewDestinations() {

        windowContent();
        panelManager();
        loadDestinations();

    }

    public void loadDestinations() {
        readDestinationsFromFile("dests.txt");
    }

    //Method populates arraylist with data from file
    public void readDestinationsFromFile(String fileName) {

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

                Destinations newDest = new Destinations(index, name, price, numSeats);

                destinations.addElement(newDest);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewDestinations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void panelManager() {
        northPanel();
        centerPanel();
        southPanel();

    }

    public void windowContent() {
        //Panels
        northPanel = new JPanel();
        southPanel = new JPanel();

        //Labels
        titleLabel = new JLabel("View Destinations here: ");

        //Fonts
        titleFont = new Font("Calibri", Font.BOLD, 18);

        //JFrame Componenets
        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }

    public void southPanel() {

        this.add(list, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);


    }

    public void centerPanel() {
        list.setPreferredSize(new Dimension((400), 450));

        list.setFixedCellWidth(150);
        list.setFixedCellHeight(25);

        list.setBorder(BorderFactory.createLineBorder(Color.black));

       // this.setBackground(Color.red);

    }

    public void northPanel() {
        northPanel.add(titleLabel);
        titleLabel.setFont(titleFont);
        //northPanel.setBackground(Color.orange);
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setPreferredSize(new Dimension((100), 50));

    }

}
