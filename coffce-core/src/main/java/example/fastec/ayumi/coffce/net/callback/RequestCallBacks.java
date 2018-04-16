package example.fastec.ayumi.coffce.net.callback;

import android.os.Handler;

import example.fastec.ayumi.coffce.ui.LatteLoader;
import example.fastec.ayumi.coffce.ui.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUEECSS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYPE;
    private static final Handler HANDLER =new Handler();
    public RequestCallBacks(IRequest request, ISuccess success, IError error, IFailure failure, LoaderStyle loaderstype) {
        this.REQUEST = request;
        this.SUEECSS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYPE = loaderstype;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUEECSS!=null){
                    SUEECSS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR !=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        stoploading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE !=null){
            FAILURE.onFailure();
        }

        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stoploading();
    }

    private void stoploading(){
        if(LOADER_STYPE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stoploading();
                }
            },1000);
        }
    }
}
