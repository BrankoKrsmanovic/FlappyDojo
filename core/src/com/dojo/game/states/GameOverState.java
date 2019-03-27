package com.dojo.game.states;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dojo.game.FlappyDojo;

public class GameOverState extends State {
    private Texture background;
    private Texture gameOver;
    private String yourScore;
    private BitmapFont displayScore;
    private BitmapFont displayHighScore;
    private int score;
    private int highScore;

    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        cam.setToOrtho(false, FlappyDojo.WIDTH / 2, FlappyDojo.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        this.score = score;
        calculateHighScore();
        yourScore = "YOUR SCORE: " + score;
        displayScore = new BitmapFont();
        displayHighScore = new BitmapFont();
    }

    public void calculateHighScore() {

        try {
            if (!Gdx.files.local("scores.txt").exists())
                Gdx.files.local("scores.txt").file().createNewFile();

            FileHandle handle = Gdx.files.local("scores.txt");
            if (handle.readString() == "")
                highScore = 0;
            else
                highScore = Integer.parseInt(handle.readString());

            highScore = Math.max(highScore, score);
            handle.writeString(highScore + "", false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameOver, cam.position.x - gameOver.getWidth() / 2, cam.position.y);
        displayScore.setColor(1, 1, 1, 1.0f);
        displayScore.draw(sb, yourScore, 63, 100);
        displayHighScore.setColor(1, 1, 1, 1.f);
        displayHighScore.draw(sb, ("HIGH SCORE: " + highScore), 66, 130);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
    }
}
