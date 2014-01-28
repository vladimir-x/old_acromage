/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.gdx;

import acromage.gdx.game.ApplicationImpl;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ViewportLayout;

/**
 *
 * @author admin
 */
public class AcromageDesktop {

    static JFrame frame;
    static ApplicationImpl applicationImpl;

    public static void main(String[] argv) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                makePanel();
            }
        });

    }

    public static void makePanel() {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = false;

        applicationImpl = new ApplicationImpl();
        LwjglCanvas lwCanvas = new LwjglCanvas(applicationImpl, config);

        frame = new JFrame();
        frame.setSize(800, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(lwCanvas.getCanvas());
        //frame.pack();

        frame.setVisible(true);

        frame.setMenuBar(getMenuBar());
    }

    public static MenuBar getMenuBar() {

        MenuItem startItem = new MenuItem("Старт");
        MenuItem optionItem = new MenuItem("Настройки");
        MenuItem exitItem = new MenuItem("Выход");

        startItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        optionItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        Menu mainMenu = new Menu("Игра");
        mainMenu.add(startItem);
        mainMenu.add(optionItem);
        mainMenu.add(exitItem);

        MenuBar mb = new MenuBar();

        mb.add(mainMenu);
        return mb;
    }
}
