/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.desktop;

import acromage.game.ApplicationImpl;
import acromage.game.Settings;
import java.awt.Window;
import java.awt.event.WindowEvent;

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
    }

    public static void closeWindow(Window window) {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public static void applyVideoSettings() {
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
