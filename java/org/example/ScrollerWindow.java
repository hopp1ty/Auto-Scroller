package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

import org.example.Scroller;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;



public class ScrollerWindow {
    private volatile boolean stopRequested = false;

    public void WindowCreation() {
        JFrame frame = new JFrame("Auto org.example.Scroller");
        Scroller scroller = new Scroller();
 
        JButton startButton = new JButton("Start");
        startButton.setFocusPainted(false);
        JButton stopButton = new JButton("Stop");
        stopButton.setFocusPainted(false);
        JTextField ScrollTime = new JTextField("0");
        JLabel ScrollTimeLabel = new JLabel("Pages to scroll:");
        JTextField URL = new JTextField("https://www.reddit.com");
        JLabel URLLabel = new JLabel("URL:");
        JButton URLGoButton = new JButton("Go!");

        startButton.setBounds(60, 100, 80, 30);
        stopButton.setBounds(160, 100, 80, 30);
        ScrollTime.setBounds(160, 60, 60, 30);
        ScrollTimeLabel.setBounds(60, 60, 120, 30);
        URL.setBounds(40, 20, 200, 30);
        URLLabel.setBounds(10, 20, 50, 30);
        URLGoButton.setBounds(240, 20, 60, 30);
        // SwingWorker handles background work
        SwingWorker<Void, Void> scrollerWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                stopRequested = false; // Reset the flag on start
                int ScrollTimes = 0;
                try {
                    ScrollTimes = Integer.parseInt(ScrollTime.getText());
                } catch (NumberFormatException e) {
                    System.out.println ("Invalid Number format");
                    return null;
                }
                for (int i = 0; i < ScrollTimes; i++) {
                    if (stopRequested) break;

                    scroller.scroll();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

                return null;
            }
        };

        startButton.addActionListener(e -> {
            if (!scrollerWorker.isDone()) {
                scrollerWorker.execute(); // Start the background thread
            }
        });

        stopButton.addActionListener(e -> {
            stopRequested = true; // Signal the worker to stop
        });
        URLGoButton.addActionListener(e-> {
            String url = URL.getText();
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (Exception i) {
                        i.printStackTrace();
                        System.err.println("Error opening URL: " + i.getMessage());
                    }
                } else {
                    System.out.println("Desktop browsing is not supported on this system.");

                }
            }catch (Exception x){
                x.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid URL.");
            }
        });

        frame.setLayout(null);
        frame.setSize(320, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(startButton);
        frame.add(stopButton);
        frame.add(ScrollTime);
        frame.add(ScrollTimeLabel);
        frame.add(URL);
        frame.add(URLLabel);
        frame.add(URLGoButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScrollerWindow().WindowCreation());
    }
}
