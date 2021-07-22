# textramode
Extra features for fonts in libGDX.

### But what kind of extra features?

![These features.](https://i.imgur.com/JsFxWAB.png)

Textramode is a small, new library that provides at least a
glimpse of what text handling in libGDX could be like. It extends
the color markup syntax used optionally in libGDX `BitmapFont`s,
making it so on top of color markup like `[RED]` and `[#66CCFF]`,
you can use markup to declare bold, oblique/italic, underlined,
struck-through, subscript, superscript, and "midscript" transforms
(and combinations of most of those), and also capitalization
changes (such as for generated text that should be ALL CAPS, lower
case, or Capitalized Initially). We also support normal bitmap fonts,
distance field fonts (also called SDF), and the newer, more-crisp MSDF
fonts (multi-channel signed distance field fonts). This library has its
own `Font`class, which is mostly unrelated to libGDX's `BitmapFont`
class, and it offers several features `BitmapFont` doesn't. `Font` is
partly a thin wrapper around an `IntMap` that maps each `char`
(which can be treated as an `int` key here) to a `TextureRegion`.
You can access each glyph's visual appearance as a type of
`TextureRegion`, which is utterly frustrating to attempt with
`BitmapFont`. The logic to actually draw the font is there too,
and that's not thin at all! As an equivalent to libGDX's
`GlyphLayout`, which is part of one of the most complicated heaps of code
in all of libGDX, we have `Layout`, which is a rather simple collection
of `Line`s, and of course that `Line` class (with just 34 lines of code).

## So, how do I use it?

Most of the way Textramode works is based around building a `Layout` once
and then drawing that same Layout every frame while it is visible. You
usually fill up a `Layout` using methods in `Font`, which modifies its
`Layout` parameter in most cases. Assembling a `Font` in the first place
has several options available to you, including (for compatibility) using
an existing libGDX `BitmapFont` to copy as a basis. You can specify the
`Font.DistanceFieldType` if you use something other than the most minimal
constructors; the default is `STANDARD` for a normal bitmap font, and you
can also use `SDF` or `MSDF` if you have an appropriate font (you can use
some predefined ones from [the Glamer repo](https://github.com/tommyettinger/Glamer/tree/master/premade)
as a starting point). Frequently, because of the imprecise nature of bitmap
fonts, you may need to adjust the x, y, width, and height modifiers of the
font to account for padding. As an example, if you use Gentium.fnt from
this repo, then it works well with `-1f, 0f, -4.5f, 0f` for those four
modifiers, but most fonts need manual adjustment to get the distances
between chars right.

Once you have a Font `myFont` and an empty Layout `myLayout`, you can just
call `myFont.markup("Some string with [/]markup[/]!", myLayout);`, which will
fill up `myLayout` with the needed info to draw the mix of normal and oblique
text in `myFont`. `myFont.drawGlyphs(myBatch, myLayout, x, y);` is usually all
that's needed in `render()` to draw that Layout.

## How do I get it?

JitPack! [You can use the JitPack repository to get a release or recent commit.](https://jitpack.io/#tommyettinger/textramode/)
There's also some releases here, in the Releases page in the sidebar.

The code is licensed under the Apache License, 2.0, which is the same license
that libGDX uses. See the file LICENSE for the full legal text.
