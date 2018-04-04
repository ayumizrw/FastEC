package example.fastec.ayumi.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.fastec.ayumi.coffce.delegates.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
