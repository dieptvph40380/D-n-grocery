package com.example.dngrocery.custom;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
public class RounderItemGrocery extends AppCompatImageView{

    private float radius = 40.0f; // Đều chỉnh góc bo tròn
    private Path path;
    private RectF rectF;

    public RounderItemGrocery(Context context) {
        super(context);
        init();
    }

    public RounderItemGrocery(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RounderItemGrocery(Context context, AttributeSet attrs, int defStyleAttr) {
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
        float[] radii = {radius, radius, radius, radius, radius, radius, radius, radius}; // Chỉ bo góc dưới bên trái
        path.addRoundRect(rectF, radii, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
