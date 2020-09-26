package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Laser {

    private TextureRegion textureRegion;

    private Rectangle shape;

    public Laser(TextureRegion textureRegion, float laserX, float laserY) {
        this.textureRegion = textureRegion;
        float height = 20;
        float width = 5;

        shape = new Rectangle(laserX, laserY, width, height);
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    public void move() {
        shape.setY(shape.getY() + 7);
    }

    public boolean isOutOfScreen() {
        if (shape.getY() > GameScreen.HEIGHT)
            return true;
        return false;
    }
}
