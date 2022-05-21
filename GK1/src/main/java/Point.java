public class Point {
    private double X;
    private double Y;
    private double Z;
    private double normalizedX;
    private double normalizedY;
    private double normalizedZ;
    public Point (double X, double Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ() {
        return Z;
    }

    public double getNormalizedX() {
        return normalizedX;
    }

    public double getNormalizedY() {
        return normalizedY;
    }

    public double getNormalizedZ() {
        return normalizedZ;
    }

    public void Normalize(int Xc, int Yc){
        this.normalizedX = (X*GFG1.d)*GFG1.zoom/(Z+GFG1.d) + Xc;
        this.normalizedY = (Y*GFG1.d)*GFG1.zoom/(Z+GFG1.d) + Yc;
        this.normalizedZ = 0;
    }

    public void Move(double x, double y, double z){
        this.X += x;
        this.Y += y;
        this.Z += z;
    }

    public void rotateZ(double angle){
        double radians = Math.toRadians(angle);
        double x = this.X;
        double y = this.Y;
        this.X = x*Math.cos(radians) - y*Math.sin(radians);
        this.Y = x*Math.sin(radians) + y*Math.cos(radians);
    }

    public void rotateY(double angle){
        double radians = Math.toRadians(angle);
        double x = this.X;
        double z = this.Z;
        this.X = x*Math.cos(radians) + z*Math.sin(radians);
        this.Z = -x*Math.sin(radians) + z*Math.cos(radians);
    }

    public void rotateX(double angle){
        double radians = Math.toRadians(angle);
        double y = this.Y;
        double z = this.Z;
        this.Y = y*Math.cos(radians) - z*Math.sin(radians);
        this.Z = y*Math.sin(radians) + z*Math.cos(radians);
    }

}
