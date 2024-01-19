import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import com.formdev.flatlaf.FlatDarkLaf;
/**
 * MyFrame is a class that extends JFrame and implements ActionListener.
 * This class is used as the main menu for the ThomasHelper application.
 * @author Thomas Walsh
 */
public class MyFrame extends JFrame implements ActionListener{

    JButton checkwork;
    JButton notepad;
    JButton steam;
    JButton discord;
    JButton workEmail;
    JButton practice;
    JButton exit;

    public MyFrame()
    {
        //sets up the FlatDarkLaf library for enhanced UI
        FlatDarkLaf.setup();
        
        //configures the JFrame
        this.setTitle("Thomas' helper ver 1.0.0");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        

        //configures the panels
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        //configures header
        JPanel header = new JPanel();
        JLabel headerLabel = new JLabel("Welcome to Thomas' helper!");
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        header.add(headerLabel);
        header.setBounds(0, 0, 700, 40);

        //configures the body
        JPanel body = new JPanel();
        body.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        body.setLayout(new GridLayout(3, 2, 10, 10));


        //generates the buttons
        checkwork = new JButton("School Work");
        notepad = new JButton("Start To-Do");
        steam = new JButton("Steam");
        discord = new JButton("Discord");
        workEmail = new JButton("Work Email");
        practice = new JButton("Practice");
        exit = new JButton("Exit");

        //unfocus all buttons for modern GUI look
        checkwork.setFocusable(false);
        notepad.setFocusable(false);
        steam.setFocusable(false);
        discord.setFocusable(false);
        workEmail.setFocusable(false);
        practice.setFocusable(false);
        exit.setFocusable(false);

        //Makes a list of jbuttons to be iterated over for easier use
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(checkwork);
        buttons.add(notepad);
        buttons.add(steam);
        buttons.add(discord);
        buttons.add(workEmail);
        buttons.add(practice);
        buttons.add(exit);

        //gives each button an enchanced look when hovered over
        buttons.forEach(button -> button.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(java.awt.event.MouseEvent event) {
                    
                    button.setBackground(UIManager.getColor("Button.hoverBackground"));

                    //adds a visual exception for exit button
                    if(!(button == exit))
                    {
                        button.setBackground(UIManager.getColor("Button.hoverBackground"));
                        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
                        button.setFont(new Font("Tahoma", Font.BOLD, 14));
                    }
                    else //for all other buttons
                    {
                        
                        button.setFont(UIManager.getFont("defaultFont"));
                        button.setBackground(Color.RED);
                    }
                }
                //returns buttons back to normal state
                public void mouseExited(java.awt.event.MouseEvent eventt) {
  
                    button.setBackground(UIManager.getColor("Button.background"));


                    //exception for exit button
                    if(!(button == exit))
                    {
                        button.setFont(UIManager.getFont("defaultFont"));
                        button.setBorder(BorderFactory.createEmptyBorder());
                    }
                    else
                    {
                        button.setFont(UIManager.getFont("defaultFont"));
                    }

                }
            }));
        
        //set action listeners
        checkwork.addActionListener(this);
        notepad.addActionListener(this);
        steam.addActionListener(this);
        discord.addActionListener(this);
        workEmail.addActionListener(this);
        practice.addActionListener(this);
        exit.addActionListener(this);


        //adds buttons to the body
        body.add(checkwork);
        body.add(notepad);
        body.add(steam);
        body.add(discord);
        body.add(workEmail);
        body.add(practice);
        
        //configures body panel
        body.setBounds(0, 40, 700, 660);
        

        //adds items to main panel
        content.add(header, BorderLayout.NORTH);
        content.add(body, BorderLayout.CENTER);
        content.add(exit, BorderLayout.SOUTH);
        content.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.setContentPane(content);
        this.setVisible(true);
        // this.setLocationRelativeTo(null);

        // displayColorKeysUI();
    }
    
    /**
     * Required for the implementation of ActionListener.
     * Listens for when action is performed.
     * @param e is the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //checkWork button
        if(e.getSource()==checkwork)
        {
            System.out.println("Checking Work...");
            new ChromeUser();
        }
        //ToDoList button
        if(e.getSource()==notepad)
        {
            System.out.println("Opening ToDoList...");
            new ToDoList().setVisible(true);;
        }
        //Steam button
        if(e.getSource()==steam)
        {
            System.out.println("Opening Steam...");
            new SteamHandler();
        }
        //Discord button
        if(e.getSource()==discord)
        {
            System.out.println("Opening Discord...");
        }
        //WorkEmail (inlcudes LinkedIn) button
        if(e.getSource()==workEmail)
        {
            System.out.println("Opening Work Email...");
            new WorkEmailHandler();
        }
        //Guitar practice button
        if(e.getSource()==practice)
        {
            System.out.println("Opening Google Tabs For Guitar...");
            new GuitarPractice();
        }
        //exit button
        if(e.getSource()==exit)
        {
            System.out.println("Now exiting...");
            this.dispose();
        }
    }

    /**
     * Searches UIManager for defaults and prints them to the console.
     * Used to enchance GUI development using FlatDarkLaf.
     */
    public void displayColorKeysUI()
    {
        List<String> colors = new ArrayList<String>();
        for (Map.Entry<Object, Object> entry : UIManager.getDefaults().entrySet()) {
            if (entry.getValue() instanceof Color) {
                colors.add((String) entry.getKey()); // all the keys are strings
            }
        }
        
        Collections.sort(colors);
        for (String name : colors)
            System.out.println(name);

        List<String> fonts = new ArrayList<String>();
        for (Map.Entry<Object, Object> entry : UIManager.getDefaults().entrySet()) {
            if (entry.getValue() instanceof Font) {
                fonts.add((String) entry.getKey()); // all the keys are strings
            }
        }
        Collections.sort(fonts);
        for (String name : fonts)
            System.out.println(name);

    }
}
