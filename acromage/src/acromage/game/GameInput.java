/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author elduderino
 */
public class GameInput implements InputProcessor {

    private AppImpl appImpl;

    public GameInput(AppImpl appImpl) {
        this.appImpl = appImpl;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        float propX = screenX * AppImpl.settings.cameraWidth / (1.0f * AppImpl.settings.windowWidth);
        float propY = (AppImpl.settings.windowHeight - screenY) * AppImpl.settings.cameraHeight / (1.0f * AppImpl.settings.windowHeight);

        appImpl.acromage.promptToStep(propX, propY, button);

        return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }

}
