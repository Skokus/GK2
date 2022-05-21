import org.junit.Before;
import org.junit.Test;

public class WallTest {
    Wall w1;
    Wall w2;
    @Before
    public void init() {
        LoadFile.readFile();
        w1 = Line.walls.get(0);
        w2 = Line.walls.get(1);
    }

    @Test
    public void checkIfCountsProper(){
        for(Point p : Line.points){
            p.rotateZ(180);
            p.Normalize(500, 500);
        }
        for(Line l : w2.getLines()){
            System.out.println(l.getP1().getNormalizedY() + " ");
            System.out.println(l.getP2().getNormalizedY() + " ");
        }
        System.out.println(w1.getZinPoint(500, 470));
        System.out.println(w2.getZinPoint(500, 470));
    }
}
