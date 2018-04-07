package example.fastec.ayumi.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import example.fastec.ayumi.coffce.delegates.LatteDelegate;
import example.fastec.ayumi.coffce.net.RestClient;
import example.fastec.ayumi.coffce.net.callback.IError;
import example.fastec.ayumi.coffce.net.callback.IFailure;
import example.fastec.ayumi.coffce.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        TestRestCilent();
    }

    private void TestRestCilent(){
        RestClient.builder()
                .url("https://news.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String Response) {
                        System.out.print(Response);
                        Toast.makeText(getContext(),Response,Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
