/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Computer;
import acromage.game.data.Player;
import acromage.game.data.User;
import acromage.game.desk.Board;
import acromage.game.interfaсe.Actionable;
import acromage.game.desk.Deskzone;
import acromage.game.desk.Hand;
import acromage.game.desk.ResPanel;
import acromage.game.interfaсe.GameControlable;
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
/**
 *
 * @author elduderino
 */
public class Arcomage implements Rendereble, Actionable, GameControlable {

    ResPanel resLeft, resRight;

    Board board;
    Hand hand, userHand, opponentHand;
    ResPanel resPanels[];

    Player player, user, opponent;
    RoundEnum round;
    CardActionEnum cardAction;

    Boolean isTurning;

    public Arcomage() {

        board = new Board(Deskzone.CENTER);
        resLeft = new ResPanel(Deskzone.WEST);
        resRight = new ResPanel(Deskzone.EAST);

        user = new User();
        opponent = new Computer();
        
        userHand = new Hand(Deskzone.SOUTH, board.getActiveSlot(),user);
        opponentHand = new Hand(Deskzone.SOUTH, board.getActiveSlot(),opponent  );
        userHand.debugstr = "user";
        opponentHand.debugstr = "oppon";

        board.setColor(Color.DARK_GRAY.cpy().sub(0, 0, 0, 0.5f));
        resLeft.setColor(Color.BLUE.cpy().sub(0, 0, 0, 0.5f));
        resRight.setColor(Color.RED.cpy().sub(0, 0, 0, 0.5f));

        userHand.setColor(Color.LIGHT_GRAY.sub(0, 0, 0, 0.5f));
        opponentHand.setColor(Color.LIGHT_GRAY.sub(0, 0, 0, 0.5f));

        
        isTurning = !true;//поменяется в  startGame()->swicthTurn()
        startGame();
    }

    private void startGame() {

        user.takeCard(AppImpl.settings.cardCount);
        opponent.takeCard(AppImpl.settings.cardCount);

        switchTurn();
    }

    public void switchTurn() {
        isTurning = !isTurning;
        if (isTurning) {
            hand = userHand;
            player = user;
        } else {
            hand = opponentHand;
            player = opponent;
        }
        board.passCard(hand);
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        
                
        spriteBatch.begin();
        spriteBatch.draw(AppImpl.resources.boardTexture, 0, 0);
        spriteBatch.end();
                
        board.render(renderer, spriteBatch);
        resLeft.render(renderer, spriteBatch);
        resRight.render(renderer, spriteBatch);
        hand.render(renderer, spriteBatch);
       
    }

    @Override
    public void update() {
        board.update();
        resLeft.update();
        resRight.update();

        userHand.update();
        opponentHand.update();
    }

    @Override
    public void action(float delta) {
        board.action(delta);
        hand.action(delta);
    }

    public void promptToStep(float propX, float propY, int button) {
        if (isTurning) {

            if (button == Input.Buttons.LEFT) {
                hand.promptToSelect(propX, propY, false);
            } else if (button == Input.Buttons.RIGHT) {
                hand.promptToSelect(propX, propY, true);
            }

        }
    }

    @Override
    public boolean playCard(int r, boolean drop) {
        return hand.promptToSelect(r, drop);
    }

}
