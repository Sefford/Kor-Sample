package com.sefford.kor.samples.ui.transformations;

import android.graphics.*;
import com.squareup.picasso.Transformation;

/**
 * Picasso Transformation for creating images with rounded corners
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class RoundedBorderTransformation implements Transformation {

    /**
     * Pixels to round by corner
     */
    private final float pixels;

    /**
     * Creates a new instance of RoundedBorderTransformation.
     *
     * @param pixels Pixels to round the corner
     */
    public RoundedBorderTransformation(float pixels) {
        this.pixels = pixels;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap output = Bitmap.createBitmap(source.getWidth(), source
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, rect, rect, paint);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "rounded";
    }
}