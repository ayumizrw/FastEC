package example.fastec.ayumi.coffce.net;

import java.util.Map;
import java.util.WeakHashMap;

import example.fastec.ayumi.coffce.net.callback.IError;
import example.fastec.ayumi.coffce.net.callback.IFailure;
import example.fastec.ayumi.coffce.net.callback.IRequest;
import example.fastec.ayumi.coffce.net.callback.ISuccess;
import example.fastec.ayumi.coffce.net.callback.RequestCallBacks;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 请求的具体实现类
 */
public class RestClient {

    private final String URL;
    private  static  final WeakHashMap<String,Object> PARAMS =RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUEECSS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest REQUEST,
                      ISuccess SUEECSS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY) {
        this.URL = URL;
       PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUEECSS = SUEECSS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMetod httpMetod){
        final RestService service = RestCreator.getRestService();
        Call<String>  call =null;

        switch (httpMetod){
            case GET:
                call =service.get(URL,PARAMS);
                break;
            case POST:
                call =service.post(URL,PARAMS);
                break;
            case PUT:
                call =service.put(URL,PARAMS);
                break;
            case DELETE:
                call =service.delete(URL,PARAMS);
                break;
            case UOLODE:
                break;
                default:
                    break;
        }

        if(call !=null){
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallBacks(REQUEST,SUEECSS,ERROR,FAILURE);
    }
    /**
     * 具体使用方法
     */

    public final void get(){
        request(HttpMetod.GET);
    }
    public final void post(){
        request(HttpMetod.POST);
    }
    public final void put(){
        request(HttpMetod.PUT);
    }
    public final void delete(){
        request(HttpMetod.DELETE);
    }
}
