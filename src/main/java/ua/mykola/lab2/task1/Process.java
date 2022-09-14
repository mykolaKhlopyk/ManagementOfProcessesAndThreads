package main.java.ua.mykola.lab2.task1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Process extends JPanel {
    private static BufferedImage imagePooh;
    private static BufferedImage imageBee;
    private static final int framesWidth = 1000;
    private static final int framesHeight = 700;
    public static final int sizeOfPoint = 50;
    private static final int numberOfBees = 3;
    private Bear pooh;
    private Bee[] bees;
    private Field field;
    public void start() {
        Bag bag = new Bag(framesHeight / sizeOfPoint - 1);
        field = new Field(framesHeight / sizeOfPoint -1 , framesWidth / sizeOfPoint -1);
        pooh = new Bear(field);
        bees = new Bee[numberOfBees];
        for (int i = 0; i < numberOfBees; i++) {
            bees[i] = new Bee(field, pooh, bag);
        }

        pooh.start();
        for (Bee bee : bees) {
            bee.start();
        }
        JFrame f = new JFrame();
        f.add(this);
        try {
            imagePooh = ImageIO.read(new File("D://3_course//DistributedComputing//ManagementOfProcessesAndThreads//res//pooh.png"));
            imageBee = ImageIO.read(new File("D://3_course//DistributedComputing//ManagementOfProcessesAndThreads//res//bee.png"));
        } catch (IOException e) {
            System.out.println("incorrect path");
        }
        f.setSize(framesWidth, framesHeight);
        //f.setLayout(null);
        f.setVisible(true);
    }

    public void paint(Graphics g) {

        super.paint(g);
      //  g.drawRect(0, 100, 50, 50);
        for (Bee bee : bees) {
            g.drawImage(imageBee, bee.getX() * sizeOfPoint, bee.getRowForSearching() * sizeOfPoint, sizeOfPoint, sizeOfPoint, null);
        }
        if (!pooh.isAlive){
            return;
        }
        g.drawImage(imagePooh,  pooh.getX()* sizeOfPoint, pooh.getY() * sizeOfPoint, sizeOfPoint, sizeOfPoint, null);
        //System.out.println(pooh.getX()*sizeOfPoint+" "+pooh.getY()*sizeOfPoint);

        repaint();

    }



}
