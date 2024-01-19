import javax.swing.*;
import java.awt.*;

public class SteamHandler extends JFrame{

    Robot r;
    JLabel label;
    JProgressBar bar;
    JPanel main;
    Process p;

    public SteamHandler()
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
        label.setText("<html> Now launching the steam app. <br/> Please allow about five seconds for the windows to be opened. </html>");
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
                try
                {
                    p = Runtime.getRuntime().exec("\"/Program Files (x86)/Steam/steam.exe\"");
                    p.waitFor();
                    System.out.println("Steam launching...");

                    while(bar.getValue() < 100)
                    {
                        Thread.sleep(250);
                        addProgress();
                    }

                    bar.setString("Done!");

                    Thread.sleep(2000);

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
}
