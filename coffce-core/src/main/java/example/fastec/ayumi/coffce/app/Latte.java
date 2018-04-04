package example.fastec.ayumi.coffce.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {
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
    private static HashMap<String,Object> getConfinguratons(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfinguratons().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
