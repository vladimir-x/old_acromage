/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elduderino
 */
public class GridRender {

    private class GridLine {

        Vector2 p1, p2, pt;
        Color color;
        String title;
    };

    List<GridLine> lines;
    BitmapFont font;

    int stepx;
    int stepy;

    public GridRender() {
        font = new BitmapFont(
                new FileHandle("data" + File.separator + "fc.fnt"),
                new FileHandle("data" + File.separator + "fc.png"),
                false
        );
        stepx = 50;
        stepy = 50;
        update();
    }

    public void update() {
        lines = new ArrayList<GridLine>();

        for (int i = 0, cnt = 0; i <= ApplicationImpl.settings.cameraWidth; i += stepx, cnt++) {
            GridLine line = new GridLine();
            line.p1 = new Vector2(i, -ApplicationImpl.settings.cameraHeight);
            line.p2 = new Vector2(i, ApplicationImpl.settings.cameraHeight);
            line.pt = new Vector2(i, 50);

            line.color = Color.RED;
            line.title = String.valueOf(cnt);

            lines.add(line);
        }

        for (int i = 0, cnt = 0; i <= ApplicationImpl.settings.cameraHeight; i += stepy, cnt++) {
            GridLine line = new GridLine();
            line.p1 = new Vector2(-ApplicationImpl.settings.cameraWidth, i);
            line.p2 = new Vector2(ApplicationImpl.settings.cameraWidth, i);
            line.pt = new Vector2(50, i);

            line.color = Color.CYAN;
            line.title = String.valueOf(cnt);

            lines.add(line);
        }
    }

    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (GridLine line : lines) {
            renderer.setColor(line.color);
            renderer.line(line.p1, line.p2);
        }
        renderer.end();

        spriteBatch.begin();
        for (GridLine line : lines) {
            font.draw(spriteBatch, line.title, line.pt.x, line.pt.y);
        }
        spriteBatch.end();
    }

}
