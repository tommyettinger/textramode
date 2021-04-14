package com.github.tommyettinger.textra;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pools;

public class TextraLabel extends Widget {
    public Layout layout;
    public Font font;
    public int align = Align.center;
    public TextraLabel(){
        layout = Pools.obtain(Layout.class);
        font = new Font(new BitmapFont(), false, 0, 0, 0, 0);
    }
    public TextraLabel(String text, Label.LabelStyle style) {
        font = new Font(style.font, false, 0, 0, 0, 0);
        layout = Pools.obtain(Layout.class);
        layout.setBaseColor(style.fontColor);
        font.markup(text, layout);
    }
    public TextraLabel(String text, Font font) {
        this.font = font;
        layout = Pools.obtain(Layout.class);
        font.markup(text, layout);
    }
    public TextraLabel(String text, Font font, Color color) {
        this.font = font;
        layout = Pools.obtain(Layout.class);
        layout.setBaseColor(color);
        font.markup(text, layout);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1f, 1f, 1f, parentAlpha);
        font.drawGlyphs(batch, layout, getX(align), getY(align), align);
    }
}
