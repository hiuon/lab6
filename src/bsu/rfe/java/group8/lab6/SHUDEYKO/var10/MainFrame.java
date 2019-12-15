package bsu.rfe.java.group8.lab6.SHUDEYKO.var10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem frictionOnMenuItem;
    private JMenuItem frictionOffMenuItem;

    private Field field = new Field();

    public MainFrame(String F){
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);

        setExtendedState(MAXIMIZED_BOTH);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);
                }
                if (!frictionOffMenuItem.isEnabled() && !frictionOnMenuItem.isEnabled()) {
                    frictionOnMenuItem.setEnabled(true);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action frictionOnAction = new AbstractAction("Включить трение"){
            @Override
            public void actionPerformed(ActionEvent e) {
                //метод включения трения
                frictionOnMenuItem.setEnabled(false);
                frictionOffMenuItem.setEnabled(true);
            }
        };
        frictionOnMenuItem = controlMenu.add(frictionOnAction);
        frictionOnMenuItem.setEnabled(false);
        Action frictionOffAction = new AbstractAction("Выключить трение"){
            @Override
            public void actionPerformed(ActionEvent e) {
                //метод выключения трения
                frictionOffMenuItem.setEnabled(false);
                frictionOnMenuItem.setEnabled(true);
            }
        };
        frictionOffMenuItem = controlMenu.add(frictionOffAction);
        frictionOffMenuItem.setEnabled(false);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        String friction = "";
        if (args.length != 0){
            friction = args[0];
        }

        MainFrame frame = new MainFrame(friction);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
