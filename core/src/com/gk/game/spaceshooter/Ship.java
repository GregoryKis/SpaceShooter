package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

import static com.gk.game.spaceshooter.GameScreen.HEIGHT;
import static com.gk.game.spaceshooter.GameScreen.WIDTH;

public class Ship {

    private TextureRegion textureRegion;

    private float fireRate = 20;
    private float sinceLastFire = 0;

    private Rectangle shape;

    private boolean isHit;

    public Ship(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;

        float width = 100;
        float height = 100;

        float x = WIDTH / 2 - width / 2;
        float y = 0;
        shape = new Rectangle(x, y, width, height);
    }

    public Ship(TextureRegion textureRegion, float x, float y) {
        this.textureRegion = textureRegion;

        float width = 100;
        float height = 100;

        shape = new Rectangle(x, y, width, height);
    }

    public Ship(TextureRegion textureRegion, float x, float y, float width, float height) {
        this.textureRegion = textureRegion;
        shape = new Rectangle(x, y, width, height);
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

    public boolean isOutOfScreen() {
        if (shape.getY() < -shape.getHeight())
            return true;
        return false;
    }

    public void checkHit(List<Laser> lasers) {
        for (Laser laser : lasers) {
            if (shape.overlaps(laser.getShape()) && !laser.isDestroyed()) {
                isHit = true;
                laser.hit();
            }
        }
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

    public Rectangle getShape() {
        return shape;
    }

    public boolean isDead() {
        return isHit;
    }
}
