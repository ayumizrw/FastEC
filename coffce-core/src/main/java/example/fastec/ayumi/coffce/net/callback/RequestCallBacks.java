package example.fastec.ayumi.coffce.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUEECSS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RequestCallBacks(IRequest request, ISuccess success, IError error, IFailure failure) {
        this.REQUEST = request;
        this.SUEECSS = success;
        this.ERROR = error;
        this.FAILURE = failure;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE !=null){
            FAILURE.onFailure();
        }

        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
