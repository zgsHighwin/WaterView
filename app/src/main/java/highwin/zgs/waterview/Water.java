package highwin.zgs.waterview;

import android.graphics.Paint;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description: 这个是保存小圆的参数设置的javabean
 * Create-Time: 2016/8/31 11:45
 */
public class Water {

    private float x;   //y坐标|y coordinate
    private float y;   //x坐标|x coordinate
    private Paint p;    //画笔 ｜paint
    private boolean isAnimation;    //是否设置动画监听 |whether animation listener
    private float radius;           //半径|circle radius
    private float strokeWidth;      //画笔宽度 | paint stroke width;
    private int alpha;              //设置alpha  | circler alpha

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
