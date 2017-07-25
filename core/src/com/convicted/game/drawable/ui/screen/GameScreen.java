package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.ConvictedGame;
import com.convicted.game.controller.MonsterController;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.entity.character.Monster;
import com.convicted.game.drawable.environment.Environment;
import com.convicted.game.drawable.ui.widget.Joystick;

public class GameScreen extends ConvictedScreen
{
    private Sprite sprite;
    private Monster monster;
    private Joystick joystick;

    private Environment environment;

    public GameScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        this.environment = new Environment(this.game);
        //this.environment.setScale(6f);

        this.sprite = new Sprite(this.game.getAssetManager().<Texture>get(Asset.ROGUE));
        //this.sprite.setScale(1.5f);

        this.joystick = new Joystick(
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_MOVE_JOYSTICK_ALIGN_Y));

        this.monster = new Monster(this.game.getAssetManager().<Texture>get(Asset.GRUB));
        this.monster.setController(new MonsterController(this.monster));
        this.monster.setPosition(500, 200);
        this.monster.update(0);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.joystick.getProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void update(float delta)
    {
        this.environment.update(delta);
        this.monster.update(delta);
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.environment);
        batch.draw(this.monster);
        batch.draw(this.joystick);
    }

    @Override
    public void dispose()
    {
        this.game.getAssetManager().dispose();
        this.joystick.dispose();
        super.dispose();
    }
}
