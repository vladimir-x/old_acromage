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
import java.io.File;

/**
 *
 * @author admin
 */
public class ArcomageDesktop {

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
        // реализация из файла настроек позже
        Settings settings = new Settings();

        settings.windowWidth = 800;
        settings.windowHeight = 600;

        settings.fullscreen = false;

        settings.cameraWidth = 640;
        settings.cameraHeight = 480;

        settings.cardWidth = 95;
        settings.cardHeight = 128;

        settings.cardCount = 6;

        //шрифты
        settings.fontFnt ="data" + File.separator + "fc.fnt";
        settings.fontPng ="data" + File.separator + "fc.png";
        
        //текстуры
        settings.welcomeTexture = "data" + File.separator + "layout.bmp";
        settings.aboutTexture = "data" + File.separator + "credits.bmp";
        settings.boardTexture = "data" + File.separator + "layout.bmp";
        settings.deckTexture = "data" + File.separator + "deck_ru.bmp";
        settings.itemsTexture = "data" + File.separator + "items.bmp";
        settings.titleTexture = "data" + File.separator + "title.bmp";

        // ширина и высота текстур
        settings.welcomeTextureWidth = 640;
        settings.welcomeTextureHeight = 480;

        settings.boardTextureWidth = 640;
        settings.boardTextureHeight = 480;

        settings.deckTextureWidth = 95;
        settings.deckTextureHeight = 128;

        settings.towerTextureWidth = 45;
        settings.towerTextureHeight = 200;
        settings.towerHeadTextureWidth = 68;
        settings.towerHeadTextureHeight = 94;
        
        settings.wallTextureWidth=24;
        settings.wallTextureHeight=200;
        settings.wallHeadTextureWidth=45;
        settings.wallHeadTextureHeight=38;
        
        settings.resTextureWidth = 78;
        settings.resTextureHeight = 72;

        // место текстуры
        settings.deckUndoTextureX = 0;
        settings.deckUndoTextureY = 0;

        settings.towerTextureX = 330;
        settings.towerTextureY = 0;
        settings.towerHeadRedTextureX =100;
        settings.towerHeadRedTextureY =0;
        settings.towerHeadBlueTextureX =100;
        settings.towerHeadBlueTextureY =100;
        
        settings.wallTextureX=300;
        settings.wallTextureY=0;
        settings.wallHeadTextureX=0;
        settings.wallHeadTextureY=128;
        
        settings.brickTextureX = 200;
        settings.brickTextureY = 0;
        settings.gemTextureX = 200;
        settings.gemTextureY = 72;
        settings.beastTextureX = 200;
        settings.beastTextureY = 144;

        // количество в циклах
        settings.deckCountX = 10;
        settings.deckCountY = 12;

        return settings;
    }

    public static void saveSettings(Settings settings) {
        // реализация в фаил настроек позже
    }
}
