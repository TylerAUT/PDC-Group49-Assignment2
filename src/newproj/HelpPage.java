/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
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
    JPanel topPanel, bottomPanel;
    Font titleFont;

    HelpPage() {
        windowContent();
        panelManager();
    }

    public void panelManager() {
        topPanel.add(titleLabel);
        titleLabel.setFont(titleFont);
        bottomPanel.add(scrollPane);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
        textArea.setEditable(false);

    }

    public void windowContent() {
        //Labels
        titleLabel = new JLabel("View Help here: ");

        //Area
        textArea = new JTextArea();

        //Panels
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        //topPanel.setBackground(Color.orange);
        //bottomPanel.setBackground(Color.red);

        //Fonts
        titleFont = new Font("Calibri", Font.BOLD, 18);

        //ScrollPane
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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

        //Sizes
        topPanel.setPreferredSize(new Dimension((100), 50));
        bottomPanel.setPreferredSize(new Dimension((100), 250));
        scrollPane.setPreferredSize(new Dimension((550), 250));

        this.setLayout(new BorderLayout());//new FlowLayout());
        this.setVisible(true);

    }
}
