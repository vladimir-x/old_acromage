/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.gdx.game;

/**
 *
 * @author elduderino
 */
public class Settings {

    private Integer width;
    private Integer height;

    public Settings() {
        
        width = 800;
        height = 600;
    }

    public void load() {
        throw new UnsupportedOperationException("no supported yet");
    }

    public void save() {
        throw new UnsupportedOperationException("no supported yet");
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
