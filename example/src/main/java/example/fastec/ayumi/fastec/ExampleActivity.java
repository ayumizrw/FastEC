package example.fastec.ayumi.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;

import example.fastec.ayumi.coffce.activities.ProxyActivity;
import example.fastec.ayumi.coffce.app.Latte;
import example.fastec.ayumi.coffce.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Latte.getConfinguratons().w
    }
}
