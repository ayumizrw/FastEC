package example.fastec.ayumi.fastec;

import example.fastec.ayumi.coffce.activities.ProxyActivity;
import example.fastec.ayumi.coffce.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
