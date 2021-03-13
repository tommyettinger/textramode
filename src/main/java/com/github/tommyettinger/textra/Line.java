package com.github.tommyettinger.textra;

import com.badlogic.gdx.utils.LongArray;
import com.badlogic.gdx.utils.Pool;

public class Line implements Pool.Poolable {
    public final LongArray glyphs;
    public float width, height;

    public Line() {
        glyphs = new LongArray(16);
    }

    public Line(int capacity) {
        glyphs = new LongArray(capacity);
    }

    public Line size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Resets the object for reuse. This clears {@link #glyphs}, rather than nulling it. The sizes are set to 0.
     */
    @Override
    public void reset() {
        glyphs.clear();
        width = 0;
        height = 0;
    }
}
