package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.ColorDodge;
import com.mygdx.game.Screens.PlayScreen;

import java.util.Random;


/**
 * Created by musial321 on 3/19/2016.
 */
public class Ball extends Sprite{


    Vector2 ballVelocity;
    Texture ballTex;
    Vector2 points[];
    Vector2 proj;
    Vector2 wallBot;
    ShapeRenderer r;
    public static int startHeight = -1;

    int pixel;
    Color color;
    public boolean gameStart = false;
    public boolean hit = false;


    public Ball()
    {


        super(new Texture("sprite.png"));

        r = new ShapeRenderer();
        r.setAutoShapeType(true);


        proj = new Vector2();
        ballTex = new Texture("sprite.png");
        color = new Color(Color.LIGHT_GRAY);
        ballVelocity = new Vector2(200f, 200f);
        points = new Vector2[2];

        this.setColor(Color.LIGHT_GRAY);
        this.setPosition(ColorDodge.camera.viewportWidth/2 - this.getWidth()/2, 150);
        this.setBounds(ColorDodge.camera.viewportWidth/2 - .4f*this.getWidth()/2, 150, ballTex.getWidth()*.4f, ballTex.getHeight()*.4f);
        wallBot = new Vector2(10,getY());
    }

    public void contains(Obstacle o)
    {
        /*if(getX() > Walls.WIDTH + 1 && getX() < ColorDodge.camera.viewportWidth - Walls.WIDTH - this.getWidth() - 1)
        {
            //Middle bottom
            points[0] = PlayScreen.view.project(new Vector2((int)getX() + (int)getWidth()/2,(int)getY()-4));
            //Middle top
            points[1] = PlayScreen.view.project(new Vector2((int)getX() + (int)getWidth()/2,(int)getY()+getHeight()));

            pixel = ScreenUtils.getFrameBufferPixmap(0,0, ColorDodge.totalWidth, ColorDodge.totalHeight).getPixel((int)points[0].x, (int)points[0].y);

            if(pixel!=255)
            {
                System.out.println("Hit");
            }


            System.out.println(pixel);
        }*/






        for(Vector2 pts: o.hitPoints)
        {
            //r.begin();
            //r.setProjectionMatrix(ColorDodge.camera.combined);
            //r.rect(pts.x,pts.y,4,4);
            //r.setColor(Color.CORAL);
            //r.rect(getX()+getWidth()/2,getY()+getHeight()/2,2,2);
           // r.end();

            double apart = Math.pow((getX()+getWidth()- pts.x),2) + Math.pow((getY()+getHeight()- pts.y),2) ;




            if(gameStart && apart<600 && Math.abs(Color.LIGHT_GRAY.r - this.color.r) > .02 && Math.abs(Color.LIGHT_GRAY.r - o.color.r) > .02) {
                startHeight = (int)PlayScreen.view.getCamera().position.y;
                if (Math.abs(this.color.r - o.color.r) > .01 || Math.abs(this.color.b - o.color.b) > .01 || Math.abs(this.color.g - o.color.g) > .01) {
                    hit = true;
                    System.out.println("HIT");
                    System.out.println(this.color.r + " " + this.color.g + " " + this.color.b + " " + this.color.a);
                    System.out.println(o.color.r + " " + o.color.g + " " + o.color.b + " " + o.color.a);
                } else if (Math.abs(this.color.r - o.color.r) < .01 && Math.abs(this.color.b - o.color.b) < .01 && Math.abs(this.color.g - o.color.g) < .01) {
                    //FOR A SAME HIT
                    //hit = true;
                    //System.out.println(this.color.r + " " + o.color.r);
                    //System.out.println("SAME");
                }
            }



        }
        //System.out.println("Circle x" + this.getX());
       // System.out.println("Circle y" + this.getY());
        //System.out.println("Obs x" + o.hitPoints[0].x);
        //System.out.println("Obs y" + o.hitPoints[0].y);


    }



    public void update(float delta)
    {
        wallBot.set(10,getY());
        this.translate(ballVelocity.x*delta, ballVelocity.y*delta);



        if(this.getY() + 4 < (ColorDodge.camera.position.y - ColorDodge.camera.viewportHeight/2))
        {
            hit = true;
        }

        if(gameStart)
            ballVelocity.y -= 700f*delta; //Gravity

        if(getX() > ColorDodge.camera.viewportWidth - Walls.WIDTH - this.getWidth())
        {
            setX(ColorDodge.camera.viewportWidth - Walls.WIDTH - this.getWidth() - 2);
            ballVelocity.x = -200f;


            proj = PlayScreen.view.project(wallBot);

            switch((int)(this.getY()/160%5))
            {
                case 0: color.set(Color.RED);break;
                case 1: color.set(Color.YELLOW);break;
                case 2: color.set(Color.LIGHT_GRAY);break;
                case 3: color.set(Color.GREEN);break;
                case 4: color.set(Color.BLUE);break;
            }
           // pixel = ScreenUtils.getFrameBufferPixmap(0,0, ColorDodge.totalWidth, ColorDodge.totalHeight).getPixel((int)proj.x, (int)proj.y);



            // color.set(pixel);

            this.setColor(color);

        }


        if(getX() < Walls.WIDTH)
        {
            setX(Walls.WIDTH + 2);
            ballVelocity.x = 200f;

            //proj = PlayScreen.view.project(wallBot);

            //pixel = ScreenUtils.getFrameBufferPixmap(0,0, ColorDodge.totalWidth, ColorDodge.totalHeight).getPixel((int)proj.x,(int)proj.y);


            switch((int)(this.getY()/160%5))
            {
                case 0: color.set(Color.RED);break;
                case 1: color.set(Color.YELLOW);break;
                case 2: color.set(Color.LIGHT_GRAY);break;
                case 3: color.set(Color.GREEN);break;
                case 4: color.set(Color.BLUE);break;
            }

          // color.set(pixel);




            this.setColor(color.r,color.g,color.b,color.a);


        }

        if(Gdx.input.justTouched())
        {
            ColorDodge.jump.play(1.0f);
            gameStart = true;

            if(ballVelocity.y < 0)
            {
                ballVelocity.y = 0f;
            }

            ballVelocity.y += 300;

            if(ballVelocity.y > 1200)
            {
                ballVelocity.y = 1200;
            }
        }


    }

    public void dispose()
    {
        ballTex.dispose();
    }

}
