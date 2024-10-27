package com.work.screen;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

/**
 *
 * @author linux
 */
public class Application {

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            System.out.println("Width: " + screenSize.getWidth() + " - Height: " + screenSize.getHeight());

            System.out.println("<Esc> para salir...");
            // Bucle para mover el mouse cada 10 segundos mientras esté en ejecución                                                    
            while (true) {

                if (isKeyPressed(27)) {
                    System.out.println("Saliendo....");
                    System.exit(0);
                }

                Point mousePos = MouseInfo.getPointerInfo().getLocation();
                int x = mousePos.x;
                int y = mousePos.y;
                System.out.println("x = " + x + " y = " + y);

                int newX = x + 150;
                int newY = y + 10;
                if (newX > screenSize.getWidth()) {
                    robot.mouseMove((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
                    captureScreen(robot, screenSize);

                } else {
                    robot.mouseMove(newX, newY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }

                TimeUnit.SECONDS.sleep(10);
            }

        } catch (AWTException | InterruptedException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static void captureScreen(Robot robot, Dimension screenSize) {
        try {
            Rectangle screenRect = new Rectangle(screenSize);
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);

            // Generar un nombre de archivo con la fecha y hora
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File file = new File("screenshot_" + timeStamp + ".png");

            // Guardar la captura de pantalla
            ImageIO.write(screenCapture, "png", file);
            System.out.println("Captura de pantalla guardada: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private static boolean isKeyPressed(int keyCode) {
        return ReaderConsole.getCh() == keyCode;
    }
}
