/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.gdx;

import acromage.gdx.game.ApplicationImpl;
import acromage.gdx.game.Settings;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import java.awt.Button;
import java.awt.Container;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class AcromageDesktop {

    static MainFrame frame;
    static Settings settings;
    static ApplicationImpl applicationImpl;

    public static void main(String[] argv) {

        makePanel();

    }

    public static void makePanel() {

        settings = loadSettings();
        applicationImpl = new ApplicationImpl(settings);

        frame = new MainFrame(applicationImpl, settings);
        frame.setLocationRelativeTo(null);
    }

    public static void closeWindow(Window window) {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public static void applyVideoSettings() {
        Settings settings = applicationImpl.getSettings();
        frame.setResizable(true);
        frame.setSize(settings.windowWidth, settings.windowHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static Settings loadSettings() {
        Settings settings = new Settings();

        settings.windowWidth = 800;
        settings.windowHeight = 600;

        settings.fullscreen = false;
        
        settings.cameraWidth = 640;
        settings.cameraHeight = 480;

        return settings;
    }

    public static void saveSettings(Settings settings) {
        // реализация позже
    }
}
