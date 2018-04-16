package example.fastec.ayumi.coffce.utill;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import example.fastec.ayumi.coffce.app.Latte;

/**
 * 测量的工具方法
 */
public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
