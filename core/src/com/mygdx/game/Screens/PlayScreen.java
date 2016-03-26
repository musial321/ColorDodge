package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ColorDodge;
import com.mygdx.game.Sprites.*;
import com.sun.jndi.toolkit.url.Uri;

/**
 * Created by musial321 on 3/19/2016.
 */
public class PlayScreen implements Screen {

    ColorDodge game;
    public static Walls walls;
    static Ball ball;
    static ObstacleSpawner obstacleSpawner;
    boolean paused = false;
    double score;
    Texture circularButton;
    SpriteBatch nonMove;

    ShapeRenderer renderer;
    public static Viewport view;

    public PlayScreen(ColorDodge game)
    {
        this.game = game;

    }

    @Override
    public void show() {

        System.out.println("Show");
        nonMove = new SpriteBatch();
        //Gdx.net.openURI("http://google.com/");

        circularButton = new Texture("button.png");
        renderer = new ShapeRenderer();
        score = 0;
        walls = new Walls();
        ball = new Ball();
        obstacleSpawner = new ObstacleSpawner();
        view = new FitViewport(ColorDodge.WIDTH, ColorDodge.HEIGHT,  game.camera);
        view.getCamera().position.x = ColorDodge.WIDTH/2;
        view.getCamera().position.y = ColorDodge.HEIGHT/2;

    }

    @Override
    public void render(float delta) {





        if(!paused) {

            Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            game.batch.setProjectionMatrix(view.getCamera().combined);


            game.batch.begin();

                obstacleSpawner.update();
                obstacleSpawner.render();


            ColorDodge.font36.draw(game.batch,(int)score+"",40,view.getCamera().position.y + 375);
            ball.draw(game.batch);
            walls.createWalls();
            walls.reposition();

            if(!ball.gameStart)
            {

                ColorDodge.font60.draw(game.batch,"COLOR\nDODGE",view.getCamera().viewportWidth/2-115,view.getCamera().position.y + 200);
                ColorDodge.font36.draw(game.batch,"Tap to start",view.getCamera().viewportWidth/2-130,view.getCamera().position.y+40);


                ColorDodge.font36.draw(game.batch,"High Score:",view.getCamera().viewportWidth/2-110,view.getCamera().position.y-100);


                String addOn = "";

                for(int i = 0; i < 12-Integer.toString(ColorDodge.prefs.getInteger("highscore")).length(); i++)
                {
                    addOn += " ";
                }

                ColorDodge.font36.draw(game.batch,addOn + ColorDodge.prefs.getInteger("highscore"),view.getCamera().viewportWidth/2-140,view.getCamera().position.y-140);
                //game.batch.draw(circularButton,view.getCamera().viewportWidth/2-100,view.getCamera().position.y-70,50,50);



                game.batch.draw(circularButton,view.getCamera().viewportWidth/2-100,view.getCamera().position.y-70,50,50);
                ColorDodge.font36Black.draw(game.batch,"?",view.getCamera().viewportWidth/2-85,view.getCamera().position.y-30);



                //ColorDodge.font36.draw(game.batch,"?",view.getCamera().viewportWidth/2-135,view.getCamera().position.y+20);
                //renderer.circle(400,450, 30);
                //renderer.end();

            }

            game.batch.end();



            if(!ball.hit) {
                update(delta);
            }
            else
            {
                if(ColorDodge.prefs.getInteger("highscore") < score)
                {
                    ColorDodge.prefs.putInteger("highscore", (int)score);
                    ColorDodge.prefs.flush();
                }

                game.setScreen(new PlayScreen(game));
            }
        }
    }

    private void update(float delta)
    {
        if(ball.gameStart)
            score += delta*100;

        ball.update(delta);

        for(Obstacle o: obstacleSpawner.getSet1())
        {
            ball.contains(o);
        }

        for(Obstacle o: obstacleSpawner.getSet2())
        {
            ball.contains(o);
        }


        game.camera.translate(0,200*delta);

        if(ball.getY() > game.camera.position.y)
        {
            game.camera.position.y = ball.getY();
        }

        game.camera.update();
    }

    @Override
    public void resize(int width, int height) {

        view.update(width, height, false);
        ColorDodge.totalHeight = height;
        ColorDodge.totalWidth = width;
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        walls.dispose();
        ball.dispose();
        obstacleSpawner.dispose();
        circularButton.dispose();
        renderer.dispose();



    }
}
