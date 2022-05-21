public class WallZ {
    private Wall wall;
    private double z;

    public WallZ(Wall wall, double z) {
        this.wall = wall;
        this.z = z;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
