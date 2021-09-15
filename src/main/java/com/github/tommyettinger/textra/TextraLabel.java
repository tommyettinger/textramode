package com.github.tommyettinger.textra;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pools;

/**
 * A scene2d.ui Widget that displays text using a {@link Font} rather than a libGDX BitmapFont. This supports being
 * laid out in a Table just like the typical Label.
 * <br>
 * ...Maybe not quite ready for prime-time yet. This could use some more features for ease-of-use.
 */
public class TextraLabel extends Widget {
    public Layout layout;
    public Font font;
    public int align = Align.bottomLeft;
    public TextraLabel(){
        layout = Pools.obtain(Layout.class);
        font = new Font(new BitmapFont(), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0);
    }
    public TextraLabel(String text, Skin skin) {
        this(text, skin.get(Label.LabelStyle.class));
    }

    public TextraLabel(String text, Skin skin, String styleName) {
        this(text, skin.get(styleName, Label.LabelStyle.class));
    }


    public TextraLabel(String text, Label.LabelStyle style) {
        font = new Font(style.font, Font.DistanceFieldType.STANDARD, 0, 0, 0, 0);
        layout = Pools.obtain(Layout.class);
        layout.setBaseColor(style.fontColor);
        font.markup(text, layout);
        setSize(layout.getWidth(), layout.getHeight());
    }
    public TextraLabel(String text, Font font) {
        this.font = font;
        layout = Pools.obtain(Layout.class);
        font.markup(text, layout);
        setSize(layout.getWidth(), layout.getHeight());
    }
    public TextraLabel(String text, Font font, Color color) {
        this.font = font;
        layout = Pools.obtain(Layout.class);
        layout.setBaseColor(color);
        font.markup(text, layout);
        setSize(layout.getWidth(), layout.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1f, 1f, 1f, parentAlpha);
        font.drawGlyphs(batch, layout, getX(align), getY(align), align);
    }

    @Override
    public float getPrefWidth() {
        return layout.getWidth();
    }

    @Override
    public float getPrefHeight() {
        return layout.getHeight();
    }

    /**
     * Gets the alignment for the text in this TextraLabel.
     * This is a constant in {@link Align}.
     * @see Align
     * @return the alignment used by this TextraLabel, as a constant from {@link Align}
     */
    public int getAlignment() {
        return align;
    }

    /**
     * Sets the alignment for the text in this TextraLabel.
     * @see Align
     * @param alignment a constant from {@link Align}
     */
    public void setAlignment (int alignment) {
        align = alignment;
    }

    /**
     * Changes the text in this TextraLabel to the given String, parsing any markup in it.
     * @param markupText a String that can contain Font markup
     */
    public void setText(String markupText) {
        font.markup(markupText, layout.clear());
    }
}
