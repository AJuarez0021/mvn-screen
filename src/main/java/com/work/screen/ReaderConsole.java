package com.work.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 *
 * @author linux
 */
public class ReaderConsole {

    private static int ch;

    public static int getCh() {
        final JFrame frame = new JFrame();
        synchronized (frame) {

            frame.setUndecorated(true);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            frame.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    synchronized (frame) {
                        frame.setVisible(false);
                        frame.dispose();
                        frame.notify();
                        ch = e.getKeyCode();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }
            });
            frame.setVisible(true);
        }
        return ch;
    }

    public static void reset() {
        ch = 0;
    }

}
