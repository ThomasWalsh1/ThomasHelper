import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

/**
 * Class used to open Chrome and simulate mouse clicks to open desired tabs
 * 
 * @author Thomas Walsh
 */
public class ThreadChrome implements Runnable
{
    
    /**
     * Method required for the implementation of Runnable.
     * Opens Chrome and makes mouse clicks through a series of clicks and waits
     */
    public void run()
    {
        System.out.println("Going....");
        try {
            Thread.sleep(2000);
            Process p;
            try {
                //runs a command that launches chrome
                // System.out.println("Works 2");
                p = Runtime.getRuntime().exec("\"/Program Files/Google/Chrome/Application/chrome.exe\"");
                p.waitFor();
                System.out.println("Google chrome launched");

                //using robot
                Robot joe = null;
                joe = new Robot();
                

                //the movement begins

                //1110, 554 opens chrome profile
                joe.delay(1400);
                
                click(joe, 945, 416);

                //906, 496 opens gmail app
                joe.delay(500);
                click(joe, 906, 496);

                //294, 14 opens new tab
                Thread.sleep(500);
                click(joe, 294, 14);

                //1636, 101 opens bookmarks
                Thread.sleep(500);
                click(joe, 1636, 101);

                //1466, 216 clicks canvas bookmark
                Thread.sleep(500);
                click(joe, 1466, 216);

                //38, 206 canvas logon
                Thread.sleep(500);
                click(joe, 38, 206);

                //1034, 556 sign in button
                Thread.sleep(500);
                click(joe, 1034, 556);

                //513, 12 opens new tab
                Thread.sleep(500);
                click(joe, 525, 21);

                //1636, 101 opens bookmarks
                Thread.sleep(500);
                click(joe, 1636, 101);

                //1457, 240 clicks gradescope bookmark
                Thread.sleep(500);
                click(joe, 1457, 240);

                //1313, 134 gradescope log on
                Thread.sleep(1500);
                click(joe, 1313, 134);

                //867, 418 gradescope log on
                Thread.sleep(1500);
                click(joe, 867, 418);

                //768, 20 new tab
                Thread.sleep(1600);
                click(joe, 768, 20);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (AWTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
        System.out.println("Gone");
    }

    /**
     * Helper method for making a mouse click in the Chrome browser
     * @param bot the Robot being used to make the click
     * @param x horizontal location of the click
     * @param y vertical location of the click
     */
    public void click(Robot bot, int x, int y)
    {
        bot.mouseMove(0, 0); //sets the mouse in its native position, for dealing with library bug

        bot.mouseMove(x, y); //now moves the mouse to the desired position

        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}