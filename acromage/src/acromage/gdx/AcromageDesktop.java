/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.gdx;

import acromage.gdx.game.ApplicationImpl;
import acromage.gdx.game.OptionDialog;
import acromage.gdx.game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglFrame;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class AcromageDesktop {

    static JFrame frame;
    static ApplicationImpl applicationImpl;

    public static void main(String[] argv) {

        makePanel();

    }

    public static void makePanel() {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = false;
        config.width = 320;
        config.height = 240;
        
        applicationImpl = new ApplicationImpl();

        frame = new LwjglFrame(applicationImpl, config);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

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
                showOptionFrame();
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

    public static void showOptionFrame() {
        
        if (true){
            
            OptionDialog od = new OptionDialog(frame, true);
            od.setLocationRelativeTo(null);
            od.setVisible(true);
            return;
        }
        final JDialog options = new JDialog(frame); //new JFrame("Настройки");

        Container panel = options.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(new TextField("some text1"));
        panel.add(new TextField("some text2"));
        panel.add(new TextField("some text3"));

        JPanel buttonPanel = new JPanel();

        Button okButton = new Button("Применить");
        Button cancelButton = new Button("Отмена");

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                applyVideoSettings();
                closeWindow(options);
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow(options);
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);

        options.setTitle("Настройки");
        options.pack();
        options.setModal(true);
        options.setLocationRelativeTo(null);
        options.setVisible(true);

    }

    public static void closeWindow(Window window) {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public static void applyVideoSettings() {
        Settings settings = applicationImpl.getSettings();
        frame.setSize(settings.getWidth(), settings.getHeight());
        frame.setLocationRelativeTo(null);
    }
}
