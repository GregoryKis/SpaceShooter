package com.gk.game.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;
    //    private Texture background;
    private TextureAtlas textureAtlas;
    private TextureRegion background, playerShipTR, playerShildTR, playerLazerTR, enemyShipTR, enemyShildTR, enemyLazerTR;
    private SpriteBatch batch;

    public static final float WIDTH = 1000;
    public static final float HEIGHT = 1000;

    private static boolean UP = true;
    private static boolean RIGHT = true;

    private static float X = 0;
    private static float Y = 0;

    private Ship ship;

    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);

        textureAtlas = new TextureAtlas("texture2.atlas");

        background = textureAtlas.findRegion("purple");

        batch = new SpriteBatch();

        ship = new Ship(textureAtlas.findRegion("playerShip1_blue"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        if (Y + HEIGHT <= 0) {
            Y = 0;
        } else {
            Y -= background.getRegionHeight() / 10;
        }

        batch.draw(background, X, Y, WIDTH, HEIGHT);
        batch.draw(background, X, Y + HEIGHT, WIDTH, HEIGHT);

        updateShipMovement(ship);
        ship.draw(batch);

        batch.end();
    }

    private void updateShipMovement(Ship ship) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ship.moveY(ship.getShipY() + 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ship.moveY(ship.getShipY() - 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ship.moveX(ship.getShipX() + 5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ship.moveX(ship.getShipX() - 5);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
