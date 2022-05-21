import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Line {
    private Point p1;
    private Point p2;
    public static ArrayList<Point> points = new ArrayList<>();
    public static ArrayList<Line> lines = new ArrayList<>();
    public static ArrayList<Wall> walls = new ArrayList<>();

    public Line (Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public static PointOnLine findX(Line l, int y){
        double pointsy[] = new double[]{l.getP1().getNormalizedY(), l.getP2().getNormalizedY()};
        Arrays.sort(pointsy);
        if(y >= pointsy[0] && y <= pointsy[1]){
            if(pointsy[0] == pointsy[1]){
                double x1 = l.getP1().getNormalizedX();
                double x2 = l.getP1().getNormalizedX();
                return x2 < x1 ? new PointOnLine(l,x2,l.getP2().getNormalizedZ()) : new PointOnLine(l,x1,l.getP1().getNormalizedZ());
            }
            double xa = l.getP1().getNormalizedX();
            double xb = l.getP2().getNormalizedX();
            double ya = l.getP1().getNormalizedY();
            double yb = l.getP2().getNormalizedY();
            double za = l.getP1().getZ();
            double zb = l.getP2().getZ();
            double x = (yb==ya)? xa : ((y-ya)*(xb-xa))/(yb-ya) + xa;
            double z = (xb == xa)? zb : ((x-xa)*(zb-za))/(xb-xa) + za;
            return new PointOnLine(l,x,z);
        }
        return null;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public void drawLine(Graphics g){
        if(p1.getZ() > -GFG1.d && p2.getZ() > -GFG1.d){
            g.drawLine((int) p1.getNormalizedX(), (int) p1.getNormalizedY(), (int) p2.getNormalizedX(), (int) p2.getNormalizedY());
            //System.out.print("nowe koordy");
            //System.out.println((int) p1.getNormalizedY() + " ");
        }

    }

    public ArrayList<Wall> wallsWithLine(){
        ArrayList<Wall> walls = new ArrayList<>();
        for(Wall w: Line.walls){
            if(w.checkLine(this)){
                walls.add(w);
            }
        }
        return walls;
    }

    public static void sortWalls(){

    }
}
