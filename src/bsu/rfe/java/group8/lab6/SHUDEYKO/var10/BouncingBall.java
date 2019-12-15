package bsu.rfe.java.group8.lab6.SHUDEYKO.var10;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable {

    private static final int MAX_RADIUS = 40;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_SPEED = 15;

    private Field field;
    private int radius;
    private Color color;

    private double x;
    private double y;

    private double speedX;
    private double speedY;
    private int speed;
    private boolean fOn = false;
    private double friction;

    public BouncingBall(Field field){
        this.field = field;
        this.radius = new Double(Math.random()*(MAX_RADIUS-MIN_RADIUS)).intValue() + MIN_RADIUS;
        this.speed = new Double(Math.round(5*MAX_SPEED/radius)).intValue();
        if (speed > MAX_SPEED){
            speed = MAX_SPEED;
        }

        double angle = Math.random()*2*Math.PI;
        this.speedX = 3*Math.cos(angle);
        this.speedY = 3*Math.sin(angle);
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        x = Math.random()*(field.getSize().getWidth()-2*radius) + radius;
        y = Math.random()*(field.getSize().getHeight()-2*radius) + radius;
        Thread thisThread = new Thread(this);
        thisThread.start();

    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x-radius, y-radius, 2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }




    @Override
    public void run() {
        try{
            while(true){
                if (fOn){
                    if (Math.abs(speedX) > 1e-5 || Math.abs(speedY) > 1e-5) {
                        field.canMove(this);
                        if (x + speedX <= radius) {
                            speedX = -speedX*friction;
                            x = radius;
                        } else if (x + speedX >= field.getWidth() - radius) {
                            speedX = -speedX*friction;
                            x = new Double(field.getWidth() - radius).intValue();
                        } else if (y + speedY <= radius) {
                            speedY = -speedY*friction;
                            y = radius;
                        } else if (y + speedY >= field.getHeight() - radius) {
                            speedY = -speedY*friction;
                            y = new Double(field.getHeight() - radius).intValue();
                        } else {
                            x += speedX*friction;
                            y += speedY*friction;
                        }
                    }
                    else {
                        field.canMove(this);
                        speedX = 0.0;
                        speedY = 0.0;
                    }
                }
                else {
                    field.canMove(this);
                    if (x + speedX <= radius) {
                        speedX = -speedX;
                        x = radius;
                    } else if (x + speedX >= field.getWidth() - radius) {
                        speedX = -speedX;
                        x = new Double(field.getWidth() - radius).intValue();
                    } else if (y + speedY <= radius) {
                        speedY = -speedY;
                        y = radius;
                    } else if (y + speedY >= field.getHeight() - radius) {
                        speedY = -speedY;
                        y = new Double(field.getHeight() - radius).intValue();
                    } else {
                        x += speedX;
                        y += speedY;
                    }
                }
                Thread.sleep(16 - speed);
            }
        } catch (InterruptedException ex){

        }
    }
}
