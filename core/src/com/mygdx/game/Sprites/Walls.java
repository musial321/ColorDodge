package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ColorDodge;

/**
 * Created by musial321 on 3/19/2016.
 */
public class Walls {

    Texture wall;
    Vector2 leftWall;
    Vector2 rightWall;
    Vector2 leftWall2;
    Vector2 rightWall2;

    static float WIDTH = 30;
    static float HEIGHT = 800;


    public Walls()
    {
        wall = new Texture("wall.png");

        leftWall = new Vector2(0.0f, 0.0f);
        rightWall = new Vector2(ColorDodge.camera.viewportWidth-wall.getWidth(),0);
        leftWall2 = new Vector2(0, wall.getHeight());
        rightWall2 = new Vector2(ColorDodge.camera.viewportWidth-wall.getWidth(),wall.getHeight());

    }

    public void createWalls()
    {
        ColorDodge.batch.draw(wall, rightWall.x, rightWall.y);
        ColorDodge.batch.draw(wall, leftWall.x , leftWall.y);
        ColorDodge.batch.draw(wall, rightWall2.x, rightWall2.y);
        ColorDodge.batch.draw(wall, leftWall2.x , leftWall2.y);
    }

    public void reposition()
    {
        if(ColorDodge.camera.position.y  - ColorDodge.camera.viewportHeight/2 > (leftWall.y + wall.getHeight()))
        {
            leftWall.y += wall.getHeight()*2;
            rightWall.y += wall.getHeight()*2;
        }

        if(ColorDodge.camera.position.y  - ColorDodge.camera.viewportHeight/2 > (leftWall2.y + wall.getHeight()))
        {
            leftWall2.y += wall.getHeight()*2;
            rightWall2.y += wall.getHeight()*2;
        }
    }

    public void dispose()
    {
        wall.dispose();
    }






}
