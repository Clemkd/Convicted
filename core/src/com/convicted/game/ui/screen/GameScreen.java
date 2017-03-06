package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.convicted.game.ConvictedGame;
import com.convicted.game.ui.widget.SampleJoystick;

public class GameScreen extends AbstractScreen
{
    private SampleJoystick movementJoystick;
    private SampleJoystick fireJoystick;

    public GameScreen(ConvictedGame game)
    {
        super(game);
        this.movementJoystick = new SampleJoystick(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 2 / 7);
        this.fireJoystick = new SampleJoystick(Gdx.graphics.getWidth() * 4 / 5, Gdx.graphics.getHeight() * 2 / 7);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.movementJoystick.getProcessor());
        inputMultiplexer.addProcessor(this.fireJoystick.getProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        super.render(delta);
    }

    @Override
    public void draw()
    {
        super.draw();
        this.getBatch().begin();
        // TODO : Draw here
        this.movementJoystick.draw(this.getBatch());
        this.fireJoystick.draw(this.getBatch());
        this.getBatch().end();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause()
    {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume()
    {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide()
    {

    }
}
