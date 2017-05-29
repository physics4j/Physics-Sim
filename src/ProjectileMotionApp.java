import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/*
 *  Creates JFrame and runs the simulation
 */
public class ProjectileMotionApp {

    public static void main(String[] args) {

        // The main panel that has embedded panels for the UI and the scene
        GUI gui = new GUI();

        // 60 fps (assuming computer can keep up)
        int DELAY = 1000 / 60;

        Timer timer;

        JFrame frame = new JFrame("Projectile Motion");
        frame.setContentPane(gui.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 600);
        frame.setVisible(true);

        timer = new java.util.Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                // has moving objects
                if(gui.getScene().inFlux()) {
                    gui.getScene().updateBalls();
                    gui.getScene().repaint();
                }
            }
        }, 0, DELAY);
    }
}
