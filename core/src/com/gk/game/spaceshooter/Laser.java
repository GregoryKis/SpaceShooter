package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Laser {

    private TextureRegion textureRegion;

    private float height;
    private float width;
    private float laserX;
    private float laserY;

    public Laser(TextureRegion textureRegion, float laserX, float laserY) {
        this.textureRegion = textureRegion;
        this.height = 20;
        this.width = 5;
        this.laserX = laserX;
        this.laserY = laserY;
    }

    public void draw(Batch batch){
        batch.draw(textureRegion, laserX, laserY, width, height);
    }

    public void move(){
        laserY += 7;
    }

    public boolean isOutOfScreen(){
        if (laserY > GameScreen.HEIGHT)
            return true;
        return false;
    }
}
