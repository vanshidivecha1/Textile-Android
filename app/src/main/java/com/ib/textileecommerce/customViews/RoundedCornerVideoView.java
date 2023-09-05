package com.ib.textileecommerce.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.VideoView;

public class RoundedCornerVideoView extends VideoView {
    private Path clipPath;

    public RoundedCornerVideoView(Context context) {
        super(context);
        init();
    }

    public RoundedCornerVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedCornerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clipPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cornerRadius = 20f; // change this to adjust the corner radius
        clipPath.reset();
        clipPath.addRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}

