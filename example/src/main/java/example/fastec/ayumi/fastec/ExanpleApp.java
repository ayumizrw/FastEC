package example.fastec.ayumi.fastec;

import android.app.Application;

import example.fastec.ayumi.coffce.app.latte;

public class ExanpleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        latte.init(this)
                .withApiHost("")
                .configure();
    }
}
