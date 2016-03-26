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
public class SpikeLeft extends Obstacle {




    public SpikeLeft(Vector2 position, Color c) {
        super(position, c);

        hitPoints[0] = new Vector2(18+position.x,27+position.y);
        hitPoints[1] = new Vector2(18+position.x,81+position.y);
        hitPoints[2] = new Vector2(18+position.x,135+position.y);


    }

    @Override
    public void render() {
        //Sprite c = new Sprite(ColorDodge.spike);
        ColorDodge.spikesprite.setPosition(position.x, position.y);
        ColorDodge.spikesprite.setColor(this.color);

        ColorDodge.spikesprite.draw(ColorDodge.batch);
    }
}
