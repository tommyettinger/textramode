package com.github.tommyettinger.textra;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Layout implements Pool.Poolable {
    protected Font font;
    protected final Array<Line> lines = new Array<>(true, 8, Line.class);

    public Layout() {
    }

    public Layout(Font font) {
        this.font = font;
        lines.add(Pools.obtain(Line.class));
    }

    public Layout font(Font font) {
        if(this.font == null || !this.font.equals(font))
        {
            this.font = font;
            Pools.freeAll(lines);
            lines.clear();
            lines.add(Pools.obtain(Line.class));
        }
        return this;
    }

    public Layout add(long glyph){
        if((glyph & 0xFFFFL) == 10L)
        {
            lines.add(Pools.obtain(Line.class));
        }
        else {
            lines.peek().glyphs.add(glyph);
        }
        return this;
    }

    public Layout clear() {
        Pools.freeAll(lines);
        lines.clear();
        lines.add(Pools.obtain(Line.class));
        return this;
    }

    public float getWidth() {
        float w = 0;
        for (int i = 0, n = lines.size; i < n; i++) {
            w = Math.max(w, lines.get(i).width);
        }
        return w;
    }

    public float getHeight() {
        float h = 0;
        for (int i = 0, n = lines.size; i < n; i++) {
            h = Math.max(h, lines.get(i).height);
        }
        return h;
    }

    public int lines() {
        return lines.size;
    }

    public Line getLine(int i) {
        return lines.get(i);
    }

    public Line peekLine() {
        return lines.peek();
    }

    public Line pushLine() {
        Line line = Pools.obtain(Line.class);
        lines.add(line);
        return line;
    }

    /**
     * Resets the object for reuse. The font is nulled, but the lines are freed, cleared, and then one blank line is
     * re-added to lines so it can be used normally later.
     */
    @Override
    public void reset() {
        font = null;
        Pools.freeAll(lines);
        lines.clear();
        lines.add(Pools.obtain(Line.class));
    }

    public StringBuilder appendInto(StringBuilder sb){
        for (int i = 0, n = lines.size; i < n;) {
            Line line = lines.get(i);
            for (int j = 0, ln = line.glyphs.size; j < ln; j++) {
                sb.append((char)line.glyphs.get(j));
            }
            if(++i < n)
                sb.append('\n');
        }
        return sb;
    }

    @Override
    public String toString() {
        return appendInto(new StringBuilder()).toString();
    }
}
