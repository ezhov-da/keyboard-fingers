package ru.ezhov.keyboardFingers;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ezhov_da on 05.10.2017.
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start() {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSlider sliderOpacity = new JSlider(1, 10);
            JButton buttonClose = new JButton("x");
            buttonClose.setBorder(BorderFactory.createEmptyBorder());
            Dimension dimension = new Dimension(25, 25);
            buttonClose.setMaximumSize(dimension);
            buttonClose.setMinimumSize(dimension);
            buttonClose.setPreferredSize(dimension);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(sliderOpacity, BorderLayout.CENTER);
            panel.add(buttonClose, BorderLayout.EAST);

            JDialog frame = new JDialog();

            frame.setUndecorated(true);

            AWTUtilities.setWindowOpacity(frame, 0.5F);

            JLabel label = new JLabel(new ImageIcon(App.class.getResource("/key.png")));
            frame.add(label, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.SOUTH);

            frame.pack();

            frame.setResizable(false);

            frame.setLocationRelativeTo(null);
            frame.setAlwaysOnTop(true);

            buttonClose.addActionListener(e -> {
                frame.dispose();
                System.exit(0);
            });

            MouseMoveWindowListener mouseMoveWindowListener = new MouseMoveWindowListener(frame);
            label.addMouseMotionListener(mouseMoveWindowListener);
            label.addMouseListener(mouseMoveWindowListener);

            sliderOpacity.addChangeListener(e -> {
                int val = sliderOpacity.getValue();

                float f;

                if (val == 10) {
                    f = 1F;
                } else {
                    f = Float.valueOf("0." + val);
                }
                changeOpacity(frame, f);
            });

            frame.setVisible(true);
        });
    }

    private void changeOpacity(Window frame, float op) {
        AWTUtilities.setWindowOpacity(frame, op);
    }

    class MouseMoveWindowListener extends MouseAdapter {

        private Point diffOnScreen;
        private Component component;

        public MouseMoveWindowListener(Component component) {
            this.component = component;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point pressedPointLocationOnScreen = e.getLocationOnScreen();
            int x = pressedPointLocationOnScreen.x - component.getLocationOnScreen().x;
            int y = pressedPointLocationOnScreen.y - component.getLocationOnScreen().y;
            diffOnScreen = new Point(x, y);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point nowMouseLocation = e.getLocationOnScreen();
            component.setLocation(
                    nowMouseLocation.x - diffOnScreen.x,
                    nowMouseLocation.y - diffOnScreen.y);
        }
    }
}
