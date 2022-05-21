public class PointOnLine {
    private Line line;
    private double x;
    private double z;

    public PointOnLine(Line line, double x) {
        this.line = line;
        this.x = x;
        this.z = 0;
    }

    public PointOnLine(Line line, double x, double z) {
        this.line = line;
        this.x = x;
        this.z = z;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
