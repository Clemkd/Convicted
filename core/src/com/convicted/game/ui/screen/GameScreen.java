package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.convicted.game.ConvictedGame;
import com.convicted.game.action.PlayerController;
import com.convicted.game.entity.Entity;
import com.convicted.game.entity.Player;
import com.convicted.game.entity.monsters.Grub;
import com.convicted.game.entity.projectiles.Projectile;
import com.convicted.game.ui.widget.SampleJoystick;

import java.util.Iterator;

public class GameScreen extends AbstractScreen
{
    private GameContext context;
    private SampleJoystick movementJoystick;
    private SampleJoystick fireJoystick;

    public GameScreen(ConvictedGame game)
    {
        super(game);


        this.context = new GameContext(this);

        this.movementJoystick = new SampleJoystick((int)this.getViewport().getWorldWidth() / 8, (int)this.getViewport().getScreenHeight() * 1 / 8);
        this.fireJoystick = new SampleJoystick((int)this.getViewport().getWorldWidth() * 7 / 8, (int)this.getViewport().getScreenHeight() * 1 / 8);
        this.movementJoystick.setScale(0.8f);
        this.fireJoystick.setScale(0.8f);

        Player player = new Player(new Texture(Gdx.files.internal("charset.png")), this.context);
        PlayerController controller = new PlayerController(player, movementJoystick, fireJoystick);
        player.setController(controller);
        this.context.player = player;

        this.context.entities.add(player);
        this.context.entities.add(new Grub(this.context));
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

        this.addActor(this.movementJoystick);
        this.addActor(this.fireJoystick);

        for(Entity entity : this.context.entities)
        {
            this.addActor(entity);
        }
    }


    @Override
    public void update(float delta)
    {
        //this.player.act(delta);

        Iterator<Projectile> iterator = this.context.projectiles.iterator();
        while(iterator.hasNext())
        {
            Projectile projectile = iterator.next();
            projectile.act(delta);

            // Mort du projectile par sortie d'écran, fin de déplacement ou par hit
            if(projectile.isDead())
            {
                // TODO: Animation de fin de vie du projectile ?
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(Batch batch)
    {
        batch.begin();

        Iterator<Projectile> iterator = this.context.projectiles.iterator();
        while(iterator.hasNext())
        {
            Projectile projectile = iterator.next();
            projectile.draw(batch, 1f);
        }
        batch.end();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {
        this.getViewport().update(width, height, true);
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
