package com.github.tommyettinger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.LongArray;
import com.github.tommyettinger.textra.TextraFont;

public class TextraFontTest extends ApplicationAdapter {

    TextraFont font;
    SpriteBatch batch;
    LongArray[] glyphs = new LongArray[6];

    public static void main(String[] args){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("TextraFont test");
        config.setWindowedMode(800, 640);
        config.disableAudio(true);
        config.useVsync(true);
        new Lwjgl3Application(new TextraFontTest(), config);
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        font = new TextraFont("Gentium.fnt", false, -1f, 0f, -4.5f, 0f).scale(0.5f, 0.5f);
        for(TextureRegion parent : font.parents){
            parent.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
//        font = new TextraFont("Cozette.fnt", "Cozette.png", false, 1, 1, -1, -1);//.scale(2f, 2f);
        font = new TextraFont("AStarry.fnt", false, 1, 1, -1, -1);//.scale(2f, 2f);
//        font = new TextraFont("Iosevka-Slab-msdf.fnt", "Iosevka-Slab-msdf.png", true, 3f, 6, -4f, -7).scale(0.75f, 0.75f);
//        font = new TextraFont("Inconsolata-LGC-Custom-msdf.fnt", "Inconsolata-LGC-Custom-msdf.png", true, 5f, 1f, -10f, -8f).scaleTo(16f, 36f);
//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new TextraFont("dawnlike/PlainAndSimplePlus.fnt", atlas.findRegion("PlainAndSimplePlus"), false, 0, 0, 2, 2);

        int line = 0;
        font.markup("[#22BB22FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!", glyphs[line++] = new LongArray());
        font.markup("The [RED]MAW[] of the [/][CYAN]wendigo[/] (wendigo)[] [*]appears[*]!", glyphs[line++] = new LongArray());
        font.markup("The [_][GRAY]BLADE[] of [*][/][YELLOW]KINGS[] strikes!", glyphs[line++] = new LongArray());
        font.markup("[_][;]Each cap, [,]All lower, [!]Caps lock[], [?]Unknown[]?", glyphs[line++] = new LongArray());
        font.markup("[GOLD]phi[] = (1 + 5[^]0.5[^]) * 0.5", glyphs[line++] = new LongArray());
        font.markup("[ORANGE][*]Mister Bond[*]! This is my right-hand man, Nosejob.[]", glyphs[line] = new LongArray());
//        font.markup("[GOLD]φ[] = (1 + 5[^]0.5[^]) * 0.5", glyphs[line++] = new LongArray());
//        font.markup("[ORANGE]¿Qué? ¡Arribate, mijo![]", glyphs[line] = new LongArray());
//        font.markup("Music, or muzak? [.]♭[=]♭[^]♭[=]♭[.]♭[]", glyphs[5] = new LongArray());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.25f, 0.4f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = 0, y = font.cellHeight * glyphs.length;
        batch.begin();
        font.enableShader(batch);

        for (int i = 0; i < glyphs.length; i++) {
            font.drawGlyphs(batch, glyphs[i], x, y -= font.cellHeight);
        }

        batch.end();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }
}
