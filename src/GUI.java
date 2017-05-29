import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  Creates GUI.  Adds a UI and "scene"
 */
public class GUI {

    private JPanel rootPanel;
    private JPanel userInputPanel;

    private JButton FIREButton;
    private JTextField ballMassTextField;
    private JTextField ballFireVelocityTextField;
    private JTextField gravTextField;
    private JTextField airResistanceTextField;
    private JLabel ballMassLabel;
    private JLabel ballFireVelLabel;
    private JLabel gravityConstantLabel;
    private JLabel airResistanceLabel;
    private JLabel massUnitLabel;
    private JLabel gravUnitLabel;
    private JLabel velUnitLabel;
    private JLabel forceUnitLabel;
    private JTextField dirTextField;

    private Scene scene;

    // convert to seconds
    private final double deltaTime = 1 / (double)60;

    public GUI() {

        rootPanel = new JPanel();

        // tried BorderLayout to make scene as big as possible in JFrame, didn't work
        rootPanel.setLayout(new GridLayout(2, 1));

        // Instantiates scene with Earth-like gravity(9.8 m/s^2) and zero air resistance(0 N)
        scene = new Scene(9.8, 0, deltaTime);

        rootPanel.add(userInputPanel);
        rootPanel.add(scene);

        FIREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //15 kg
                double mass = 15;
                // 150 m/s
                double velocity = 150;
                // 45 degrees
                double dir = 45;

                /* Replace default values */
                if(!ballMassTextField.getText().equals("")) {
                    mass = Double.parseDouble(ballMassTextField.getText());
                }
                if(!ballFireVelocityTextField.getText().equals("")) {
                    velocity = Double.parseDouble(ballFireVelocityTextField.getText());
                }
                if(!gravTextField.getText().equals("")) {
                    scene.setGravity(Double.parseDouble(gravTextField.getText()));
                }
                else {
                    scene.setGravity(9.8);
                }
                if(!airResistanceTextField.getText().equals("")) {
                    scene.setDrag(Double.parseDouble(airResistanceTextField.getText()));
                }
                else {
                    scene.setDrag(0);
                }
                if(!dirTextField.getText().equals("")) {
                    dir = Double.parseDouble(dirTextField.getText());
                }

                // makes angle with horizontal axis
                dir = Math.toRadians(360 - dir);

                Projectile ball = new Projectile(10, scene.getHeight() - (scene.getHeight() / 16), mass, velocity, dir);

                scene.addBall(ball);
            }
        });
    }

    public JPanel getRootPanel() {

        return rootPanel;
    }

    public Scene getScene() {

        return scene;
    }
}
