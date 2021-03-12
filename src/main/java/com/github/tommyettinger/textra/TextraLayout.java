package com.github.tommyettinger.textra;

import com.badlogic.gdx.utils.LongArray;
import com.badlogic.gdx.utils.Pool;

public class TextraLayout implements Pool.Poolable {
    public TextraFont font;
    public final LongArray glyphs;
    public float x, y, width, height;

    public TextraLayout(){
        glyphs = new LongArray(16);
    }

    public TextraLayout(TextraFont font) {
        this.font = font;
        glyphs = new LongArray(16);
    }

    public TextraLayout(TextraFont font, int capacity) {
        this.font = font;
        glyphs = new LongArray(capacity);
    }

    public TextraLayout font(TextraFont font) {
        this.font = font;
        return this;
    }

    public TextraLayout position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public TextraLayout size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextraLayout placement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        font = null;
        glyphs.clear();
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }
}
