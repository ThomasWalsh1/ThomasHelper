import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

/**
 * Class that creates a window for the JProgressBar
 * 
 * @author Thomas Walsh
 */
public class ThreadFrame implements Runnable{
    
    //instance variables
    private JFrame frame = new JFrame("Progress");
    private JLabel label;
    private JProgressBar bar;

    /**
     * Uses the run method required for Runnable implementation.
     * Creates the JFrame with a JLabel and JProgressBar.
     */
    public void run()
    {
        System.out.println("Hello");
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Ok done");

        
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(450,200);
        frame.setLocation(0,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JPanel main = new JPanel();
        main.setSize(frame.getSize());

        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.setLayout(new FlowLayout(1, 10, 10));

        label = new JLabel();
        label.setText("<html> The mouse is currently overtaken by a robot. <br/> Please allow about five seconds for the windows to be opened. </html>");
        label.setFont(new Font("Droid Sans", Font.PLAIN, 13));
        label.setBounds(0, 0, 400, 50);
        
        
        bar = new JProgressBar(0,100);
        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        bar.setPreferredSize(new Dimension(350, 80));
        bar.setFont(new Font("Droid Sans", Font.PLAIN, 15));
        bar.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);


        main.add(label);
        main.add(bar);
        frame.add(main);
   

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        
        while(bar.getValue() < 100)
        {
            try
            {
                Thread.sleep(125);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            bar.setValue(bar.getValue() + 1);
        }
        bar.setString("Done!");
        //progressbar has been completed

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.dispose();
    }

}
