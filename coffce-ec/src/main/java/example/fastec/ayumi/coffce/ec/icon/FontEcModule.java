package example.fastec.ayumi.coffce.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 自定义的icon图标
 */
public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        //支付小书本图标
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
