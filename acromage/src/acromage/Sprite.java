/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author admin
 */
public class Sprite {
    private Image image; //изображение
    
    public Sprite(Image image) {
        this.image = image;
    }
    
    public int getWidth() { //получаем ширину картинки
        return image.getWidth(null);
    }

    public int getHeight() { //получаем высоту картинки
        return image.getHeight(null);
    }
    
    public void draw(Graphics g,int x,int y) { //рисуем картинку
        g.drawImage(image,x,y,null);
    }
}
