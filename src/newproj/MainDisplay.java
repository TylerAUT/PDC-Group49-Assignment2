package newproj;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Tyler Costa
 */
public class MainDisplay extends JFrame implements Runnable {

    JFrame display = new JFrame();
    public JPanel northPanel, centerPanel, southPanel, eastPanel, westPanel;
    public JButton viewDestinationsButton, buyTickets, viewBoughtTicketsButton, helpButton, logOutButton;
    public JTextField textfield;
    public JLabel headerLabel, clockLabel, centerLabel, southLabel, westLabel, eastLabel;
    public String loggedInAccount, time;
    public Font titleFont, clockFont;
    CardLayout cardlayout;
    Container container;
    Thread clockThread = new Thread(this);

    public MainDisplay(String account) {
        this.loggedInAccount = account;

        WindowContents();

        panelManager();
        actionListens();
        runClock();

    }

    public void northPanel() {
        //North Panel

        northPanel.setBackground(Color.WHITE);
        northPanel.add(headerLabel);
        headerLabel.setText("Welcome to the Wellington Train Booking Service, " + loggedInAccount);
        headerLabel.setPreferredSize(new Dimension((600), 50));
        headerLabel.setFont(titleFont);
        display.add(northPanel, BorderLayout.NORTH);
        northPanel.setPreferredSize(new Dimension((100), 100));

    }

    public void centerPanel() {

        container = getContentPane();
        cardlayout = new CardLayout();
        container.setLayout(cardlayout);

        container.add("a", new ViewDestinations());

        container.add("b", new BuyTickets(loggedInAccount));

        container.add("c", new ViewBoughtTickets(loggedInAccount));

        container.add("d", new HelpPage());
        display.add(container, BorderLayout.CENTER);

    }

    public void southPanel() {
//South Panel

        southPanel.add(southLabel);
       // southLabel.setText("SOUTH");
      //  southPanel.setBackground(Color.RED);
        southPanel.add(logOutButton);
        logOutButton.setText("Logout");
        display.add(southPanel, BorderLayout.SOUTH);

        southPanel.setPreferredSize(new Dimension((100), 50));

    }

    public void eastPanel() {
        //East Panel
      //  eastPanel.setBackground(Color.CYAN);
        display.add(eastPanel, BorderLayout.EAST);
        eastPanel.add(eastLabel);
        //eastLabel.setText("EAST");

        eastPanel.setPreferredSize(new Dimension((100), 50));

    }

    public void westPanel() {

        //West Panel
        westPanel.add(westLabel);
      //  westPanel.setBackground(Color.PINK);
        display.add(westPanel, BorderLayout.WEST);

        westPanel.setPreferredSize(new Dimension((200), 50));
        westPanel.add(clockLabel);
        clockLabel.setFont(clockFont);

        westPanel.add(viewDestinationsButton);
        westPanel.add(buyTickets);
        westPanel.add(viewBoughtTicketsButton);
        westPanel.add(helpButton);

        clockLabel.setPreferredSize(new Dimension((200), 50));

    }

    public void panelManager() {

        northPanel();
        southPanel();
        centerPanel();
        eastPanel();
        westPanel();

    }

    public void WindowContents() {

        //Panels
        this.northPanel = new JPanel();
        this.centerPanel = new JPanel();
        this.southPanel = new JPanel();
        this.eastPanel = new JPanel();
        this.westPanel = new JPanel();

        //this.button = new JButton("Balls");
        this.textfield = new JTextField(20);

        //Labels
        this.headerLabel = new JLabel("", SwingConstants.CENTER);
        this.clockLabel = new JLabel("", SwingConstants.CENTER); //centers the text
        this.centerLabel = new JLabel("", SwingConstants.CENTER);
        this.southLabel = new JLabel();
        this.westLabel = new JLabel();
        this.eastLabel = new JLabel();
        //this.logOutButton = new JButton();

        //Fonts
        this.titleFont = new Font("Calibri", Font.BOLD, 26);
        this.clockFont = new Font("SansSerif", Font.BOLD, 12);

        //Buttons
        this.viewDestinationsButton = new JButton("Destinations");
        this.buyTickets = new JButton("Buy Tickets");
        this.viewBoughtTicketsButton = new JButton("View Tickets");
        this.helpButton = new JButton("Help");
        this.logOutButton = new JButton("Logout");

        //Button Sizes
        this.viewDestinationsButton.setPreferredSize(new Dimension((150), 30));
        this.buyTickets.setPreferredSize(new Dimension((150), 30));
        this.viewBoughtTicketsButton.setPreferredSize(new Dimension((150), 30));
        this.helpButton.setPreferredSize(new Dimension((150), 30));

        //JFrame components
        display.setSize(900, 600);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLocationRelativeTo(null);
        display.setTitle("Wellington Train Booking System");
        display.setResizable(true);
        display.setVisible(true);

    }

    public void actionListens() {

        viewDestinationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.add("a", new ViewDestinations());

                cardlayout.show(container, "a");
                //display.setResizable(false);
            }

        });

        buyTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(container, "b");
            }
        });

        viewBoughtTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.add("c", new ViewBoughtTickets(loggedInAccount));

                cardlayout.show(container, "c");
            }

        });

        helpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(container, "d");
            }
        });
        
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Back to login Button Clicked!");
                display.dispose();
                LoginWindow launch = new LoginWindow();
                launch.setVisible(true);
            }
        });
    }

    public void runClock() {
        clockThread.start();
    }

    @Override
    public void run() {
        while (true) {

            Calendar cal = Calendar.getInstance();
            Date dat = cal.getTime();
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss aa");
            String time12 = sdf12.format(dat);
            clockLabel.setText("The Time is: " + time12);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Thread killed");
                break;
            }
        }
    }
}
