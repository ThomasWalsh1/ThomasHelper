/**
 * Driver class to create two threads:
 * Unlike in WorkEmailHandler, the progressBar is in a serparate thread called
 * progressPanel.
 * 
 * progressPanel creates a frame that includes a JProgressBar that updates automatically
 * chromeThread opens chrome and uses a Robot to similuate clicks throughout the web browser.
 * 
 * @author Thomas Walsh
 * 
 */
public class ChromeUser
{
    //threads
    private Thread progressPanel = new Thread(new ThreadFrame());
    private Thread chromeThread = new Thread(new ThreadChrome());


    
    public ChromeUser()
    {   
        progressPanel.start();
        // System.out.println("Works");
        chromeThread.start();
    }

}


