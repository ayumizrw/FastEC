package example.fastec.ayumi.coffce.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.WeakHashMap;

/**
 * 配置文件的存储 以及获取
 */
public class Configurator {
    private static final HashMap<String,Object> LATTE_CONFIGS =new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    /**
     * 配置开始
     */
    private Configurator(){

        LATTE_CONFIGS.put(ConfigType.CONFING_READY.name(),false);
    }

    /**
     * 单例的静态内部类
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    /**
     * 配置结束
     */
    public final  void configure(){
        //加入字体图标
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFING_READY.name(),true);
    }

    /**
     * 配置API HOST
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    /**
     * 初始化工具Iconify
     */
    private void initIcons(){
        if(ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i = 1;i <ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 检查配置项完成
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFING_READY.name());
        if(!isReady){
            throw  new RuntimeException("is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
