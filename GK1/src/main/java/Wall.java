import java.awt.*;
import java.util.*;

public class Wall{
    private ArrayList<Line> lines;
    private Color color;
    private boolean isActive;
    private double z;

    public Wall(ArrayList<Line> l, Color c){
        this.lines = l;
        this.color = c;
        isActive = false;
        z = 0;
    }

    public void ChangeActive(){
        if(isActive == true){
            isActive = false;
        } else {
            isActive = true;
        }
    }

    public boolean checkLine(Line l){
        for(Line line: lines){
            if(l == line){
                return true;
            }
        }
        return false;
    }

    public void paintWall(Graphics g){
        ArrayList<Point> points = new ArrayList<>();
        for(Line l : lines){
            points.add(l.getP1());
            points.add(l.getP2());
        }
        int min = 1000;
        int max = 0;
        for(Point p : points){
            if(p.getNormalizedY() > max){
                max = (int)p.getNormalizedY();
            }
            if(p.getNormalizedY() < min){
                min = (int)p.getNormalizedY();
            }
        }
        if(max > 1000){
            max = 1000;
        }
        if(min < 0){
            min = 0;
        }
        for(int i = min; i <= max; i++){
            ArrayList<PointOnLine> list = new ArrayList<>();
            for(Line l : lines){
                PointOnLine p = Line.findX(l,i);
                if(p != null && l.getP1().getZ() > -GFG1.d && l.getP2().getZ() > -GFG1.d){
                    list.add(p);
                }
            }
            for(int j = 0; j < list.size()/2; j+=2){
                System.out.println("NOWE: " + color.toString() + " " + i);
                System.out.println((int)list.get(j).getX() + " " + (int)list.get(j+1).getX());
                g.setColor(color);
                g.drawLine((int)list.get(j).getX(), i, (int)list.get(j+1).getX(), i);
            }
        }

    }

    public static void paintWalls(Graphics g, ArrayList<Wall> walls){
        ArrayList<Point> points = new ArrayList<>();
        for(Wall w : walls){
            for(Line l : w.getLines()){
                points.add(l.getP1());
                points.add(l.getP2());
            }
        }
        int min = (int)points.get(0).getNormalizedY();
        int max = (int)points.get(0).getNormalizedY();
        for(Point p : points){
            if(p.getNormalizedY() > max){
                max = (int)p.getNormalizedY();
            }
            if(p.getNormalizedY() < min){
                min = (int)p.getNormalizedY();
            }
        }
        for(int i = min-1; i <= max+1; i++){
            ArrayList<PointOnLine> pointList = new ArrayList<>();
            ArrayList<Wall> activeWalls = new ArrayList<>();
            ArrayList<Wall> wallsOrder = new ArrayList<>();
            for(Line l : Line.lines){
                PointOnLine p = Line.findX(l,i);
                if(p != null && l.getP1().getZ() > -GFG1.d && l.getP2().getZ() > -GFG1.d){
                    pointList.add(p);
                }
            }
            Collections.sort(pointList, new Comparator<PointOnLine>() {
                @Override
                public int compare(PointOnLine o1, PointOnLine o2) {
                    double r = o1.getX() - o2.getX();
                    if(r < 0){
                        return -1;
                    } else if(r > 0){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            for(int a = 0; a < pointList.size()-1; a++){
                ArrayList<Wall> wallsWithLine = pointList.get(a).getLine().wallsWithLine();
                for(Wall w : wallsWithLine){
                    if(w.isActive == false){
                        activeWalls.add(w);
                        w.ChangeActive();
                    } else {
                        activeWalls.remove(w);
                        w.ChangeActive();
                    }
                }
                if(activeWalls.size() == 0){
                    wallsOrder.add(new Wall(new ArrayList<>(), Color.WHITE));
                } else if(activeWalls.size() == 1){
                    wallsOrder.add(activeWalls.get(0));
                } else {
                    Wall maxW = activeWalls.get(0);
                    double maxz = maxW.getZinPoint(pointList.get(a).getX()+0.1, i);
                    for(Wall wall : activeWalls){
                        if(maxz > wall.getZinPoint(pointList.get(a).getX()+0.1, i)){
                            maxz = wall.getZinPoint(pointList.get(a).getX()+0.1, i);
                            maxW = wall;
                        }
                    }
                    wallsOrder.add(maxW);
                }
            }
            for(int j = 0; j < wallsOrder.size(); j++){
                g.setColor(wallsOrder.get(j).getColor());
                g.drawLine((int)pointList.get(j).getX(), i, (int)pointList.get(j+1).getX(), i);
            }
            for(Wall w : Line.walls){
                w.setActive(false);
            }
        }
    }

    public double getZinPoint(double x, int y){
        ArrayList<PointOnLine> points = new ArrayList<>();
        for(Line l : lines){
            PointOnLine p = Line.findX(l, y);
            if(p != null){
                points.add(p);
            }
        }
        if(points.size() < 2){
            return points.get(0).getZ();
        }

        double xa = points.get(0).getX();
        double xb = points.get(1).getX();
        double za = points.get(0).getZ();
        double zb = points.get(1).getZ();
        if(xb == xa){
            return za;
        }
        return ((x-xa)*(zb-za))/(xb-xa) + za;
    }

    public boolean isInFrontOfCamera(int d){
        for(Line line: lines){
            if(line.getP1().getZ() > d || line.getP2().getZ() > d){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
