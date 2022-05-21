import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class LoadFile {
    public static void readFile(){
        try {
            Line.points = new ArrayList<>();
            Line.lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/dane2.txt"));
            String line = reader.readLine();
            while(line != null){
                String dane[] = line.split(" ");
                if(dane[0].compareTo("P") == 0){
                    Line.points.add(new Point(Integer.parseInt(dane[1]), Integer.parseInt(dane[2]), Integer.parseInt(dane[3])));
                } else if(dane[0].compareTo("L") == 0) {
                    Line.lines.add(new Line(Line.points.get(Integer.parseInt(dane[1])),Line.points.get(Integer.parseInt(dane[2]))));
                } else {
                    ArrayList<Line> lines = new ArrayList<>();
                    for(int i = 1; i < dane.length -1; i++){
                        lines.add(Line.lines.get(Integer.parseInt(dane[i])));
                    }
                    Color color;
                    try {
                        Field field = Class.forName("java.awt.Color").getField(dane[dane.length-1]);
                        color = (Color)field.get(null);
                    } catch (Exception e) {
                        color = Color.RED; // Not defined
                    }
                    Wall wall = new Wall(lines, color);
                    Line.walls.add(wall);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
