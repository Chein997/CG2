package com.chein.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;


public class DrawArc {
    public static void drawArc(final GraphicsContext graphicsContext, final int xc, final int yc,
                               final int radius, final Color startColor, final Color endColor, double startAng, double endAng) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        startAng = Math.toRadians(startAng);
        endAng = Math.toRadians(endAng);

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

            double ang1 = Math.atan2(x, y);
            double ang2 = Math.atan2(y, x);
            double ang3 = Math.atan2(y, -x);
            double ang4 = Math.atan2(x, -y);
            double ang5 = Math.atan2(-x, -y) + 2 * Math.PI;
            double ang6 = Math.atan2(-y, -x) + 2 * Math.PI;
            double ang7 = Math.atan2(-y, x) + 2 * Math.PI;
            double ang8 = Math.atan2(-x, y) + 2 * Math.PI;

            double moveEndAng = endAng;
            if (endAng < startAng) {
                moveEndAng += 2 * Math.PI;
            }

            if (ang1 >= startAng && ang1 <= endAng) { // первый окнтант
                double fraction = (ang1 - startAng) / (endAng - startAng); // отношение текущей длины ко всей
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc + y, yc + x, color);
            }
            if (ang2 >= startAng && ang2 <= endAng) { // второй октант
                double fraction = (ang2 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc + x, yc + y, color);
            }
            if (ang3 >= startAng && ang3 <= endAng) { // третий октант
                double fraction = (ang3 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc - x, yc + y, color);
            }
            if (ang4 >= startAng && ang4 <= endAng) { // четвертый октант
                double fraction = (ang4 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc - y, yc + x, color);
            }

            // здесь обработка случая когда конечный угол больше 180
            if (ang5 >= startAng && ang5 <= moveEndAng) { //пятый октант
                double fraction = (ang5 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc - y, yc - x, color);
            }
            if (ang6 >= startAng && ang6 <= moveEndAng) { //шестой октант
                double fraction = (ang6 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc - x, yc - y, color);
            }
            if (ang7 >= startAng && ang7 <= moveEndAng) { //седьмой октант
                double fraction = (ang7 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc + x, yc - y, color);
            }
            if (ang8 >= startAng && ang8 <= moveEndAng) { //восьмой октант
                double fraction = (ang8 - startAng) / (endAng - startAng);
                Color color = interpolation(startColor, endColor, fraction);
                pixelWriter.setColor(xc + y, yc - x, color);
            }
        }
    }

    public static Color interpolation(Color startColor, Color endColor, double fraction) {
        double red = startColor.getRed() + (endColor.getRed() - startColor.getRed()) * fraction; //Это формулы линейной интерполяции
        double green = startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * fraction;
        double blue = startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * fraction;

        return new Color(red, green, blue, 1);
    }


}
