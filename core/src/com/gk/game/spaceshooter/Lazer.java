package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Lazer {

    private TextureRegion textureRegion;

    private float lazerX;
    private float lazerY;
    private float height;
    private float width;

    public Lazer(TextureRegion textureRegion, float lazerX, float lazerY) {
        this.textureRegion = textureRegion;
        this.lazerX = lazerX;
        this.lazerY = lazerY;
        this.height = 20;
        this.width = 5;
    }
}
