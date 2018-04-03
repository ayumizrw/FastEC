package example.fastec.ayumi.coffce.app;

import java.util.WeakHashMap;

/**
 * 配置文件的存储 以及获取
 */
public class Configurator {
    private static final WeakHashMap<String,Object> LATTE_CONFIGS =new WeakHashMap<>();

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

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    /**
     * 配置结束
     */
    public final  void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFING_READY.name(),true);
    }

    /**
     * 配置API HOST
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
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
