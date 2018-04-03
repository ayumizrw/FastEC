package example.fastec.ayumi.coffce.app;

import android.content.Context;

import java.util.WeakHashMap;

public final class latte {
    /**
     * 返回配置,把对象的引用转入了配置项
     * @param context
     * @return
     */
    public static Configurator init(Context context){
        getConfinguratons().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 返回 weakhashmap
     * @return
     */
    private static WeakHashMap<String,Object> getConfinguratons(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
