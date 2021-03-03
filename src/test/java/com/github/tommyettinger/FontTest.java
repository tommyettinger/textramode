package com.github.tommyettinger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.LongArray;
import com.github.tommyettinger.textra.Font;

public class FontTest extends ApplicationAdapter {

    Font font;
    SpriteBatch batch;
    RandomXS128 random;
    int[][] backgrounds;
    char[][] lines;
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
        random = new RandomXS128(1L);
        batch = new SpriteBatch();
//        font = KnownFonts.getInconsolataLGC().scaleTo(16, 32);
//        font = KnownFonts.getCascadiaMono().scale(0.5f, 0.5f);
//        font = KnownFonts.getIosevka().scale(0.75f, 0.75f);
//        font = KnownFonts.getIosevkaSlab().scale(0.75f, 0.75f);
//        font = KnownFonts.getDejaVuSansMono().scale(0.75f, 0.75f);
//        font = KnownFonts.getCozette();
//        font = KnownFonts.getOpenSans().scale(0.75f, 0.75f);
//        font = KnownFonts.getAStarry();

        font = new Font("Cozette.fnt", "Cozette.png", false).scale(2f, 2f);

//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new Font("dawnlike/PlainAndSimplePlus.fnt", atlas.findRegion("PlainAndSimplePlus"), false, 0, 0, 2, 2);

        font.markup("[#00DD00FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!", glyphs[0] = new LongArray());
//        font.markup("[#"+ DigitTools.hex(color) +"]Hello, [~]World[~]Universe[.]♪[=]♪[^]♪[^]!", glyphs[0] = new LongList());
        font.markup("The [RED]MAW[] of the [/][CYAN]wendigo[] [*]appears[*]!", glyphs[1] = new LongArray());
        font.markup("The [_][PURPLE]BLADE[] of [*][/][YELLOW]KINGS[] strikes!", glyphs[2] = new LongArray());
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
        font.drawGlyphs(batch, glyphs[1], 0, font.cellHeight * 3);
        font.drawGlyphs(batch, glyphs[2], 0, font.cellHeight * 2);
        font.drawGlyphs(batch, glyphs[3], 0, font.cellHeight);

        batch.end();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }
}
