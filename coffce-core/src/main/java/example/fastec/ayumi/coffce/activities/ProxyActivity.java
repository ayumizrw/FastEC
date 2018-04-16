package example.fastec.ayumi.coffce.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import example.fastec.ayumi.coffce.R;
import example.fastec.ayumi.coffce.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作为activity容器
 */
public abstract class ProxyActivity extends SupportActivity{
    public abstract LatteDelegate  setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initContaniner(savedInstanceState);
    }

    /**
     * 初始化我们的视图
     * @param savedInstanceState
     */
    private void initContaniner(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container =new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState ==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
