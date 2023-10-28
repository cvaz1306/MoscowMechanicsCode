package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

public class ColorConverter {
    public static float[] rgbToHsv(int pixelColor) {
        // Extract RGB components from the pixel color
        int red = Color.red(pixelColor);
        int green = Color.green(pixelColor);
        int blue = Color.blue(pixelColor);

        // Normalize RGB values to the range [0, 1]
        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        // Find the minimum and maximum values among R, G, and B
        float min = Math.min(Math.min(r, g), b);
        float max = Math.max(Math.max(r, g), b);

        float hue = 0;
        float saturation = 0;
        float value = max;

        float delta = max - min;

        if (max != 0) {
            saturation = delta / max;
            if (r == max) {
                hue = (g - b) / delta;
            } else if (g == max) {
                hue = 2 + (b - r) / delta;
            } else {
                hue = 4 + (r - g) / delta;
            }
            hue *= 60;
            if (hue < 0) {
                hue += 360;
            }
        }

        float[] hsv = {hue, saturation, value};
        return hsv;
    }
    public static float[] rgbToCmyk(int red, int green, int blue) {
        // Normalize RGB values to the range [0, 1]
        float r = red / 255.0f;
        float g = green / 255.0f;
        float b = blue / 255.0f;

        // Calculate Key (K)
        float k = 1.0f - Math.max(Math.max(r, g), b);

        // Calculate Cyan (C), Magenta (M), and Yellow (Y)
        float c = (1.0f - r - k) / (1.0f - k);
        float m = (1.0f - g - k) / (1.0f - k);
        float y = (1.0f - b - k) / (1.0f - k);

        // Ensure that CMYK values are in the range [0, 1]
        c = Math.max(0.0f, Math.min(1.0f, c));
        m = Math.max(0.0f, Math.min(1.0f, m));
        y = Math.max(0.0f, Math.min(1.0f, y));
        k = Math.max(0.0f, Math.min(1.0f, k));

        // Create and return the CMYK array
        float[] cmyk = {c, m, y, k};
        return cmyk;
    }
}
