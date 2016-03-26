package com.mygdx.game.Sprites;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorDodge;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by musial321 on 3/23/2016.
 */
public class ObstacleSpawner {

    Array<Obstacle> set1 = new Array<Obstacle>(15);
    Array<Obstacle> set2 = new Array<Obstacle>(15);
    int height1 = 0;
    int height2 = (int)Walls.HEIGHT;

    public Array<Obstacle> getSet2() {
        return set2;
    }

    public Array<Obstacle> getSet1() {
        return set1;
    }

    public ObstacleSpawner()
    {
        setObstacles(set1, height1);
        setObstacles(set2, height2);
    }

    public void setObstacles(Array<Obstacle> set, int height)
    {
        Color[] colors = {Color.RED,Color.YELLOW,Color.LIGHT_GRAY,Color.GREEN,Color.BLUE};

        spawnSpikes(set, height);

        switch(MathUtils.random(2)) {
            case 0:

                    set.add(new BarTop(new Vector2(ColorDodge.camera.viewportWidth/2-BarTop.WIDTH/2, height + BarTop.HEIGHT), colors[MathUtils.random(4)]));
                    set.add(new BarBot(new Vector2(ColorDodge.camera.viewportWidth/2-BarTop.WIDTH/2, height), colors[MathUtils.random(4)]));
                    //set.add(new WallObstacle(new Vector2(),colors[rand+1]));


                break;
            case 1:
                    int offset = MathUtils.random(600);
                    set.add(new BarLeft(new Vector2(ColorDodge.camera.viewportWidth/2-BarLeft.WIDTH, height + offset), colors[MathUtils.random(4)]));
                    set.add(new BarRight(new Vector2(ColorDodge.camera.viewportWidth/2, height + offset), colors[MathUtils.random(4)]));
                break;

            case 2:

        }



    }

    public void spawnSpikes(Array<Obstacle> set, int height)
    {
        Color[] colors = {Color.RED,Color.YELLOW,Color.LIGHT_GRAY,Color.GREEN,Color.BLUE};

        for(int i = 0; i < 5; i++) {


            switch (MathUtils.random(2)) {
                case 0:
                    set.add(new SpikeLeft(new Vector2(Walls.WIDTH, height), colors[i]));
                    break;
                case 1:


            }

            switch (MathUtils.random(2)) {
                case 0:
                    set.add(new SpikeRight(new Vector2(ColorDodge.camera.viewportWidth-Walls.WIDTH-35, height), colors[i]));
                    break;
                case 1:


            }





            height += Walls.HEIGHT/5;


        }
    }

    public void update()
    {
        if(ColorDodge.camera.position.y - ColorDodge.camera.viewportHeight/2 > height1 + Walls.HEIGHT)
        {
            set1.clear();
            height1 += Walls.HEIGHT*2;
            setObstacles(set1, height1);
        }
        if(ColorDodge.camera.position.y - ColorDodge.camera.viewportHeight/2 > height2 + Walls.HEIGHT)
        {
            set2.clear();
            height2 += Walls.HEIGHT*2;
            setObstacles(set2, height2);
        }
    }


    public void render()
    {
        for(Obstacle o: set1)
        {
            o.render();
        }
        for(Obstacle o: set2)
        {
            o.render();
        }
    }

    public void dispose()
    {

    }

}
