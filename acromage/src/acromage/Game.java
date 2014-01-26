/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int WIDTH = 400; //ширина
    public static int HEIGHT = 300; //высота
    public static String NAME = "TUTORIAL 1"; //заголовок окна
    private boolean running = false;
    
    public static Sprite hero;

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER); //добавляем холст на наш фрейм
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        game.start();
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void init() {
            
        hero = getSprite("backdrop.bmp");
        addKeyListener(new KeyInputHandler());
        addMouseListener(new MouseInputHandler());
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2); //создаем BufferStrategy для нашего холста
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics(); //получаем Graphics из созданной нами BufferStrategy
        g.setColor(Color.black); //выбрать цвет
        g.fillRect(0, 0, getWidth(), getHeight()); //заполнить прямоугольник 

        hero.draw(g, 20, 20);
        
        g.dispose();
        bs.show(); //показать
    }

    public void update(long delta) {

    }

    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            String exepath = System.getProperty("user.dir");
            String imgPath = "img";
            String fullPath = exepath + File.separator + imgPath + File.separator + path;
            sourceImage = ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }
}
