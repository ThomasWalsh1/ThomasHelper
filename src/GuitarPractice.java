import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;



public class GuitarPractice extends JFrame{

    JLabel label;
    JProgressBar bar;
    JPanel main;
    Process p;
    Robot r;

    public GuitarPractice()
    {
        //configures JFrame
        this.setTitle("Progress");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(450, 200);
        this.setLocation(0, 600);
        this.setLayout(null);

        //configures panel
        main = new JPanel();
        main.setSize(this.getSize());
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.setLayout(new FlowLayout(1, 10, 10));

        //configures label
        label = new JLabel();
        label.setText("<html> The mouse is currently overtaken by a robot. <br/> Please allow about five seconds for the windows to be opened. </html>");
        label.setFont(new Font("Droid Sans", Font.PLAIN, 13));
        label.setBounds(0, 0, 400, 50);

        //configures progress bar
        bar = new JProgressBar(0,100);
        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        bar.setPreferredSize(new Dimension(350, 80));
        bar.setFont(new Font("Droid Sans", Font.PLAIN, 15));
        bar.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        bar.setValue(0);

        //adds to the JFrame
        main.add(label);
        main.add(bar);
        this.add(main);
        this.setAlwaysOnTop(true);
        this.setVisible(true);

        //run function for thread, once all items have been created
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                
                try {
                    //opens chrome and waits until the command is complete
                    p = Runtime.getRuntime().exec("\"/Program Files/Google/Chrome/Application/chrome.exe\"");
                    p.waitFor();
                    System.out.println("Chrome Launched!");

                    r = null;
                    try
                    {
                        r = new Robot();
                        // System.out.println("Robot created successfully");
                    }
                    catch (Exception e)
                    {
                        // System.out.println("Robot creation failed.");
                        e.printStackTrace();
                    }

                    //here is a series of waits and clicks to keep up with the chrome app

                    Thread.sleep(1000);

                    //766, 419: Open Main profile
                    click(r, 756, 410);
                    
                    
                    Thread.sleep(1000);
                    
                    //1636, 94: bookmarks bar
                    click(r, 1636, 94);

                    //1461, 304: clicks on metronome bookmark
                    click(r, 1461, 304);

                    //completes the progress bar when all clicks are done
                    while(bar.getValue() < 100)
                    {
                        Thread.sleep(250);
                        addProgress();
                    }


                    Thread.sleep(1500);
                    
                    
                    //disposes the window
                    dispose();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                

            }
        });


    }
    private void addProgress()
    {
        bar.setValue(bar.getValue() + 10);
        bar.update(bar.getGraphics());
    }

    public void click(Robot r, int x, int y)
    {
        // System.out.println("Ok working now");
        
        r.mouseMove(0, 0);
        r.mouseMove(x, y);

        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        addProgress();
    }
}
