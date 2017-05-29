import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/*
 *  The "scene" of the simulation.  JPanel that draws balls
 */
public class Scene extends JPanel {

    private ArrayList<Projectile> balls;
    private ArrayList<Projectile> explosionBits;
    private ArrayList<Integer> locs;
    private double g;
    private double d;
    private double dt;

    public Scene(double gravity, double airResistance, double deltaTime) {

        this.setBackground(Color.WHITE);
        this.setSize(500, 800);

        balls = new ArrayList<>();
        explosionBits = new ArrayList<>();
        locs = new ArrayList<>();

        g = gravity;
        d = airResistance;
        dt = deltaTime;
    }

    public void addBall(Projectile ball) {

        balls.add(ball);
    }

    // adds exploded bits to scene on impact
    public void addExplosiveBits() {


    }

    /*
     *  Updates coordinates according to kinematic equations
     */
    public void updateBalls() {

        for(int i = 0; i < balls.size(); i++) {

            balls.get(i).updatePosition(dt, g, d);
            balls.get(i).updateVelocity(dt, g, d);

            if(balls.get(i).getXPos() < 0 || balls.get(i).getYPos() > this.getHeight()) {
                balls.remove(i);
                //addExplosiveBits();
                i--;
            }
        }
    }

    public void setGravity(double gravity) {

        g = gravity;
    }

    public void setDrag(double airResistance) {

        d = airResistance;
    }

    public ArrayList<Projectile> getBalls() {

        return balls;
    }

    public boolean inFlux() {

        if(balls.isEmpty())
            return false;

        return true;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        /* Cast it to Graphics2D */
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.BLACK);

    /* Enable anti-aliasing and pure stroke */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        for(Projectile ball : balls) {
            /* Construct a shape and draw it */
            Ellipse2D.Double shape = new Ellipse2D.Double(ball.getXPos(), ball.getYPos(), 5, 5);
            g2.fill(shape);
        }
    }
}
