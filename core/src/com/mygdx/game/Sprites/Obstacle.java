package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ColorDodge;

/**
 * Created by musial321 on 3/21/2016.
 */
public abstract class Obstacle {

    public Vector2 position;
    Vector2[] hitPoints = new Vector2[3];
    Color color;

    public Obstacle(Vector2 position, Color c)
    {
       this.position = position;
        this.color = c;
    }

    public void repositionObstacles()
    {

    }

    public abstract void render();

    public void dispose()
    {
    }




}
