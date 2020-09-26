package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.gk.game.spaceshooter.GameScreen.HEIGHT;
import static com.gk.game.spaceshooter.GameScreen.WIDTH;

public class Ship {

    private TextureRegion textureRegion;

    private float fireRate;
    private float sinceLastFire;

    private Rectangle shape;

    public Ship(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;

        float width = 100;
        float height = 100;

        float x = WIDTH / 2 - width / 2;
        float y = 0;
        shape = new Rectangle(x, y, width, height);

        fireRate = 100;
        sinceLastFire = 0;
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    public void updateLocation(float x, float y) {
        shape.setPosition(x, y);
    }

    public void moveUp(float y) {
        if (shape.getY() + y >= HEIGHT)//TOP
            this.setShipY(HEIGHT - shape.getHeight());
        else if (shape.getY() + y <= 0)//BOTTOM
            shape.setY(0);
        else
            shape.setY(shape.getY() + y);
    }

    public void moveRight(float x) {
        if (shape.getX() + shape.getWidth() + x >= WIDTH)//RIGHT
            shape.setX(WIDTH - shape.getWidth());
        else if (shape.getX() + x <= 0)//LEFT
            shape.setX(0);
        else
            shape.setX(shape.getX() + x);
    }

    public boolean isCanFire() {
        if (sinceLastFire == 0) {
            sinceLastFire += fireRate;
            return true;
        }
        --sinceLastFire;
        return false;
    }


    public float getShipX() {
        return shape.getX();
    }

    public float getShipY() {
        return shape.getY();
    }

    public void setShipX(float shipX) {
        shape.setX(shipX);
    }

    public void setShipY(float shipY) {
        shape.setY(shipY);
    }

    public float getHeight() {
        return shape.getHeight();
    }

    public float getWidth() {
        return shape.getWidth();
    }
}
