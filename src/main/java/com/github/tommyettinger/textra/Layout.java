package com.github.tommyettinger.textra;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Layout implements Pool.Poolable {
    protected Font font;
    protected final Array<Line> lines = new Array<>(true, 8, Line.class);
    protected int maxLines = Integer.MAX_VALUE;
    protected String ellipsis = null;
    protected float targetWidth = 0f;
    protected float baseColor = Color.WHITE_FLOAT_BITS;

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
            if(lines.size < maxLines)
            {
                lines.add(Pools.obtain(Line.class));
            }
            // TODO: The ellipsis behavior should be moved to Font, and affect glyphs added, not newlines.
            else {
                if(ellipsis != null){
                    Line latest = lines.peek();
                    for (int eLen = ellipsis.length(), i = 0, s = latest.glyphs.size - eLen;
                         i < eLen; i++, s++) {
                        if(s >= 0)
                            latest.glyphs.set(s, (latest.glyphs.get(s) & 0xFFFFFFFFFFFF0000L) | ellipsis.charAt(i));
                    }
                }
            }
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

    public float getTargetWidth() {
        return targetWidth;
    }

    public Layout setTargetWidth(float targetWidth) {
        // we may want this to lay existing lines out again if the width changed.
        this.targetWidth = targetWidth;
        return this;
    }

    /**
     * Gets the base color of the Layout, as the float bits of a Color. The base color is what font color
     * will be used immediately after resetting formatting with {@code []}, as well as the initial color
     * used by text that hasn't been formatted. You can fill a Color object with this value using
     * {@link Color#abgr8888ToColor(Color, float)} (it modifies the Color you give it).
     * @return the base color of the Layout, as float bits
     */
    public float getBaseColor() {
        return baseColor;
    }

    /**
     * Sets the base color of the Layout; this is what font color will be used immediately after resetting
     * formatting with {@code []}, as well as the initial color used by text that hasn't been formatted.
     * This takes the color as a primitive float, which you can get from a Color object with
     * {@link Color#toFloatBits()}, or in some cases from existing data produced by {@link Font}.
     * @param baseColor the float bits of a Color, as obtainable via {@link Color#toFloatBits()}
     */
    public void setBaseColor(float baseColor) {
        this.baseColor = baseColor;
    }

    /**
     * Sets the base color of the Layout; this is what font color will be used immediately after resetting
     * formatting with {@code []}, as well as the initial color used by text that hasn't been formatted.
     * If the given Color is null, this treats it as white.
     * @param baseColor a Color to use for text that hasn't been formatted; if null, will be treated as white
     */
    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor == null ? Color.WHITE_FLOAT_BITS : baseColor.toFloatBits();
    }

    /**
     * Resets the object for reuse. The font is nulled, but the lines are freed, cleared, and then one blank line is
     * re-added to lines so it can be used normally later.
     */
    @Override
    public void reset() {
        targetWidth = 0f;
        baseColor = Color.WHITE_FLOAT_BITS;
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
