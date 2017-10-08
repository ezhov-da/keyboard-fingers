package ru.ezhov.keyboardFingers;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ezhov_da on 05.10.2017.
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                {
                    final JDialog frame = new JDialog();
                    JLabel label = new JLabel(new ImageIcon(App.class.getResource("/key.png")));
                    frame.add(label);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setAlwaysOnTop(true);
                    frame.setVisible(true);
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            frame.dispose();
                            System.exit(0);
                        }
                    });
                }
            }
        });
    }
}