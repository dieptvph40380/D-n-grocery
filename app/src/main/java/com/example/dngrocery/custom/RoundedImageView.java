package com.example.dngrocery.custom;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class RoundedImageView extends AppCompatImageView {
    private float radius = 126.0f; // Điều chỉnh góc bo tròn
    private Path path;
    private RectF rectF;

    public RoundedImageView(Context context) {
        super(context);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF.set(0, 0, getWidth(), getHeight());
        path.reset();
        float[] radii = {0, 0, 0, 0, 0, 0, radius, radius}; // Chỉ bo góc dưới bên trái
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
