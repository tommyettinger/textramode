package com.github.tommyettinger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.Layout;

public class TextraFontTest extends ApplicationAdapter {

    Font font;
    SpriteBatch batch;
    Layout layout = new Layout().setTargetWidth(550);

    public static void main(String[] args){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("textramode Font test");
        config.setWindowedMode(800, 640);
        config.disableAudio(true);
        ShaderProgram.prependVertexCode = "#version 150\n";
        ShaderProgram.prependFragmentCode = "#version 150\n";
        config.enableGLDebugOutput(true, System.out);
        config.useVsync(true);
        new Lwjgl3Application(new TextraFontTest(), config);
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
//        font = new Font(new BitmapFont(Gdx.files.internal("Gentium.fnt")), Font.DistanceFieldType.STANDARD, -1f, 0f, -4.5f, 0f).scale(0.5f, 0.5f);
////        font = new Font("Gentium.fnt", Font.DistanceFieldType.STANDARD, -1f, 0f, -4.5f, 0f).scale(0.5f, 0.5f);
//        for(TextureRegion parent : font.parents){
//            parent.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//        }
//        font = new Font("LibertinusSerif.fnt",
//                new TextureRegion(new Texture(Gdx.files.internal("LibertinusSerif.png"), true)), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0)
//        .scale(0.25f, 0.25f);
//        for(TextureRegion parent : font.parents){
//            parent.getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.MipMapLinearNearest);
//        }
//        font = new Font("Cozette.fnt", "Cozette.png", Font.DistanceFieldType.STANDARD, 2, 2, 0, 0).scale(2f, 2f);
//        font = new Font("AStarry.fnt", Font.DistanceFieldType.STANDARD, 1, 1, -1, -1);//.scale(2f, 2f);
//        font = new Font("Iosevka-Slab-msdf.fnt", "Iosevka-Slab-msdf.png", Font.DistanceFieldType.MSDF, 3f, 6f, -4f, -7).scale(0.75f, 0.75f);
//        font = new Font("LibertinusSerif-Regular-msdf.fnt", "LibertinusSerif-Regular-msdf.png", Font.DistanceFieldType.MSDF, 6f, 1f, -1f, -1f).scale(1.5f, 1.5f);
//        font = new Font("Inconsolata-LGC-Custom-msdf.fnt", "Inconsolata-LGC-Custom-msdf.png", Font.DistanceFieldType.MSDF, 5f, 1f, -10f, -8f).scaleTo(16f, 28f);
        font = new Font("Iosevka-distance.fnt", "Iosevka-distance.png", Font.DistanceFieldType.SDF, 0, 0, 0, 0).scale(0.75f, 0.75f);

//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new Font("dawnlike/PlainAndSimplePlus.fnt", atlas.findRegion("PlainAndSimplePlus"), Font.DistanceFieldType.STANDARD, 0, 0, 2, 2);

//        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dawnlike/Dawnlike.atlas"), Gdx.files.internal("dawnlike"));
//        font = new Font(new BitmapFont(Gdx.files.internal("dawnlike/PlainAndSimplePlus.fnt"), atlas.findRegion("PlainAndSimplePlus")), Font.DistanceFieldType.STANDARD, 0, 0, 2, 2);

        layout.setBaseColor(Color.SLATE);
        layout.setMaxLines(7);
        layout.setEllipsis("...");
        font.markup("I wanna thank you all for coming here tonight..."
                + "\n[#22BB22FF]Hello, [~]World[~]Universe[.]$[=]$[^]$[^]!"
                + "\nThe [RED]MAW[] of the [/][CYAN]wendigo[/] (wendigo)[] [*]appears[*]!"
                + "\nThe [_][GRAY]BLADE[] of [*][/][YELLOW]DYNAST-KINGS[] strikes!"
                + "\n[_][;]Each cap, [,]All lower, [!]Caps lock[], [?]Unknown[]?"
                + "\n[GOLD]phi[] = (1 + 5[^]0.5[^]) * 0.5"
                + "\n[ORANGE][*]Mister Bond[*]! This is my right-hand man, Nosejob."
                + "\nPchnąć[] w tę łódź [TAN]jeża[] lub ośm skrzyń [PURPLE]fig[]."
                , layout);
//        layout.clear();
//        font.markup("Good day to you all, sirs and madams!"
//                + "\n[*]Водяно́й[] — в славянской мифологии дух, обитающий в воде, хозяин вод[^][BLUE][[2][]."
//                + "\nВоплощение стихии воды как отрицательного и опасного начала[^][BLUE][[3][].", layout);
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
        Gdx.gl.glClearColor(0.24f, 0.33f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = 800, y = font.cellHeight * (layout.lines() - 1);
        batch.begin();
        font.enableShader(batch);

        font.drawGlyphs(batch, layout, x, y, Align.right);
//        batch.draw(font.parents.first(), 0, 0);
        batch.end();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }
}
