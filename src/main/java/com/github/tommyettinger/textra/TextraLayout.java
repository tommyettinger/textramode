package com.github.tommyettinger.textra;

import com.badlogic.gdx.utils.LongArray;
import com.badlogic.gdx.utils.Pool;

public class TextraLayout implements Pool.Poolable {
    public TextraFont font;
    public final LongArray glyphs;
    public float width, height;

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

    public TextraLayout size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Resets the object for reuse. {@link #font} is nulled, while {@link #glyphs} is cleared. The size is set to 0.
     */
    @Override
    public void reset() {
        font = null;
        glyphs.clear();
        width = 0;
        height = 0;
    }
}
