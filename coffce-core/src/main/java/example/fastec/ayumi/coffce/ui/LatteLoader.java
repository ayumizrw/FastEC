package example.fastec.ayumi.coffce.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import example.fastec.ayumi.coffce.utill.DimenUtil;

public class LatteLoader {

    /**
     * 宽高比
     * 偏移量
     */
    private static final  int LOADER_SIZE_SCALE = 8;

    private static final int LOADER_OFFSET_SCALE =10;
    //存储所有的loader
    private static final ArrayList<AppCompatDialog> LOADERS =new ArrayList<>();
    //默认的loader样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showloading(Context context,Enum<LoaderStyle> type){
        showloading(context,type.name());
    }
    public static void showloading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context,example.fastec.ayumi.coffce.R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deciveHigth = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if(dialogWindow != null){
            WindowManager.LayoutParams lp =dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height =deciveHigth/LOADER_SIZE_SCALE;

            lp.height =lp.height+deciveHigth/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showloading(Context context){
        showloading(context,DEFAULT_LOADER);
    }

    /**
     * 停止
     */
    public static void stoploading(){
        for(AppCompatDialog dialog :LOADERS){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
