package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public class Position {
    private double x,y,z;

    public Position() {
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position po = (Position)o;
            return distance(po) < 1e-4;
        }
        return false;
    }

    public Position(Position orig) {
        this.x = orig.x;
        this.y = orig.y;
        this.z = orig.z;
    }

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double distance(Position p) {
        double dx = p.x - x;
        double dy = p.y - y;
        double dz = p.z - z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
