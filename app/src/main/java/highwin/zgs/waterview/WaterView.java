package highwin.zgs.waterview;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class WaterView extends View {
    private float mActionDownX;
    private float mActionDownY;
    private List<Water> mWater;


    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWater = new CopyOnWriteArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float mRadius;
    private boolean mIsAnimation;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Water w : mWater) {
            if (w.getP().getAlpha() == 0) {
                mWater.remove(w);
            } else {
                if (w.isAnimation()) {
                    canvas.drawCircle(w.getX(), w.getY(), w.getRadius(), w.getP());
                } else {
                    radiusAnimation(w, 1, TimeUnit.SECONDS);
                }
            }
        }
    }

    private void radiusAnimation(Water w, long time, TimeUnit unit) {
        PropertyValuesHolder radius = PropertyValuesHolder.ofFloat("radius", getFloat(PaintColorType.TYPE_RADIUS));
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", getFloat(PaintColorType.TYPE_ALPHA));
        PropertyValuesHolder strokeWidth = PropertyValuesHolder.ofFloat("strokeWdith", getFloat(PaintColorType.TYPE_STROKE_WIDTH));
        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(radius, alpha, strokeWidth).setDuration(getTime(time, unit));
        valueAnimator.addUpdateListener(new MyAnimatorListener(w));
        valueAnimator.start();
    }

    public long getTime(long time, TimeUnit unit) {
        return unit.toMillis(time);
    }


    class MyAnimatorListener implements ValueAnimator.AnimatorUpdateListener {
        private Water w;

        public MyAnimatorListener(Water w) {
            this.w = w;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float aniRadius = (float) valueAnimator.getAnimatedValue("radius");
            float aniAlpha = (float) valueAnimator.getAnimatedValue("alpha");
            float aniStrokeWdith = (float) valueAnimator.getAnimatedValue("strokeWdith");
            Log.d("MyAnimatorListener", "aniAlpha:" + aniAlpha);
            Log.d("MyAnimatorListener", "aniRadius:" + aniRadius);
            Log.d("MyAnimatorListener", "aniStrokeWdith:" + aniStrokeWdith);
            w.setRadius(aniRadius);
            w.setAnimation(true);
            w.getP().setStrokeWidth(aniStrokeWdith);
            w.getP().setAlpha((int) aniAlpha);
            w.setStrokeWidth(aniStrokeWdith);
            w.setAlpha((int) aniAlpha);
            invalidate();
        }
    }

    private float increaseStrokeWidth = 1;
    private float baseStrokeWidth = 10;
    private float increaseRadius = 10;
    private float baseRadius = 5;
    private int destiny = 20;
    private boolean fixed = true;
    private float fixedSize = 20;

    private float[] getFloat(@PaintColorType.paintType String type) {
        float[] floats = new float[destiny];
        if (type.equals(PaintColorType.TYPE_RADIUS)) {
            for (int i = 0; i < destiny; i++) {
                if (fixed) {
                    floats[i] = (float) (baseRadius * (i + increaseRadius));
                } else {
                    floats[i] = fixedSize;
                }
            }
        } else if (type.equals(PaintColorType.TYPE_ALPHA)) {
            for (int i = 0; i < destiny; i++) {
                floats[i] = (float) (255 - (i + 1) * (255 / destiny));
            }
            floats[destiny - 1] = 0;
        } else if (type.equals(PaintColorType.TYPE_STROKE_WIDTH)) {
            for (int i = 0; i < destiny; i++) {
                floats[i] = (float) (baseStrokeWidth + (1 + i) * increaseStrokeWidth);
            }
        }
        return floats;
    }

    private boolean mIsActionDown;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 多点触控
         */
        int pointerCount = event.getPointerCount();
        Log.i("WaterView", "pointerCount:" + pointerCount);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < pointerCount; i++) {
                    float mActionDownX = event.getX(i);
                    float mActionDownY = event.getY(i);
                    mWater.add(new Water(0, false, getPaint(getRadomColor()), 0, 0, mActionDownX, mActionDownY));
                    invalidate();
                }
                break;
        }


        /**
         * 单点触控
         */
    /*    int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
           *//*     invalidate();
                break;*//*
            case MotionEvent.ACTION_MOVE:
                mActionDownX = event.getX();
                mActionDownY = event.getY();
                mWater.add(new Water(0, false, getPaint(getRadomColor()), 0, 0, mActionDownX, mActionDownY));
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }*/
        return true;
    }

    @PaintColorType.paintColor
    private int getRadomColor() {
        int[] colors = {PaintColorType.COLOR_RED, PaintColorType.COLOR_GREEN, PaintColorType.COLOR_YELLOW, PaintColorType.COLOR_BALCK, PaintColorType.COLOR_BLUE};
        int i = new Random().nextInt(colors.length);
        return colors[i];
    }

    private Paint getPaint(@PaintColorType.paintColor int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setDither(true);
        paint.setColor(color);
        return paint;
    }

    private int getPaintAlpha() {

        return 0;
    }

    class Water {
        private float x;   //y坐标
        private float y;    //x坐标
        private Paint p;      //画笔
        private boolean isAnimation;  //是否设置动画监听
        private float radius;       //半径
        private float strokeWidth;     //画笔宽度
        private int alpha;           //设置alpha

        public Water() {
        }

        public Water(int alpha, boolean isAnimation, Paint p, float radius, float strokeWidth, float x, float y) {
            this.alpha = alpha;
            this.isAnimation = isAnimation;
            this.p = p;
            this.radius = radius;
            this.strokeWidth = strokeWidth;
            this.x = x;
            this.y = y;
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public boolean isAnimation() {
            return isAnimation;
        }

        public void setAnimation(boolean animation) {
            isAnimation = animation;
        }

        public Paint getP() {
            return p;
        }

        public void setP(Paint p) {
            this.p = p;
        }

        public float getRadius() {
            return radius;
        }

        public void setRadius(float radius) {
            this.radius = radius;
        }

        public float getStrokeWidth() {
            return strokeWidth;
        }

        public void setStrokeWidth(float strokeWidth) {
            this.strokeWidth = strokeWidth;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }
}