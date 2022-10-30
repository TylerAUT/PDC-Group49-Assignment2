/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.util.Collections.list;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author tscos
 */
public class HelpPage extends JPanel {
 JScrollPane scrollPane;
    public JLabel titleLabel, text;
    public JTextArea textArea;
    JPanel panel, panel2;

    HelpPage() {
        windowContent();
        panelManager();
    }

    public void panelManager() {
        panel.add(titleLabel);
        //panel.add(list, BorderLayout.CENTER);
        //panel2.add(text);
        panel2.add(scrollPane);
        this.add(panel, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);

    }

    public void windowContent() {
        //Labels
        panel = new JPanel();
        panel2 = new JPanel();

        textArea = new JTextArea();

        // this.add(panel, FlowLayout.CENTER);
        panel.setBackground(Color.orange);
        titleLabel = new JLabel("View Help here: ");
        text = new JLabel("test");
        panel2.setBackground(Color.red);

        ReadFromFile rff = new ReadFromFile("help");
        String help = rff.readFileString();

        textArea.setText(
                "\n"
                + "Information about this application:\n"
                + "\n"
                + "This Application has been built on top of the previous CUI \n"
                + "program. Its core functions are very similar but has been \n"
                + "largely improved where possible. Many methods and functions \n"
                + "have been condensed to make it much smaller and smarter. "
                + "\n"
                + "The GUI is very straight forward, using the net beans JForms \n"
                + "for the login and user pages, but the Main display page is \n"
                + "hard coded. This is to utilize both options for the sake of \n"
                + "displaying knowledge in both.\n"
                + "\n"
                + "The main page display uses a Card Layout to switch through \n"
                + "the different panels based on button clicks\n"
                + "\n"
                + "The GUI also contains validation, checking that user input is \n"
                + "appropriate and alterting the user if not so.\n"
                + "\n"
                + "This program was created by: \n"
                + "\n"
                + "Group 49\n"
                + "Tyler Costa 19075541\n"
                + "Shambhavi Bhadauria 20108165\n"
                + "");

        textArea.setEditable(false);
         scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//scrollPane.setViewportView(textArea);
//.setText(help);
        //textArea.setText(help);
        //list.setLocationRelavtiveTo(null);
        //  list.setSize(225, 125);
        //  list.setPreferredSize(new Dimension((100), 100));
        panel.setPreferredSize(new Dimension((100), 50));
        panel2.setPreferredSize(new Dimension((100), 250));
       scrollPane.setPreferredSize(new Dimension((550), 250));
        

        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }
}
