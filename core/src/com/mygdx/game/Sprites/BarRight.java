package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorDodge;

/**
 * Created by musial321 on 3/23/2016.
 */
public class BarRight extends Obstacle {

    final static int WIDTH = 100;

    public BarRight(Vector2 position, Color c) {
        super(position, c);

        hitPoints[0] = new Vector2(26+position.x,position.y);
        hitPoints[1] = new Vector2(53+position.x,position.y);
        hitPoints[2] = new Vector2(92+position.x,position.y);


    }

    @Override
    public void render() {
        Sprite c = new Sprite(ColorDodge.rightbar);
        c.setPosition(position.x, position.y);
        c.setColor(this.color);

        c.draw(ColorDodge.batch);
    }
}
