/*
 *  Projectile with properties
 */
public class Projectile {

    private double xPos; // x-coordinate
    private double yPos; // y-coordinate
    private double xVel; // x component velocity magnitude
    private double yVel; // y component velocity magnitude
    private double dir;  // angle ball is shot from
                         // used to instantiate component velocities
    private double m;    // mass

    public Projectile(double x, double y, double mass, double velocity, double direction) {

        xPos = x;
        yPos = y;

        m = mass;

        dir = direction;

        xVel = velocity * Math.cos(dir);
        yVel = velocity * Math.sin(dir);
    }

    /*
     *  Updates coordinates according Kinematic Equations(metric system)
     *
     *  x = xo + vo t + ½ a t2
     */
    void updatePosition(double dt, double g, double d) {

        xPos = xPos + (xVel * dt) - ((d/m) * Math.pow(dt, 2) * 0.5);
        yPos = yPos + (yVel * dt) - (g * Math.pow(dt, 2) * 0.5);
    }

    /*
     *  Updates component velocities according Kinematic Equations(metric system)
     *
     *  v = vo + at
     */
    void updateVelocity(double dt, double g, double d) {

        xVel = xVel - dt * d;
        yVel = yVel + dt * g;
    }

    public double getXPos() {

        return xPos;
    }

    public double getYPos() {

        return yPos;
    }
}