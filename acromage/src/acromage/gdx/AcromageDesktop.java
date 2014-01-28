/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.gdx;

import acromage.gdx.game.ApplicationImpl;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.ViewportLayout;

/**
 *
 * @author admin
 */
public class AcromageDesktop {

    public static void main(String[] argv) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = false;
        config.title = "Acromage gdx";
        config.width = 800;
        config.height = 600;
        config.useGL20 = true;

        LwjglCanvas canvas = new LwjglCanvas(new ApplicationImpl(), config);

        JFrame frame = getJpanel();
        frame.setLayout(new BorderLayout());
        frame.add(canvas.getCanvas(), BorderLayout.CENTER);
        frame.pack();
    }

    public static JFrame getJpanel() {

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
