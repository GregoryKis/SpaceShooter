package com.gk.game.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sun.security.provider.SHA;

class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;
    //    private Texture background;
    private TextureAtlas textureAtlas;
    private TextureRegion background;
    private SpriteBatch batch;

    public static final float WIDTH = 1000;
    public static final float HEIGHT = 1000;

    private static boolean UP = true;
    private static boolean RIGHT = true;

    private static float X = 0;
    private static float Y = 0;

    private Ship playerShip;

    private LinkedList<Ship> enemyShips;
    private float enemyShipCreationInterval;

    private LinkedList<Laser> laserList;

    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);

        textureAtlas = new TextureAtlas("texture2.atlas");

        background = textureAtlas.findRegion("purple");

        batch = new SpriteBatch();

        playerShip = new Ship(textureAtlas.findRegion("playerShip1_blue"));

        enemyShips = new LinkedList<>();
        enemyShipCreationInterval = 100;

        laserList = new LinkedList<>();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        drawBackground();

        updateShipMovement(playerShip);
        updateShipFire(playerShip);
        playerShip.draw(batch);

        updateEnemyShips();

        batch.end();
    }

    private void drawBackground() {
        if (Y + HEIGHT <= 0) {
            Y = 0;
        } else {
            Y -= background.getRegionHeight() / 10;
        }

        batch.draw(background, X, Y, WIDTH, HEIGHT);
        batch.draw(background, X, Y + HEIGHT, WIDTH, HEIGHT);
    }

    private void updateEnemyShips() {
        --enemyShipCreationInterval;

        if (enemyShipCreationInterval <= 0) {
            enemyShipCreationInterval = 100;
            Ship enemy = new Ship(textureAtlas.findRegion("enemyRed3"), WIDTH / 2, HEIGHT);//TODO factory
            enemyShips.addFirst(enemy);
        }

        Iterator<Ship> shipIterator = enemyShips.iterator();
        while (shipIterator.hasNext()){
            Ship ship = shipIterator.next();
            ship.checkHit(laserList);
            if (ship.isDead()) {
                shipIterator.remove();
                continue;
            }

            ship.setShipY(ship.getShipY() - 5);//TODO change
            ship.draw(batch);
        }

    }

    private void updateShipFire(Ship ship) {
//        if (ship.isCanFire() && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        if (ship.isCanFire()) {
            Laser laser = new Laser(textureAtlas.findRegion("laserBlue01"), ship.getShipX() + ship.getWidth() / 2, ship.getShipY() + ship.getHeight());
            laserList.addFirst(laser);
        }

        Iterator<Laser> laserIterator = laserList.iterator();
        while (laserIterator.hasNext()){
            Laser next = laserIterator.next();
            if (next.isDestroyed() || next.isOutOfScreen()) {
                laserIterator.remove();
                continue;
            }
            next.draw(batch);
            next.move(5);
        }
    }

    private void updateShipMovement(Ship ship) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ship.moveUp(5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ship.moveUp(-5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ship.moveRight(5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ship.moveRight(-5);
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
