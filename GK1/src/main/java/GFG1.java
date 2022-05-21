import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class MyCanvas extends JComponent {
    int Xc = 500;
    int Yc = 500;
    public void paint(Graphics g)
    {
        for (Point p: Line.points) {
            p.Normalize(Xc, Yc);
        }
        /*for (Wall w : Line.walls) {
            w.paintWall(g);
        }*/
        /*g.setColor(Color.BLACK);
        */
        Wall.paintWalls(g, Line.walls);
        //g.drawLine(30, 20, 80, 190);
        g.setColor(Color.BLACK);
        /*for (Line l: Line.lines){
            l.drawLine(g);
        }*/
        g.setColor(Color.RED);
        g.drawLine(500, 510, 500, 490);
        g.drawLine(510, 500, 490, 500);
    }
}

public class GFG1 extends JFrame implements KeyListener {
    int Xc = 500;
    int Yc = 500;
    public static int d = 500;
    public static double zoom = 1;
    int x = 0;
    int y = 0;
    int z = 0;
    int Xangle = 0;
    int Yangle = 0;
    int Zangle = 0;
    static JFrame window = new JFrame();
    static MyCanvas currentCanvas = new MyCanvas();
    Box box = Box.createVerticalBox();
    double krokd = 10;
    double krokzooma = 0.2;
    double krok = 10;
    double angle = 2;
    GFG1()
    {
        LoadFile.readFile();
        for(Point p : Line.points){
            for(int i = 0; i < 360/2; i++){
                p.rotateZ(2);
            }
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addKeyListener(this);
        window.setBounds(0, 0, 1000, 1000);
        window.add(currentCanvas, BorderLayout.CENTER);
        window.add(box, BorderLayout.EAST);
        window.setTitle("Paweł Cegielski 307332 - Projekt 2");
        window.getContentPane().setBackground(Color.WHITE);
        window.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_Z:
                for (Point p: Line.points) {
                    p.Move(0,0,-krok);
                }
                break;
            case KeyEvent.VK_D:
                for (Point p: Line.points) {
                    p.Move(-krok,0,0);
                }
                break;
            case KeyEvent.VK_A:
                for (Point p: Line.points) {
                    p.Move(krok,0,0);
                }
                break;
            case KeyEvent.VK_X:
                for (Point p: Line.points) {
                    p.Move(0,0,krok);
                }
                break;
            case KeyEvent.VK_W:
                for (Point p: Line.points) {
                    p.Move(0,krok,0);
                }
                break;
            case KeyEvent.VK_S:
                for (Point p: Line.points) {
                    p.Move(0,-krok,0);
                }
                break;
            case KeyEvent.VK_U:
                for (Point p: Line.points) {
                    p.rotateZ(angle);
                }
                break;
            case KeyEvent.VK_J:
                for (Point p: Line.points) {
                    p.rotateZ(-angle);
                }
                break;
            case KeyEvent.VK_I:
                for (Point p: Line.points) {
                    p.Move(0,0,d);
                    p.rotateY(angle);
                    p.Move(0,0,-d);
                }
                break;
            case KeyEvent.VK_K:
                for (Point p: Line.points) {
                    p.Move(0,0,d);
                    p.rotateY(-angle);
                    p.Move(0,0,-d);
                }
                break;
            case KeyEvent.VK_O:
                for (Point p: Line.points) {
                    p.Move(0,0,d);
                    p.rotateX(angle);
                    p.Move(0,0,-d);
                }
                break;
            case KeyEvent.VK_L:
                for (Point p: Line.points) {
                    p.Move(0,0,d);
                    p.rotateX(-angle);
                    p.Move(0,0,-d);
                }
                break;
            case KeyEvent.VK_Q:
                zoom(krokd);
                break;
            case KeyEvent.VK_E:
                zoom(-krokd);
                break;
            case KeyEvent.VK_R:
                przybliz(krokzooma);
                break;
            case KeyEvent.VK_F:
                przybliz(-krokzooma);
                break;
        }
        currentCanvas.repaint();
    }

    public void zoom(double i){
        if(d + i> 10)
            d += i;
    }

    public void przybliz(double i){
        if(zoom + i > 0.1)
            zoom += i;
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {

        GFG1 go = new GFG1();
    }
}