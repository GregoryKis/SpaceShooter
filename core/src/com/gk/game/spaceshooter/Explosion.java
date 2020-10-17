package com.gk.game.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {

    private Animation<TextureRegion> explosionAnimation;

    private final Rectangle shape;

    private float timer;

    private static final float ANIMATION_TIME = 2.0f;
    private static final int ANIMATION_FRAMES = 16;

    public Explosion(Texture texture, float x, float y, float width, float height) {

        this.timer = 0;

        TextureRegion[][] texture2D = TextureRegion.split(texture, 64, 64);
        TextureRegion[] texture1D = new TextureRegion[ANIMATION_FRAMES];

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                texture1D[index] = texture2D[i][j];
                ++index;
            }
        }

        this.explosionAnimation = new Animation<TextureRegion>(ANIMATION_TIME / ANIMATION_FRAMES, texture1D);


        this.shape = new Rectangle(x, y, width, height);
    }

    public void update(float deltaTime) {
        timer += deltaTime;
    }

    public void draw(SpriteBatch batch){
        batch.draw(explosionAnimation.getKeyFrame(timer),shape.getX(),shape.getY(),shape.getWidth(),shape.getHeight());
    }

    public boolean isFinished(){
        return explosionAnimation.isAnimationFinished(timer);
    }

}
