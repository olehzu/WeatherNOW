package com.example.weatherapp;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {
    private final int radius;  // corner radius, higher value = more rounded

    public CircleTransform(int radius) {
        this.radius = radius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        // Create a new bitmap with the same dimensions as the original
        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());

        // Create a canvas for drawing the rounded image
        Canvas canvas = new Canvas(output);

        // Prepare the paint for rendering the rounded image
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);

        // Draw the rounded rectangle
        RectF rect = new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);

        // Recycle the original bitmap if needed
        if (source != output) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return "rounded_corners";
    }
}