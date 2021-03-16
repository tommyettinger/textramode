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
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.Layout;

public class TextraFontTest extends ApplicationAdapter {

    Font font;
    SpriteBatch batch;
    Layout layout = new Layout();

    public static void main(String[] args){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("textramode Font test");
        config.setWindowedMode(800, 640);
        config.disableAudio(true);
        config.useVsync(true);
        new Lwjgl3Application(new TextraFontTest(), config);
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        font = new Font("Gentium.fnt", false, -1f, 0f, -4.5f, 0f).scale(0.5f, 0.5f);
        for(TextureRegion parent : font.parents){
            parent.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        font = new Font("Cozette.fnt", "Cozette.png", false, 2, 2, 0, 0).scale(2f, 2f);
//        font = new Font("AStarry.fnt", false, 1, 1, -1, -1);//.scale(2f, 2f);
//        font = new Font("Iosevka-Slab-msdf.fnt", "Iosevka-Slab-msdf.png", true, 3f, 6, -4f, -7).scale(0.75f, 0.75f);
//        font = new Font("Inconsolata-LGC-Custom-msdf.fnt", "Inconsolata-LGC-Custom-msdf.png", true, 5f, 1f, -10f, -8f).scaleTo(16f, 36f);
//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new Font("dawnlike/PlainAndSimplePlus.fnt", atlas.findRegion("PlainAndSimplePlus"), false, 0, 0, 2, 2);

        int line = 0;
        font.markup("[#22BB22FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!"
                + "\nThe [RED]MAW[] of the [/][CYAN]wendigo[/] (wendigo)[] [*]appears[*]!"
                + "\nThe [_][GRAY]BLADE[] of [*][/][YELLOW]KINGS[] strikes!"
                + "\n[_][;]Each cap, [,]All lower, [!]Caps lock[], [?]Unknown[]?"
                + "\n[GOLD]phi[] = (1 + 5[^]0.5[^]) * 0.5"
                + "\n[ORANGE][*]Mister Bond[*]! This is my right-hand man, Nosejob."
                + "\nPchnąć[] w tę łódź [TAN]jeża[] lub ośm skrzyń [PURPLE]fig[]."
                , layout);
        System.out.println(layout);

//        font.markup("[#22BB22FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!", layouts[line++] = new TextraLayout());
//        font.markup("The [RED]MAW[] of the [/][CYAN]wendigo[/] (wendigo)[] [*]appears[*]!", layouts[line++] = new TextraLayout());
//        font.markup("The [_][GRAY]BLADE[] of [*][/][YELLOW]KINGS[] strikes!", layouts[line++] = new TextraLayout());
//        font.markup("[_][;]Each cap, [,]All lower, [!]Caps lock[], [?]Unknown[]?", layouts[line++] = new TextraLayout());
//        font.markup("[GOLD]phi[] = (1 + 5[^]0.5[^]) * 0.5", layouts[line++] = new TextraLayout());
//        font.markup("[ORANGE][*]Mister Bond[*]! This is my right-hand man, Nosejob.[]", layouts[line] = new TextraLayout());

//        font.markup("[GOLD]φ[] = (1 + 5[^]0.5[^]) * 0.5", glyphs[line++] = new LongArray());
//        font.markup("[ORANGE]¿Qué? ¡Arribate, mijo![]", glyphs[line] = new LongArray());
//        font.markup("Music, or muzak? [.]♭[=]♭[^]♭[=]♭[.]♭[]", glyphs[5] = new LongArray());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.25f, 0.4f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = 800, y = font.cellHeight * (layout.lines() - 1);
        batch.begin();
        font.enableShader(batch);

        font.drawGlyphs(batch, layout, x, y, Align.right);

        batch.end();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }
}
