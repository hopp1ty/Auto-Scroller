import java.awt.Desktop;
import java.net.URI;
import java.lang.Thread;


public class OpenWebsite {

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

        for (int i = 0; i < 10; i++) {
            scroller.scroll();

            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }

        }

    }


