package highwin.zgs.waterview;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PaintColorType {

    public static final int COLOR_RED = Color.RED;
    public static final int COLOR_GREEN = Color.GREEN;
    public static final int COLOR_YELLOW = Color.YELLOW;
    public static final int COLOR_BALCK = Color.BLACK;
    public static final int COLOR_BLUE = Color.BLUE;

  /*  public static final String TYPE_ALPHA = "TYPE_ALPHA";
    public static final String TYPE_STROKE_WIDTH = "TYPE_STROKE_WIDTH";
    public static final String TYPE_RADIUS = "YPE_RADIUS";*/

    /**
     * 类型
     */
    @StringDef({paintType.TYPE_ALPHA, paintType.TYPE_STROKE_WIDTH, paintType.TYPE_RADIUS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface paintType {
        String TYPE_ALPHA = "TYPE_ALPHA";
        String TYPE_STROKE_WIDTH = "TYPE_STROKE_WIDTH";
        String TYPE_RADIUS = "TYPE_RADIUS";
    }

    @PaintColorType.paintType
    int type;

    @PaintColorType.paintType
    public int getType() {
        return type;
    }

    public void setType(@PaintColorType.paintType int type) {
        this.type = type;
    }

    /**
     * 颜色
     */
    @IntDef({COLOR_RED, COLOR_GREEN, COLOR_YELLOW, COLOR_BALCK, COLOR_BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface paintColor {
    }

    @PaintColorType.paintColor
    int color;

    @PaintColorType.paintColor
    public int getColor() {
        return color;
    }

    public void setColor(@PaintColorType.paintColor int color) {
        this.color = color;
    }

}
