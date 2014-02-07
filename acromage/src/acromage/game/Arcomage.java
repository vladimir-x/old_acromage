/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.desk.Board;
import acromage.game.interfaсe.Actionable;
import acromage.game.desk.Deskzone;
import acromage.game.desk.Hand;
import acromage.game.desk.ResPanel;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author elduderino
 */
public class Arcomage implements Rendereble, Actionable {

    ResPanel resLeft, resRight;

    Deskzone zones[];

    Board board;
    Hand hand;
    ResPanel resPanels[];

    public Arcomage() {

        board = new Board(Deskzone.CENTER);
        resLeft = new ResPanel(Deskzone.WEST);
        resRight = new ResPanel(Deskzone.EAST);
        hand = new Hand(Deskzone.SOUTH,board.getActiveSlot());

        board.setColor(Color.DARK_GRAY.sub(0, 0, 0, 0.5f));
        resLeft.setColor(Color.BLUE.sub(0, 0, 0, 0.5f));
        resRight.setColor(Color.RED.sub(0, 0, 0, 0.5f));
        hand.setColor(Color.LIGHT_GRAY.sub(0, 0, 0, 0.5f));

        zones = new Deskzone[]{board, resLeft, resRight, hand};

    }

    @Override
    public void render(ShapeRenderer renderer,SpriteBatch spriteBatch) {

        
        spriteBatch.begin();
        spriteBatch.draw(AppImpl.resources.boardTexture, 0, 0);
        spriteBatch.end();

        
        for (Deskzone zone : zones) {
            zone.render(renderer,spriteBatch);
        }
        
    }

    @Override
    public void update() {
        for (Deskzone zone : zones) {
            zone.update();
        }
    }

    @Override
    public void action(float delta) {
        board.action(delta);
        hand.action(delta);
    }
    

    public void promptToStep(float propX, float propY) {
        boolean hasSelect = hand.promptToSelect(propX,propY);
    }

    void promptToDrop(float propX, float propY) {
        boolean hasSelect = hand.promptToSelect(propX,propY);
    }
}
