import java.awt.Desktop;
import java.net.URI;
import java.lang.Thread;
import java.util.Scanner;

public class OpenWebsite {

    public static volatile boolean stopRequested = false;
    public static void main(String[] args) {
        String url = "https:www.reddit.com/login/"; // replace with url

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error opening URL: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop browsing is not supported on this system.");

        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

            Scroller scroller = new Scroller();
        Thread inputListener = new Thread (() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("q")) { //key to stop the program.
                    stopRequested = true;
                    System.out.println("Stopping Program...");
                    break;
                }
            }
        });
        inputListener.setDaemon(true);
        inputListener.start();

        for (int i = 0; i < 10; i++) { // edit the number (i < __ ) for the for loop to increase the amount of times it will scroll.
             if(stopRequested){
                break;
            }
            
            scroller.scroll();

            try {
                Thread.sleep(5000);  // milliseconds for website to wait, can be higher or lower depending on internet speed.
            }catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
            System.out.println("Program ending...");
     }

}


