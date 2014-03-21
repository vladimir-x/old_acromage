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
import acromage.game.interfaсe.Rendereble;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    Board board;
    Hand hand, userHand, opponentHand;
    ResPanel resPanels[];

    Player player, user, opponent;
    RoundEnum round;
    CardActionEnum cardAction;

    Boolean firstTurn;

    public Arcomage() {

        board = new Board(Deskzone.CENTER);
        resLeft = new ResPanel(Deskzone.WEST);
        resRight = new ResPanel(Deskzone.EAST);

        userHand = new Hand(Deskzone.SOUTH, board.getActiveSlot());
        opponentHand = new Hand(Deskzone.SOUTH, board.getActiveSlot());

        board.setColor(Color.DARK_GRAY.sub(0, 0, 0, 0.5f));
        resLeft.setColor(Color.BLUE.sub(0, 0, 0, 0.5f));
        resRight.setColor(Color.RED.sub(0, 0, 0, 0.5f));

        userHand.setColor(Color.LIGHT_GRAY.sub(0, 0, 0, 0.5f));
        opponentHand.setColor(Color.LIGHT_GRAY.sub(0, 0, 0, 0.5f));

        user = new User();
        opponent = new Computer();
        firstTurn = true;

        startGame();
    }

    private void startGame() {

        user.takeCard(AppImpl.settings.cardCount);
        opponent.takeCard(AppImpl.settings.cardCount);
        
        userHand.fillHand(user.cards);
        opponentHand.fillHand(opponent.cards);

        round = RoundEnum.NOGAME;
        swicthRound();
    }

    public void swicthRound() {
        switch (round) {
            case OPPONENT:
                round = RoundEnum.USER_TURN;
                hand = userHand;
                player = user;
                break;
            case USER_TURN:
                round = RoundEnum.OPPONENT;
                hand = opponentHand;
                player = opponent;
                break;
            case NOGAME:
                if (firstTurn) {
                    round = RoundEnum.OPPONENT;
                } else {
                    round = RoundEnum.USER_TURN;
                }
                swicthRound();
                break;
        }
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
        hand.update();
    }

    @Override
    public void action(float delta) {
        board.action(delta);
        userHand.action(delta);
    }

    public void promptToStep(float propX, float propY, int button) {
        if (round.equals(RoundEnum.USER_TURN)) {

            if (button == Input.Buttons.LEFT) {
                cardAction = CardActionEnum.PROCESS;
            } else if (button == Input.Buttons.RIGHT) {
                cardAction = CardActionEnum.DROP;
            }
            boolean hasSelect = userHand.promptToSelect(propX, propY);

        }
    }

}
