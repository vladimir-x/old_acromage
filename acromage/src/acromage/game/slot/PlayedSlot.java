/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.slot;

import acromage.game.AppImpl;
import acromage.game.desk.Board;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Сыгранные карты
 *
 * @author elduderino
 */
public class PlayedSlot extends Slot {

    private static final int SPACER = 5;

    public int posX, posY;
    Board board;

    public PlayedSlot(Board board, int posX, int posY) {
        this.board = board;
        this.posX = posX;
        this.posY = posY;

        card = null;
    }

    @Override
    public void update() {

        float x = board.getRectangle().x + board.CARDS_SPACE_X + posX * (AppImpl.settings.cardWidth + SPACER);
        float y = board.getRectangle().y + board.getRectangle().height
                - board.CARDS_SPACE_Y - AppImpl.settings.cardHeight
                - posY * (AppImpl.settings.cardHeight + SPACER);
        rect = new Rectangle(x, y,
                AppImpl.settings.cardWidth,
                AppImpl.settings.cardHeight
        );
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);
        renderer.rect(rect.x, rect.y, rect.width, rect.height);
        renderer.end();
    }

    @Override
    void onGetCard() {
        System.out.println("card.isSwitchTurn() = " + card.isSwitchTurn());
        if (card.isSwitchTurn()) {
            AppImpl.control.switchTurn();
        } 
        board.makeEmptySlot();
        board.update();
    }

}
