package example.fastec.ayumi.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import example.fastec.ayumi.coffce.app.Latte;
import example.fastec.ayumi.coffce.ec.icon.FontEcModule;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
               // .withApiHost("http://127.0.0.1")
                .configure();
    }
}
