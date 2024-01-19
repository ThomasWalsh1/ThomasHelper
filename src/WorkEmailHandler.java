
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class WorkEmailHandler extends JFrame{

    JLabel label;
    JProgressBar bar;
    JPanel main;
    Process p;
    Robot r;
    //Thread chrome = new Thread();

    public WorkEmailHandler()
    {
        this.setTitle("Progress");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(450, 200);
        this.setLocation(0, 600);
        this.setLayout(null);

        main = new JPanel();
        main.setSize(this.getSize());
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
        bar.setValue(0);

        main.add(label);
        main.add(bar);
        this.add(main);
        this.setAlwaysOnTop(true);
        this.setVisible(true);


        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                
                try {
                    p = Runtime.getRuntime().exec("\"/Program Files/Google/Chrome/Application/chrome.exe\"");
                    p.waitFor();
                    
                    System.out.println("Chrome Launched!");
                    //test
                    System.out.println("Chrome launched, again.");
                    

                    try
                    {
                        r = new Robot();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Robot creation failed.");
                        e.printStackTrace();
                    }

                    Thread.sleep(1000);

                    //begin clicks

                    //1128, 428: Opens work chrome profile
                    click(r, 1128, 428);

                    Thread.sleep(1000);

                    //854, 360: Opens work email
                    click(r, 854, 460);

                    Thread.sleep(500);
                    //295, 16: New tab
                    click(r, 295, 16);

                    Thread.sleep(500);
                    //1644, 94: Open Bookmarks
                    click(r, 1644, 94);

                    Thread.sleep(1000);
                    //1461, 182: Open Linkedin Bookmark
                    click(r, 1461, 182);

                    //completes the progress bar
                    while(bar.getValue() < 100)
                    {
                        Thread.sleep(250);
                        addProgress();
                    }

                    bar.setString("Done!");

                    Thread.sleep(1500);
                    
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
        r.mouseMove(0, 0);
        r.mouseMove(x, y);

        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        addProgress();
    }

}
