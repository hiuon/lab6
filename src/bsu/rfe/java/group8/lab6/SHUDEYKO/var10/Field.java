package bsu.rfe.java.group8.lab6.SHUDEYKO.var10;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Field extends JPanel {

    private boolean paused;
    private ArrayList<BouncingBall> balls = new ArrayList<>(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball : balls){
            ball.paint(canvas);
        }
    }

    public void addBall() {
        balls.add(new BouncingBall(this));
    }


}
