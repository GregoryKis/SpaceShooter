package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship {

    TextureRegion textureRegion;

    public Ship(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void draw(Batch batch){
        batch.draw(textureRegion,0,0,100,100);
    }
}
