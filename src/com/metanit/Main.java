package com.metanit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import static java.lang.StrictMath.*;

public class Main {
    public static void main(String[] args) {
        class Draw extends JFrame {
            class dot3 extends Point {
                public double x;
                public double y;
                public double z;

                public dot3(double x, double y, double z) {
                    this.x = x;
                    this.y = y;
                    this.z = z;
                }

                public dot3(double x, double y) {
                    this.x = x;
                    this.y = y;
                }
            }
            class Line {
                dot3 start;
                dot3 end;
                public Line(dot3 start, dot3 end) {
                    this.start = start;
                    this.end = end;
                }
            }

            public Line[] lines;
            public dot3[] points;
            public double ER = 2000; // до объекта
            public double EF = 200; //повротов в мировых коордах
            public double ET = 0; // поворот по x,y
            public double d = 1500; // расстояние до экрана

            public  dot3 tVCoord(dot3 point){
                return new dot3( point.x* (-sin(ET))+ point.y* cos(ET),
                        point.x* (-cos(EF)* cos(ET))+ point.y* (-cos(EF)* sin(ET))+ point.z* sin(EF),
                        point.x* (-sin(EF)*  cos(ET))+ point.y*(-sin(EF)* sin(ET))+ point.z* (-cos(EF))+ ER);
            }

            public dot3 tPCoord(dot3 point){
                return new dot3(d* point.x/ point.z + 200, d* point.y/ point.z + 200);
            }

            public  Draw(double ER, double ET, double EF){
                super("куб");
                this.ER = ER;
                this.ET = ET;
                this.EF = EF;
                lines = new Line[]{
                        new Line(new dot3(-100,-100,-100), new dot3(100,-100,-100)),
                        new Line(new dot3(-100,-100,-100), new dot3(-100,100,-100)),
                        new Line(new dot3(-100,-100,-100), new dot3(-100,-100,100)),
                        new Line(new dot3(100,-100,-100), new dot3(100,100,-100)),

                        new Line(new dot3(100,-100,-100), new dot3(100,-100,100)),
                        new Line(new dot3(100,100,-100), new dot3(-100,100,-100)),
                        new Line(new dot3(100,100,-100), new dot3(100,100,100)),
                        new Line(new dot3(-100,100,-100), new dot3(-100,100,100)),

                        new Line(new dot3(-100,100,100), new dot3(100,100,100)),
                        new Line(new dot3(-100,100,100), new dot3(-100,-100,100)),
                        new Line(new dot3(100,100,100), new dot3(100,-100,100)),
                        new Line(new dot3(100,-100,100), new dot3(-100,-100,100)),

                        /*new Line(new dot3(0,0,0), new dot3(200,0,0)),
                        new Line(new dot3(0,0,0), new dot3(0,200,0)),
                        new Line(new dot3(0,0,0), new dot3(0,0,200)),
                        new Line(new dot3(200,0,0), new dot3(200,200,0)),

                        new Line(new dot3(200,0,0), new dot3(200,0,200)),
                        new Line(new dot3(200,200,0), new dot3(0,200,0)),
                        new Line(new dot3(200,200,0), new dot3(200,200,200)),
                        new Line(new dot3(0,200,0), new dot3(0,200,200)),

                        new Line(new dot3(0,200,200), new dot3(200,200,200)),
                        new Line(new dot3(0,200,200), new dot3(0,0,200)),
                        new Line(new dot3(200,200,200), new dot3(200,0,200)),
                        new Line(new dot3(200,0,200), new dot3(0,0,200)),*/
                };
                points = new dot3[]{
                        new dot3(100, 100, 100),  //0
                        new dot3(100, 100, -100),  //1
                        new dot3(-100, 100, -100),  //2
                        new dot3(-100, 100, 100),  //3

                        new dot3(100, -100, 100),  //4
                        new dot3(100, -100, -100),  //5
                        new dot3(-100, -100, -100),  //6
                        new dot3(-100, -100, 100),  //7
                };
            }
            public ArrayList<ArrayList<dot3>> createGrani(){
                ArrayList<dot3> a = new ArrayList<dot3>();
                ArrayList<ArrayList<dot3>> grani = new ArrayList<ArrayList<dot3>>();

                a.add(tVCoord (points[0]));
                a.add(tVCoord (points[1]));
                a.add(tVCoord (points[2]));
                a.add(new dot3(255, 255, 0));
                a.add(tPCoord(tVCoord (points[0])));
                a.add(tPCoord(tVCoord (points[1])));
                a.add(tPCoord(tVCoord (points[2])));
                a.add(tPCoord(tVCoord (points[3])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();

                a.add(tVCoord (points[0]));
                a.add(tVCoord (points[3]));
                a.add(tVCoord (points[7]));
                a.add(new dot3(255, 0, 255));
                a.add(tPCoord(tVCoord (points[0])));
                a.add(tPCoord(tVCoord (points[3])));
                a.add(tPCoord(tVCoord (points[7])));
                a.add(tPCoord(tVCoord (points[4])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();

                a.add(tVCoord (points[0]));
                a.add(tVCoord (points[4]));
                a.add(tVCoord (points[5]));
                a.add(new dot3(0, 255, 255));
                a.add(tPCoord(tVCoord (points[0])));
                a.add(tPCoord(tVCoord (points[4])));
                a.add(tPCoord(tVCoord (points[5])));
                a.add(tPCoord(tVCoord (points[1])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();

                a.add(tVCoord (points[7]));
                a.add(tVCoord (points[6]));
                a.add(tVCoord (points[5]));
                a.add(new dot3(0, 0, 255));
                a.add(tPCoord(tVCoord (points[7])));
                a.add(tPCoord(tVCoord (points[6])));
                a.add(tPCoord(tVCoord (points[5])));
                a.add(tPCoord(tVCoord (points[4])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();

                a.add(tVCoord (points[7]));
                a.add(tVCoord (points[3]));
                a.add(tVCoord (points[2]));
                a.add(new dot3(0, 255, 0));
                a.add(tPCoord(tVCoord (points[7])));
                a.add(tPCoord(tVCoord (points[3])));
                a.add(tPCoord(tVCoord (points[2])));
                a.add(tPCoord(tVCoord (points[6])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();

                a.add(tVCoord (points[6]));
                a.add(tVCoord (points[2]));
                a.add(tVCoord (points[1]));
                a.add(new dot3(255, 0, 0));
                a.add(tPCoord(tVCoord (points[6])));
                a.add(tPCoord(tVCoord (points[2])));
                a.add(tPCoord(tVCoord (points[1])));
                a.add(tPCoord(tVCoord (points[5])));
                grani.add((ArrayList<dot3>) a.clone());
                a.clear();
                return grani;
            }

            public double h (dot3 p1, dot3 p2, dot3 p3){
                double h;
                h = -p1.x*((p2.y - p1.y)*(p3.z - p1.z) - (p2.z - p1.z)*(p3.y - p1.y)) +
                        p1.y*((p2.x - p1.x)*(p3.z - p1.z) - (p2.z - p1.z)*(p3.x - p1.x)) -
                        p1.z*((p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x));
                return h;
            }

            public dot3 baricentr(){
                double bx = 0;
                double by = 0;
                double bz = 0;
                for(dot3 point : points){
                    bx += tVCoord(point).x;
                    by += tVCoord(point).y;
                    bz += tVCoord(point).z;
                }
                bx /= 8;
                by /= 8;
                bz /= 8;
                return new dot3(bx, by, bz);
            }

            public boolean vectAngle(dot3 p1, dot3 p2, dot3 p3){
                double Ex = (p2.y - p1.y)*(p3.z - p1.z) - (p2.z - p1.z)*(p3.y - p1.y);
                double Ey = -(p2.x - p1.x)*(p3.z - p1.z) + (p2.z - p1.z)*(p3.x - p1.x);
                double Ez = (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);

                boolean centrSign = (baricentr().x*Ex + baricentr().y*Ey + baricentr().z*Ez + h(p1, p2, p3)) < 0;
                if(centrSign) {Ex *= -1; Ey *= -1; Ez *= -1;}

                double Elength = Math.sqrt(Ex*Ex + Ey*Ey + Ez*Ez);

                double Dx = (p1.x + p2.x + p3.x);
                Dx /= 3;
                double Dy = (p1.y + p2.y + p3.y);
                Dy /= 3;
                double Dz = (p1.z + p2.z + p3.z);
                Dz /= 3;
                double Dlength = Math.sqrt(Dx*Dx + Dy*Dy + Dz*Dz);

                if(((Dx*Ex + Dy*Ey + Dz*Ez)/(Elength*Dlength)) > 0 ) return true;
                else return false;
            }

            public Path2D.Double viewedGran(dot3 p1, dot3 p2, dot3 p3, dot3 p4){
                Path2D.Double parallelogram;
                parallelogram = new Path2D.Double();
                parallelogram.moveTo(p1.x + 100,p1.y + 100);
                parallelogram.lineTo(p2.x + 100,p2.y + 100);
                parallelogram.lineTo(p3.x + 100,p3.y + 100);
                parallelogram.lineTo(p4.x + 100,p4.y + 100);
                parallelogram.closePath();
                return parallelogram;
            }
            public void paint(Graphics gr){
                super.paint(gr);
                Graphics2D g = (Graphics2D)gr;
                for(ArrayList<dot3> gran : createGrani()){
                    if(vectAngle(gran.get(0), gran.get(1), gran.get(2))){
                        Color myColor = new Color((int)gran.get(3).x, (int)gran.get(3).y, (int)gran.get(3).z);
                        g.setColor(myColor);
                        g.fill(viewedGran(gran.get(4), gran.get(5), gran.get(6), gran.get(7)));
                    }
                }
            }
   /*         public void paint(Graphics g){
                super.paint(g);
                for(int i = 0; i <lines.length; i++ ){
                    g.setColor(Color.red);
                    dot3 start = tPCoord (tVCoord (lines[i].start));
                    dot3 end = tPCoord (tVCoord (lines[i].end));
                    g.drawLine((int) start.x, (int) start.y,  (int) end.x,  (int) end.y);
                }
            }*/
        }

        Draw frame = new Draw(2000, 0,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        ActionListener listener = e -> {
            frame.EF += 0.1;
            frame.ET += 0.1;
            SwingUtilities.invokeLater(() -> {
                frame.repaint();
            });
        };
        Timer timer = new Timer(250, listener);
        timer.start();
    }
}
