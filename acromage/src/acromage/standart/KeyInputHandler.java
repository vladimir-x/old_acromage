/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.standart;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author admin
 */
@Deprecated
public class KeyInputHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) { //клавиша нажата
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //клавиша отпущена
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
    }

}
