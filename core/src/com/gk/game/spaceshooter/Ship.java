package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.gk.game.spaceshooter.GameScreen.HEIGHT;
import static com.gk.game.spaceshooter.GameScreen.WIDTH;

public class Ship {

    private TextureRegion textureRegion;

    private float shipX;
    private float shipY;
    private float height;
    private float width;


    public Ship(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        height = 100;
        width = 100;
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, shipX, shipY, width, height);
    }

    public void updateLocation(float x, float y) {
        shipX = x;
        shipY = y;
    }

    public void moveY(float y) {
        if (shipY + y >= HEIGHT)//TOP
            this.setShipY(HEIGHT - height);
        else if(shipY + y <= 0)//BOTTOM
            this.setShipX(0);
        else
            this.setShipY(shipY + y);
    }

    public void moveX(float x) {
        if (shipX + x >= WIDTH)//RIGHT
            this.setShipX(WIDTH - width);
        else if (shipX + x <= 0)//LEFT
            this.setShipX(0);
        else
            this.setShipX(shipX + x);
    }


    public float getShipX() {
        return shipX;
    }

    public float getShipY() {
        return shipY;
    }

    public void setShipX(float shipX) {
        this.shipX = shipX;
    }

    public void setShipY(float shipY) {
        this.shipY = shipY;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
