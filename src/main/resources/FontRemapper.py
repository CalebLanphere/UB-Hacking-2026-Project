import sys

sys.path.append("target/classes/packages")

from fontTools.ttLib import TTFont

font = TTFont(input_path)

# Obtain original Unicode-to-glyph mappings.
original = font.getBestCmap()

for unicode_value, glyph_name in sorted(original.items()):
    print(f"{glyph_name}", end=", ")

    if mappings.get(glyph_name):
        # Removes the old glyph
        original.pop(glyph_name, None)
        # Sets all values to a specific glyph
        original[unicode_value] = mappings[glyph_name]

font.save(output_path)
