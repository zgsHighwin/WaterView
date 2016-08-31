package highwin.zgs.waterview;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PaintType {

    /**
     * 小圆的变化值
     * cirlce value change
     */
    @StringDef({PaintTypeMode.TYPE_ALPHA, PaintTypeMode.TYPE_STROKE_WIDTH, PaintTypeMode.TYPE_RADIUS,
            PaintTypeMode.TYPE_FIXED, PaintTypeMode.TYPE_ENLARGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PaintTypeMode {
        String TYPE_ALPHA = "TYPE_ALPHA";
        String TYPE_STROKE_WIDTH = "TYPE_STROKE_WIDTH";
        String TYPE_RADIUS = "TYPE_RADIUS";

        /**
         * 圆是一个固定圆，改量变是靠alpha和画笔的宽度和圆的半径来决定的
         * cirlce fixed only change by alpha,width and radius
         */
        String TYPE_FIXED = "TYPE_FIXED";
        String TYPE_ENLARGE = "TYPE_FIXED";
    }


    /**
     * 小圆的颜色的变化，可以根据自己的喜欢改成不同的颜色
     * circle change color you can also choose your color instead
     */
    @IntDef({PaintColor.COLOR_RED, PaintColor.COLOR_GREEN, PaintColor.COLOR_YELLOW, PaintColor.COLOR_BALCK, PaintColor.COLOR_BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PaintColor {
        int COLOR_RED = Color.RED;
        int COLOR_GREEN = Color.GREEN;
        int COLOR_YELLOW = Color.YELLOW;
        int COLOR_BALCK = Color.BLACK;
        int COLOR_BLUE = Color.BLUE;
    }
}
