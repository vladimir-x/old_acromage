/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acromage.game.interfaсe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author admin
 */
public interface Rendereble {
 
    // прямоугольник, в котором находится объект
    //Rectangle getRectangle();
    
    // пересчитать данные для рендеринга (перемещение объекта или изменение настроек экрана)
    void update();
    
    //отрисовка примитивов
    void render(ShapeRenderer renderer);
    
    //отрисовка спрайтов
    void render(SpriteBatch spriteBatch);
    
}
