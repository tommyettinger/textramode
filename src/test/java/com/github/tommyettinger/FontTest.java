package com.github.tommyettinger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.LongArray;
import com.github.tommyettinger.textra.Font;

public class FontTest extends ApplicationAdapter {

    Font font;
    SpriteBatch batch;
    LongArray[] glyphs = new LongArray[4];

    public static void main(String[] args){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Font test");
        config.setWindowedMode(800, 640);
        config.disableAudio(true);
        config.useVsync(false);
        new Lwjgl3Application(new FontTest(), config);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        BitmapFont bm = new BitmapFont();
        bm.getData().
//        font = new Font("Cozette.fnt", "Cozette.png", false).scale(2f, 2f);
//        font = new Font("Iosevka-Slab-msdf.fnt", "Iosevka-Slab-msdf.png", true, 3f, 6, -4f, -7).scale(0.75f, 0.75f);
        font = new Font("Inconsolata-LGC-Custom-msdf.fnt", "Inconsolata-LGC-Custom-msdf.png", true, 5f, 1f, -10f, -8f).scaleTo(16f, 36f);

//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new Font("dawnlike/PlainAndSimplePlus.fnt", atlas.findRegion("PlainAndSimplePlus"), false, 0, 0, 2, 2);

        font.markup("[#22BB22FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!", glyphs[0] = new LongArray());
//        font.markup("[#"+ DigitTools.hex(color) +"]Hello, [~]World[~]Universe[.]♪[=]♪[^]♪[^]!", glyphs[0] = new LongList());
        font.markup("The [RED]MAW[] of the [/][CYAN]wendigo[] [*]appears[*]!", glyphs[1] = new LongArray());
        font.markup("The [_][GRAY]BLADE[] of [*][/][YELLOW]KINGS[] strikes!", glyphs[2] = new LongArray());
        font.markup("[;]Each cap[], [,]All lower[], [!]Caps lock[], [?]Unknown[]?", glyphs[3] = new LongArray());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.3f, 0.4f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = 0, y = font.cellHeight * 4;
        batch.begin();
        font.enableShader(batch);

        font.drawGlyphs(batch, glyphs[0], x, y);
        font.drawGlyphs(batch, glyphs[1], x, y -= font.cellHeight);
        font.drawGlyphs(batch, glyphs[2], x, y -= font.cellHeight);
        font.drawGlyphs(batch, glyphs[3], x, y -= font.cellHeight);

        batch.end();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }
}
