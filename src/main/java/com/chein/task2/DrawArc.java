package com.chein.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;


public class DrawArc {
    public static void drawArc(final GraphicsContext graphicsContext, final int xc, final int yc,
                               final int radius, final Color startColor, final Color endColor, double startAng, double endAng) {
        startAng = Math.toRadians(startAng) % Math.toRadians(360);
        endAng = Math.toRadians(endAng) % Math.toRadians(360);

        if (startAng < 0) {
            startAng += Math.toRadians(360);
        }
        if (endAng < 0) {
            endAng += Math.toRadians(360);
        }
        if (Math.round(startAng) == Math.round(endAng)){
            endAng += Math.toRadians(360);
        }

        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {

            if (d >= 0) {
                d = d + 4 * (x - y) + 10;
                x++;
                y--;
            }
            if (d < 0) {
                d = d + 4 * x + 6;
                x++;
            }

            double ang1 = perevodUgla(Math.atan2(x, y), startAng);
            double ang2 = perevodUgla(Math.atan2(y, x), startAng);
            double ang3 = perevodUgla(Math.atan2(y, -x), startAng);
            double ang4 = perevodUgla(Math.atan2(x, -y), startAng);
            double ang5 = perevodUgla(Math.atan2(-x, -y) + 2 * Math.PI, startAng);
            double ang6 = perevodUgla(Math.atan2(-y, -x) + 2 * Math.PI, startAng);
            double ang7 = perevodUgla(Math.atan2(-y, x) + 2 * Math.PI, startAng);
            double ang8 = perevodUgla(Math.atan2(-x, y) + 2 * Math.PI, startAng);

            if (endAng < startAng) {
                endAng += 2 * Math.PI;
            }

            draw(ang1, startAng, endAng, startColor, endColor, xc + y, yc + x, graphicsContext);
            draw(ang2, startAng, endAng, startColor, endColor, xc + x, yc + y, graphicsContext);
            draw(ang3, startAng, endAng, startColor, endColor, xc - x, yc + y, graphicsContext);
            draw(ang4, startAng, endAng, startColor, endColor, xc - y, yc + x, graphicsContext);
            draw(ang5, startAng, endAng, startColor, endColor, xc - y, yc - x, graphicsContext);
            draw(ang6, startAng, endAng, startColor, endColor, xc - x, yc - y, graphicsContext);
            draw(ang7, startAng, endAng, startColor, endColor, xc + x, yc - y, graphicsContext);
            draw(ang8, startAng, endAng, startColor, endColor, xc + y, yc - x, graphicsContext);

        }
    }

    private static void draw(double ang, double startAng, double endAng, Color startColor, Color endColor,
                             int x, int y, final GraphicsContext graphicsContext){
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        if (ang >= startAng && ang <= endAng) {
            double fraction = (ang - startAng) / (endAng - startAng);
            Color color = interpolation(startColor, endColor, fraction);
            pixelWriter.setColor(x, y, color);
        }
    }

    private static double perevodUgla(double ang, double startAng){
        if (ang < startAng){
            ang += 2*Math.PI;
        }
        return ang;
    }

    public static Color interpolation(Color startColor, Color endColor, double fraction) {
        double red = startColor.getRed() + (endColor.getRed() - startColor.getRed()) * fraction; //Это формулы линейной интерполяции
        double green = startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * fraction;
        double blue = startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * fraction;

        return new Color(red, green, blue, 1);
    }


}


